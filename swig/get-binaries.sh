#!/usr/bin/env bash

rm -rf bin
mkdir bin

# android
wget --no-check-certificate -P bin/release/android/armeabi-v7a https://s3.amazonaws.com/libTAU4j/release/android/armeabi-v7a/libTAU4j.so
wget --no-check-certificate -P bin/release/android/arm64-v8a https://s3.amazonaws.com/libTAU4j/release/android/arm64-v8a/libTAU4j.so
wget --no-check-certificate -P bin/release/android/x86 https://s3.amazonaws.com/libTAU4j/release/android/x86/libTAU4j.so
wget --no-check-certificate -P bin/release/android/x86_64 https://s3.amazonaws.com/libTAU4j/release/android/x86_64/libTAU4j.so

# linux
wget --no-check-certificate -P bin/release/linux/x86_64 https://s3.amazonaws.com/libTAU4j/release/linux/x86_64/libTAU4j.so

# macos
wget --no-check-certificate -P bin/release/macos/x86_64 https://s3.amazonaws.com/libTAU4j/release/macos/x86_64/libTAU4j.dylib
