package lesson09;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.time.Duration;


@Listeners({TestListener.class})
public class TestBase {
    private WebDriver mWebDriver;
    public TestBase() {

    }
    @Parameters({"browser"})
    @BeforeSuite
    public void beforeSuite(@Optional("chrome") String browser) {
       // Before Suite
    }

    @BeforeMethod(alwaysRun = true)
    public void addInvocation(ITestResult tr) {
        // Action before test method
    }

    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void createDriver(@Optional("chrome") String browser) {
        // Khởi tạo Chrome Driver
        if (browser.equalsIgnoreCase("chrome")) {
            String chromeDriverPath = "src/test/resources/driver/chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            ChromeOptions chromeOptions = new ChromeOptions();
            // Cài đặt Chrome mở full screen
            chromeOptions.addArguments("--start-maximized");
            chromeOptions.addArguments("--remote-allow-origins=*");
            mWebDriver = new ChromeDriver(chromeOptions);
        }  else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        gotoWebsite();
    }

    @Test(description = "Thiết lập truy cập trang chủ website trước khi thực thi tại mỗi class test")
    public void practice09_Test1(){
        String webTitle = mWebDriver.getTitle();
        System.out.println("Truy cập website" + webTitle + " thành công !");
    }

    @AfterClass(alwaysRun = true)
    public void closeDriver() {
        // Quite Chrome Driver
        if (mWebDriver != null) {
            mWebDriver.quit();
            mWebDriver = null;
        }
    }
    public void gotoWebsite(){
        WebDriverWait webDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
        String url = "https://rise.fairsketch.com";
        mWebDriver.get(url);
        //Input Email
        inputInfo("Email","client@demo.com");

        //Input password
        inputInfo("Password","riseDemo");

        //Click login button
        WebElement loginBtn = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Sign in']")));
        clickElement(loginBtn);

        // Wait for user icon shown
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='avatar-xs avatar me-1']")));
    }
    private void inputInfo(String valueOfAttribute, String textInput){
        String  LOGIN_FORM = "//input[@placeholder='%s']";
        WebDriverWait webDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
        WebElement element = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(LOGIN_FORM,valueOfAttribute))));
        element.clear();
        element.sendKeys(textInput);
        System.out.println("Input text: " + textInput + " to " + element);
    }
    private void clickElement(WebElement element) {
        element.click();
        // Bo sung them thong tin bao cao
        System.out.println("Click to " + element);
    }
}
