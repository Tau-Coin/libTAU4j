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
    STATE_UPDATE(state_update_alert.alert_type),
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

        arr[0] = UNKNOWN;
        arr[1] = UNKNOWN;
        arr[2] = UDP_ERROR;
        arr[3] = EXTERNAL_IP;
        arr[4] = LISTEN_FAILED;
        arr[5] = LISTEN_SUCCEEDED;
        arr[6] = PORTMAP_ERROR;
        arr[7] = PORTMAP;
        arr[8] = PORTMAP_LOG;
        arr[9] = UNKNOWN;
        arr[10] = UNKNOWN;
        arr[11] = UNKNOWN;
        arr[12] = DHT_BOOTSTRAP;
        arr[13] = SES_START_OVER;
        arr[14] = UNKNOWN;
        arr[15] = UNKNOWN;
        arr[16] = SES_STOP_OVER;
        arr[17] = SESSION_STATS;
        arr[18] = DHT_ERROR;
        arr[19] = UNKNOWN;
        arr[20] = UNKNOWN;
        arr[21] = UNKNOWN;
        arr[22] = UNKNOWN;
        arr[23] = LOG;
        arr[24] = UNKNOWN;
        arr[25] = DHT_STATS;
        arr[26] = DHT_LOG;
        arr[27] = DHT_PKT;
        arr[28] = UNKNOWN;
        arr[29] = UNKNOWN;
        arr[30] = UNKNOWN;
        arr[31] = SESSION_ERROR;
        arr[32] = UNKNOWN;
        arr[33] = SESSION_STATS_HEADER;
        arr[34] = UNKNOWN;
        arr[35] = ALERTS_DROPPED;
        arr[36] = UNKNOWN;
        arr[37] = COMM_NEW_DEVICE_ID;
        arr[38] = COMM_NEW_MSG;
        arr[39] = COMM_CONFIRM_ROOT;
        arr[40] = COMM_SYNC_MSG;
        arr[41] = COMM_FRIEND_INFO;
        arr[42] = COMM_LOG;

        return arr;
    }
}
