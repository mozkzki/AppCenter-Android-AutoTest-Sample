# AppCenter-Android-AutoTest-Sample

AppCenter上でAndroidの自動テストを行うサンプル。Appiumを利用。

## 検証済みバージョン

| tool | version |
|------|-----|
| Java | AdoptOpenJDK (build 1.8.0_292-b10) |
| [Android Studio](https://developer.android.com/studio) | 2021.1.1 Patch 2 for Mac |
| [Appium Desktop](https://github.com/appium/appium-desktop/releases) | 1.22.2 |
| VSCode plugin : Extension Pack for Java | 0.22.2 |

## 環境構築(Mac)

上の表のツールを一通りインストール。

- Android Studio（SDK）
  - [ここ](https://akira-watson.com/android/adt-mac.html)を見た
  - SDK最新(12)を追加、System Imageも追加
  - ~/.zshrc等に環境変数追加

    ```sh
    export ANDROID_SDK_ROOT=~/Library/Android/sdk
    ```

- Appium Desktop
  - ハマリポイント
    - Appium DesktopのUI（環境変数設定）に設定が必要（無いとエラー）
      - ANDROID_HOMEにsdkのパス(↑と同じ)を入れる（[参考](https://stackoverflow.com/questions/65439683/neither-android-home-nor-android-sdk-root-environment-variable-was-exported-wi)）

## 実行

1. エミュレーター起動
    - Android Studio の Device Managerから (下記UI)
    ![devmng](./doc/devmng.png)
    ![emu](./doc/emu.png)
2. Appiumサーバー起動
    ![appium](./doc/appium.png)
3. テスト実行
   - 方法①： テストメソッド実行

appiumをpythonで操作できるが、app centerがpython動作を保証してない
app center保証はjava junit4のみ

