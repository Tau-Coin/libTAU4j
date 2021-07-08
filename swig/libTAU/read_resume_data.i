%ignore libTAU::read_resume_data(span<char const>, error_code&, load_torrent_limits const&);
%ignore libTAU::read_resume_data(span<char const>, error_code&);
%ignore libTAU::read_resume_data(bdecode_node const&, int);
%ignore libTAU::read_resume_data(bdecode_node const&);
%ignore libTAU::read_resume_data(span<char const>, load_torrent_limits const&);
%ignore libTAU::read_resume_data(span<char const>);

%include "libTAU/read_resume_data.hpp"
