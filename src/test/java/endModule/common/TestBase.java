package endModule.common;

import endModule.pages.HomePage;
import endModule.pages.LoginPage;
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
    public String baseURL = "https://opensource-demo.orangehrmlive.com/";
    public LoginPage loginPage;
    public HomePage homePage;

    @BeforeSuite
    public void beforeSuite(){
        String chromeDriverPath = "src/test/resources/driver/chromedriverWin119.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        mWebDriver = getWebDriverInstance();
        DriverManager.setWebDriver(mWebDriver);
        loginPage = new LoginPage(mWebDriver);
        loginPage.gotoWebsite(baseURL);
        // Login website với tài khoản hợp lệ -> Thành công truy cập vào HomePage
        homePage = loginPage.login("Admin", "admin123");

    }

    @AfterSuite
    public void afterSuite() {
        DriverManager.quit();
    }

    public WebDriver getWebDriverInstance() {
        return Objects.nonNull(mWebDriver)? mWebDriver: new ChromeDriver(configChromeOption());
    }

    public ChromeOptions configChromeOption() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--remote-allow-origins=*");
        return chromeOptions;
    }
}
