/*
 * Copyright (c) 2018-2020, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.libTAU4j;

import org.libTAU4j.alerts.Alert;
import org.libTAU4j.alerts.AlertType;
import org.libTAU4j.alerts.Alerts;
import org.libTAU4j.alerts.DhtStatsAlert;
import org.libTAU4j.alerts.ExternalIpAlert;
import org.libTAU4j.alerts.ListenSucceededAlert;
import org.libTAU4j.alerts.SessionStatsAlert;
import org.libTAU4j.alerts.SocketType;
import org.libTAU4j.swig.address;
import org.libTAU4j.swig.alert;
import org.libTAU4j.swig.alert_category_t;
import org.libTAU4j.swig.alert_ptr_vector;
import org.libTAU4j.swig.byte_vector;
import org.libTAU4j.swig.entry;
import org.libTAU4j.swig.error_code;
import org.libTAU4j.swig.libTAU;
import org.libTAU4j.swig.port_filter;
import org.libTAU4j.swig.remove_flags_t;
import org.libTAU4j.swig.session;
import org.libTAU4j.swig.session_params;
import org.libTAU4j.swig.settings_pack;
import org.libTAU4j.swig.tcp_endpoint_vector;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author gubatron
 * @author aldenml
 */
public class SessionManager {

    private static final long REQUEST_STATS_RESOLUTION_MILLIS = 1000;
    private static final long ALERTS_LOOP_WAIT_MILLIS = 500;

    private final boolean logging;

    private final AlertListener[] listeners;

    private final ReentrantLock sync;
    private final ReentrantLock syncMagnet;

    private volatile session session;

    private final SessionStats stats;
    private long lastStatsRequestTime;
    private boolean firewalled;
    private final Map<String, String> listenEndpoints;
    private String externalAddress;
    private int externalPort;
    private Thread alertsLoop;

    private Throwable lastAlertError;

    public SessionManager(boolean logging) {
        this.logging = logging;

        this.listeners = new AlertListener[Alerts.NUM_ALERT_TYPES + 1];

        this.sync = new ReentrantLock();
        this.syncMagnet = new ReentrantLock();

        this.stats = new SessionStats();
        this.listenEndpoints = new HashMap<>();

        resetState();
    }

    public SessionManager() {
        this(false);
    }

    public session swig() {
        return session;
    }

    public void addListener(AlertListener listener) {
        modifyListeners(true, listener);
    }

    public void removeListener(AlertListener listener) {
        modifyListeners(false, listener);
    }

    public void start(SessionParams params) {
        if (session != null) {
            return;
        }

        sync.lock();

        try {
            if (session != null) {
                return;
            }

            onBeforeStart();

            resetState();

            SettingsPack sp = params.getSettings();

            // we always control the alert mask
            sp.setInteger(settings_pack.int_types.alert_mask.swigValue(), alertMask(logging).to_int());

            // limit metadata size by default
            if (!sp.hasValue(settings_pack.int_types.max_metadata_size.swigValue())) {
                sp.setMaxMetadataSize(2 * 1024 * 1024);
            }

            // use some dht bootstrap nodes if none is provided
            if (!sp.hasValue(settings_pack.string_types.dht_bootstrap_nodes.swigValue())) {
                sp.setDhtBootstrapNodes(defaultDhtBootstrapNodes());
            }

            session = new session(params.swig());
            alertsLoop();

            // block all connections to port < 1024, but
            // allows 80 and 443 for web seeds
            port_filter f = new port_filter();
            f.add_rule(0, 79, 1);
            f.add_rule(81, 442, 1);
            f.add_rule(444, 1023, 1);
            session.set_port_filter(f);

            onAfterStart();

        } finally {
            sync.unlock();
        }
    }

    public void start() {
        start(new SessionParams());
    }

