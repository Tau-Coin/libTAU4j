%include "libTAU/torrent_flags.hpp"

namespace libTAU {

struct torrent_flags_tag;
%template(torrent_flags_t) flags::bitfield_flag<std::uint64_t, torrent_flags_tag>;

}
