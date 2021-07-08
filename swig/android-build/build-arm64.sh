#!/bin/bash

docker run --rm -it -v "$PWD/../../":/libTAU4j lt4j:latest "/b2-arm64.sh"

pushd ../../
./gradlew clean
./gradlew jar
./gradlew nativeAndroidArm64Jar
popd
