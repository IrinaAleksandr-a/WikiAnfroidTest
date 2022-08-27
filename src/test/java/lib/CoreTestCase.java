package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class CoreTestCase {

    protected AppiumDriver<WebElement> driver;
    private static final String APPIUM_URL = "http://0.0.0.0:4723/wd/hub";
    protected static final String ANDROID_VERSION = "10";

    @BeforeEach
    public void setUp() throws Exception {
        URL URL = new URL(APPIUM_URL);
        this.driver = new AndroidDriver(URL, getAndroidDesiredCapabilities());
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterEach
    public void tearDown() {
        this.driver.quit();
    }

    private static DesiredCapabilities getAndroidDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("avd", "ver" + ANDROID_VERSION);
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", ANDROID_VERSION + ".0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        String pathWiki = System.getProperty("user.dir") + "/apps/org.wikipedia.apk";
        capabilities.setCapability("app", pathWiki);
        return capabilities;
    }
}
