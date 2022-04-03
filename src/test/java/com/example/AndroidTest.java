package com.example;

import static org.junit.Assert.assertNotNull;

import com.microsoft.appcenter.appium.EnhancedAndroidDriver;
import com.microsoft.appcenter.appium.Factory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Androidの自動テストサンプル.
 */
public class AndroidTest {
  @Rule
  public TestWatcher watcher = Factory.createWatcher();

  // AppCenterでのAppium実行では"Enhanced"の方を使う
  // private AndroidDriver driver;
  private static EnhancedAndroidDriver<MobileElement> driver;

  /**
   * 毎テスト開始時に呼び出されるメソッド.
   */
  @Before
  public void setUp() throws MalformedURLException {
    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

    // ==============================================================
    // ローカル実行する場合、この中だけコメントアウトすること
    desiredCapabilities.setCapability("appium:app",
        // 絶対パス必須
        "/Users/yutaka/src/temp/appium-test/maven-test/demo/ApiDemos-debug.apk");
    // ==============================================================

    // 下記はAppCenter側で指定されるため、ここに指定するとエラーになる（コメントはずさないこと）
    // desiredCapabilities.setCapability("appium:platformVersion", "12");
    // desiredCapabilities.setCapability("appium:deviceName", "Android Emulator");
    // desiredCapabilities.setCapability("appium:appPackage", "io.appium.android.apis");
    // desiredCapabilities.setCapability("appium:appActivity", ".view.TextFields");
    // desiredCapabilities.setCapability("appium:automationName", "UiAutomator2");

    // 固定
    desiredCapabilities.setCapability("platformName", "Android");
    // 下記はとりあえず固定で良い（安定度を見て必要なら調整）
    desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
    desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
    desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);

    URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

    // AppCenter用に変更している
    driver = Factory.createAndroidDriver(remoteUrl, desiredCapabilities);
  }

  /**
   * アプリのテストサンプル.
   */
  @Test
  public void testApp() {
    assertNotNull(driver.findElementByAccessibilityId("Content"));

    driver.findElementByAccessibilityId("Views").click();
    driver.pressKey(new KeyEvent(AndroidKey.PAGE_DOWN));
    driver.pressKey(new KeyEvent(AndroidKey.PAGE_DOWN));
    driver.pressKey(new KeyEvent(AndroidKey.PAGE_DOWN));
    driver.pressKey(new KeyEvent(AndroidKey.PAGE_DOWN));
    driver.findElementByAccessibilityId("TextFields").click();

    MobileElement el = (MobileElement) driver.findElementById("io.appium.android.apis:id/edit");
    el.click();
    el.sendKeys("This is test.");
  }

  /**
   * 毎テスト終了時に呼び出されるメソッド.
   */
  @After
  public void tearDown() {
    if (driver != null) {
      driver.label("Stopping App");
      driver.quit();
    }
  }
}
