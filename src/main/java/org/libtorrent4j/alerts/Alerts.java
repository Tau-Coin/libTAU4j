/*
 * Copyright (c) 2018-2021, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.libtorrent4j.alerts;

import org.libtorrent4j.swig.alert;
import org.libtorrent4j.swig.libtorrent;

import static org.libtorrent4j.swig.alert.*;

/**
 * @author gubatron
 * @author aldenml
 */
public final class Alerts {

    public static final int NUM_ALERT_TYPES = libtorrent.getNum_alert_types();

    private static final CastLambda[] TABLE = buildTable();

    private Alerts() {
    }

    public static Alert cast(alert a) {
        return TABLE[a.type()].cast(a);
    }

    private static CastLambda[] buildTable() {
        CastLambda[] arr = new CastLambda[NUM_ALERT_TYPES];

        arr[0] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[1] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[2] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[3] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[4] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[5] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[6] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[7] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[8] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[9] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[10] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[11] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[12] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[13] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[14] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[15] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[16] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[17] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[18] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[19] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[20] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[21] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[22] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[23] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[24] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[25] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[26] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[27] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[28] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[29] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[30] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[31] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[32] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[33] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[34] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[35] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[36] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[38] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[39] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[40] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[41] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[42] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[43] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[44] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[45] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[46] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new UdpErrorAlert(cast_to_udp_error_alert(a));
            }
        };
        arr[47] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new ExternalIpAlert(cast_to_external_ip_alert(a));
            }
        };
        arr[48] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new ListenFailedAlert(cast_to_listen_failed_alert(a));
            }
        };
        arr[49] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new ListenSucceededAlert(cast_to_listen_succeeded_alert(a));
            }
        };
        arr[50] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new PortmapErrorAlert(cast_to_portmap_error_alert(a));
            }
        };
        arr[51] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new PortmapAlert(cast_to_portmap_alert(a));
            }
        };
        arr[52] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new PortmapLogAlert(cast_to_portmap_log_alert(a));
            }
        };
        arr[53] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[54] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[55] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[56] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new DhtGetPeersAlert(cast_to_dht_get_peers_alert(a));
            }
        };
        arr[57] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[58] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[59] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[60] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[61] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[62] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new DhtBootstrapAlert(cast_to_dht_bootstrap_alert(a));
            }
        };
        arr[63] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[64] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[65] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[66] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[69] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[70] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new SessionStatsAlert(cast_to_session_stats_alert(a));
            }
        };
        arr[71] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[72] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[73] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new DhtErrorAlert(cast_to_dht_error_alert(a));
            }
        };
        arr[74] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new DhtImmutableItemAlert(cast_to_dht_immutable_item_alert(a));
            }
        };
        arr[75] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new DhtMutableItemAlert(cast_to_dht_mutable_item_alert(a));
            }
        };
        arr[76] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new DhtPutAlert(cast_to_dht_put_alert(a));
            }
        };
        arr[77] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[78] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new DhtOutgoingGetPeersAlert(cast_to_dht_outgoing_get_peers_alert(a));
            }
        };
        arr[79] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new LogAlert(cast_to_log_alert(a));
            }
        };
        arr[80] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[81] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[82] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[83] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new DhtStatsAlert(cast_to_dht_stats_alert(a));
            }
        };
        arr[84] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[85] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new DhtLogAlert(cast_to_dht_log_alert(a));
            }
        };
        arr[86] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new DhtPktAlert(cast_to_dht_pkt_alert(a));
            }
        };
        arr[87] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new DhtGetPeersReplyAlert(cast_to_dht_get_peers_reply_alert(a));
            }
        };
        arr[88] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[89] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[90] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new SessionErrorAlert(cast_to_session_error_alert(a));
            }
        };
        arr[91] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new DhtLiveNodesAlert(cast_to_dht_live_nodes_alert(a));
            }
        };
        arr[92] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new SessionStatsHeaderAlert(cast_to_session_stats_header_alert(a));
            }
        };
        arr[93] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[94] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[95] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new AlertsDroppedAlert(cast_to_alerts_dropped_alert(a));
            }
        };
        arr[96] = new CastLambda() {
            @Override
            public Alert<?> cast(alert a) {
                return handleUnknownAlert(a);
            }
        };
        arr[97] = new CastLambda() {
            @Override
            public Alert<?> cast(alert a) {
                return handleUnknownAlert(a);
            }
        };

        return arr;
    }

    private static Alert handleUnknownAlert(alert a) {
        throw new IllegalArgumentException("alert not known: " + a.type() + " - " + a.message());
    }

    private interface CastLambda {
        Alert cast(alert a);
    }
}