    /**
     * This method blocks during the destruction of the native session, it
     * could take some time, don't call this from the UI thread or other
     * sensitive multithreaded code.
     */
    public void stop() {
        if (session == null) {
            return;
        }

        sync.lock();

        try {
            if (session == null) {
                return;
            }

            onBeforeStop();
        
            session.stop();

            processStopAlerts();

            session s = session;
            session = null; // stop alerts loop and session methods

            /*
            // guarantee one more alert is post and detected
            s.post_session_stats();
            try {
                // 250 is to ensure that the sleep is bigger
                // than the wait in alerts loop
                Thread.sleep(ALERTS_LOOP_WAIT_MILLIS + 250);
            } catch (InterruptedException ignore) {
            }
            */
            if (alertsLoop != null) {
                try {
                    alertsLoop.join();
                } catch (Throwable e) {
                    // ignore
                }
            }

            resetState();

            s.delete();

            onAfterStop();

        } finally {
            sync.unlock();
        }
    }

    /**
     * This method blocks for at least a second plus the time
     * needed to destroy the native session, don't call it from the UI thread.
     */
    public void restart() {
        sync.lock();

        try {

            session_params params = session.session_state();

            stop();
            Thread.sleep(1000); // allow some time to release native resources
            start(new SessionParams(params));

        } catch (InterruptedException e) {
            // ignore
        } finally {
            sync.unlock();
        }
    }

    public boolean isRunning() {
        return session != null;
    }

    public SessionStats stats() {
        return stats;
    }

    public long downloadRate() {
        return stats.downloadRate();
    }

    public long uploadRate() {
        return stats.uploadRate();
    }

    public long totalDownload() {
        return stats.totalDownload();
    }

    public long totalUpload() {
        return stats.totalUpload();
    }

    public long dhtNodes() {
        return stats.dhtNodes();
    }

	public long invokedRequests() {
		return stats.invokedRequests();
	}

    public boolean isFirewalled() {
        return firewalled;
    }

    public String externalAddress() {
        return externalAddress;
    }

    public List<String> listenEndpoints() {
        return new ArrayList<>(listenEndpoints.values());
    }

	
    /**
     * Update listen interfaces and this method will trigger reopening network sockets.
     *
     * @param interfaces listen network interfaces.
     */
    public void updateListenInterfaces(String interfaces) {
		if (session != null) {
			new SessionHandle(session).updateListenInterfaces(interfaces);
		}
    }

	/**
	 * Update bootstrap time interval.
	 *
	 * @param interval the unit is seconds
	 */
	public void updateBootstrapIntervel(int interval) {
		if (session != null) {
			new SessionHandle(session).updateBootstrapIntervel(interval);
		}
	}

	public void updateAccountSeed(byte[] seed) {
		if (session != null) {
			new SessionHandle(session).updateAccountSeed(seed);
		}
	}

	/**
	 * Update non_referrable setting
	 *
	 * @param value non_referrable setting
	 */
	public void setNonReferrable(boolean value) {
		if (session != null) {
			new SessionHandle(session).setNonReferrable(value);
		}
	}

	/**
	 * Update auto_relay setting
	 *
	 * @param value auto_relay setting
	 */
	public void setAutoRelay(boolean value) {
		if (session != null) {
			new SessionHandle(session).setAutoRelay(value);
		}
	}

    public void disconnectNetwork() {
		if (session != null) {
			new SessionHandle(session).disconnectNetwork();
		}
    }

    public void reconnectNetwork() {
		if (session != null) {
			new SessionHandle(session).reconnectNetwork();
		}
    }

    public void stopService() {
		if (session != null) {
			new SessionHandle(session).stopService();
		}
    }

    public void restartService() {
		if (session != null) {
			new SessionHandle(session).stopService();
		}
    }

    public void setHighFrequencyMode() {
		if (session != null) {
			new SessionHandle(session).setHighFrequencyMode();
		}
    }

    public void setLowFrequencyMode() {
		if (session != null) {
			new SessionHandle(session).setLowFrequencyMode();
		}
	}

    public void pauseService() {
		if (session != null) {
			new SessionHandle(session).pauseService();
		}
    }

    // Resume tau service
    public void resumeService() {
		if (session != null) {
			new SessionHandle(session).resumeService();
		}
    }

    public int getMaxAccountSize() {
		//return libTAU.getMAX_ACCOUNT_SIZE();
		return 0;
    }

    public int getChainEpochBlockSize() {
		return libTAU.getCHAIN_EPOCH_BLOCK_SIZE();
    }

    public int getMaxTxEncodedSize() {
		return libTAU.getMAX_TX_ENCODE_SIZE();
    }

    public int getMaxOverdraft() {
		return libTAU.getMAX_OVERDRAFT();
    }

