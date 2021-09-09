%ignore libTAU::add_torrent_params::file_priorities;
%ignore libTAU::add_torrent_params::piece_priorities;
%ignore libTAU::add_torrent_params::merkle_trees;
%ignore libTAU::add_torrent_params::merkle_tree_mask;
%ignore libTAU::add_torrent_params::verified_leaf_hashes;
%ignore libTAU::add_torrent_params::unfinished_pieces;
%ignore libTAU::add_torrent_params::renamed_files;
%ignore libTAU::add_torrent_params::have_pieces;
%ignore libTAU::add_torrent_params::verified_pieces;
%ignore libTAU::add_torrent_params::extensions;

%include "libTAU/add_torrent_params.hpp"

namespace libTAU {

%extend add_torrent_params
{
    std::vector<std::int8_t> get_file_priorities()
    {
        auto* v = &$self->file_priorities;
        return *reinterpret_cast<std::vector<std::int8_t>*>(v);
    }

    void set_file_priorities(std::vector<std::int8_t>& v)
    {
        auto* t = reinterpret_cast<std::vector<libTAU::download_priority_t>*>(&v);
        $self->file_priorities = *t;
    }

    std::vector<std::int8_t> get_piece_priorities()
    {
        auto* v = &$self->piece_priorities;
        return *reinterpret_cast<std::vector<std::int8_t>*>(v);
    }

    void set_piece_priorities(std::vector<std::int8_t>& v)
    {
        auto* t = reinterpret_cast<std::vector<libTAU::download_priority_t>*>(&v);
        $self->piece_priorities = *t;
    }

    std::vector<std::vector<libTAU::sha256_hash>> get_merkle_trees()
    {
        auto* v = &$self->merkle_trees;
        return *reinterpret_cast<std::vector<std::vector<libTAU::sha256_hash>>*>(v);
    }

    void set_merkle_trees(std::vector<std::vector<libTAU::sha256_hash>>& v)
    {
        auto* t = reinterpret_cast<libTAU::aux::vector<std::vector<libTAU::sha256_hash>, libTAU::file_index_t>*>(&v);
        $self->merkle_trees = *t;
    }

    std::vector<std::vector<bool>> get_merkle_tree_mask()
    {
        auto* v = &$self->merkle_tree_mask;
        return *reinterpret_cast<std::vector<std::vector<bool>>*>(v);
    }

    void set_merkle_tree_mask(std::vector<std::vector<bool>>& v)
    {
        auto* t = reinterpret_cast<lt::aux::vector<std::vector<bool>, libTAU::file_index_t>*>(&v);
        $self->merkle_tree_mask = *t;
    }

    std::vector<std::vector<bool>> get_verified_leaf_hashes()
    {
        auto* v = &$self->verified_leaf_hashes;
        return *reinterpret_cast<std::vector<std::vector<bool>>*>(v);
    }

    void set_verified_leaf_hashes(std::vector<std::vector<bool>>& v)
    {
        auto* t = reinterpret_cast<libTAU::aux::vector<std::vector<bool>, libTAU::file_index_t>*>(&v);
        $self->verified_leaf_hashes = *t;
    }

    std::map<int, bitfield> get_unfinished_pieces()
    {
        auto* v = &$self->unfinished_pieces;
        return *reinterpret_cast<std::map<int, libTAU::bitfield>*>(v);
    }

    void set_unfinished_pieces(std::map<int, bitfield>& v)
    {
        auto* t = reinterpret_cast<std::map<libTAU::piece_index_t, libTAU::bitfield>*>(&v);
        $self->unfinished_pieces = *t;
    }

    std::map<int, std::string> get_renamed_files()
    {
        auto* v = &$self->renamed_files;
        return *reinterpret_cast<std::map<int, std::string>*>(v);
    }

    void set_renamed_files(std::map<int, std::string>& v)
    {
        auto* t = reinterpret_cast<std::map<libTAU::file_index_t, std::string>*>(&v);
        $self->renamed_files = *t;
    }

    bitfield get_have_pieces()
    {
        auto* v = &$self->have_pieces;
        return *reinterpret_cast<libTAU::bitfield*>(v);
    }

    void set_have_pieces(bitfield& v)
    {
        auto* t = reinterpret_cast<libTAU::typed_bitfield<libTAU::piece_index_t>*>(&v);
        $self->have_pieces = *t;
    }

    bitfield get_verified_pieces()
    {
        auto* v = &$self->verified_pieces;
        return *reinterpret_cast<libTAU::bitfield*>(v);
    }

    void set_verified_pieces(bitfield& v)
    {
        auto* t = reinterpret_cast<libTAU::typed_bitfield<libTAU::piece_index_t>*>(&v);
        $self->verified_pieces = *t;
    }
}

}
