%ignore libTAU::read_piece_alert::read_piece_alert;
%ignore libTAU::read_piece_alert::buffer;
%ignore libTAU::peer_log_alert::event_type;
%ignore libTAU::dht_pkt_alert::pkt_buf;
%ignore libTAU::session_stats_alert::counters;
%ignore libTAU::dht_lookup::type;
%ignore libTAU::dht_sample_infohashes_alert::endpoint;
%ignore libTAU::dht_sample_infohashes_alert::interval;
%ignore libTAU::dht_announce_alert::ip;
%ignore libTAU::external_ip_alert::external_address;
%ignore libTAU::listen_failed_alert::address;
%ignore libTAU::listen_succeeded_alert::address;
%ignore libTAU::incoming_connection_alert::endpoint;
%ignore libTAU::peer_alert::endpoint;
%ignore libTAU::dht_direct_response_alert::endpoint;
%ignore libTAU::dht_outgoing_get_peers_alert::endpoint;
%ignore libTAU::dht_pkt_alert::node;
%ignore libTAU::udp_error_alert::endpoint;
%ignore libTAU::portmap_alert::mapping;
%ignore libTAU::portmap_error_alert::mapping;
%ignore libTAU::listen_failed_alert::socket_type;
%ignore libTAU::listen_succeeded_alert::socket_type;
%ignore libTAU::lsd_error_alert::local_address;
%ignore libTAU::portmap_alert::local_address;
%ignore libTAU::portmap_error_alert::local_address;
%ignore libTAU::portmap_log_alert::local_address;
%ignore libTAU::socks5_alert::ip;
%ignore libTAU::tracker_alert::local_endpoint;
%ignore libTAU::dht_stats_alert::local_endpoint;
%ignore libTAU::communication_new_device_id_alert::device_id;
%ignore libTAU::communication_friend_info_alert::peer;
%ignore libTAU::communication_friend_info_alert::friend_info;
%ignore libTAU::communication_confirmation_root_alert::peer;
%ignore libTAU::communication_confirmation_root_alert::time;
%ignore libTAU::communication_syncing_message_alert::peer;
%ignore libTAU::communication_syncing_message_alert::time;
%ignore libTAU::communication_last_seen_alert::peer;
%ignore libTAU::communication_last_seen_alert::last_seen;
%ignore libTAU::performance_alert::deprecated_bittyrant_with_no_uplimit;
%ignore libTAU::dht_mutable_item_alert::key;
%ignore libTAU::dht_mutable_item_alert::signature;
%ignore libTAU::dht_put_alert::public_key;
%ignore libTAU::dht_put_alert::signature;
%ignore libTAU::performance_warning_str;

%rename("$ignore", regextarget=1, %$isconstructor) ".*_alert$";

%include "libTAU/alert_types.hpp"

