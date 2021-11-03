%module (jniclassname="libTAU_jni", directors="1") libTAU

// Overloaded method <name> ignored, using <name> instead.
#pragma SWIG nowarn=516
// Specialization of non-template '<name>'.
#pragma SWIG nowarn=317

%pragma(java) jniclasscode=%{

    public static String libTAUVersion() {
        return "0.0.0";
    }

    static {
        try {
            String path = System.getProperty("libTAU4j.jni.path", "");
            if ("".equals(path)) {
                System.loadLibrary("TAU4j-" + libTAUVersion());
            } else {
                System.load("TAU4j");
            }
        } catch (LinkageError e) {
            throw new LinkageError(
                "Look for your architecture binary instructions at: https://github.com/tau-coin/libTAU4j", e);
        }
    }

    public static final native long directBufferAddress(java.nio.Buffer buffer);
    public static final native long directBufferCapacity(java.nio.Buffer buffer);
%}

%exception {
    try {
        $action
    } catch (std::exception& e) {
        SWIG_JavaThrowException(jenv, SWIG_JavaRuntimeException, e.what());
        return $null;
    } catch (...) {
        SWIG_JavaThrowException(jenv, SWIG_JavaRuntimeException, "Unknown exception type");
        return $null;
    }
}

%{
// BEGIN common set include ----------------------------------------------------

#include "libTAU/flags.hpp"
#include "libTAU/address.hpp"
#include "libTAU/socket.hpp"
#include "libTAU/kademlia/dht_state.hpp"
#include "libTAU/kademlia/types.hpp"
#include "libTAU/communication/message.hpp"
#include "libTAU/blockchain/account.hpp"
#include "libTAU/blockchain/vote.hpp"
#include "libTAU/blockchain/transaction.hpp"
#include "libTAU/blockchain/block.hpp"
#include "libTAU/client_data.hpp"
#include "libTAU/sha1_hash.hpp"
#include "libTAU/info_hash.hpp"
#include "libTAU/bitfield.hpp"
#include "libTAU/operations.hpp"
#include "libTAU/error_code.hpp"
#include "libTAU/announce_entry.hpp"
#include "libTAU/peer_request.hpp"
#include "libTAU/bdecode.hpp"
#include "libTAU/close_reason.hpp"
#include "libTAU/peer_info.hpp"
#include "libTAU/pex_flags.hpp"
#include "libTAU/performance_counters.hpp"
#include "libTAU/portmap.hpp"
#include "libTAU/socket_type.hpp"
#include "libTAU/entry.hpp"
#include "libTAU/tracker_event.hpp"
#include "libTAU/alert.hpp"
#include "libTAU/alert_types.hpp"
#include "libTAU/settings_pack.hpp"
#include "libTAU/peer_class.hpp"
#include "libTAU/peer_class_type_filter.hpp"
#include "libTAU/ip_filter.hpp"
#include "libTAU/session_types.hpp"
#include "libTAU/session_params.hpp"
#include "libTAU/session_handle.hpp"
#include "libTAU/session.hpp"
#include "libTAU/session_stats.hpp"
#include "libTAU/version.hpp"
#include "libTAU/fingerprint.hpp"

#include <libTAU/hex.hpp>
#include <libTAU/bencode.hpp>

namespace lt = libTAU;

using piece_index_t = libTAU::piece_index_t;
using file_index_t = libTAU::file_index_t;

#include "libTAU.hpp"

template <typename IndexType>
using typed_bitfield = libTAU::typed_bitfield<IndexType>;

// END common set include ------------------------------------------------------
%}

// direct buffer code
%{
#ifdef __cplusplus
extern "C" {
#endif

SWIGEXPORT jlong JNICALL Java_org_libTAU4j_swig_libTAU_1jni_directBufferAddress(JNIEnv *jenv, jclass jcls, jobject jbuf) {
    try {
        return jlong(jenv->GetDirectBufferAddress(jbuf));
    } catch (std::exception& e) {
      SWIG_JavaThrowException(jenv, SWIG_JavaRuntimeException, e.what());
    } catch (...) {
      SWIG_JavaThrowException(jenv, SWIG_JavaRuntimeException, "Unknown exception type");
    }

    return 0;
}

SWIGEXPORT jlong JNICALL Java_org_libTAU4j_swig_libTAU_1jni_directBufferCapacity(JNIEnv *jenv, jclass jcls, jobject jbuf) {
    try {
        return jlong(jenv->GetDirectBufferCapacity(jbuf));
    } catch (std::exception& e) {
      SWIG_JavaThrowException(jenv, SWIG_JavaRuntimeException, e.what());
    } catch (...) {
      SWIG_JavaThrowException(jenv, SWIG_JavaRuntimeException, "Unknown exception type");
    }

    return 0;
}

#ifdef __cplusplus
}
#endif
%}

