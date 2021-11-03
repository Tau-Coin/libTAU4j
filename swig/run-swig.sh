#!/bin/bash
# NOTE: Run this script manually every time you make changes to libTAU.i, this is not ran by any of the build scripts, including .travis.yml

#abort_if_var_unset "LIBTORRENT_ROOT" ${LIBTORRENT_ROOT}
#abort_if_var_unset "BOOST_ROOT" ${BOOST_ROOT}

# Extracted from ../build.gradle
JLIBTORRENT_VERSION=`sed -n -e '/^version /s/.* //p' ../build.gradle.kts | tr -d "'"`
#abort_if_var_unset "JLIBTORRENT_VERSION" ${JLIBTORRENT_VERSION}

function fixCode() {
    uname=`uname -s`
    if [ "$(uname)" == "Darwin" ]; then
      # FreeBSD's sed is weird, it needs that extra '' parameter there for some reason
      sed -i '' 's/constexpr alert_category_t all = alert_category_t::all();/ \/\/deleted temporarily because it is defined twice/g' ${LIBTORRENT_ROOT}/include/libTAU/alert.hpp
      # The line above will be reverted to normal when we build by the checkout, if not commented/deleted swig breaks
      sed -i '' 's/) &;/)  ;/g' ${LIBTORRENT_ROOT}/include/libTAU/aux_/hasher512.hpp
      sed -i '' 's/) &;/)  ;/g' ${LIBTORRENT_ROOT}/include/libTAU/aux_/announce_entry.hpp
      sed -i '' 's/) &;/)  ;/g' ${LIBTORRENT_ROOT}/include/libTAU/session.hpp
      sed -i '' 's/) &;/)  ;/g' ${LIBTORRENT_ROOT}/include/libTAU/bdecode.hpp
      sed -i '' 's/) &;/)  ;/g' ${LIBTORRENT_ROOT}/include/libTAU/session_params.hpp
      sed -i '' 's/) &;/)  ;/g' ${LIBTORRENT_ROOT}/include/libTAU/hasher.hpp
      sed -i '' 's/) &;/)  ;/g' ${LIBTORRENT_ROOT}/include/libTAU/entry.hpp
      sed -i '' 's/) &;/)  ;/g' ${LIBTORRENT_ROOT}/include/libTAU/upnp.hpp
      sed -i '' 's/) &;/)  ;/g' ${LIBTORRENT_ROOT}/include/libTAU/announce_entry.hpp

      sed -i '' 's/) & = default;/)   = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/aux_/stack_allocator.hpp
      sed -i '' 's/) & = default;/)   = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/bdecode.hpp
      sed -i '' 's/) & = default;/)   = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/session_handle.hpp

      sed -i ''  's/) & noexcept = default;/)   noexcept = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/aux_/stack_allocator.hpp
      sed -i ''  's/) & noexcept = default;/)   noexcept = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/aux_/scope_end.hpp
      sed -i ''  's/) & noexcept = default;/)   noexcept = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/aux_/copy_ptr.hpp
      sed -i ''  's/) & noexcept = default;/)   noexcept = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/info_hash.hpp
      sed -i ''  's/) & noexcept = default;/)   noexcept = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/units.hpp
      sed -i ''  's/) & noexcept = default;/)   noexcept = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/flags.hpp
      sed -i ''  's/) & noexcept = default;/)   noexcept = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/session_handle.hpp
      sed -i ''  's/) & noexcept = default;/)   noexcept = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/bitfield.hpp

      sed -i '' 's/) & noexcept;/)   noexcept;/g' ${LIBTORRENT_ROOT}/include/libTAU/session.hpp
      sed -i '' 's/) & noexcept;/)   noexcept;/g' ${LIBTORRENT_ROOT}/include/libTAU/sha1_hash.hpp
      sed -i '' 's/) & noexcept;/)   noexcept;/g' ${LIBTORRENT_ROOT}/include/libTAU/disk_buffer_holder.hpp

      sed -i '' 's/(time_point32::min)();/time_point32::min();/g' ${LIBTORRENT_ROOT}/include/libTAU/announce_entry.hpp

      sed -i '' 's/) & TORRENT_COUNTER_NOEXCEPT;/)   TORRENT_COUNTER_NOEXCEPT;/g' ${LIBTORRENT_ROOT}/include/libTAU/performance_counters.hpp
    else
      sed -i 's/constexpr alert_category_t all = alert_category_t::all();/ \/\/deleted temporarily because it is defined twice/g' ${LIBTORRENT_ROOT}/include/libTAU/alert.hpp
      sed -i 's/) &;/)  ;/g' ${LIBTORRENT_ROOT}/include/libTAU/aux_/hasher512.hpp
      sed -i 's/) &;/)  ;/g' ${LIBTORRENT_ROOT}/include/libTAU/aux_/announce_entry.hpp
      sed -i 's/) &;/)  ;/g' ${LIBTORRENT_ROOT}/include/libTAU/session.hpp
      sed -i 's/) &/)  /g' ${LIBTORRENT_ROOT}/include/libTAU/kademlia/types.hpp
      sed -i 's/) &;/)  ;/g' ${LIBTORRENT_ROOT}/include/libTAU/bdecode.hpp
      sed -i 's/) &;/)  ;/g' ${LIBTORRENT_ROOT}/include/libTAU/session_params.hpp
      sed -i 's/) &;/)  ;/g' ${LIBTORRENT_ROOT}/include/libTAU/hasher.hpp
      sed -i 's/) &;/)  ;/g' ${LIBTORRENT_ROOT}/include/libTAU/entry.hpp
      sed -i 's/) &;/)  ;/g' ${LIBTORRENT_ROOT}/include/libTAU/upnp.hpp
      sed -i 's/) &;/)  ;/g' ${LIBTORRENT_ROOT}/include/libTAU/announce_entry.hpp

      sed -i 's/) & = default;/)   = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/aux_/stack_allocator.hpp
      sed -i 's/) & = default;/)   = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/bdecode.hpp
      sed -i  's/) & = default;/)   = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/session_handle.hpp

      sed -i  's/) & noexcept = default;/)   noexcept = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/aux_/stack_allocator.hpp
      sed -i  's/) & noexcept = default;/)   noexcept = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/aux_/scope_end.hpp
      sed -i  's/) & noexcept = default;/)   noexcept = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/aux_/copy_ptr.hpp
      sed -i  's/) & noexcept = default;/)   noexcept = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/info_hash.hpp
      sed -i  's/) & noexcept = default;/)   noexcept = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/units.hpp
      sed -i  's/) & noexcept = default;/)   noexcept = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/flags.hpp
      sed -i  's/) & noexcept = default;/)   noexcept = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/session_handle.hpp
      sed -i  's/) & noexcept = default;/)   noexcept = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/bitfield.hpp

      sed -i  's/) & noexcept;/)   noexcept;/g' ${LIBTORRENT_ROOT}/include/libTAU/session.hpp
      sed -i  's/) & noexcept;/)   noexcept;/g' ${LIBTORRENT_ROOT}/include/libTAU/sha1_hash.hpp
      sed -i  's/) & noexcept;/)   noexcept;/g' ${LIBTORRENT_ROOT}/include/libTAU/disk_buffer_holder.hpp

      sed -i 's/(time_point32::min)();/time_point32::min();/g' ${LIBTORRENT_ROOT}/include/libTAU/announce_entry.hpp
      sed -i 's/) & TORRENT_COUNTER_NOEXCEPT;/)   TORRENT_COUNTER_NOEXCEPT;/g' ${LIBTORRENT_ROOT}/include/libTAU/performance_counters.hpp
    fi
}

