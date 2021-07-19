%ignore libTAU::session_handle::session_handle(aux::session_impl*);
%ignore libTAU::session_handle::session_handle(session_handle&&);
%ignore libTAU::session_handle::get_io_service;
%ignore libTAU::session_handle::get_connection_queue;
%ignore libTAU::session_handle::add_extension;
%ignore libTAU::session_handle::dht_put_item(std::array<char, 32>, std::function<void(entry&, std::array<char,64>&, std::int64_t&, std::string const&)>, std::string);
%ignore libTAU::session_handle::dht_put_item(std::array<char, 32>, std::function<void(entry&, std::array<char,64>&, std::int64_t&, std::string const&)>);
%ignore libTAU::session_handle::dht_get_item(std::array<char, 32>, std::string);
%ignore libTAU::session_handle::dht_get_item(std::array<char, 32>);
%ignore libTAU::session_handle::new_account_seed(std::array<char, 32> seed);
%ignore libTAU::session_handle::add_new_friend(std::array<char, 32> pubkey);
%ignore libTAU::session_handle::update_friend_info(std::array<char, 32> pubkey, std::vector<char> friend_info);
%ignore libTAU::session_handle::get_friend_info(std::array<char, 32> pubkey);
%ignore libTAU::session_handle::delete_friend(std::array<char, 32> pubkey);
%ignore libTAU::session_handle::add_new_message(std::vector<char> msg);
%ignore libTAU::session_handle::set_chatting_friend(std::array<char, 32> pubkey);
%ignore libTAU::session_handle::set_load_function;
%ignore libTAU::session_handle::set_alert_notify;
%ignore libTAU::session_handle::native_handle;
%ignore libTAU::session_handle::set_dht_storage;
%ignore libTAU::session_handle::get_cache_info;
%ignore libTAU::session_handle::wait_for_alert;
%ignore libTAU::session_handle::apply_settings(settings_pack&&);
%ignore libTAU::session_handle::get_context;
%ignore libTAU::session_handle::add_port_mapping;
%ignore libTAU::session_handle::delete_port_mapping;
%ignore libTAU::session_handle::delete_peer_class;
%ignore libTAU::session_handle::get_peer_class;
%ignore libTAU::session_handle::set_peer_class;
%ignore libTAU::session_handle::global_peer_class_id;
%ignore libTAU::session_handle::tcp_peer_class_id;
%ignore libTAU::session_handle::local_peer_class_id;
%ignore libTAU::session_handle::create_peer_class;

%include "libTAU/session_handle.hpp"

namespace libTAU {

%extend session_handle {

    void dht_get_item(std::array<std::int8_t, 32>& key, std::vector<std::int8_t>& salt)
    {
        std::array<char, 32> pk;
        std::copy_n(key.begin(), 32, pk.begin());

        $self->dht_get_item(pk, std::string(salt.begin(), salt.end()));
    }

    void dht_put_item(std::array<std::int8_t, 32>& key, std::array<std::int8_t, 64>& sk, entry& data, std::vector<int8_t>& salt)
    {
        std::array<char, 32> pk;
        std::copy_n(key.begin(), 32, pk.begin());

        using namespace std::placeholders;
        using namespace libTAU::dht;

        $self->dht_put_item(pk, std::bind(&dht_put_item_cb, _1, _2, _3, _4,
            public_key((char*)key.data()), secret_key((char*)sk.data()), data),
            std::string(salt.begin(), salt.end()));
    }

    alert* wait_for_alert_ms(std::int64_t max_wait)
    {
        return $self->wait_for_alert(libTAU::milliseconds(max_wait));
    }

    void set_alert_notify_callback(alert_notify_callback* cb) {
        $self->set_alert_notify(std::bind(&alert_notify_callback::on_alert, cb));
    }

    std::vector<int> add_port_mapping_ex(libTAU::portmap_protocol t
        , int external_port, int local_port)
    {
        auto mapping = $self->add_port_mapping(t, external_port, local_port);
        std::vector<int> r;
        for (auto m : mapping)
            r.push_back(static_cast<int>(m));
        return r;
    }

    void delete_port_mapping_ex(int handle)
    {
        $self->delete_port_mapping(libTAU::port_mapping_t{handle});
    }

    void new_account_seed(std::array<std::int8_t, 32>& seed)
    {
        std::array<char, 32> char_seed;
        std::copy_n(seed.begin(), 32, char_seed.begin());

        return $self->new_account_seed(char_seed);
    }

    void set_loop_time_interval(int milliseconds)
    {
        $self->set_loop_time_interval(milliseconds);
    }

    bool add_new_friend(std::array<std::int8_t, 32>& pubkey)
    {
        std::array<char, 32> pk;
        std::copy_n(pubkey.begin(), 32, pk.begin());

        return $self->add_new_friend(pk);
    }

    bool delete_friend(std::array<std::int8_t, 32>& pubkey)
    {
        std::array<char, 32> pk;
        std::copy_n(pubkey.begin(), 32, pk.begin());

        return $self->delete_friend(pk);
    }

    void set_chatting_friend(std::array<std::int8_t, 32>& pubkey)
    {
        std::array<char, 32> pk;
        std::copy_n(pubkey.begin(), 32, pk.begin());

        $self->set_chatting_friend(pk);
    }

    std::vector<std::int8_t> get_friend_info(std::array<std::int8_t, 32> pubkey)
    {
        std::array<char, 32> pk;
        std::copy_n(pubkey.begin(), 32, pk.begin());

        std::vector<char> friend_info = $self->get_friend_info(pk);

        std::vector<std::int8_t> info; 
        std::copy(friend_info.begin(), friend_info.end(), std::inserter(info, info.begin()));
        return info;
    }

    void unset_chatting_friend()
    {
        $self->unset_chatting_friend();
    }

    bool update_friend_info(std::array<std::int8_t, 32> pubkey, std::vector<std::int8_t> friend_info)
    {
        std::array<char, 32> pk;
        std::copy_n(pubkey.begin(), 32, pk.begin());

        std::vector<char> info;
        std::copy(friend_info.begin(), friend_info.end(), std::inserter(info, info.begin()));

        return $self->update_friend_info(pk, info);
    }

    void set_active_friends(std::vector<std::array<char, 32>> active_friends)
    {
        $self->set_active_friends(active_friends);
    }

    bool add_new_message(std::vector<std::int8_t> msg)
    {
        std::vector<char> message;
        std::copy(msg.begin(), msg.end(), std::inserter(message, message.begin()));

        return $self->add_new_message(message);
    }

}

}
