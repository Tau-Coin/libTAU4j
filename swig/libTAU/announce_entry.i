%ignore libTAU::announce_infohash::announce_infohash;
%ignore libTAU::announce_infohash::next_announce;
%ignore libTAU::announce_infohash::min_announce;
%ignore libTAU::announce_entry::announce_entry(string_view);
%ignore libTAU::announce_endpoint::info_hashes;

%include "libTAU/announce_entry.hpp"

namespace libTAU {

%extend announce_infohash
{
    std::int64_t get_next_announce()
    {
        return lt::total_milliseconds($self->next_announce.time_since_epoch());
    }

    std::int64_t get_min_announce()
    {
        return lt::total_milliseconds($self->min_announce.time_since_epoch());
    }
}

%extend announce_endpoint
{
    announce_infohash get_infohash_v1()
    {
        return $self->info_hashes[lt::protocol_version::V1];
    }

    announce_infohash get_infohash_v2()
    {
        return $self->info_hashes[lt::protocol_version::V2];
    }
}

%extend announce_entry
{
    announce_entry(std::string url)
    {
        return new lt::announce_entry(url);
    }
}

}
