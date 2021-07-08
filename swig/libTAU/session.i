%ignore libTAU::session::session(session_params&&, session_flags_t);
%ignore libTAU::session::session(session_params&&, io_context&);
%ignore libTAU::session::session(session_params const&, io_context&);
%ignore libTAU::session::session(session_params&&, io_context&, session_flags_t);
%ignore libTAU::session::session(session_params const&, io_context&, session_flags_t);
%ignore libTAU::session::session(settings_pack&&, io_context&, session_flags_t const);
%ignore libTAU::session::session(settings_pack const&, io_context&, session_flags_t const);
%ignore libTAU::session::session(settings_pack&&, io_context&);
%ignore libTAU::session::session(settings_pack const&, io_context&);
%ignore libTAU::session::session(session_params&&);
%ignore libTAU::session::session(settings_pack&&, session_flags_t const);
%ignore libTAU::session::session(settings_pack&&);
%ignore libTAU::session_proxy::session_proxy(session_proxy&&);
%ignore libTAU::default_disk_io_constructor;

%include "libTAU/session.hpp"
