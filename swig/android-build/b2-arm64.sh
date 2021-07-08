#!/bin/bash

rm -rf /libTAU4j/swig/deps/libTAU/deps/libdatachannel/deps/libjuice/build-*
rm -rf /libTAU4j/swig/deps/libTAU/deps/libdatachannel/deps/usrsctp/build-*

alias ranlib='/android-ndk/toolchains/llvm/prebuilt/linux-x86_64/bin/llvm-ranlib'
export ANDROID_TOOLCHAIN=/android-ndk/toolchains/llvm/prebuilt/linux-x86_64
export BOOST_ROOT=/boost
export OPENSSL_ROOT=/openssl-arm64
export LIBTORRENT_ROOT=/libTAU4j/swig/deps/libTAU
export CXX=${ANDROID_TOOLCHAIN}/bin/aarch64-linux-android21-clang++
export CC=${ANDROID_TOOLCHAIN}/bin/aarch64-linux-android21-clang
export AR=${ANDROID_TOOLCHAIN}/bin/llvm-ar
export LD=${ANDROID_TOOLCHAIN}/bin/ld
export RANLIB=${ANDROID_TOOLCHAIN}/bin/llvm-ranlib

cd /libTAU4j/swig
${BOOST_ROOT}/b2 -j2 --user-config=config/android-arm64-config.jam variant=release toolset=clang-arm64 target-os=android location=bin/release/android/arm64-v8a
${ANDROID_TOOLCHAIN}/bin/llvm-objcopy --only-keep-debug bin/release/android/arm64-v8a/libTAU4j.so bin/release/android/arm64-v8a/libTAU4j.so.debug
${ANDROID_TOOLCHAIN}/bin/llvm-strip --strip-unneeded -x bin/release/android/arm64-v8a/libTAU4j.so
${ANDROID_TOOLCHAIN}/bin/llvm-readelf -d bin/release/android/arm64-v8a/libTAU4j.so
