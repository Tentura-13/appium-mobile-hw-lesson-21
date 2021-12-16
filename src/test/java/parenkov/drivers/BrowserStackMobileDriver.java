package parenkov.drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import parenkov.config.BrowserStackConfig;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserStackMobileDriver implements WebDriverProvider {

    private static BrowserStackConfig config =
            ConfigFactory.create(BrowserStackConfig.class, System.getProperties());

    // set remote webdriver URL
    public static URL getBrowserstackUrl() {
        try {
            return new URL(config.getUrl());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {

        // Set your access credentials
        desiredCapabilities.setCapability("browserstack.user", config.getUser());
        desiredCapabilities.setCapability("browserstack.key", config.getKey());

        // Set URL of the application under test
        desiredCapabilities.setCapability("app", config.getAppUrl());

        // Specify device and os_version for testing
        desiredCapabilities.setCapability("device", config.getDevice());
        desiredCapabilities.setCapability("os_version", config.getOSVersion());

        // Set other BrowserStack capabilities
        desiredCapabilities.setCapability("project", "Android Project");
        desiredCapabilities.setCapability("build", "build-1");
        desiredCapabilities.setCapability("name", "android_test");

        return new AndroidDriver(getBrowserstackUrl(), desiredCapabilities);
    }
}
