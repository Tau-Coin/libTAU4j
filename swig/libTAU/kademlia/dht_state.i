namespace libTAU {
namespace dht {

struct dht_state
{
    std::vector<std::pair<address, libTAU::sha256_hash>> nids;

    std::vector<libTAU::dht::node_entry> nodes;
    std::vector<libTAU::dht::node_entry> nodes6;

    void clear();
};

}
}
