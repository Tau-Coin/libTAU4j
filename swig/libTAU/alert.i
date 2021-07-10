%ignore libTAU::alert::timestamp;
%ignore libTAU::alert_category::all;

%include "libTAU/alert.hpp"

namespace libTAU {

struct alert_category_tag;
%template(alert_category_t) flags::bitfield_flag<std::uint32_t, alert_category_tag>;

%extend alert {

    std::int64_t get_timestamp() {
        return libTAU::total_milliseconds($self->timestamp().time_since_epoch());
    }

#define CAST_ALERT_METHOD(name) \
    static libTAU::##name const* cast_to_##name(alert const* a) { \
        return libTAU::alert_cast<libTAU::##name>(a); \
    }

    CAST_ALERT_METHOD(udp_error_alert)
    CAST_ALERT_METHOD(external_ip_alert)
    CAST_ALERT_METHOD(listen_failed_alert)
    CAST_ALERT_METHOD(listen_succeeded_alert)
    CAST_ALERT_METHOD(portmap_error_alert)
    CAST_ALERT_METHOD(portmap_alert)
    CAST_ALERT_METHOD(portmap_log_alert)
    CAST_ALERT_METHOD(dht_announce_alert)
    CAST_ALERT_METHOD(dht_get_peers_alert)
    CAST_ALERT_METHOD(dht_bootstrap_alert)
    CAST_ALERT_METHOD(torrent_error_alert)
    CAST_ALERT_METHOD(incoming_connection_alert)
    CAST_ALERT_METHOD(state_update_alert)
    CAST_ALERT_METHOD(session_stats_alert)
    CAST_ALERT_METHOD(dht_error_alert)
    CAST_ALERT_METHOD(dht_immutable_item_alert)
    CAST_ALERT_METHOD(dht_mutable_item_alert)
    CAST_ALERT_METHOD(dht_put_alert)
    CAST_ALERT_METHOD(dht_outgoing_get_peers_alert)
    CAST_ALERT_METHOD(log_alert)
    CAST_ALERT_METHOD(peer_log_alert)
    CAST_ALERT_METHOD(dht_stats_alert)
    CAST_ALERT_METHOD(dht_log_alert)
    CAST_ALERT_METHOD(dht_pkt_alert)
    CAST_ALERT_METHOD(dht_get_peers_reply_alert)
    CAST_ALERT_METHOD(dht_direct_response_alert)
    CAST_ALERT_METHOD(picker_log_alert)
    CAST_ALERT_METHOD(session_error_alert)
    CAST_ALERT_METHOD(dht_live_nodes_alert)
    CAST_ALERT_METHOD(session_stats_header_alert)
    CAST_ALERT_METHOD(dht_sample_infohashes_alert)
    CAST_ALERT_METHOD(alerts_dropped_alert)
    CAST_ALERT_METHOD(socks5_alert)
    CAST_ALERT_METHOD(communication_log_alert)

}

}
