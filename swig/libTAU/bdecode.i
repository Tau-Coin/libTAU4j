%ignore libTAU::bdecode_node::bdecode_node(bdecode_node&&);
%ignore libTAU::bdecode_node::non_owning;
%ignore libTAU::bdecode_node::data_section;
%ignore libTAU::bdecode_node::list_string_value_at;
%ignore libTAU::bdecode_node::dict_find;
%ignore libTAU::bdecode_node::dict_find_dict;
%ignore libTAU::bdecode_node::dict_find_list;
%ignore libTAU::bdecode_node::dict_find_string;
%ignore libTAU::bdecode_node::dict_find_int;
%ignore libTAU::bdecode_node::dict_find_string_value;
%ignore libTAU::bdecode_node::dict_find_int_value;
%ignore libTAU::bdecode_node::dict_at;
%ignore libTAU::bdecode_node::string_value;
%ignore libTAU::bdecode_node::string_ptr;
%ignore libTAU::bdecode_node::swap;
%ignore libTAU::bdecode_node::reserve;
%ignore libTAU::bdecode_node::switch_underlying_buffer;
%ignore libTAU::bdecode_node::has_soft_error;
%ignore libTAU::bdecode_errors::make_error_code;
%ignore libTAU::bdecode;
%ignore libTAU::bdecode_category;
%ignore libTAU::parse_int;

%rename(bdecode_no_error) libTAU::bdecode_errors::no_error;
%rename(bdecode_errors) libTAU::bdecode_errors::error_code_enum;

%include "libTAU/bdecode.hpp"

namespace libTAU {

%extend bdecode_node {

    std::string list_string_value_at_ex(int i, std::string default_val = "")
    {
        return std::string{$self->list_string_value_at(i, default_val)};
    }

    bdecode_node dict_find_ex(std::string key) const
    {
        return $self->dict_find(key);
    }

    bdecode_node dict_find_dict_ex(std::string key) const {
        return $self->dict_find_dict(key);
    }

    bdecode_node dict_find_list_ex(std::string key) const {
        return $self->dict_find_list(key);
    }

    bdecode_node dict_find_string_ex(std::string key) const {
        return $self->dict_find_string(key);
    }

    bdecode_node dict_find_int_ex(std::string key) const {
        return $self->dict_find_int(key);
    }

    std::string dict_find_string_value_ex(std::string key, std::string default_value = "") const
    {
        return std::string{$self->dict_find_string_value(key, default_value)};
    }

    std::int64_t dict_find_int_value_ex(std::string key, std::int64_t default_val = 0) const {
        return $self->dict_find_int_value(key, default_val);
    }

    std::string string_value_ex() const
    {
        return std::string{$self->string_value()};
    }

    static std::string to_string(bdecode_node const& e, bool single_line, int indent) {
        return libTAU::print_entry(e, single_line, indent);
    }

    static int bdecode(std::vector<std::int8_t>& buffer, bdecode_node& ret, error_code& ec) {
        return libTAU::bdecode((char const*)&buffer[0], (char const*)&buffer[0] + buffer.size(), ret, ec);
    }
}

}