function refixCode() {
    uname=`uname -s`    
    if [ "$(uname)" == "Darwin" ]; then  
    sed -i '' 's/ \/\/deleted temporarily because it is defined twice/constexpr alert_category_t all = alert_category_t::all();/g' ${LIBTORRENT_ROOT}/include/libTAU/alert.hpp
 
      sed -i '' 's/)  ;/) \&;/g' ${LIBTORRENT_ROOT}/include/libTAU/aux_/hasher512.hpp
      sed -i '' 's/)  ;/) \&;/g' ${LIBTORRENT_ROOT}/include/libTAU/aux_/announce_entry.hpp
      sed -i '' 's/)  ;/) \&;/g' ${LIBTORRENT_ROOT}/include/libTAU/session.hpp
      sed -i '' 's/)  ;/) \&;/g' ${LIBTORRENT_ROOT}/include/libTAU/bdecode.hpp
      sed -i '' 's/)  ;/) \&;/g' ${LIBTORRENT_ROOT}/include/libTAU/session_params.hpp
      sed -i '' 's/)  ;/) \&;/g' ${LIBTORRENT_ROOT}/include/libTAU/hasher.hpp
      sed -i '' 's/)  ;/) \&;/g' ${LIBTORRENT_ROOT}/include/libTAU/entry.hpp
      sed -i '' 's/)  ;/) \&;/g' ${LIBTORRENT_ROOT}/include/libTAU/upnp.hpp
      sed -i '' 's/)  ;/) \&;/g' ${LIBTORRENT_ROOT}/include/libTAU/announce_entry.hpp

      sed -i '' 's/)   = default;/) \& = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/aux_/stack_allocator.hpp
      sed -i '' 's/)   = default;/) \& = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/bdecode.hpp
      sed -i '' 's/)   = default;/) \& = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/session_handle.hpp

      sed -i ''  's/)   noexcept = default;/) \& noexcept = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/aux_/stack_allocator.hpp
      sed -i ''  's/)   noexcept = default;/) \& noexcept = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/aux_/scope_end.hpp
      sed -i ''  's/)   noexcept = default;/) \& noexcept = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/aux_/copy_ptr.hpp
      sed -i ''  's/)   noexcept = default;/) \& noexcept = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/info_hash.hpp
      sed -i ''  's/)   noexcept = default;/) \& noexcept = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/units.hpp
      sed -i ''  's/)   noexcept = default;/) \& noexcept = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/flags.hpp
      sed -i ''  's/)   noexcept = default;/) \& noexcept = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/session_handle.hpp
      sed -i ''  's/)   noexcept = default;/) \& noexcept = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/bitfield.hpp

      sed -i '' 's/)   noexcept;/) \& noexcept;/g' ${LIBTORRENT_ROOT}/include/libTAU/session.hpp
      sed -i '' 's/)   noexcept;/) \& noexcept;/g' ${LIBTORRENT_ROOT}/include/libTAU/sha1_hash.hpp
      sed -i '' 's/)   noexcept;/) \& noexcept;/g' ${LIBTORRENT_ROOT}/include/libTAU/disk_buffer_holder.hpp

      sed -i '' 's/time_point32::min();/(time_point32::min)();/g' ${LIBTORRENT_ROOT}/include/libTAU/announce_entry.hpp
      sed -i '' 's/)   TORRENT_COUNTER_NOEXCEPT;/) \& TORRENT_COUNTER_NOEXCEPT;/g' ${LIBTORRENT_ROOT}/include/libTAU/performance_counters.hpp 
    else
      sed -i 's/ \/\/deleted temporarily because it is defined twice/constexpr alert_category_t all = alert_category_t::all();/g' ${LIBTORRENT_ROOT}/include/libTAU/alert.hpp

      sed -i 's/)  ;/) \&;/g' ${LIBTORRENT_ROOT}/include/libTAU/aux_/hasher512.hpp
      sed -i 's/)  ;/) \&;/g' ${LIBTORRENT_ROOT}/include/libTAU/aux_/announce_entry.hpp
      sed -i 's/)  ;/) \&;/g' ${LIBTORRENT_ROOT}/include/libTAU/session.hpp
      sed -i 's/)  /) \&/g' ${LIBTORRENT_ROOT}/include/libTAU/kademlia/types.hpp
      sed -i 's/)  ;/) \&;/g' ${LIBTORRENT_ROOT}/include/libTAU/bdecode.hpp
      sed -i 's/)  ;/) \&;/g' ${LIBTORRENT_ROOT}/include/libTAU/session_params.hpp
      sed -i 's/)  ;/) \&;/g' ${LIBTORRENT_ROOT}/include/libTAU/hasher.hpp
      sed -i 's/)  ;/) \&;/g' ${LIBTORRENT_ROOT}/include/libTAU/entry.hpp
      sed -i 's/)  ;/) \&;/g' ${LIBTORRENT_ROOT}/include/libTAU/upnp.hpp
      sed -i 's/)  ;/) \&;/g' ${LIBTORRENT_ROOT}/include/libTAU/announce_entry.hpp

      sed -i 's/)   = default;/) \& = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/aux_/stack_allocator.hpp
      sed -i 's/)   = default;/) \& = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/bdecode.hpp
      sed -i 's/)   = default;/) \& = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/session_handle.hpp

      sed -i   's/)   noexcept = default;/) \& noexcept = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/aux_/stack_allocator.hpp
      sed -i   's/)   noexcept = default;/) \& noexcept = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/aux_/scope_end.hpp
      sed -i   's/)   noexcept = default;/) \& noexcept = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/aux_/copy_ptr.hpp
      sed -i   's/)   noexcept = default;/) \& noexcept = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/info_hash.hpp
      sed -i   's/)   noexcept = default;/) \& noexcept = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/units.hpp
      sed -i   's/)   noexcept = default;/) \& noexcept = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/flags.hpp
      sed -i   's/)   noexcept = default;/) \& noexcept = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/session_handle.hpp
      sed -i   's/)   noexcept = default;/) \& noexcept = default;/g' ${LIBTORRENT_ROOT}/include/libTAU/bitfield.hpp

      sed -i 's/)   noexcept;/) \& noexcept;/g' ${LIBTORRENT_ROOT}/include/libTAU/session.hpp
      sed -i 's/)   noexcept;/) \& noexcept;/g' ${LIBTORRENT_ROOT}/include/libTAU/sha1_hash.hpp
      sed -i 's/)   noexcept;/) \& noexcept;/g' ${LIBTORRENT_ROOT}/include/libTAU/disk_buffer_holder.hpp

      sed -i 's/time_point32::min();/(time_point32::min)();/g' ${LIBTORRENT_ROOT}/include/libTAU/announce_entry.hpp
      sed -i 's/)   TORRENT_COUNTER_NOEXCEPT;/) \& TORRENT_COUNTER_NOEXCEPT;/g' ${LIBTORRENT_ROOT}/include/libTAU/performance_counters.hpp
    fi
}

