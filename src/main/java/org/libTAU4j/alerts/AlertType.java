/*
 * Copyright (c) 2018-2021, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.libTAU4j.alerts;

import org.libTAU4j.swig.*;

/**
 * @author gubatron
 * @author aldenml
 */
public enum AlertType {

    PORTMAP(portmap_alert.alert_type),
    PORTMAP_ERROR(portmap_error_alert.alert_type),
    PORTMAP_LOG(portmap_log_alert.alert_type),
    DHT_BOOTSTRAP(dht_bootstrap_alert.alert_type),
    SES_START_OVER(session_start_over_alert.alert_type),
    DHT_GET_PEERS(dht_get_peers_alert.alert_type),
    EXTERNAL_IP(external_ip_alert.alert_type),
    LISTEN_SUCCEEDED(listen_succeeded_alert.alert_type),
    SES_STOP_OVER(session_stop_over_alert.alert_type),
    SESSION_STATS(session_stats_alert.alert_type),
    LISTEN_FAILED(listen_failed_alert.alert_type),
    UDP_ERROR(udp_error_alert.alert_type),
    DHT_ERROR(dht_error_alert.alert_type),
    LOG(log_alert.alert_type),
    DHT_STATS(dht_stats_alert.alert_type),
    DHT_LOG(dht_log_alert.alert_type),
    COMM_NEW_DEVICE_ID(communication_new_device_id_alert.alert_type),
    COMM_NEW_MSG(communication_new_message_alert.alert_type),
    COMM_CONFIRM_ROOT(communication_confirmation_root_alert.alert_type),
    COMM_SYNC_MSG(communication_syncing_message_alert.alert_type),
    COMM_FRIEND_INFO(communication_friend_info_alert.alert_type),
    COMM_LOG(communication_log_alert.alert_type),
    COMM_LAST_SEEN(communication_last_seen_alert.alert_type),
    BLOCK_CHAIN_LOG(blockchain_log_alert.alert_type),
    BLOCK_CHAIN_HEAD_BLOCK(blockchain_new_head_block_alert.alert_type),
    BLOCK_CHAIN_TAIL_BLOCK(blockchain_new_tail_block_alert.alert_type),
    BLOCK_CHAIN_CONSENSUS_POINT_BLOCK(blockchain_new_consensus_point_block_alert.alert_type),
    BLOCK_CHAIN_ROLLBACK_BLOCK(blockchain_rollback_block_alert.alert_type),
    BLOCK_CHAIN_FORK_POINT(blockchain_fork_point_block_alert.alert_type),
    BLOCK_CHAIN_TOP_THREE_VOTES(blockchain_top_three_votes_alert.alert_type),
    BLOCK_CHAIN_NEW_TX(blockchain_new_transaction_alert.alert_type),
    BLOCK_CHAIN_STATE(blockchain_state_alert.alert_type),
    BLOCK_CHAIN_SYNCING_BLOCK(blockchain_syncing_block_alert.alert_type),
    BLOCK_CHAIN_SYNCING_HEAD_BLOCK(blockchain_syncing_head_block_alert.alert_type),
    BLOCK_CHAIN_TX_CONFIRM(blockchain_tx_confirmation_alert.alert_type),
	REFERRED_STATUS(referred_status_alert.alert_type),
	COMM_MSG_ARRIVED(communication_message_arrived_alert.alert_type),
	BLOCK_CHAIN_TX_SENT(blockchain_tx_sent_alert.alert_type),
	BLOCK_CHAIN_TX_ARRIVED(blockchain_tx_arrived_alert.alert_type),
	COMM_USER_INFO(communication_user_info_alert.alert_type),
	COMM_USER_EVENT(communication_user_event_alert.alert_type),
    BLOCK_CHAIN_STATE_ARRAY(blockchain_state_array_alert.alert_type),
    DHT_PKT(dht_pkt_alert.alert_type),
    SESSION_ERROR(session_error_alert.alert_type),
    SESSION_STATS_HEADER(session_stats_header_alert.alert_type),
    ALERTS_DROPPED(alerts_dropped_alert.alert_type),
    UNKNOWN(-1);

