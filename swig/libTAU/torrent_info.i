%ignore libTAU::from_span;
%ignore libTAU::from_span_t;
%ignore libTAU::torrent_info::torrent_info(char const*, int, error_code&);
%ignore libTAU::torrent_info::torrent_info(char const*, int, error_code&, int);
%ignore libTAU::torrent_info::torrent_info(span<char const>, error_code&, from_span_t);
%ignore libTAU::torrent_info::metadata;
%ignore libTAU::torrent_info::load;
%ignore libTAU::torrent_info::unload;
%ignore libTAU::torrent_info::hash_for_piece_ptr;
%ignore libTAU::torrent_info::parse_info_section;
%ignore libTAU::torrent_info::info_section;
%ignore libTAU::torrent_info::swap;
%ignore libTAU::torrent_info::add_merkle_nodes;
%ignore libTAU::torrent_info::build_merkle_list;
%ignore libTAU::torrent_info::parse_torrent_file;
%ignore libTAU::torrent_info::piece_range;
%ignore libTAU::torrent_info::internal_set_creator;
%ignore libTAU::torrent_info::internal_set_creation_date;
%ignore libTAU::torrent_info::internal_set_comment;
%ignore libTAU::torrent_info::ssl_cert;
%ignore libTAU::torrent_info::internal_merkle_trees;
%ignore libTAU::torrent_info::internal_load_merkle_trees;
%ignore libTAU::torrent_info::piece_layer;
%ignore libTAU::torrent_info::v2_piece_hashes_verified;
 %ignore libTAU::torrent_info::set_piece_layers;
%ignore libTAU::aux::sanitize_append_path_element;
%ignore libTAU::aux::verify_encoding;

%include "libTAU/torrent_info.hpp"

namespace libTAU {

%extend torrent_info
{
    torrent_info(std::int64_t buffer_ptr, int size, error_code& ec)
    {
        return new libTAU::torrent_info(reinterpret_cast<char const*>(buffer_ptr), size, ec);
    }

    libTAU::span<std::int8_t const> get_info_section()
    {
        auto v = $self->info_section();
        return libTAU::span<std::int8_t const>({reinterpret_cast<std::int8_t const*>(v.data()), v.size()});
    }
};

}
