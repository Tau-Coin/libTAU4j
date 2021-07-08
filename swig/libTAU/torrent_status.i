%ignore libTAU::torrent_status::pieces;
%ignore libTAU::torrent_status::verified_pieces;
%ignore libTAU::torrent_status::torrent_status(torrent_status&&);
%ignore libTAU::torrent_status::_dummy_string_;
%ignore libTAU::torrent_status::torrent_file;
%ignore libTAU::torrent_status::next_announce;
%ignore libTAU::torrent_status::deprecated_announce_interval_;
%ignore libTAU::torrent_status::deprecated_priority;
%ignore libTAU::torrent_status::unused_enum_for_backwards_compatibility;
%ignore libTAU::torrent_status::deprecated_is_loaded;
%ignore libTAU::torrent_status::last_upload;
%ignore libTAU::torrent_status::last_download;
%ignore libTAU::torrent_status::active_duration;
%ignore libTAU::torrent_status::finished_duration;
%ignore libTAU::torrent_status::seeding_duration;
%ignore libTAU::torrent_status::queue_position;
%ignore libTAU::torrent_status::deprecated_time_since_upload;
%ignore libTAU::torrent_status::deprecated_time_since_download;
%ignore libTAU::torrent_status::deprecated_active_time;
%ignore libTAU::torrent_status::deprecated_finished_time;
%ignore libTAU::torrent_status::deprecated_seeding_time;
%ignore libTAU::torrent_status::deprecated_last_scrape;
%ignore libTAU::torrent_status::deprecated_ip_filter_applies;
%ignore libTAU::torrent_status::deprecated_upload_mode;
%ignore libTAU::torrent_status::deprecated_share_mode;
%ignore libTAU::torrent_status::deprecated_super_seeding;
%ignore libTAU::torrent_status::deprecated_paused;
%ignore libTAU::torrent_status::deprecated_auto_managed;
%ignore libTAU::torrent_status::deprecated_sequential_download;
%ignore libTAU::torrent_status::deprecated_seed_mode;
%ignore libTAU::torrent_status::deprecated_stop_when_ready;
%ignore libTAU::torrent_status::unused_enum_for_backwards_compatibility_allocating;

namespace libTAU {

struct torrent_handle;

}

%include "libTAU/torrent_status.hpp"

namespace libTAU {

%extend torrent_status {

    torrent_info const* torrent_file_ptr() {
        return $self->torrent_file.lock().get();
    }

    int64_t get_next_announce() {
        return libTAU::total_milliseconds($self->next_announce);
    }

    int64_t get_last_upload() {
        return libTAU::total_milliseconds($self->last_upload.time_since_epoch());
    }

    int64_t get_last_download() {
        return libTAU::total_milliseconds($self->last_download.time_since_epoch());
    }

    int64_t get_active_duration() {
        return libTAU::total_milliseconds($self->active_duration);
    }

    int64_t get_finished_duration() {
        return libTAU::total_milliseconds($self->finished_duration);
    }

    int64_t get_seeding_duration() {
        return libTAU::total_milliseconds($self->seeding_duration);
    }

    int get_queue_position()
    {
        return static_cast<int>($self->queue_position);
    }

    bitfield get_pieces()
    {
        auto* v = &$self->pieces;
        return *reinterpret_cast<libTAU::bitfield*>(v);
    }

    bitfield get_verified_pieces()
    {
        auto* v = &$self->verified_pieces;
        return *reinterpret_cast<libTAU::bitfield*>(v);
    }
}

}