	public void setLoopTimeInterval(int milliseconds) {
		if (session != null) {
			new SessionHandle(session).setLoopTimeInterval(milliseconds);
		}
	}

    public boolean publishData(byte[] key, byte[] value){
		if (session != null) {
			return new SessionHandle(session).publishData(key, value);
		}
        return false;
    }

    public boolean subscribeFromPeer(byte[] pubkey, byte[] data){
		if (session != null) {
			return new SessionHandle(session).subscribeFromPeer(pubkey, data);
		}
        return false;
    }

    public boolean sendToPeer(byte[] pubkey, byte[] data){
		if (session != null) {
			return new SessionHandle(session).sendToPeer(pubkey, data);
		}
        return false;
    }

    public boolean payAttenToPeer(byte[] peer){
		if (session != null) {
			return new SessionHandle(session).payAttenToPeer(peer);
		}
        return false;
    }

	public boolean addNewFriend(String pk) {
		if (session != null) {
			return new SessionHandle(session).addNewFriend(pk);
		}
		return false;
	}

	public boolean deleteFriend(String pk) {
		if (session != null) {
			return new SessionHandle(session).deleteFriend(pk);
		}
		return false;
	}

	public void setChattingFriend(String pk) {
		if (session != null) {
			new SessionHandle(session).setChattingFriend(pk);
		}
	}

	public void unsetChattingFriend() {
		if (session != null) {
			new SessionHandle(session).unsetChattingFriend();
		}
	}

	public boolean updateFriendInfo(String pk, byte[] info) {
		if (session != null) {
			return new SessionHandle(session).updateFriendInfo(pk, info);
		}
		return false;
	}

	public byte[] getFriendInfo(String pk) {
		if (session != null) {
			return new SessionHandle(session).getFriendInfo(pk);
		} else {
			return null;
		}
	}

	public void requestFriendInfo(String pk) {
		if (session != null) {
			new SessionHandle(session).requestFriendInfo(pk);
		}
	}

	public boolean addNewMsg(Message msg) {
		if (session != null) {
			return new SessionHandle(session).addNewMsg(msg);
		}
		return false;
	}
	
	public void setActiveFriends(ArrayList<String> friends) {
		if (session != null) {
			new SessionHandle(session).setActiveFriends(friends);
		}
	}
	
    /**
     * This is for create chain id
     */
    public byte[] createChainID(byte[] type, String communityName) {
		if (session != null) {
			return new SessionHandle(session).createChainID(type, communityName);
		}
		return null;
    }

    /**
     * This is for create new community
     */
    public boolean createNewCommunity(byte[] chainID, Map<String, Account> accounts) {
		if (session != null) {
			return new SessionHandle(session).createNewCommunity(chainID, accounts);
		}
		return false;
    }

    /**
     * This is for create new community
     */
    public boolean createNewCommunity(byte[] chainID, Set<Account> accounts) {
		if (session != null) {
			return new SessionHandle(session).createNewCommunity(chainID, accounts);
		}
		return false;
    }

    /**
     * This is for follow chain
     */
    public boolean followChain(byte[] chainID, Set<String> peers) {
		if (session != null) {
			return new SessionHandle(session).followChain(chainID, peers);
		}
		return false;
    }

    /**
     * This is for unfollow chain
     */
    public boolean unfollowChain(byte[] chainID) {
		if (session != null) {
			return new SessionHandle(session).unfollowChain(chainID);
		}
		return false;
    }

    /**
     * This is for start chain
     */
    public boolean startChain(byte[] chainID) {
		if (session != null) {
			return new SessionHandle(session).startChain(chainID);
		}
		return false;
    }

    /**
     * This is for add new bs peers
     */
    public boolean addNewBootstrapPeers(byte[] chainID, Set<String> peers) {
		if (session != null) {
			return new SessionHandle(session).addNewBootstrapPeers(chainID, peers);
		}
		return false;
    }

    /**
     * This is for submit transaction
     */
    public boolean submitTransaction(Transaction tx) {
		if (session != null) {
			return new SessionHandle(session).submitTransaction(tx);
		}
		return false;
    }

    /**
     * This is for submit news transaction
     */
    public boolean submitNewsTransaction(Transaction tx, List<byte[]> slices) {
		if (session != null) {
			return new SessionHandle(session).submitNewsTransaction(tx, slices);
		}
		return false;
    }

