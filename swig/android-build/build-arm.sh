#!/bin/bash

docker run --rm -it -v "$PWD/../../":/libTAU4j lt4j:latest "/b2-arm.sh"

pushd ../../
./gradlew clean
./gradlew jar
./gradlew nativeAndroidArmJar
popd
