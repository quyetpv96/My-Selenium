package lesson11.testscript;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import lesson11.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
    private WebDriver mWebDriver;
    private String baseURL = "https://rise.fairsketch.com";
    private LoginPage loginPage;

    @BeforeMethod
    public void beforeTestMethod() {
        String chromeDriverPath = "src/test/resources/driver/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        //WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--remote-allow-origins=*");
        mWebDriver = new ChromeDriver(chromeOptions);
        loginPage = new LoginPage(mWebDriver);
    }

    @AfterMethod
    public void afterMethod() {
        if (mWebDriver != null) {
            mWebDriver.quit();
            mWebDriver = null;
        }
    }

    @Test (description ="Verify the login function")
    public void RISE_Login_001_Correct() {
        loginPage.gotoWebsite(baseURL);
        loginPage.login("admin@demo.com", "riseDemo");
    }

}
