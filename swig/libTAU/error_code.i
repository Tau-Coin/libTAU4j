%ignore libTAU::errors::deprecated_120;
%ignore libTAU::errors::deprecated_121;
%ignore libTAU::errors::deprecated_122;
%ignore libTAU::errors::deprecated_123;
%ignore libTAU::errors::deprecated_124;
%ignore libTAU::errors::make_error_code;
%ignore libTAU::http_category;
%ignore libTAU::libTAU_category;
%ignore libTAU::print_error;

%rename(libTAU_no_error) libTAU::errors::no_error;
%rename(libTAU_errors) libTAU::errors::error_code_enum;

%include "libTAU/error_code.hpp"
