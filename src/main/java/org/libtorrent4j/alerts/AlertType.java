/*
 * Copyright (c) 2018-2021, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.libtorrent4j.alerts;

import org.libtorrent4j.swig.*;

/**
 * @author gubatron
 * @author aldenml
 */
public enum AlertType {

    PORTMAP(portmap_alert.alert_type),
    PORTMAP_ERROR(portmap_error_alert.alert_type),
    PORTMAP_LOG(portmap_log_alert.alert_type),
    DHT_BOOTSTRAP(dht_bootstrap_alert.alert_type),
    DHT_GET_PEERS(dht_get_peers_alert.alert_type),
    EXTERNAL_IP(external_ip_alert.alert_type),
    LISTEN_SUCCEEDED(listen_succeeded_alert.alert_type),
    STATE_UPDATE(state_update_alert.alert_type),
    SESSION_STATS(session_stats_alert.alert_type),
    LISTEN_FAILED(listen_failed_alert.alert_type),
    UDP_ERROR(udp_error_alert.alert_type),
    DHT_ERROR(dht_error_alert.alert_type),
    LOG(log_alert.alert_type),
    DHT_STATS(dht_stats_alert.alert_type),
    DHT_LOG(dht_log_alert.alert_type),
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
        arr[2] = UNKNOWN;
        arr[3] = UNKNOWN;
        arr[4] = UNKNOWN;
        arr[5] = UNKNOWN;
        arr[6] = UNKNOWN;
        arr[7] = UNKNOWN;
        arr[8] = UNKNOWN;
        arr[9] = UNKNOWN;
        arr[10] = UNKNOWN;
        arr[11] = UNKNOWN;
        arr[12] = UNKNOWN;
        arr[13] = UNKNOWN;
        arr[14] = UNKNOWN;
        arr[15] = UNKNOWN;
        arr[16] = UNKNOWN;
        arr[17] = UNKNOWN;
        arr[18] = UNKNOWN;
        arr[19] = UNKNOWN;
        arr[20] = UNKNOWN;
        arr[21] = UNKNOWN;
        arr[22] = UNKNOWN;
        arr[23] = UNKNOWN;
        arr[24] = UNKNOWN;
        arr[25] = UNKNOWN;
        arr[26] = UNKNOWN;
        arr[27] = UNKNOWN;
        arr[28] = UNKNOWN;
        arr[29] = UNKNOWN;
        arr[30] = UNKNOWN;
        arr[31] = UNKNOWN;
        arr[32] = UNKNOWN;
        arr[33] = UNKNOWN;
        arr[34] = UNKNOWN;
        arr[35] = UNKNOWN;
        arr[36] = UNKNOWN;
        arr[37] = UNKNOWN;
        arr[38] = UNKNOWN;
        arr[39] = UNKNOWN;
        arr[40] = UNKNOWN;
        arr[41] = UNKNOWN;
        arr[42] = UNKNOWN;
        arr[43] = UNKNOWN;
        arr[44] = UNKNOWN;
        arr[45] = UNKNOWN;
        arr[46] = UDP_ERROR;
        arr[47] = EXTERNAL_IP;
        arr[48] = LISTEN_FAILED;
        arr[49] = LISTEN_SUCCEEDED;
        arr[50] = PORTMAP_ERROR;
        arr[51] = PORTMAP;
        arr[52] = PORTMAP_LOG;
        arr[53] = UNKNOWN;
        arr[54] = UNKNOWN;
        arr[55] = UNKNOWN;
        arr[56] = UNKNOWN;
        arr[57] = UNKNOWN;
        arr[58] = UNKNOWN;
        arr[59] = UNKNOWN;
        arr[60] = UNKNOWN;
        arr[61] = UNKNOWN;
        arr[62] = DHT_BOOTSTRAP;
        arr[63] = UNKNOWN;
        arr[64] = UNKNOWN;
        arr[65] = UNKNOWN;
        arr[66] = UNKNOWN;
        arr[67] = UNKNOWN;
        arr[68] = UNKNOWN;
        arr[69] = UNKNOWN;
        arr[70] = SESSION_STATS;
        arr[71] = UNKNOWN;
        arr[72] = UNKNOWN;
        arr[73] = DHT_ERROR;
        arr[74] = UNKNOWN;
        arr[75] = UNKNOWN;
        arr[76] = UNKNOWN;
        arr[77] = UNKNOWN;
        arr[78] = UNKNOWN;
        arr[79] = LOG;
        arr[80] = UNKNOWN;
        arr[81] = UNKNOWN;
        arr[82] = UNKNOWN;
        arr[83] = DHT_STATS;
        arr[84] = UNKNOWN;
        arr[85] = DHT_LOG;
        arr[86] = DHT_PKT;
        arr[87] = UNKNOWN;
        arr[88] = UNKNOWN;
        arr[89] = UNKNOWN;
        arr[90] = SESSION_ERROR;
        arr[91] = UNKNOWN;
        arr[92] = SESSION_STATS_HEADER;
        arr[93] = UNKNOWN;
        arr[94] = UNKNOWN;
        arr[95] = ALERTS_DROPPED;
        arr[96] = UNKNOWN;
        arr[97] = UNKNOWN;

        return arr;
    }
}
