package lesson08;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.*;
import java.time.Duration;

public class Practice08 {
    private WebDriver mWebDriver;
    private String baseURL1 = "https://rise.fairsketch.com/events";
    private  String baseURL2 = "https://demoqa.com/droppable";

    @BeforeMethod
    public void beforeTestMethod() {
        // Step 01: Setup file thực thi chrome driver cho system
        // Cách 01: Cài đặt driver cho chrome thông qua file thực thi
        //String chromeDriverPath = "src/test/resources/driver/chromedriver";
        String chromeDriverPath = "src/test/resources/driver/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        // Cách 02: Cài đặt tự đông
        //WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        // Lưu ý: Cần thêm thư viện webdrivermanager tại pom

        // Step 2: Khởi tạo Chrome Options : Chứa các tùy chỉnh cho Chrome
        ChromeOptions chromeOptions = new ChromeOptions();
        // Cài đặt Chrome mở full screen
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--remote-allow-origins=*");
        // Step 3: Khởi tạo WebDriver -> Tương tác với các phần tử website
        mWebDriver = new ChromeDriver(chromeOptions);
    }
    @Test
    public void practice_wait(){
        mWebDriver.get(baseURL1);

        WebDriverWait webDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));

        String LOGIN_FORM = "//input[@placeholder='%s']";

        // Wait for login shown
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(LOGIN_FORM, "Email"))));

        // Input username
        inputInfo("Email","admin@demo.com");

        // Input password
        inputInfo("Password","riseDemo");

        // Click login button
        WebElement loginButton = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        clickElement(loginButton);

        // Kiểm tra trạng thái đăng nhập thành công hay không
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='avatar-xs avatar me-1']/img")));

        // Truy cập [Events] menu
        WebElement eventsEle = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Events']")));
        clickElement(eventsEle);

        // Kiểm tra tiêu đề [Event calendar] có được hiển thị không
        WebElement calendarEle = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[normalize-space()='Event calendar']")));
        System.out.println("Tiêu đề: "+calendarEle.getText());
    }
    @Test
    public void practice_js(){
        mWebDriver.get(baseURL2);

        WebDriverWait webDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));

        //Creat new action
        Actions mActions = new Actions (mWebDriver);

        //Drag and drop element
        WebElement dragMeEle = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[normalize-space()='Drag me']")));
        WebElement dropMeEle = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='droppableExample-tabpane-simple']//div[@class='drop-box ui-droppable']")));
        mActions.dragAndDrop(dragMeEle,dropMeEle).build().perform();

        //Thực hiện alert bằng cách gọi executeScript với method alert()
//        JavascriptExecutor javaScripts = (JavascriptExecutor) mWebDriver;
//        javaScripts.executeScript("alert('Dropped!');");

    }
    private void inputInfo(String valueOfAttribute, String textInput){
        WebDriverWait webDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
        String DYNAMIC_INPUT_PLACEHOLDER = "//input[@placeholder='%s']";
        WebElement element = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(DYNAMIC_INPUT_PLACEHOLDER,valueOfAttribute))));
        element.clear();
        element.sendKeys(textInput);
        System.out.println("Input text: " + textInput + " to " + element);
    }
    private void clickElement(WebElement element) {
        element.click();
        // Bo sung them thong tin bao cao
        System.out.println("Click to " + element);
    }
//    @AfterMethod
//    public void afterMethod() {
//        if (mWebDriver != null) {
//            mWebDriver.quit();
//            mWebDriver = null;
//        }
//    }
}
