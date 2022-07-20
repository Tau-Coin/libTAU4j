/*
 * Copyright (c) 2018-2021, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.libTAU4j.alerts;

import org.libTAU4j.swig.alert;
import org.libTAU4j.swig.libTAU;

import static org.libTAU4j.swig.alert.*;

/**
 * @author gubatron
 * @author aldenml
 */
public final class Alerts {

    public static final int NUM_ALERT_TYPES = libTAU.getNum_alert_types();

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
                return new UdpErrorAlert(cast_to_udp_error_alert(a));
            }
        };
        arr[1] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new ExternalIpAlert(cast_to_external_ip_alert(a));
            }
        };
        arr[2] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new ListenFailedAlert(cast_to_listen_failed_alert(a));
            }
        };
        arr[3] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new ListenSucceededAlert(cast_to_listen_succeeded_alert(a));
            }
        };
        arr[4] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new PortmapErrorAlert(cast_to_portmap_error_alert(a));
            }
        };
        arr[5] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new PortmapAlert(cast_to_portmap_alert(a));
            }
        };
        arr[6] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new PortmapLogAlert(cast_to_portmap_log_alert(a));
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
                return new DhtBootstrapAlert(cast_to_dht_bootstrap_alert(a));
            }
        };
        arr[10] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new SessionStartOverAlert(cast_to_session_start_over_alert(a));
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
                return new SessionStopOverAlert(cast_to_session_stop_over_alert(a));
            }
        };
        arr[13] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new SessionStatsAlert(cast_to_session_stats_alert(a));
            }
        };
        arr[14] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new DhtErrorAlert(cast_to_dht_error_alert(a));
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
                return new LogAlert(cast_to_log_alert(a));
            }
        };
        arr[20] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new DhtStatsAlert(cast_to_dht_stats_alert(a));
            }
        };
        arr[21] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new DhtLogAlert(cast_to_dht_log_alert(a));
            }
        };
        arr[22] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new DhtPktAlert(cast_to_dht_pkt_alert(a));
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
                return new SessionErrorAlert(cast_to_session_error_alert(a));
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
                return new SessionStatsHeaderAlert(cast_to_session_stats_header_alert(a));
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
                return new AlertsDroppedAlert(cast_to_alerts_dropped_alert(a));
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
                return new CommNewDeviceIdAlert(cast_to_communication_new_device_id_alert(a));
            }
        };
        arr[32] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new CommNewMsgAlert(cast_to_communication_new_message_alert(a));
            }
        };
        arr[33] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new CommConfirmRootAlert(cast_to_communication_confirmation_root_alert(a));
            }
        };
        arr[34] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new CommSyncMsgAlert(cast_to_communication_syncing_message_alert(a));
            }
        };
        arr[35] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new CommFriendInfoAlert(cast_to_communication_friend_info_alert(a));
            }
        };
        arr[36] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new CommLogAlert(cast_to_communication_log_alert(a));
            }
        };
        arr[37] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new CommLastSeenAlert(cast_to_communication_last_seen_alert(a));
            }
        };
        arr[38] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new BlockChainLogAlert(cast_to_blockchain_log_alert(a));
            }
        };
        arr[39] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new BlockChainNewHeadBlockAlert(cast_to_blockchain_new_head_block_alert(a));
            }
        };
        arr[40] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new BlockChainNewTailBlockAlert(cast_to_blockchain_new_tail_block_alert(a));
            }
        };
        arr[41] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new BlockChainNewConsensusPointBlockAlert(cast_to_blockchain_new_consensus_point_block_alert(a));
            }
        };
        arr[42] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new BlockChainRollbackBlockAlert(cast_to_blockchain_rollback_block_alert(a));
            }
        };
        arr[43] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new BlockChainForkPointBlockAlert(cast_to_blockchain_fork_point_block_alert(a));
            }
        };
        arr[44] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new BlockChainTopThreeVotesAlert(cast_to_blockchain_top_three_votes_alert(a));
            }
        };
        arr[45] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new BlockChainNewTransactionAlert(cast_to_blockchain_new_transaction_alert(a));
            }
        };

        arr[46] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new BlockChainStateAlert(cast_to_blockchain_state_alert(a));
            }
        };

        arr[47] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new BlockChainSyncingBlockAlert(cast_to_blockchain_syncing_block_alert(a));
            }
        };

        arr[48] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new BlockChainSyncingHeadBlockAlert(cast_to_blockchain_syncing_head_block_alert(a));
            }
        };

        arr[49] = new CastLambda() {
            @Override
            public Alert cast(alert a) {
                return new BlockChainTxConfirmAlert(cast_to_blockchain_tx_confirmation_alert(a));
            }
        };

		arr[50] = new CastLambda() {
			@Override
			public Alert cast(alert a) {
				return new ReferredStatusAlert(cast_to_referred_status_alert(a));
			}
		};

		arr[51] = new CastLambda() {
			@Override
			public Alert cast(alert a) {
				return new CommMsgArrivedAlert(cast_to_communication_message_arrived_alert(a));
			}
		};

		arr[52] = new CastLambda() {
			@Override
			public Alert cast(alert a) {
				return new BlockChainTxSentAlert(cast_to_blockchain_tx_sent_alert(a));
			}
		};

		arr[53] = new CastLambda() {
			@Override
			public Alert cast(alert a) {
				return new BlockChainTxArrivedAlert(cast_to_blockchain_tx_arrived_alert(a));
			}
		};

		arr[54] = new CastLambda() {
			@Override
			public Alert cast(alert a) {
				return new CommUserInfoAlert(cast_to_communication_user_info_alert(a));
			}
		};

		arr[55] = new CastLambda() {
			@Override
			public Alert cast(alert a) {
				return new CommUserEventAlert(cast_to_communication_user_event_alert(a));
			}
		};

		arr[56] = new CastLambda() {
			@Override
			public Alert cast(alert a) {
				return new BlockChainStateArrayAlert(cast_to_blockchain_state_array_alert(a));
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
