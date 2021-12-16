package parenkov.tests;

import com.codeborne.selenide.Configuration;
import parenkov.config.TestCredentialsConfig;
import parenkov.drivers.BrowserStackMobileDriver;
import parenkov.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static parenkov.helpers.Attach.getSessionId;

public class TestBase {

    public TestCredentialsConfig testConfig;

    @BeforeAll
    public static void setup() {
        addListener("AllureSelenide", new AllureSelenide());

        Configuration.browser = BrowserStackMobileDriver.class.getName();
        Configuration.browserSize = null;
        Configuration.timeout = 10000;
    }

    @BeforeEach
    public void startDriver() {
        open();
    }

    @AfterEach
    public void afterEach() {
        // явно получаем sessionId
        String sessionId = getSessionId();

        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();

        closeWebDriver();
        // видеоаттач получаем только после закрытия вебдрайвера
        Attach.attachVideo(sessionId);
    }
}
