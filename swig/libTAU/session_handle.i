%ignore libTAU::session_handle::session_handle(aux::session_impl*);
%ignore libTAU::session_handle::session_handle(session_handle&&);
%ignore libTAU::session_handle::get_io_service;
%ignore libTAU::session_handle::get_connection_queue;
%ignore libTAU::session_handle::add_extension;
%ignore libTAU::session_handle::dht_put_item(std::array<char, 32>, std::function<void(entry&, std::array<char,64>&, std::int64_t&, std::string const&)>, std::string);
%ignore libTAU::session_handle::dht_put_item(std::array<char, 32>, std::function<void(entry&, std::array<char,64>&, std::int64_t&, std::string const&)>);
%ignore libTAU::session_handle::dht_get_item(std::array<char, 32>, std::string);
%ignore libTAU::session_handle::dht_get_item(std::array<char, 32>);
%ignore libTAU::session_handle::update_friend_info(dht::public_key& pubkey, std::vector<char> friend_info);
%ignore libTAU::session_handle::create_chain_id(std::vector<char> community_name);
%ignore libTAU::session_handle::create_new_community(std::vector<char> chain_id, const std::map<dht::public_key, blockchain::account>& accounts);
%ignore libTAU::session_handle::follow_chain(std::vector<char> chain_id, const std::set<dht::public_key>& peers);
%ignore libTAU::session_handle::unfollow_chain(std::vector<char> chain_id);
%ignore libTAU::session_handle::get_access_list(std::vector<char> chain_id);
%ignore libTAU::session_handle::get_ban_list(std::vector<char> chain_id);
%ignore libTAU::session_handle::get_gossip_list(std::vector<char> chain_id);
%ignore libTAU::session_handle::get_account_info(std::vector<char> chain_id, dht::public_key pub_key);
%ignore libTAU::session_handle::get_top_tip_block(std::vector<char> chain_id, int num);
%ignore libTAU::session_handle::get_median_tx_free(std::vector<char> chain_id);
%ignore libTAU::session_handle::get_mining_time(std::vector<char> chain_id);
%ignore libTAU::session_handle::set_priority_chain(std::vector<char> chain_id);
%ignore libTAU::session_handle::get_block_by_number(std::vector<char> chain_id, std::int64_t block_number);
%ignore libTAU::session_handle::get_block_by_hash(std::vector<char> chain_id, sha256_hash block_hash);
%ignore libTAU::session_handle::is_transaction_in_fee_pool(std::vector<char> chain_id, sha256_hash txid);
%ignore libTAU::session_handle::request_chain_state(std::vector<char> chain_id);
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
%ignore libTAU::session_handle::get_friend_info;

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

    void set_loop_time_interval(int milliseconds)
    {
        $self->set_loop_time_interval(milliseconds);
    }

    void unset_chatting_friend()
    {
        $self->unset_chatting_friend();
    }

    bool update_friend_info(dht::public_key & pubkey, std::vector<std::int8_t> friend_info)
    {
        std::vector<char> info;
        std::copy(friend_info.begin(), friend_info.end(), std::inserter(info, info.begin()));

        return $self->update_friend_info(pubkey, info);
    }

    std::vector<std::int8_t> get_friend_info_java(dht::public_key & pubkey)
    {   
        std::vector<char> info = $self->get_friend_info(pubkey);
        std::vector<std::int8_t> friend_info;
        std::copy(info.begin(), info.end(), std::inserter(friend_info, friend_info.begin()));

        return friend_info;
    }

    std::vector<std::int8_t> create_chain_id(std::vector<std::int8_t> community_name)
    {   
        std::vector<char> name;
        std::copy(community_name.begin(), community_name.end(), std::inserter(name, name.begin()));

        std::vector<char> id = $self->create_chain_id(name);
        
        std::vector<std::int8_t> chain_id;
        std::copy(id.begin(), id.end(), std::inserter(chain_id, chain_id.begin()));

        return chain_id;
    }

    bool create_new_community(std::vector<std::int8_t> chain_id, const std::map<dht::public_key, blockchain::account>& accounts)
    {   
        std::vector<char> id;
        std::copy(chain_id.begin(), chain_id.end(), std::inserter(id, id.begin()));
        return $self->create_new_community(id, accounts);
    }

    bool follow_chain(std::vector<std::int8_t> chain_id, std::set<dht::public_key> peers)
    {   
        std::vector<char> id;
        std::copy(chain_id.begin(), chain_id.end(), std::inserter(id, id.begin()));
        return $self->follow_chain(id, peers);
    }

    bool unfollow_chain(std::vector<std::int8_t> chain_id)
    {   
        std::vector<char> id;
        std::copy(chain_id.begin(), chain_id.end(), std::inserter(id, id.begin()));
        return $self->unfollow_chain(id);
    }

    blockchain::account session_handle::get_account_info(std::vector<std::int8_t> chain_id, dht::public_key pub_key)
    {
        std::vector<char> id;
        std::copy(chain_id.begin(), chain_id.end(), std::inserter(id, id.begin()));
        return $self->get_account_info(id, pub_key);
    }

    std::vector<libTAU::blockchain::block> session_handle::get_top_tip_block(std::vector<std::int8_t> chain_id, int num)
    {
        std::vector<char> id;
        std::copy(chain_id.begin(), chain_id.end(), std::inserter(id, id.begin()));
        std::vector<libTAU::blockchain::block> blks = $self->get_top_tip_block(id, num);
        return blks;
    }

    std::set<libTAU::dht::public_key> session_handle::get_access_list(std::vector<std::int8_t> chain_id)
    {
        std::vector<char> id;
        std::copy(chain_id.begin(), chain_id.end(), std::inserter(id, id.begin()));
        std::set<libTAU::dht::public_key> keys = $self->get_access_list(id);
        return keys;
    }

    std::set<libTAU::dht::public_key> session_handle::get_ban_list(std::vector<std::int8_t> chain_id)
    {
        std::vector<char> id;
        std::copy(chain_id.begin(), chain_id.end(), std::inserter(id, id.begin()));
        std::set<libTAU::dht::public_key> keys = $self->get_ban_list(id);
        return keys;
    }

    std::set<libTAU::dht::public_key> session_handle::get_gossip_list(std::vector<std::int8_t> chain_id)
    {
        std::vector<char> id;
        std::copy(chain_id.begin(), chain_id.end(), std::inserter(id, id.begin()));
        std::set<libTAU::dht::public_key> keys = $self->get_gossip_list(id);
        return keys;
    }

    std::int64_t session_handle::get_median_tx_free(std::vector<std::int8_t> chain_id)
    {
        std::vector<char> id;
        std::copy(chain_id.begin(), chain_id.end(), std::inserter(id, id.begin()));
        return $self->get_median_tx_free(id);
    }

    std::int64_t session_handle::get_mining_time(std::vector<std::int8_t> chain_id)
    {
        std::vector<char> id;
        std::copy(chain_id.begin(), chain_id.end(), std::inserter(id, id.begin()));
        return $self->get_mining_time(id);
    }

    void session_handle::set_priority_chain(std::vector<std::int8_t> chain_id)
    {
        std::vector<char> id;
        std::copy(chain_id.begin(), chain_id.end(), std::inserter(id, id.begin()));
        return $self->set_priority_chain(id);
    }

    blockchain::block session_handle::get_block_by_number(std::vector<std::int8_t> chain_id, std::int64_t block_number)
    {
        std::vector<char> id;
        std::copy(chain_id.begin(), chain_id.end(), std::inserter(id, id.begin()));
        return $self->get_block_by_number(id, block_number);
    }

    blockchain::block session_handle::get_block_by_hash(std::vector<std::int8_t> chain_id, sha256_hash block_hash)
    {
        std::vector<char> id;
        std::copy(chain_id.begin(), chain_id.end(), std::inserter(id, id.begin()));
        return $self->get_block_by_hash(id, block_hash);
    }

    bool session_handle::is_transaction_in_fee_pool(std::vector<std::int8_t> chain_id, sha256_hash txid)
    {
        std::vector<char> id;
        std::copy(chain_id.begin(), chain_id.end(), std::inserter(id, id.begin()));
        return $self->is_transaction_in_fee_pool(id, txid);
    }

    void session_handle::request_chain_state(std::vector<std::int8_t> chain_id)
    {
        std::vector<char> id;
        std::copy(chain_id.begin(), chain_id.end(), std::inserter(id, id.begin()));
        return $self->request_chain_state(id);
    }

}
}
