package lesson04;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Objects;

/**
 * Hoc vien hoan thanh bai tap trong class nay
 */
public class BTVNForm {
    // The driver for interacting with the webpage
    private WebDriver webDriver;

    /**
     * Method thực thi trước mỗi class, tiến hành cấu hình Chrome Driver và khởi tạo
     */
    @BeforeClass
    public void beforeClass() {
        String chromeDriverPath = "C:\\Users\\HIiii\\codegym-01-selenium\\src\\test\\resources\\driver\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        //WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--max-window-size");
        chromeOptions.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(chromeOptions);
    }

    /**
     * Method thực thi cuối cùng mỗi class, sẽ tiến hành đóng toàn bộ các chrome session đang chạy - Debug Mode
     **/
    @AfterClass
    public void afterClass() {
        if (Objects.nonNull(webDriver)) webDriver.quit();
    }


    /**
     * Description:
     */
    @Test(description = "Xác định phần tử với tìm kiếm nâng cao ")
    public void btvn_improve_findingLocator_01() {
        gotoDemOrangeHrmWebsite();

        System.out.println("Vincent-Debug: Access website");
        // TODO: 10/05/2023 : Tìm kiếm với nhiều kịch bản advance khác nhau cho cùng component
        //Employee Full Name
        String PLACEHOLDER_FORM = "//div[@data-v-957b4417]/input[@placeholder='%s']";

        String firstName = String.format(PLACEHOLDER_FORM,"First Name");
        webDriver.findElement(By.xpath(firstName));
        String middleName = String.format(PLACEHOLDER_FORM,"Middle Name");
        webDriver.findElement(By.xpath(middleName));
        String lastName = String.format(PLACEHOLDER_FORM,"Last Name");
        webDriver.findElement(By.xpath(lastName));

        //Nickname, Employee Id, Other Id, Driver's License Number, SSN Number, SIN Number, Military Service
        String LABEL_FORM = "//label[normalize-space()='%s']/../following-sibling::div/input";

        String nickName = String.format(LABEL_FORM,"Nickname");
        webDriver.findElement(By.xpath(nickName));
        String employeeId = String.format(LABEL_FORM,"Employee Id");
        webDriver.findElement(By.xpath(employeeId));
        String otherId = String.format(LABEL_FORM,"Other Id");
        webDriver.findElement(By.xpath(otherId));
        String driverLicenseNumber = String.format(LABEL_FORM,"Driver's License Number");
        webDriver.findElement(By.xpath(driverLicenseNumber));
        String ssnNumber = String.format(LABEL_FORM,"SSN Number");
        webDriver.findElement(By.xpath(ssnNumber));
        String sinNumber = String.format(LABEL_FORM,"SIN Number");
        webDriver.findElement(By.xpath(sinNumber));
        String militaryService = String.format(LABEL_FORM,"Military Service");
        webDriver.findElement(By.xpath(militaryService));

        //License Expiry Date, Nationality, Marital Status, Date of Birth, Smoker, Blood Type
        String COMBO_FORM = "//label[normalize-space()='%s']/../following-sibling::div//i";

        String licenseExpDate = String.format(COMBO_FORM,"License Expiry Date");
        webDriver.findElement(By.xpath(licenseExpDate));
        String doB = String.format(COMBO_FORM,"Date of Birth");
        webDriver.findElement(By.xpath(firstName));
        String national = String.format(COMBO_FORM,"Nationality");
        webDriver.findElement(By.xpath(firstName));
        String maritalSts = String.format(COMBO_FORM,"Marital Status");
        webDriver.findElement(By.xpath(firstName));
        String smoker = String.format(COMBO_FORM,"Smoker");
        webDriver.findElement(By.xpath(firstName));
        String bloodType = String.format(COMBO_FORM,"Blood Type");
        webDriver.findElement(By.xpath(firstName));

        //Gender
        String RADIO_FORM = "//label[normalize-space()='%s']/span";

        String male = String.format(RADIO_FORM,"Male");
        webDriver.findElement(By.xpath(firstName));
        String female = String.format(RADIO_FORM,"Female");
        webDriver.findElement(By.xpath(firstName));

        //Save Personal Details, Save Custom Fields, Attachment
        String SAVE_ATTACH_FORM = "//h6[normalize-space()='%s']/../descendant::button";

        String savePer = String.format(SAVE_ATTACH_FORM,"Personal Details");
        webDriver.findElement(By.xpath(firstName));
        String saveCustomFilelds = String.format(SAVE_ATTACH_FORM,"Custom Fields");
        webDriver.findElement(By.xpath(firstName));
        String attachments = String.format(SAVE_ATTACH_FORM,"Attachments");
        webDriver.findElement(By.xpath(firstName));
    }


    /**
     * Go to DemoQA Website
     */
    private void gotoDemOrangeHrmWebsite() {
        String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        webDriver.get(url);

        String LOGIN_FORM ="//input[@placeholder='%s']";

        // Wait for login shown
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(LOGIN_FORM,"Username"))));

        // Input username
        WebElement loginEle = webDriver.findElement(By.xpath(String.format(LOGIN_FORM,"Username")));
        loginEle.clear();
        loginEle.sendKeys("Admin");

        // Input password
        WebElement passwordEle = webDriver.findElement(By.xpath(String.format(LOGIN_FORM,"Password")));
        passwordEle.clear();
        passwordEle.sendKeys("admin123");

        // Click login button
        WebElement loginButton = webDriver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();

        // Wait for user icon shown
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//header//img[@alt='profile picture']")));

        // Access my profile
        WebElement myProfileEle = webDriver.findElement(By.xpath("//nav[@role='navigation']//span[text()='My Info']"));
        myProfileEle.click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='oxd-layout-context']//img[@alt='profile picture']")));
    }


}