namespace libTAU {

struct picker_flags_tag;
%template(picker_flags_t) flags::bitfield_flag<std::uint32_t, picker_flags_tag>;

%extend dht_announce_alert {

    address get_ip() {
        return $self->ip;
    }
}

%extend external_ip_alert {

    address get_external_address() {
        return $self->external_address;
    }
}

%extend listen_failed_alert {

    address get_address() {
        return $self->address;
    }
}

%extend listen_succeeded_alert {

    address get_address() {
        return $self->address;
    }
}

%extend incoming_connection_alert {

    tcp::endpoint get_endpoint() {
        return $self->endpoint;
    }
}

%extend peer_alert {

    tcp::endpoint get_endpoint() {
        return $self->endpoint;
    }
}

%extend dht_direct_response_alert {

    udp::endpoint get_endpoint() {
        return $self->endpoint;
    }
}

%extend dht_outgoing_get_peers_alert {

    udp::endpoint get_endpoint() {
        return $self->endpoint;
    }
}

%extend dht_pkt_alert
{

    udp::endpoint get_node()
    {
        return $self->node;
    }

    std::vector<std::int8_t> get_pkt_buf()
    {
        auto buf = $self->pkt_buf();
        return {buf.begin(), buf.end()};
    }
}

%extend udp_error_alert {

    udp::endpoint get_endpoint() {
        return $self->endpoint;
    }
}

%extend dht_sample_infohashes_alert {

    udp::endpoint get_endpoint() {
        return $self->endpoint;
    }

    std::int64_t get_interval() {
        return libTAU::total_milliseconds($self->interval);
    }
}

%extend dht_lookup {

    std::string get_type() {
        return std::string($self->type);
    }
}

%extend portmap_alert {

    int get_mapping() {
        return static_cast<int>($self->mapping);
    }
}

%extend portmap_error_alert {

    int get_mapping() {
        return static_cast<int>($self->mapping);
    }
}

%extend dht_mutable_item_alert {

    std::array<std::int8_t, 32> get_key()
    {
        std::array<char, 32> arr = $self->key;
        return *reinterpret_cast<std::array<std::int8_t, 32>*>(&arr);
    }

    std::array<std::int8_t, 64> get_signature()
    {
        std::array<char, 64> arr = $self->signature;
        return *reinterpret_cast<std::array<std::int8_t, 64>*>(&arr);
    }

    int64_t get_timestamp()
    {
        return int64_t($self->ts);
    }

    std::vector<std::int8_t> get_salt()
    {
        std::string s = $self->salt;
        return std::vector<std::int8_t>(s.begin(), s.end());
    }
}

%extend dht_put_alert {

    std::array<std::int8_t, 32> get_public_key()
    {
        std::array<char, 32> arr = $self->public_key;
        return *reinterpret_cast<std::array<std::int8_t, 32>*>(&arr);
    }

    std::array<std::int8_t, 64> get_signature()
    {
        std::array<char, 64> arr = $self->signature;
        return *reinterpret_cast<std::array<std::int8_t, 64>*>(&arr);
    }

    std::vector<std::int8_t> get_salt()
    {
        std::string s = $self->salt;
        return std::vector<std::int8_t>(s.begin(), s.end());
    }

    int64_t get_timestamp()
    {
        return int64_t($self->ts);
    }
}

%extend session_stats_alert {
    long long get_value(int index) {
        return $self->counters()[index];
    }
}

%extend peer_log_alert {
    std::string get_event_type() {
        return std::string($self->event_type);
    }
}

%extend listen_failed_alert
{
    int get_socket_type()
    {
        return static_cast<int>($self->socket_type);
    }
}

%extend listen_succeeded_alert
{
    int get_socket_type()
    {
        return static_cast<int>($self->socket_type);
    }
}

%extend dht_stats_alert
{
    udp::endpoint get_local_endpoint()
    {
        return $self->local_endpoint;
    }
}

%extend communication_new_device_id_alert
{
    std::vector<std::int8_t> get_device_id()
    {
        std::vector<char> device_id = $self->device_id;
        return *reinterpret_cast<std::vector<std::int8_t>*>(&device_id);
    }
}

%extend communication_confirmation_root_alert
{
    std::vector<std::int8_t> get_peer()
    {
        std::vector<char> peer = $self->peer;
        return *reinterpret_cast<std::vector<std::int8_t>*>(&peer);
    }

    std::uint32_t get_timestamp()
    {
        return $self->time;
    }
}

%extend communication_friend_info_alert
{
    std::vector<std::int8_t> get_peer()
    {
        std::vector<char> peer = $self->peer;
        return *reinterpret_cast<std::vector<std::int8_t>*>(&peer);
    }

    std::vector<std::int8_t> get_friend_info()
    {
        std::vector<char> info = $self->friend_info;
        return *reinterpret_cast<std::vector<std::int8_t>*>(&info);
    }
}

%extend communication_syncing_message_alert
{
    std::vector<std::int8_t> get_peer()
    {
        std::vector<char> peer = $self->peer;
        return *reinterpret_cast<std::vector<std::int8_t>*>(&peer);
    }

    std::uint32_t get_timestamp()
    {
        return $self->time;
    }
}

%extend communication_last_seen_alert
{
    std::vector<std::int8_t> get_peer()
    {
        std::vector<char> peer = $self->peer;
        return *reinterpret_cast<std::vector<std::int8_t>*>(&peer);
    }

    std::uint32_t get_last_seen()
    {
        return $self->last_seen;
    }
}
}