%include <stdint.i>
%include <typemaps.i>
%include <std_string.i>
%include <std_pair.i>
%include <std_vector.i>
%include <std_map.i>
%include <std_array.i>
%include <std_bitset.i>

%include "boost/boost_map.i"

%apply std::int8_t { char };
%apply std::int64_t { void* };
%apply std::int64_t { std::ptrdiff_t };
%apply std::int64_t { std::time_t };

%define TYPE_INTEGRAL_CONVERSION_EX(name, underlying_type, api_type, java_type)
%typemap(jni) name, const name& "java_type"
%typemap(jtype) name, const name& "java_type"
%typemap(jstype) name, const name& "java_type"

%typemap(in) name {
    $1 = name(static_cast<underlying_type>($input));
}
%typemap(out) name {
    $result = static_cast<api_type>(static_cast<underlying_type>($1));
}
%typemap(javain) name, const name& "$javainput"
%typemap(javaout) name, const name& {
    return $jnicall;
  }
%enddef

%define TYPE_INTEGRAL_CONVERSION(name, underlying_type, java_type)
TYPE_INTEGRAL_CONVERSION_EX(name, underlying_type, underlying_type, java_type)
%enddef

TYPE_INTEGRAL_CONVERSION(piece_index_t, std::int32_t, int)
TYPE_INTEGRAL_CONVERSION(file_index_t, std::int32_t, int)

// template definitions
%template(int_byte_pair) std::pair<int, std::int8_t>;
%template(string_int_pair) std::pair<std::string, int>;
%template(string_string_pair) std::pair<std::string, std::string>;
%template(byte_vector_byte_vector_pair) std::pair<std::vector<std::int8_t>, std::vector<std::int8_t>>;
%template(sha1_hash_udp_endpoint_pair) std::pair<libTAU::digest32<160>, libTAU::udp::endpoint>;
%template(sha256_hash_udp_endpoint_pair) std::pair<libTAU::digest32<256>, libTAU::udp::endpoint>;
%template(bdecode_node_bdecode_node_pair) std::pair<libTAU::bdecode_node, libTAU::bdecode_node>;
%template(address_sha160_hash_pair) std::pair<libTAU::address, libTAU::digest32<160>>;
%template(address_sha256_hash_pair) std::pair<libTAU::address, libTAU::digest32<256>>;

%template(string_vector) std::vector<std::string>;
%template(int_vector) std::vector<int>;
%template(int64_vector) std::vector<long long>;
%template(byte_vector) std::vector<std::int8_t>;
%template(bool_vector) std::vector<bool>;
%template(int_byte_pair_vector) std::vector<std::pair<int, std::int8_t>>;
%template(string_int_pair_vector) std::vector<std::pair<std::string, int>>;
%template(string_string_pair_vector) std::vector<std::pair<std::string, std::string>>;

%template(tcp_endpoint_vector) std::vector<libTAU::tcp::endpoint>;
%template(udp_endpoint_vector) std::vector<libTAU::udp::endpoint>;
%template(announce_endpoint_vector) std::vector<libTAU::announce_endpoint>;
%template(announce_entry_vector) std::vector<libTAU::announce_entry>;
%template(dht_lookup_vector) std::vector<libTAU::dht_lookup>;
%template(dht_routing_bucket_vector) std::vector<libTAU::dht_routing_bucket>;
%template(entry_vector) std::vector<libTAU::entry>;
%template(peer_info_vector) std::vector<libTAU::peer_info>;
%template(alert_ptr_vector) std::vector<libTAU::alert*>;
%template(stats_metric_vector) std::vector<libTAU::stats_metric>;
%template(ip_interface_vector) std::vector<ip_interface>;
%template(ip_route_vector) std::vector<ip_route>;

