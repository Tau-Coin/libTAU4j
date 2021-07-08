#!/usr/bin/env bash

source build-utils-arm.shinc
check_min_req_vars

export os_arch=arm64
export os_build=android
export android_api=22
export SHARED_LIB=${LIBRARY_NAME}.so
export SHARED_LIB_FINAL=${SHARED_LIB} # dummy for macosx
export CXX=g++
export NDK_VERSION=r22b
prepare_android_toolchain
abort_if_var_unset "ANDROID_TOOLCHAIN" ${ANDROID_TOOLCHAIN}
export CC=$ANDROID_TOOLCHAIN/bin/aarch64-linux-android-clang
export run_openssl_configure="./Configure linux-aarch64 ${OPENSSL_NO_OPTS} -march=armv8-a+crypto -mfpu=neon -fPIC --prefix=${OPENSSL_ROOT}";
export run_readelf="${ANDROID_TOOLCHAIN}/bin/aarch64-linux-android-readelf -d bin/release/${os_build}/${os_arch}-v8a/${SHARED_LIB}"
export run_bjam="${BOOST_ROOT}/b2 -j2 --user-config=config/${os_build}-${os_arch}-config.jam variant=release toolset=clang-${os_arch} target-os=${os_build} location=bin/release/${os_build}/${os_arch}-v8a"
export run_strip="${ANDROID_TOOLCHAIN}/bin/aarch64-linux-android-strip --strip-unneeded -x -g bin/release/${os_build}/${os_arch}-v8a/${SHARED_LIB}"
export run_objcopy="${ANDROID_TOOLCHAIN}/bin/aarch64-linux-android-objcopy --only-keep-debug bin/release/${os_build}/${os_arch}-v8a/${SHARED_LIB} bin/release/${os_build}/{$os_arch}-v8a/${SHARED_LIB}.debug"
export PATH=$ANDROID_TOOLCHAIN/aarch64-linux-android/bin:$PATH;
sed -i 's/RANLIB = ranlib/RANLIB = "${ANDROID_TOOLCHAIN}\/bin\/aarch64-linux-android-ranlib"/g' ${BOOST_ROOT}/tools/build/src/tools/gcc.jam;

create_folder_if_it_doesnt_exist ${SRC}
prompt_msg "About to prepare BOOST ${BOOST_VERSION}"
press_any_to_continue
#prepare_boost
#prepare_openssl
#build_openssl
#prepare_libTAU
build_libraries
#cleanup_objects