    private static final AlertType[] TABLE = buildTable();

    AlertType(int swigValue) {
        this.swigValue = swigValue;
    }

    private final int swigValue;

    /**
     * @return the native swig value
     */
    public int swig() {
        return swigValue;
    }

    /**
     * @param swigValue the native swig value
     * @return the API enum alert type
     */
    public static AlertType fromSwig(int swigValue) {
        return TABLE[swigValue];
    }

    private static AlertType[] buildTable() {
        AlertType[] arr = new AlertType[Alerts.NUM_ALERT_TYPES];

        arr[0] = UDP_ERROR;
        arr[1] = EXTERNAL_IP;
        arr[2] = LISTEN_FAILED;
        arr[3] = LISTEN_SUCCEEDED;
        arr[4] = PORTMAP_ERROR;
        arr[5] = PORTMAP;
        arr[6] = PORTMAP_LOG;
        arr[7] = UNKNOWN;
        arr[8] = UNKNOWN;
        arr[9] = DHT_BOOTSTRAP;
        arr[10] = SES_START_OVER;
        arr[11] = UNKNOWN;
        arr[12] = SES_STOP_OVER;
        arr[13] = SESSION_STATS;
        arr[14] = DHT_ERROR;
        arr[15] = UNKNOWN;
        arr[16] = UNKNOWN;
        arr[17] = UNKNOWN;
        arr[18] = UNKNOWN;
        arr[19] = LOG;
        arr[20] = DHT_STATS;
        arr[21] = DHT_LOG;
        arr[22] = DHT_PKT;
        arr[23] = UNKNOWN;
        arr[24] = UNKNOWN;
        arr[25] = SESSION_ERROR;
        arr[26] = UNKNOWN;
        arr[27] = SESSION_STATS_HEADER;
        arr[28] = UNKNOWN;
        arr[29] = ALERTS_DROPPED;
        arr[30] = UNKNOWN;
        arr[31] = COMM_NEW_DEVICE_ID;
        arr[32] = COMM_NEW_MSG;
        arr[33] = COMM_CONFIRM_ROOT;
        arr[34] = COMM_SYNC_MSG;
        arr[35] = COMM_FRIEND_INFO;
        arr[36] = COMM_LOG;
        arr[37] = COMM_LAST_SEEN;
        arr[38] = BLOCK_CHAIN_LOG;
        arr[39] = BLOCK_CHAIN_HEAD_BLOCK;
        arr[40] = BLOCK_CHAIN_TAIL_BLOCK;
        arr[41] = BLOCK_CHAIN_CONSENSUS_POINT_BLOCK;
        arr[42] = BLOCK_CHAIN_ROLLBACK_BLOCK;
        arr[43] = BLOCK_CHAIN_FORK_POINT;
        arr[44] = BLOCK_CHAIN_TOP_THREE_VOTES;
        arr[45] = BLOCK_CHAIN_NEW_TX;
        arr[46] = BLOCK_CHAIN_STATE;
        arr[47] = BLOCK_CHAIN_SYNCING_BLOCK;
        arr[48] = BLOCK_CHAIN_SYNCING_HEAD_BLOCK;
        arr[49] = BLOCK_CHAIN_TX_CONFIRM;
		arr[50] = REFERRED_STATUS;
		arr[51] = COMM_MSG_ARRIVED;
		arr[52] = BLOCK_CHAIN_TX_SENT;
		arr[53] = BLOCK_CHAIN_TX_ARRIVED;
		arr[54] = COMM_USER_INFO;
		arr[55] = COMM_USER_EVENT;
		arr[56] = BLOCK_CHAIN_STATE_ARRAY;

        return arr;
    }
}
