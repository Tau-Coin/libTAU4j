%ignore libTAU::session_params::session_params(settings_pack&&, std::vector<std::shared_ptr<plugin>>);
%ignore libTAU::session_params::session_params(settings_pack const&, std::vector<std::shared_ptr<plugin>>);
%ignore libTAU::session_params::session_params(session_params&&);
%ignore libTAU::session_params::session_params(settings_pack&& sp);
%ignore libTAU::session_params::extensions;
%ignore libTAU::session_params::disk_io_constructor;
%ignore libTAU::read_session_params;
%ignore libTAU::write_session_params;
%ignore libTAU::write_session_params_buf;

%include "libTAU/session_params.hpp"

namespace libTAU
{

%extend session_params
{
    static lt::session_params read_session_params(lt::bdecode_node const& e
        , lt::save_state_flags_t flags = lt::save_state_flags_t::all())
    {
        return lt::read_session_params(e, flags);
    }

    static lt::entry write_session_params(lt::session_params const& sp
    	, lt::save_state_flags_t flags = lt::save_state_flags_t::all())
    {
        return lt::write_session_params(sp, flags);
    }

    static std::vector<std::int8_t> write_session_params_buf(
        lt::session_params const& sp
        , lt::save_state_flags_t flags = lt::save_state_flags_t::all())
    {
        auto v = lt::write_session_params_buf(sp, flags);
        return {v.begin(), v.end()};
    }
}

}