%template(bool_vector_vector) std::vector<std::vector<bool>>;
%template(sha256_hash_vector_vector) std::vector<std::vector<libTAU::digest32<256>>>;
%template(sha256_hash_udp_endpoint_pair_vector) std::vector<std::pair<libTAU::digest32<256>, libTAU::udp::endpoint>>;
%template(address_sha256_hash_pair_vector) std::vector<std::pair<libTAU::address, libTAU::digest32<256>>>;
%template(block_vector) std::vector<libTAU::blockchain::block>;
%template(block_vote_vector) std::vector<libTAU::blockchain::vote>;

%template(byte_array_32) std::array<std::int8_t, 32>;
%template(byte_array_64) std::array<std::int8_t, 64>;
%template(vector_byte_array_32) std::vector<std::array<std::int8_t, 32>>;
%template(unsigned_byte_array_32) std::array<std::uint8_t, 32>;

%template(int_string_map) std::map<int, std::string>;
%template(string_string_map) std::map<std::string, std::string>;
%template(int_bitfield_map) std::map<int, libTAU::bitfield>;
%template(string_entry_map) std::map<std::string, libTAU::entry>;
%template(pubkey_account_map) std::map<libTAU::dht::public_key, libTAU::blockchain::account>;

%template(boost_string_entry_map) boost::container::map<std::string, libTAU::entry>;

%template(bitset_128) std::bitset<128>;

// ignore of operators
%ignore operator=;
%ignore operator^=;
%ignore operator&=;
%ignore operator|=;
%ignore operator<<=;
%ignore operator>>=;

// rename of operators
%rename(eq) operator==;
%rename(ne) operator!=;
%rename(lt) operator<;
%rename(gt) operator>;
%rename(lte) operator<=;
%rename(gte) operator>=;
%rename(inv) operator~;
%rename(xor) operator^;
%rename(or_) operator|;
%rename(and_) operator&;
%rename(at) operator[];
%rename(to_bool) operator bool;

// general ignores
%ignore libTAU::aux;
%ignore mem_copy;

// directors
%feature("director") alert_notify_callback;
%feature("director") add_files_listener;
%feature("director") set_piece_hashes_listener;

// includes
%include "boost/system/error_code.i"

%include "libTAU/span.i"
%include "libTAU/bytes.i"
%include "libTAU/flags.i"
%include "libTAU/address.i"
%include "libTAU/socket.i"
%include "libTAU/kademlia/dht_state.i"
%include "libTAU/kademlia/types.i"
%include "libTAU/client_data.i"
%include "libTAU/sha1_hash.i"
%include "libTAU/info_hash.i"
%include "libTAU/bitfield.i"
%include "libTAU/operations.i"
%include "libTAU/error_code.i"
%include "libTAU/announce_entry.i"
%include "libTAU/peer_request.i"
%include "libTAU/bdecode.i"
%include "libTAU/close_reason.i"
%include "libTAU/peer_info.i"
%include "libTAU/pex_flags.i"
%include "libTAU/performance_counters.i"
%include "libTAU/portmap.i"
%include "libTAU/socket_type.i"
%include "libTAU/entry.i"
%include "libTAU/tracker_event.i"
%include "libTAU/alert.i"
%include "libTAU/alert_types.i"
%include "libTAU/settings_pack.i"
%include "libTAU/peer_class.i"
%include "libTAU/peer_class_type_filter.i"
%include "libTAU/ip_filter.i"
%include "libTAU/communication/message.i"
%include "libTAU/blockchain/account.i"
%include "libTAU/blockchain/vote.i"
%include "libTAU/blockchain/transaction.i"
%include "libTAU/blockchain/block.i"
%include "libTAU/session_types.i"
%include "libTAU/session_params.i"
%include "libTAU/session_handle.i"
%include "libTAU/session.i"
%include "libTAU/session_stats.i"
%include "libTAU/version.i"
%include "libTAU/fingerprint.i"

// for libTAU.hpp
%ignore set_piece_hashes_listener::progress_index;
%ignore dht_put_item_cb;

%include "libTAU.hpp"
