#!/bin/bash

docker run --rm -it -v "$PWD/../../":/libTAU4j lt4j:latest "/b2-x86.sh"

pushd ../../
./gradlew clean
./gradlew jar
./gradlew nativeAndroidX86Jar
popd