    /**
     * This is for get_port_from_pubkey
     */
    public int getPortFromPubkey(String pubkey) {
		if (session != null) {
			return new SessionHandle(session).getPortFromPubkey(pubkey);
		}
		return 6881;
    }

    /**
     * This is for get_account_info
     */
    public Account getAccountInfo(byte[] chainID, String pubkey) {
		if (session != null) {
			return new SessionHandle(session).getAccountInfo(chainID, pubkey);
		}
		return null;
    }

    /**
     * This is for get_top_tip_block
     */
    public ArrayList<Block> getTopTipBlock(byte[] chainID, int num) {
		if (session != null) {
			return new SessionHandle(session).getTopTipBlock(chainID, num);
		}
		return null;
    }

    /**
     * This is for get access list
     */
    public ArrayList<String> getAccessList(byte[] chainID) {
		if (session != null) {
			return new SessionHandle(session).getAccessList(chainID);
		}
		return null;
    }

    /**
     * This is for get active list
     */
    public ArrayList<String> getActiveList(byte[] chainID) {
		if (session != null) {
			return new SessionHandle(session).getActiveList(chainID);
		}
		return null;
    }

    /**
     * This is for get ban list
     */
    public ArrayList<String> getBanList(byte[] chainID) {
		if (session != null) {
			return new SessionHandle(session).getBanList(chainID);
		}
		return null;
    }

    /**
     * This is for get gossip list
     */
    public ArrayList<String> getGossipList(byte[] chainID) {
		if (session != null) {
			return new SessionHandle(session).getGossipList(chainID);
		}
		return null;
    }

    /**
     * This is for get all chains
     */
    public ArrayList<String> getAllChains() {
		if (session != null) {
			return new SessionHandle(session).getAllChains();
		}
		return null;
    }

    /**
     * This is for get_median_tx_free
     */
    public long getMedianTxFee(byte[] chainID) {
		if (session != null) {
			return new SessionHandle(session).getMedianTxFee(chainID);
		}
		return -1;
    }

    /**
     * This is for get_mining_time
     */
    public long getMiningTime(byte[] chainID) {
		if (session != null) {
			return new SessionHandle(session).getMiningTime(chainID);
		}
		return -1;
    }

    /**
     * This is for focus_on_chain
     */
    public void setPriorityChain(byte[] chainID) {
		if (session != null) {
            new SessionHandle(session).setPriorityChain(chainID);
		}
    }

    /**
     * This is for send online signal
     */
    public boolean sendOnlineSignal(byte[] chainID) {
		if (session != null) {
            return new SessionHandle(session).sendOnlineSignal(chainID);
		}
        return false;
    }

    /**
     * This is for chain connect
     */
    public boolean connectChain(byte[] chainID) {
		if (session != null) {
            return new SessionHandle(session).connectChain(chainID);
		}
        return false;
    }

    /**
     * This is for chain touch
     */
    public boolean touchChain(byte[] chainID) {
		if (session != null) {
            return new SessionHandle(session).touchChain(chainID);
		}
        return false;
    }

    /**
     * This is for unfocus_on_chain
     */
    public void unsetPriorityChain() {
		if (session != null) {
            new SessionHandle(session).unsetPriorityChain();
		}
    }

    /**
     * This is for get block by number
     */
    public Block getBlockByNumber(byte[] chainID, long blockNumber) {
		if (session != null) {
			return new SessionHandle(session).getBlockByNumber(chainID, blockNumber);
		}
		return null;
    }

    /**
     * This is for get block by hash
     */
    public Block getBlockByHash(byte[] chainID, String blockHash) {
		if (session != null) {
			return new SessionHandle(session).getBlockByHash(chainID, blockHash);
		}
		return null;
    }

    /**
     * This is judge tx
     */
    public boolean isTxInFeePool(byte[] chainID, String txID) {
		if (session != null) {
			return new SessionHandle(session).isTxInFeePool(chainID, txID);
		}
		return false;
    }

    /**
     * This is for request chain state
     */
    public void requestChainState(byte[] chainID) {
		if (session != null) {
			new SessionHandle(session).requestChainState(chainID);
		}
    }

