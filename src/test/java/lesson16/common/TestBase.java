package lesson16.common;


import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import lesson16.pages.HomePage;
import lesson16.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.util.Objects;

@Listeners(TestListener.class)
public class TestBase {
    public WebDriver mWebDriver;
    public String baseURL = "https://rise.fairsketch.com/";
    public LoginPage loginPage;
    public HomePage homePage;

    @BeforeSuite
    public void beforeSuite() {
        String chromeDriverPath = "src/test/resources/driver/chromedriverWin119.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        //WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        mWebDriver = getWebDriverInstance();
        DriverManager.setWebDriver(mWebDriver);
    }


    @AfterSuite
    public void afterSuite() {
        DriverManager.quit();
    }

    /**
     * Init WebDriver
     */
    public WebDriver getWebDriverInstance() {
        return Objects.nonNull(mWebDriver)? mWebDriver: new ChromeDriver(configChromeOption());
    }

    /**
     * Config Chrome Option
     */
    public ChromeOptions configChromeOption() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--remote-allow-origins=*");
        return chromeOptions;
    }
}
