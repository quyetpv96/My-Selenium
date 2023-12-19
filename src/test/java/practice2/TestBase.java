package practice2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.Objects;

public class TestBase {
    public WebDriver mWebDriver;

    @BeforeSuite
    public void beforeSuite(){
        String chromeDriverPath = "src/test/resources/driver/chromedriver120.exe";
        System.setProperty("webdriver.chrome.driver",chromeDriverPath);
        mWebDriver = getWebDriverInstance();
        DriverManager.setWebDriver(mWebDriver);
    }

    @AfterSuite
    public void afterSuite(){
        DriverManager.quit();
    }

    public WebDriver getWebDriverInstance(){
        return Objects.nonNull(mWebDriver) ? mWebDriver : new ChromeDriver(configChromeOption());
    }
    public ChromeOptions configChromeOption(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--remote-allow-origins=*");
        return chromeOptions;
    }
}
