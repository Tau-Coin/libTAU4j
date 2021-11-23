%ignore libTAU::blockchain::chain_url::get_URL;

%include "libTAU/blockchain/chain_url.hpp"

namespace libTAU {
    namespace blockchain {
            
        %extend chain_url {

            std::vector<std::int8_t> get_URL_java()
            {
                std::string url = $self->get_URL();
                std::vector<std::int8_t> chain_url;
                std::copy(url.begin(), url.end(), std::inserter(chain_url, chain_url.begin()));
                return chain_url;
            }

        }
    }
}
