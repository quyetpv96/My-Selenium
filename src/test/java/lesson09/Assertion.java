package lesson09;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

import static java.lang.Thread.sleep;

/**
 * Class to find the Web Element and interact with
 *
 * @author: Vincent
 */
public class Assertion {
    // The driver for interacting with the webpage
    private WebDriver webDriver;

    /**
     * Method thực thi trước mỗi class, tiến hành cấu hình Chrome Driver và khởi tạo
     */
    @BeforeClass
    public void beforeClass() {

        // Config driver manually
//        String chromeDriverPath = "src/test/resources/driver/chromedriver117";
        String chromeDriverPath = "src/test/resources/driver/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        // Tai Driver tu dong
        //WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--max-window-size");
        chromeOptions.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(chromeOptions);
    }

    /**
     * Method thực thi cuối cùng mỗi class, sẽ tiến hành đóng toàn bộ các chrome session đang chạy - Debug Mode
     **/
//    @AfterClass
//    public void afterClass() {
//        if (Objects.nonNull(webDriver)) webDriver.quit();
//    }

    @Test
    public void codegymClass() {
        gotoDemoQAWebsite();

        String nameInput = "quyetpv";
        String nameExpected = "Name:"+nameInput;

        WebElement fullNameEle = webDriver.findElement(By.xpath("//input[@placeholder='Full Name']"));
        fullNameEle.clear();
        fullNameEle.sendKeys(nameInput);


        WebElement emailEle = webDriver.findElement(By.xpath("//input[@placeholder='name@example.com']"));
        emailEle.clear();
        emailEle.sendKeys("quyetpv@gmail.com");

        WebElement currentAddEle = webDriver.findElement(By.xpath("//textarea[@placeholder='Current Address']"));
        currentAddEle.clear();
        currentAddEle.sendKeys("abc");

        WebElement permanentAddressEle = webDriver.findElement(By.xpath("//textarea[@id='permanentAddress']"));
        permanentAddressEle.clear();
        permanentAddressEle.sendKeys("qwe");

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        WebElement submitBtn = webDriver.findElement(By.xpath("//button[@id='submit']"));
        js.executeScript("arguments[0].scrollIntoView();", submitBtn);
        submitBtn.click();

        WebElement fullNameResultEle = webDriver.findElement(By.xpath("//p[@id='name']"));
        String nameResult = fullNameResultEle.getText();

        //Compare name input vs name actual
        Assert.assertEquals(nameResult,nameExpected);

        Assert.assertTrue(nameResult.contains(nameInput),"Valid name input");

    }
    /**
     * Sleep for debugging
     *
     * @param milliseconds : waiting time (unit: milliseconds)
     */
    private void waitForDebug(long milliseconds) {
        try {
            sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Go to DemoQA Website
     */
    private void gotoDemoQAWebsite() {
        String url = "https://demoqa.com/text-box";
        webDriver.get(url);
    }
}
