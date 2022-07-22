/*
 * Copyright (c) 2018-2020, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.libTAU4j;

import org.libTAU4j.ChainURL;
import org.libTAU4j.alerts.SessionStatsAlert;
import org.libTAU4j.swig.error_code;
import org.libTAU4j.swig.block_vector;
import org.libTAU4j.swig.byte_vector;
import org.libTAU4j.swig.int_vector;
import org.libTAU4j.swig.libTAU;
import org.libTAU4j.swig.libTAU_errors;
import org.libTAU4j.swig.portmap_protocol;
import org.libTAU4j.swig.public_key;
import org.libTAU4j.swig.byte_vector_vector;
import org.libTAU4j.swig.pubkey_set;
import org.libTAU4j.swig.pubkey_vector;
import org.libTAU4j.swig.pubkey_account_map;
import org.libTAU4j.swig.pubkey_account_set;
import org.libTAU4j.swig.remove_flags_t;
import org.libTAU4j.swig.reopen_network_flags_t;
import org.libTAU4j.swig.sha1_hash;
import org.libTAU4j.swig.save_state_flags_t;
import org.libTAU4j.swig.session_flags_t;
import org.libTAU4j.swig.session_handle;
import org.libTAU4j.swig.vector_byte_array_32;
import org.libTAU4j.swig.byte_array_32;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * The session holds all state that spans multiple torrents. Among other
 * things it runs the network loop and manages all torrents. Once it's
 * created, the session object will spawn the main thread that will do all
 * the work. The main thread will be idle as long it doesn't have any
 * torrents to participate in.
 * <p>
 * This class belongs to a middle logical layer of abstraction. It's a wrapper
 * of the underlying swig session object (from libTAU4j), but it does not
 * expose all the raw features.
 *
 * @author gubatron
 * @author aldenml
 */
