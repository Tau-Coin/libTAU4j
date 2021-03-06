%include "libTAU/info_hash.hpp"

namespace libTAU {

%extend info_hash_t
{
    bool op_eq(info_hash_t const& n) const {
        return *$self == n;
    }

    bool op_ne(info_hash_t const& n) const {
        return *$self != n;
    }
}

}