    /**
     * This is for request chain data
     */
    public void requestChainData(byte[] chainID, String peer) {
		if (session != null) {
			new SessionHandle(session).requestChainData(chainID, peer);
		}
    }

    /**
     * This is for put all chain data
     */
    public void putAllChainData(byte[] chainID) {
		if (session != null) {
			new SessionHandle(session).putAllChainData(chainID);
		}
    }

    /**
     * This is for get session time
     */
    public long getSessionTime() {
		if (session != null) {
			return new SessionHandle(session).getSessionTime();
		}
		return -1;
    }

    /**
     * This is for enable or disable log
     */
    public void setLogLevel(int logged) {
		if (session != null) {
            new SessionHandle(session).setLogLevel(logged);
		}
    }

    /**
     * This is for crash test
     */
    public void crashTest() {
		if (session != null) {
            new SessionHandle(session).crashTest();
		}
    }

    /**
     * This is for sql test
     */
    public void sqlTest() {
		if (session != null) {
            new SessionHandle(session).sqlTest();
		}
    }

    //--------------------------------------------------
    // Settings methods
    //--------------------------------------------------

    /**
     * Returns a setting pack with all the settings
     * the current session is working with.
     * <p>
     * If the current internal session is null, returns
     * null.
     */
    public SettingsPack settings() {
        return session != null ? new SettingsPack(session.get_settings()) : null;
    }

    public void applySettings(SettingsPack sp) {
        if (session != null) {

            if (sp == null) {
                throw new IllegalArgumentException("settings pack can't be null");
            }

            session.apply_settings(sp.swig());
            onApplySettings(sp);
        }
    }

    public int downloadRateLimit() {
        if (session == null) {
            return 0;
        }
        return settings().downloadRateLimit();
    }

    public void downloadRateLimit(int limit) {
        if (session == null) {
            return;
        }
        applySettings(new SettingsPack().downloadRateLimit(limit));
    }

    public int uploadRateLimit() {
        if (session == null) {
            return 0;
        }
        return settings().uploadRateLimit();
    }

    public void uploadRateLimit(int limit) {
        if (session == null) {
            return;
        }
        applySettings(new SettingsPack().uploadRateLimit(limit));
    }

    public int maxActiveDownloads() {
        if (session == null) {
            return 0;
        }
        return settings().activeDownloads();
    }

    public void maxActiveDownloads(int limit) {
        if (session == null) {
            return;
        }
        applySettings(new SettingsPack().activeDownloads(limit));
    }

    public int maxActiveSeeds() {
        if (session == null) {
            return 0;
        }
        return settings().activeSeeds();
    }

    public void maxActiveSeeds(int limit) {
        if (session == null) {
            return;
        }
        applySettings(new SettingsPack().activeSeeds(limit));
    }

    public int maxConnections() {
        if (session == null) {
            return 0;
        }
        return settings().connectionsLimit();
    }

    public void maxConnections(int limit) {
        if (session == null) {
            return;
        }
        applySettings(new SettingsPack().connectionsLimit(limit));
    }

    public int maxPeers() {
        if (session == null) {
            return 0;
        }
        return settings().maxPeerlistSize();
    }

    public void maxPeers(int limit) {
        if (session == null) {
            return;
        }
        applySettings(new SettingsPack().maxPeerlistSize(limit));
    }

    public String listenInterfaces() {
        if (session == null) {
            return null;
        }
        return settings().listenInterfaces();
    }

    public void listenInterfaces(String value) {
        if (session == null) {
            return;
        }
        applySettings(new SettingsPack().listenInterfaces(value));
    }

    //--------------------------------------------------
    // more methods
    //--------------------------------------------------

    /**
     * This function will post a {@link SessionStatsAlert} object, containing a
     * snapshot of the performance counters from the internals of libTAU4j.
     */
    public void postSessionStats() {
        if (session != null) {
            session.post_session_stats();
        }
    }

    /**
     * This will cause a {@link DhtStatsAlert} to be posted.
     */
    public void postDhtStats() {
        if (session != null) {
            session.post_dht_stats();
        }
    }

    public boolean isDhtRunning() {
        return session != null && session.is_dht_running();
    }

    public void startDht() {
        toggleDht(true);
    }

    public void stopDht() {
        toggleDht(false);
    }

