#!/bin/sh

rm -rf ./target/upload/
mvn -DskipTests -P prepare-for-upload package
# apkの指定は不要にしたい(AppCenter側の最新版が使われるようにしたい)
# appcenter test run appium --app "hoge-mozkzki/FirstTestApp" --devices "hoge-mozkzki/test-set-android-1" --test-series "master" --locale "ja_JP" --build-dir target/upload
appcenter test run appium --app "hoge-mozkzki/FirstTestApp" --devices "hoge-mozkzki/test-set-android-1" --app-path ./ApiDemos-debug.apk --test-series "master" --locale "ja_JP" --build-dir target/upload