function runJni()
{
    JAVA_SRC_OUTPUT=../src/main/java/org/libTAU4j/swig
    rm -rf ${JAVA_SRC_OUTPUT}
    mkdir -p ${JAVA_SRC_OUTPUT}

    # If you're running from Ubuntu and you have swig 3.0.5 you might end up with a segfault running the swig command below
    #./run-swig.sh: line 33: 46204 Segmentation fault
    # Make sure your swig command is compiled from source, version 3.0.12 works on mac
    # https://sourceforge.net/projects/swig/files/swig/swig-3.0.12/swig-3.0.12.tar.gz/download
    
    swig -c++ -java -o libTAU_jni.cpp \
        -outdir ${JAVA_SRC_OUTPUT} \
        -package org.libTAU4j.swig \
        -I${BOOST_ROOT} \
        -I${LIBTORRENT_ROOT}/include \
        -DBOOST_ASIO_DECL="" \
        -DBOOST_NO_TYPEID=1 \
        -DBOOST_NO_EXCEPTIONS \
        -DBOOST_POSIX_API=1 \
        -DBOOST_SYSTEM_CONSTEXPR="" \
        -DBOOST_SYSTEM_NOEXCEPT="" \
        -DBOOST_SYSTEM_DECL="" \
        -DBOOST_SYSTEM_NO_DEPRECATED=1 \
        -DBOOST_NO_IOSTREAM \
        -DBOOST_SYMBOL_VISIBLE \
        -DBOOST_NOEXCEPT="" \
        -DBOOST_NOEXCEPT_OR_NOTHROW="" \
        -DTORRENT_ABI_VERSION=4 \
        -DTORRENT_VERSION_NAMESPACE_2="" \
        -DTORRENT_VERSION_NAMESPACE_2_END="" \
        -DTORRENT_VERSION_NAMESPACE_3="" \
        -DTORRENT_VERSION_NAMESPACE_3_END="" \
        -DTORRENT_VERSION_NAMESPACE_4="" \
        -DTORRENT_VERSION_NAMESPACE_4_END="" \
        -DTORRENT_IPV6_NAMESPACE="" \
        -DTORRENT_IPV6_NAMESPACE_END="" \
        -DTORRENT_CFG="TORRENT_CFG" \
        -DTORRENT_NO_DEPRECATE \
        -DTORRENT_DEPRECATED_EXPORT="" \
        -DTORRENT_DEPRECATED_MEMBER="" \
        -DTORRENT_DEPRECATED_ENUM="" \
        -DTORRENT_DEPRECATED \
        -DTORRENT_EXPORT="" \
		-DTORRENT_UNEXPORT \
        -DTORRENT_EXTRA_EXPORT="" \
        -DTORRENT_FORMAT\(x,y\)="" \
        -DNDEBUG=1 \
        -D_bit="" \
        -Dfinal="" \
        libTAU.i

    # at first sight, this could look like a very dangerous thing to
    # do, but in practice, these director types are controlled by us
    # and we know we can do it. The main reason is to be able to
    # compile with -fno-rtti.
    uname=`uname -s`
    if [ "$(uname)" == "Darwin" ]; then    
	sed -i '' 's/dynamic_cast<SwigDirector_/static_cast<SwigDirector_/g' libTAU_jni.cpp
    else
	sed -i 's/dynamic_cast<SwigDirector_/static_cast<SwigDirector_/g' libTAU_jni.cpp	
    fi
    
    # replace jlibTAU version
    #uname=`uname -s`
    if [ "$(uname)" == "Darwin" ]; then
	sed -i '' 's/\$JLIBTORRENT_VERSION\$/'"${JLIBTORRENT_VERSION}"'/g' ../src/main/java/org/libTAU4j/swig/libTAU_jni.java
    else
	sed -i 's/\$JLIBTORRENT_VERSION\$/'"${JLIBTORRENT_VERSION}"'/g' ../src/main/java/org/libTAU4j/swig/libTAU_jni.java	
    fi
}

fixCode
runJni
refixCode