public final class SessionHandle
    extends SwigObject<session_handle> {

    // protocols used by add_port_mapping()
    public static final portmap_protocol UDP = session_handle.udp;
    public static final portmap_protocol TCP = session_handle.tcp;

    /**
     * @param handle the native object
     */
    public SessionHandle(session_handle handle) {
        super(handle);
    }

    public boolean isValid() {
        return h.is_valid();
    }

    /**
     * Saves settings (i.e. the {@link SettingsPack}).
     */
    public static final save_state_flags_t SAVE_SETTINGS = session_handle.save_settings;

    /**
     * Saves dht state such as nodes and node-id, possibly accelerating
     * joining the DHT if provided at next session startup.
     */
    public static final save_state_flags_t SAVE_DHT_STATE = session_handle.save_dht_state;

    /**
     * Load or save state from plugins.
     */
    public static final save_state_flags_t SAVE_EXTENSION_STATE = session_handle.save_extension_state;

    /**
     * Load or save the IP filter set on the session.
     */
    public static final save_state_flags_t SAVE_IP_FILTER = session_handle.save_ip_filter;

    /**
     * Returns the current session state.
     *
     * This can be passed to write_session_params() to save the state to disk
     * and restored using read_session_params() when constructing a new session.
     *
     * The kind of state that's included is all settings, the DHT routing table,
     * possibly plugin-specific state.
     *
     * The flags parameter can be used to only save certain parts of the
     * session state.
     *
     * @param flags specifies to safe certain parts
     * @return the session params
     */
    public SessionParams sessionState(save_state_flags_t flags) {
        return new SessionParams(h.session_state(flags));
    }

    /**
     * Same as {@link #sessionState(save_state_flags_t)} with all
     * the flags bits active.
     *
     * @return the session params
     */
    public SessionParams sessionState() {
        return new SessionParams(h.session_state());
    }

    /**
     * This function will post a {@link SessionStatsAlert} object, containing a
     * snapshot of the performance counters from the internals of libTAU4j.
     * To interpret these counters, query the session via
     * session_stats_metrics().
     */
    public void postSessionStats() {
        h.post_session_stats();
    }

    /**
     * This will cause a dht_stats_alert to be posted.
     */
    public void postDhtStats() {
        h.post_dht_stats();
    }

    // Stop tau service
    public void stopService() {
        h.stop_service();
    }

    // Restart tau service
    public void restartService() {
        h.restart_service();
    }

    // Pause tau service
    public void pauseService() {
        h.pause_service();
    }

    // Resume tau service
    public void resumeService() {
        h.resume_service();
    }

	public void send(String to, Entry payload, int invoke_window
		, int invoke_limit, int hit_limit)
	{
		byte[] pk = Hex.decode(to);
		byte_array_32 bpk = Vectors.bytes2byte_array_32(pk);
		h.send(bpk, payload.swig(), invoke_window, invoke_limit, hit_limit);
	}

    /**
     * This is for get chain epoch block size
     */
    public int getChainEpochBlockSize() {
		return libTAU.getCHAIN_EPOCH_BLOCK_SIZE();
    }

    /**
     * This is for main loop interval set.
     */
    public void setLoopTimeInterval(int milliseconds) {
		h.set_loop_time_interval(milliseconds);
    }

    public boolean publishData(byte[] key, byte[] value){
        return h.publish_data(Vectors.bytes2byte_vector(key), Vectors.bytes2byte_vector(value));
    }

    public boolean subscribeFromPeer(byte[] pubkey, byte[] data){
		byte_array_32 bpk = Vectors.bytes2byte_array_32(pubkey);
        public_key key = new public_key(bpk);
        return h.subscribe_from_peer(key, Vectors.bytes2byte_vector(data));  
    }

    public boolean sendToPeer(byte[] pubkey, byte[] data){
		byte_array_32 bpk = Vectors.bytes2byte_array_32(pubkey);
        public_key key = new public_key(bpk);
        return h.send_to_peer(key, Vectors.bytes2byte_vector(data));  
    }

    /**
     * This is for adding new friend.
     */
    public boolean addNewFriend(String pubkey) {
		//key -> dht pubkey
		byte[] pk = Hex.decode(pubkey);
		byte_array_32 bpk = Vectors.bytes2byte_array_32(pk);
        public_key key = new public_key(bpk);
        return h.add_new_friend(key);
    }

    /**
     * This is for deleting friend.
     */
    public boolean deleteFriend(String pubkey) {
		//key -> dht pubkey
		byte[] pk = Hex.decode(pubkey);
		byte_array_32 bpk = Vectors.bytes2byte_array_32(pk);
        public_key key = new public_key(bpk);
        return h.delete_friend(key);
    }

    /**
     * This is for updating friend info.
     */
    public boolean updateFriendInfo(String pubkey, byte[] info) {
		//key -> dht pubkey
		byte[] pk = Hex.decode(pubkey);
		byte_array_32 bpk = Vectors.bytes2byte_array_32(pk);
        public_key key = new public_key(bpk);
        return h.update_friend_info(key, Vectors.bytes2byte_vector(info));
    }

    /**
     * This is for getting friend info.
     */
    public byte[] getFriendInfo(String pubkey) {
		//key -> dht pubkey
		byte[] pk = Hex.decode(pubkey);
		byte_array_32 bpk = Vectors.bytes2byte_array_32(pk);
        public_key key = new public_key(bpk);
        byte_vector info = h.get_friend_info_java(key);
		return Vectors.byte_vector2bytes(info);
    }

    /**
     * This is for setting active friends.
     */
    public void setActiveFriends(ArrayList<String> pubkeys) {

		pubkey_vector pks = new pubkey_vector();

		for(int i= 0; i< pubkeys.size(); i++){
			byte[] pk = Hex.decode(pubkeys.get(i));
			byte_array_32 bpk = Vectors.bytes2byte_array_32(pk);
        	public_key key = new public_key(bpk);
			pks.add(key);
		}

        h.set_active_friends(pks);
    }

    /**
     * This is for friend info request.
     */
    public void requestFriendInfo(String pubkey) {
		byte[] pk = Hex.decode(pubkey);
		byte_array_32 bpk = Vectors.bytes2byte_array_32(pk);
        public_key key = new public_key(bpk);
        h.request_friend_info(key);
    }

    /**
     * This is for setting chatting friend.
     */
    public void setChattingFriend(String pubkey) {
		byte[] pk = Hex.decode(pubkey);
		byte_array_32 bpk = Vectors.bytes2byte_array_32(pk);
        public_key key = new public_key(bpk);
        h.set_chatting_friend(key);
    }

    /**
     * This is for adding new msg.
     */
    public boolean addNewMsg(Message msg) {
        return h.add_new_message(msg.swig());
    }

    /**
     * This is for unset chatting friend.
     */
    public void unsetChattingFriend() {
        h.unset_chatting_friend();
    }

    /**
     * This is for create chain id
     */
    public byte[] createChainID(byte[] type, String communityName) {
		byte[] name = communityName.getBytes();
		byte_vector id = h.create_chain_id(Vectors.bytes2byte_vector(type), Vectors.bytes2byte_vector(name));
		return Vectors.byte_vector2bytes(id);
    }

    /**
     * This is for create new community
     */
    public boolean createNewCommunity(byte[] chainID, Map<String, Account> accounts) {
		//map string, account -> public_key, account
        /*
		pubkey_account_set sa = new pubkey_account_set();
		for(String pubkey : accounts.keySet()) {
			byte[] pk = Hex.decode(pubkey);
		    byte_array_32 bpk = Vectors.bytes2byte_array_32(pk);
        	public_key key = new public_key(bpk);
			pam.put(key, accounts.get(pubkey).swig());
		}
        return h.create_new_community(Vectors.bytes2byte_vector(chainID), pam);
        */
        return false;
    }

    /**
     * This is for create new community
     */
    public boolean createNewCommunity(byte[] chainID, Set<Account> accounts) {
		//map string, account -> public_key, account
		pubkey_account_set sa = new pubkey_account_set();
		for(Account act : accounts) {
			sa.add(act.swig());
		}
        return h.create_new_community(Vectors.bytes2byte_vector(chainID), sa);
    }

    /**
     * This is for follow chain
     */
    public boolean followChain(byte[] chainID, Set<String> peers) {
        pubkey_set keyset = new pubkey_set();
        for(String peer : peers) {
		    byte[] pk = Hex.decode(peer);
	        byte_array_32 bpk = Vectors.bytes2byte_array_32(pk);
       	    public_key key = new public_key(bpk);
            keyset.add(key);
        }

        return h.follow_chain(Vectors.bytes2byte_vector(chainID), keyset);
    }

    /**
     * This is for add new bs peers
     */
    public boolean addNewBootstrapPeers(byte[] chainID, Set<String> peers) {
        pubkey_set keyset = new pubkey_set();
        for(String peer : peers) {
		    byte[] pk = Hex.decode(peer);
	        byte_array_32 bpk = Vectors.bytes2byte_array_32(pk);
       	    public_key key = new public_key(bpk);
            keyset.add(key);
        }

        return h.add_new_bootstrap_peers(Vectors.bytes2byte_vector(chainID), keyset);
    }

    /**
     * This is for unfollow chain
     */
    public boolean unfollowChain(byte[] chainID) {
        return h.unfollow_chain(Vectors.bytes2byte_vector(chainID));
    }

    /**
     * This is for submit transaction
     */
    public boolean submitTransaction(Transaction tx) {
		return h.submit_transaction(tx.swig());
    }

    /**
     * This is for get_account_info
     */
    public Account getAccountInfo(byte[] chainID, String pubkey) {
		//key -> dht pubkey
		byte[] pk = Hex.decode(pubkey);
	    byte_array_32 bpk = Vectors.bytes2byte_array_32(pk);
       	public_key key = new public_key(bpk);
        return new Account(h.get_account_info(Vectors.bytes2byte_vector(chainID), key));
    }

	
    /**
     * This is for get_top_tip_block
     */
    public ArrayList<Block> getTopTipBlock(byte[] chainID, int num) {
        block_vector bv = h.get_top_tip_block(Vectors.bytes2byte_vector(chainID), num);
		ArrayList<Block> blks = new ArrayList<Block>();
		for(int i = 0; i < bv.size(); i++){
			blks.add(new Block(bv.get(i)));
		}
		return blks;
    }

    /**
     * This is for get access list
     */
    public ArrayList<String> getAccessList(byte[] chainID) {
        pubkey_set keyset = h.get_access_list(Vectors.bytes2byte_vector(chainID));
		ArrayList<String> keys = new ArrayList<String>();
        Iterator<public_key> key_iter = keyset.iterator();
        while(key_iter.hasNext()) {
            keys.add(Hex.encode(Vectors.byte_vector2bytes(key_iter.next().to_bytes())));
		}
		return keys;
    }

    /**
     * This is for get ban list
     */
    public ArrayList<String> getBanList(byte[] chainID) {
        pubkey_set keyset = h.get_ban_list(Vectors.bytes2byte_vector(chainID));
		ArrayList<String> keys = new ArrayList<String>();
        Iterator<public_key> key_iter = keyset.iterator();
        while(key_iter.hasNext()) {
            keys.add(Hex.encode(Vectors.byte_vector2bytes(key_iter.next().to_bytes())));
		}
		return keys;
    }

    /**
     * This is for get gossip list
     */
    public ArrayList<String> getGossipList(byte[] chainID) {
        /*
        pubkey_set keyset = h.get_gossip_list(Vectors.bytes2byte_vector(chainID));
		ArrayList<String> keys = new ArrayList<String>();
        Iterator<public_key> key_iter = keyset.iterator();
        while(key_iter.hasNext()) {
            keys.add(Hex.encode(Vectors.byte_vector2bytes(key_iter.next().to_bytes())));
		}
		return keys;
        */
        return null;
    }

    /**
     * This is for get all chains
     */
    public ArrayList<String> getAllChains() {
        byte_vector_vector chain_vector = h.get_all_chains_swig();
		ArrayList<String> chains = new ArrayList<String>();
        int cv_size = chain_vector.size();
        System.out.println(cv_size);
        int i = 0;
        while(i < cv_size) {
            chains.add(ChainURL.chainIDBytesToString(Vectors.byte_vector2bytes(chain_vector.get(i))));
            i++;
		}
		return chains;
    }

    /**
     * This is for get_median_tx_free
     */
    public long getMedianTxFee(byte[] chainID) {
        return h.get_median_tx_free(Vectors.bytes2byte_vector(chainID));
    }

    /**
     * This is for get_mining_time
     */
    public long getMiningTime(byte[] chainID) {
        return h.get_mining_time(Vectors.bytes2byte_vector(chainID));
    }

    /**
     * This is for focus_on_chain
     */
    public void setPriorityChain(byte[] chainID) {
        h.set_priority_chain(Vectors.bytes2byte_vector(chainID));
    }

    /**
     * This is for un-focus_on_chain
     */
    public void unsetPriorityChain() {
        h.unset_priority_chain();
    }

    /**
    /**
     * This is for get block by number
     */
    public Block getBlockByNumber(byte[] chainID, long blockNumber) {
        return new Block(h.get_block_by_number(Vectors.bytes2byte_vector(chainID), blockNumber));
    }

    /**
     * This is for get block by hash
     */
    public Block getBlockByHash(byte[] chainID, String blockHash) {
        return new Block(h.get_block_by_hash(Vectors.bytes2byte_vector(chainID), sha1_hash.from_hex(blockHash)));
    }

    /**
     * This is for get block by hash
     */
    public boolean isTxInFeePool(byte[] chainID, String txID) {
        return h.is_transaction_in_fee_pool(Vectors.bytes2byte_vector(chainID), sha1_hash.from_hex(txID));
    }

    /**
     * This is for request chain state
     */
    public void requestChainState(byte[] chainID) {
    /* 
         h.request_chain_state(Vectors.bytes2byte_vector(chainID));
    */
    }

    /**
     * This is for get_session_time
     */
    public long getSessionTime() {
        return h.get_session_time();
    }

    /**
     * This is for enable or diable log
     */
    public void setLogLevel(int logged) {
        h.set_log_level(logged);
    }

    /**
     * This is for crashTest
     */
    public void crashTest() {
        h.crash_test();
    }

    /**
     * This is for sqlTest
     */
    public void sqlTest() {
        h.sql_test();
    }

    /**
     * Delete the files belonging to the torrent from disk,
     * including the part-file, if there is one.
     */
    public static final remove_flags_t DELETE_FILES = session_handle.delete_files;

    /**
     * Delete just the part-file associated with this torrent.
     */
    public static final remove_flags_t DELETE_PARTFILE = session_handle.delete_partfile;

    // starts/stops UPnP, NATPMP or LSD port mappers they are stopped by
    // default These functions are not available in case
    // ``TORRENT_DISABLE_DHT`` is defined. ``start_dht`` starts the dht node
    // and makes the trackerless service available to torrents. The startup
    // state is optional and can contain nodes and the node id from the
    // previous session. The dht node state is a bencoded dictionary with the
    // following entries:
    //
    // nodes
    // 	A list of strings, where each string is a node endpoint encoded in
    // 	binary. If the string is 6 bytes long, it is an IPv4 address of 4
    // 	bytes, encoded in network byte order (big endian), followed by a 2
    // 	byte port number (also network byte order). If the string is 18
    // 	bytes long, it is 16 bytes of IPv6 address followed by a 2 bytes
    // 	port number (also network byte order).
    //
    // node-id
    // 	The node id written as a readable string as a hexadecimal number.
    //
    // ``dht_state`` will return the current state of the dht node, this can
    // be used to start up the node again, passing this entry to
    // ``start_dht``. It is a good idea to save this to disk when the session
    // is closed, and read it up again when starting.
    //
    // If the port the DHT is supposed to listen on is already in use, and
    // exception is thrown, ``asio::error``.
    //
    // ``stop_dht`` stops the dht node.
    //
    // ``add_dht_node`` adds a node to the routing table. This can be used if
    // your client has its own source of bootstrapping nodes.
    //
    // ``set_dht_settings`` sets some parameters available to the dht node.
    // See dht_settings for more information.
    //
    // ``is_dht_running()`` returns true if the DHT support has been started
    // and false
    // otherwise.


    public boolean isDhtRunning() {
        return h.is_dht_running();
    }

    /**
     * takes a host name and port pair. That endpoint will be
     * pinged, and if a valid DHT reply is received, the node will be added to
     * the routing table.
     *
     * @param node
     */
    public void addDhtNode(Pair<String, Integer> node) {
        // h.add_dht_node(node.to_string_int_pair());
    }

    /**
     * Applies the settings specified by the settings pack {@code sp}. This is an
     * asynchronous operation that will return immediately and actually apply
     * the settings to the main thread of libTAU4j some time later.
     *
     * @param sp the settings
     */
    public void applySettings(SettingsPack sp) {
        h.apply_settings(sp.swig());
    }

    /**
     * @return a copy of the internal settings
     */
    public SettingsPack settings() {
        return new SettingsPack(h.get_settings());
    }

    /**
     * Update listen interfaces and this method will trigger reopening network sockets.
     *
     * @param interfaces listen network interfaces.
     */
    public void updateListenInterfaces(String interfaces) {
        SettingsPack sp = settings();
        sp.listenInterfaces(interfaces);
        applySettings(sp);
    }

	/**
	 * Update bootstrap time interval.
	 *
	 * @param interval the unit is seconds
	 */
	public void updateBootstrapIntervel(int interval) {
		SettingsPack sp = settings();
		sp.setBootstrapInterval(interval);
		applySettings(sp);
	}

	public void updateKeepIntervel(int interval) {
		SettingsPack sp = settings();
		sp.setKeepInterval(interval);
		applySettings(sp);
	}

	public void setNonReferrable(boolean value) {
		SettingsPack sp = settings();
		sp.setNonReferrable(value);
		applySettings(sp);
	}

    /**
     * This is for getting port from pubkey.
     */
    public int getPortFromPubkey(String pubkey) {
		//key -> dht pubkey
		byte[] pk = Hex.decode(pubkey);
		byte_array_32 bpk = Vectors.bytes2byte_array_32(pk);
        public_key key = new public_key(bpk);

        return h.get_port_from_pubkey(key);
    }

    /**
     * This is for changing account.
     */
    public void updateAccountSeed(byte[] seed) {
		//byte -> byte_array_32
		SettingsPack sp = settings();
		sp.setAccoutSeed(Hex.encode(seed));
		applySettings(sp);
    }

    /**
     * Adds a port forwarding on UPnP and/or NAT-PMP, using PCP if supported,
     * whichever is enabled. The return value is a handle referring to the
     * port mapping that was just created. Pass it to {@link #deletePortMapping}
     * to remove it.
     *
     * @param t            the mapping protocol
     * @param externalPort the external port
     * @param localPort    the local port
     * @return the array of port mapping ids
     */
    public int[] addPortMapping(PortmapProtocol t, int externalPort, int localPort) {
        int_vector v = h.add_port_mapping_ex(portmap_protocol.swigToEnum(t.swig()), externalPort, localPort);

        int size = (int)v.size();
        int[] arr = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = v.get(i);
        }

        return arr;
    }

    public void deletePortMapping(int handle) {
        h.delete_port_mapping_ex(handle);
    }

    /**
     * This option indicates if the ports are mapped using natpmp
     * and UPnP. If mapping was already made, they are deleted and added
     * again. This only works if natpmp and/or upnp are configured to be
     * enable.
     */
    public static final reopen_network_flags_t REOPEN_MAP_PORTS = session_handle.reopen_map_ports;

    /**
     * Instructs the session to reopen all listen and outgoing sockets.
     * <p>
     * It's useful in the case your platform doesn't support the built in
     * IP notifier mechanism, or if you have a better more reliable way to
     * detect changes in the IP routing table.
     *
     * @param options the options
     */
    public void reopenNetworkSockets(reopen_network_flags_t options) {
        h.reopen_network_sockets(options);
    }

    /**
     * Instructs the session to reopen all listen and outgoing sockets.
     * <p>
     * It's useful in the case your platform doesn't support the built in
     * IP notifier mechanism, or if you have a better more reliable way to
     * detect changes in the IP routing table.
     */
    public void reopenNetworkSockets() {
        h.reopen_network_sockets();
    }

    /**
     * will tell you whether or not the session has
     * successfully opened a listening port. If it hasn't, this function will
     * return false, and then you can use ``listen_on()`` to make another
     * attempt.
     *
     * @return {@code true} if listening
     */
    public boolean isListening() {
        return h.is_listening();
    }
}
