/*
 * Copyright (c) 2018-2020, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.libtorrent4j;

import org.libtorrent4j.alerts.DhtImmutableItemAlert;
import org.libtorrent4j.alerts.DhtMutableItemAlert;
import org.libtorrent4j.alerts.SessionStatsAlert;
import org.libtorrent4j.swig.error_code;
import org.libtorrent4j.swig.int_vector;
import org.libtorrent4j.swig.libtorrent_errors;
import org.libtorrent4j.swig.portmap_protocol;
import org.libtorrent4j.swig.remove_flags_t;
import org.libtorrent4j.swig.reopen_network_flags_t;
import org.libtorrent4j.swig.save_state_flags_t;
import org.libtorrent4j.swig.session_flags_t;
import org.libtorrent4j.swig.session_handle;
import org.libtorrent4j.swig.status_flags_t;

import java.util.ArrayList;
import java.util.List;

/**
 * The session holds all state that spans multiple torrents. Among other
 * things it runs the network loop and manages all torrents. Once it's
 * created, the session object will spawn the main thread that will do all
 * the work. The main thread will be idle as long it doesn't have any
 * torrents to participate in.
 * <p>
 * This class belongs to a middle logical layer of abstraction. It's a wrapper
 * of the underlying swig session object (from libtorrent), but it does not
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
     * snapshot of the performance counters from the internals of libtorrent.
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
        h.add_dht_node(node.to_string_int_pair());
    }

    /**
     * Applies the settings specified by the settings pack {@code sp}. This is an
     * asynchronous operation that will return immediately and actually apply
     * the settings to the main thread of libtorrent some time later.
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
     * Query the DHT for an immutable item at the target hash.
     * the result is posted as a {@link DhtImmutableItemAlert}.
     *
     * @param target
     */
    public void dhtGetItem(Sha256Hash target) {
        h.dht_get_item(target.swig());
    }

    /**
     * Query the DHT for a mutable item under the public key {@code key}.
     * this is an ed25519 key. The {@code salt} argument is optional and may be left
     * as an empty string if no salt is to be used.
     * <p>
     * if the item is found in the DHT, a {@link DhtMutableItemAlert} is
     * posted.
     *
     * @param key
     * @param salt
     */
    public void dhtGetItem(byte[] key, byte[] salt) {
        h.dht_get_item(Vectors.bytes2byte_array_32(key), Vectors.bytes2byte_vector(salt));
    }

    /**
     * Store the given bencoded data as an immutable item in the DHT.
     * the returned hash is the key that is to be used to look the item
     * up again. It's just the sha-1 hash of the bencoded form of the
     * structure.
     *
     * @param entry
     */
    public Sha256Hash dhtPutItem(Entry entry) {
        return new Sha256Hash(h.dht_put_item(entry.swig()));
    }

    // store an immutable item. The ``key`` is the public key the blob is
    // to be stored under. The optional ``salt`` argument is a string that
    // is to be mixed in with the key when determining where in the DHT
    // the value is to be stored. The callback function is called from within
    // the libtorrent network thread once we've found where to store the blob,
    // possibly with the current value stored under the key.
    // The values passed to the callback functions are:
    //
    // entry& value
    // 	the current value stored under the key (may be empty). Also expected
    // 	to be set to the value to be stored by the function.
    //
    // boost::array<char,64>& signature
    // 	the signature authenticating the current value. This may be zeroes
    // 	if there is currently no value stored. The function is expected to
    // 	fill in this buffer with the signature of the new value to store.
    // 	To generate the signature, you may want to use the
    // 	``sign_mutable_item`` function.
    //
    // boost::uint64_t& seq
    // 	current sequence number. May be zero if there is no current value.
    // 	The function is expected to set this to the new sequence number of
    // 	the value that is to be stored. Sequence numbers must be monotonically
    // 	increasing. Attempting to overwrite a value with a lower or equal
    // 	sequence number will fail, even if the signature is correct.
    //
    // std::string const& salt
    // 	this is the salt that was used for this put call.
    //
    // Since the callback function ``cb`` is called from within libtorrent,
    // it is critical to not perform any blocking operations. Ideally not
    // even locking a mutex. Pass any data required for this function along
    // with the function object's context and make the function entirely
    // self-contained. The only reason data blobs' values are computed
    // via a function instead of just passing in the new value is to avoid
    // race conditions. If you want to *update* the value in the DHT, you
    // must first retrieve it, then modify it, then write it back. The way
    // the DHT works, it is natural to always do a lookup before storing and
    // calling the callback in between is convenient.
    public void dhtPutItem(byte[] publicKey, byte[] privateKey, Entry entry, byte[] salt) {
        h.dht_put_item(Vectors.bytes2byte_array_32(publicKey),
            Vectors.bytes2byte_array_64(privateKey),
            entry.swig(),
            Vectors.bytes2byte_vector(salt));
    }

    /**
     * returns the port we ended up listening on. Since you
     * just pass a port-range to the constructor and to ``listen_on()``, to
     * know which port it ended up using, you have to ask the session using
     * this function.
     */
    public int getListenPort() {
        return h.listen_port();
    }

    public int getSslListenPort() {
        return h.ssl_listen_port();
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
