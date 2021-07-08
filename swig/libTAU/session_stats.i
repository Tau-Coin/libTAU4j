%ignore libTAU::stats_metric::name;
%ignore libTAU::find_metric_idx;

%include "libTAU/session_stats.hpp"

namespace libTAU {

%extend stats_metric
{
    std::string get_name()
    {
        return std::string($self->name);
    }
}

}
