%include "libTAU/pex_flags.hpp"

namespace libTAU {

struct pex_flags_tag;
%template(pex_flags_t) flags::bitfield_flag<std::uint8_t, pex_flags_tag>;

}