    public byte[] saveState() {
        if (session == null) {
            return null;
        }

        session_params params = session.session_state();
        byte_vector v = session_params.write_session_params_buf(params);
        return Vectors.byte_vector2bytes(v);
    }

    /**
     * Instructs the session to reopen all listen and outgoing sockets.
     * <p>
     * It's useful in the case your platform doesn't support the built in
     * IP notifier mechanism, or if you have a better more reliable way to
     * detect changes in the IP routing table.
     */
    public void reopenNetworkSockets() {
        if (session != null) {
            session.reopen_network_sockets();
        }
    }

    public String magnetPeers() {
        if (session == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        if (externalAddress != null && externalPort > 0) {
            sb.append("&x.pe=");
            sb.append(externalAddress).append(":").append(externalPort);
        }

        for (String endp : listenEndpoints.values()) {
            sb.append("&x.pe=").append(endp);
        }

        return sb.toString();
    }

    /**
     * This methods return the last error recorded calling the alert
     * listeners.
     *
     * @return the last alert listener exception registered (or null)
     */
    public Throwable lastAlertError() {
        return lastAlertError;
    }

    protected void onBeforeStart() {
    }

    protected void onAfterStart() {
    }

    protected void onBeforeStop() {
    }

    protected void onAfterStop() {
    }

    protected void onApplySettings(SettingsPack sp) {
    }

    @Override
    protected void finalize() throws Throwable {
        stop();
        super.finalize();
    }

    private void resetState() {
        stats.clear();
        firewalled = true;
        listenEndpoints.clear();
        externalAddress = null;
        alertsLoop = null;
    }

    private void modifyListeners(boolean add, AlertListener listener) {
        if (listener == null) {
            return;
        }

        int[] types = listener.types();

        // all alert-type including listener
        if (types == null) {
            modifyListeners(add, Alerts.NUM_ALERT_TYPES, listener);
        } else {
            for (int i = 0; i < types.length; i++) {
                modifyListeners(add, types[i], listener);
            }
        }
    }

    private synchronized void modifyListeners(boolean add, int type, AlertListener listener) {
        if (add) {
            listeners[type] = AlertMulticaster.add(listeners[type], listener);
        } else {
            listeners[type] = AlertMulticaster.remove(listeners[type], listener);
        }
    }

    private void fireAlert(Alert<?> a, int type) {
        AlertListener listener = listeners[type];
        if (listener != null) {
            try {
                listener.alert(a);
            } catch (Throwable e) {
                Log.warn("Error calling alert listener: " + e.getMessage());
                lastAlertError = e;
            }
        }
    }

    private void onListenSucceeded(ListenSucceededAlert alert) {
        try {
            // only store TCP endpoints
            if (alert.socketType() == SocketType.TCP) {
                return;
            }

            Address addr = alert.address();

            if (addr.isV4()) {
                // consider just one IPv4 listen endpoint port
                // as the external port
                externalPort = alert.port();
            }

            // only consider valid addresses
            if (addr.isLoopback() || addr.isMulticast() || addr.isUnspecified()) {
                return;
            }

            String address = addr.toString();
            int port = alert.port();

            // avoid local-link addresses
            if (address.startsWith("127.") || address.startsWith("fe80::")) {
                return;
            }

            String endp = (addr.isV6() ? "[" + address + "]" : address) + ":" + port;
            listenEndpoints.put(address, endp);
        } catch (Throwable e) {
            Log.error("Error adding listen endpoint to internal list", e);
        }
    }

    private void toggleDht(boolean on) {
        if (session == null || isDhtRunning() == on) {
            return;
        }

        SettingsPack sp = new SettingsPack();
        //sp.setEnableDht(on);

        applySettings(sp);
    }

    private void onExternalIpAlert(ExternalIpAlert alert) {
        try {
            // libTAU4j perform all kind of tests
            // to avoid non usable addresses
            address addr = alert.swig().get_external_address();
            // filter out non IPv4 addresses
            if (!addr.is_v4()) {
                return;
            }
            externalAddress = alert.externalAddress().toString();
        } catch (Throwable e) {
            Log.error("Error saving reported external ip", e);
        }
    }

    private static alert_category_t alertMask(boolean logging) {
        alert_category_t mask = alert.all_categories;
        if (!logging) {
            alert_category_t log_mask = alert.session_log_notification;
            log_mask = log_mask.or_(alert.dht_log_notification);
            log_mask = log_mask.or_(alert.port_mapping_log_notification);
            mask = mask.and_(log_mask.inv());
        }
        return mask;
    }

    protected String defaultDhtBootstrapNodes() {
        StringBuilder sb = new StringBuilder();

        sb.append("dht.libTAU4j.org:25401").append(",");
        sb.append("router.bittorrent.com:6881").append(",");
        sb.append("router.utorrent.com:6881").append(",");
        sb.append("dht.transmissionbt.com:6881");

        return sb.toString();
    }

    private static boolean isSpecialType(int type) {
        return type == AlertType.SESSION_STATS.swig() ||
                type == AlertType.SESSION_STATS_HEADER.swig();
    }

    private void alertsLoop() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                alert_ptr_vector v = new alert_ptr_vector();

                while (session != null) {
                    alert ptr = session.wait_for_alert_ms(ALERTS_LOOP_WAIT_MILLIS);

                    if (session == null) {
                        return;
                    }

                    if (ptr != null) {
                        session.pop_alerts(v);
                        long size = v.size();
                        for (int i = 0; i < size; i++) {
                            alert a = v.get(i);
                            int type = a.type();

                            Alert<?> alert = null;

                            switch (AlertType.fromSwig(type)) {
                                case SESSION_STATS:
                                    alert = Alerts.cast(a);
                                    stats.update((SessionStatsAlert) alert);
                                    break;
                                case PORTMAP:
                                    firewalled = false;
                                    break;
                                case PORTMAP_ERROR:
                                    firewalled = true;
                                    break;
                                case LISTEN_SUCCEEDED:
                                    alert = Alerts.cast(a);
                                    onListenSucceeded((ListenSucceededAlert) alert);
                                    break;
                                case EXTERNAL_IP:
                                    alert = Alerts.cast(a);
                                    onExternalIpAlert((ExternalIpAlert) alert);
                                    break;
                            }

                            if (listeners[type] != null) {
                                if (alert == null) {
                                    alert = Alerts.cast(a);
                                }
                                fireAlert(alert, type);
                            }

                            if (!isSpecialType(type) && listeners[Alerts.NUM_ALERT_TYPES] != null) {
                                if (alert == null) {
                                    alert = Alerts.cast(a);
                                }
                                fireAlert(alert, Alerts.NUM_ALERT_TYPES);
                            }
                        }
                        v.clear();
                    }

                    long now = System.currentTimeMillis();
                    if ((now - lastStatsRequestTime) >= REQUEST_STATS_RESOLUTION_MILLIS) {
                        lastStatsRequestTime = now;
                        postSessionStats();
                    }
                }
            }
        };

        Thread t = new Thread(r, "SessionManager-alertsLoop");
        t.setDaemon(true);
        t.start();

        alertsLoop = t;
    }

    private void processStopAlerts() {
        alert_ptr_vector v = new alert_ptr_vector();
        alert ptr = session.wait_for_alert_ms(0);

        if (session == null) {
            return;
        }

        if (ptr != null) {
            session.pop_alerts(v);
            long size = v.size();
            for (int i = 0; i < size; i++) {
                alert a = v.get(i);
                int type = a.type();
                Alert<?> alert = null;
                switch (AlertType.fromSwig(type)) {
                    case SESSION_STATS:
                        alert = Alerts.cast(a);
                        stats.update((SessionStatsAlert) alert);
                        break;
                }

                if (listeners[type] != null) {
                    if (alert == null) {
                        alert = Alerts.cast(a);
                    }
                    fireAlert(alert, type);
                }

                if (!isSpecialType(type) && listeners[Alerts.NUM_ALERT_TYPES] != null) {
                    if (alert == null) {
                        alert = Alerts.cast(a);
                    }
                    fireAlert(alert, Alerts.NUM_ALERT_TYPES);
                }
                v.clear();
            }
        }
    }

    public static final class MutableItem {

        private MutableItem(Entry item, byte[] signature, long seq) {
            this.item = item;
            this.signature = signature;
            this.seq = seq;
        }

        public final Entry item;
        public final byte[] signature;
        public final long seq;
    }
}
