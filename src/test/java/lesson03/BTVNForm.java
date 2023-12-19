package lesson03;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
        String chromeDriverPath = "C:\\Users\\Admin\\codegym-01-selenium\\src\\test\\resources\\driver\\chromedriver.exe";
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
    public void btvn_findLocator_Advance_02() {
        gotoDemoQAWebsite();

        // Full Name Component
        String fullNameLabelXPath = "//label[text()='Full Name']";
        WebElement fullNameEle = webDriver.findElement(By.xpath(fullNameLabelXPath));


        // TODO: 10/05/2023 : Tìm kiếm với nhiều kịch bản advance khác nhau cho cùng component
        //contains()
        String containsFullName = "//input[contains(@placeholder, 'Full Name')]";
        WebElement containsFullNameEle = webDriver.findElement(By.xpath(containsFullName));
        System.out.println("Contains FullName: "+containsFullNameEle.getAttribute("placeholder"));
        //starts-with()
        String startsWithFullName = "//input[starts-with(@placeholder, 'Full')]";
        WebElement startsWithFullNameEle = webDriver.findElement(By.xpath(startsWithFullName));
        System.out.println("Starts-with FullName: "+startsWithFullNameEle.getAttribute("placeholder"));
        //text()
        String txtFullName = "//label[text() = 'Full Name')]";
        WebElement txtFullNameEle = webDriver.findElement(By.xpath(txtFullName));
        System.out.println("text FullName: "+txtFullNameEle.getText());
        //normalize-space()
        String normalizeSpaceFullName = "//label[normalize-space() = 'Full Name')]";
        WebElement normalizeSpaceFullNameEle = webDriver.findElement(By.xpath(normalizeSpaceFullName));
        System.out.println("normalize-space() FullName: "+normalizeSpaceFullNameEle.getText());
        //OR
        String orFullName = "//input[@id='userName' OR placeholder='Full Name']";
        WebElement orFullNameEle = webDriver.findElement(By.xpath(orFullName));
        System.out.println("OR FullName: "+orFullNameEle.getAttribute("id"));
        //AND
        String andFullName = "//input[@id='userName' OR placeholder='Full Name']";
        WebElement andFullNameEle = webDriver.findElement(By.xpath(andFullName));
        System.out.println("AND FullName: "+andFullNameEle.getAttribute("placeholder"));

        // TODO: 10/05/2023 Làm tương tự với các đối tượng khác
        //contains()
        String containsEmail = "//input[contains(@type, 'email')]";
        WebElement containsEmailEle = webDriver.findElement(By.xpath(containsEmail));
        System.out.println("Contains Email: "+containsEmailEle.getAttribute("type"));
        //starts-with()
        String startsWithCurrentAddress = "//textarea[starts-with(@placeholder, 'Current')]";
        WebElement startsCurrentAddressEle = webDriver.findElement(By.xpath(startsWithCurrentAddress));
        System.out.println("Starts-with Current Address: "+startsCurrentAddressEle.getAttribute("placeholder"));
        //text()
        String txtPermanentAdd = "//label[text() = 'Permanent Address')]";
        WebElement txtPermanentAddEle = webDriver.findElement(By.xpath(txtPermanentAdd));
        System.out.println("text Permanent Address: "+txtPermanentAddEle.getText());
        //normalize-space()
        String normalizePermanentAdd = "//label[normalize-space() = 'Permanent Address')]";
        WebElement normalizeSpacePermanentAddEle = webDriver.findElement(By.xpath(normalizePermanentAdd));
        System.out.println("normalize-space() FullName: "+normalizeSpacePermanentAddEle.getText());
    }

    /**
     * Description:
     */
    @Test(description = "Xác định phần tử kết hợp vị trí để thực hiện tìm kiếm nâng cao")
    public void btvn_findLocator_Position_03() {
        gotoDemoQAWebsite();

        // Full Name Component
        /**
         * Xác định phần tử label (Full Name):
         * 1. Xác định phần tử input có placeholder='Full Name'
         * 2. Tìm kiếm tổ tiên của phần tử số (1) thỏa mãn điều kiện id='userName-wrapper'
         * 3. Thực hiện tìm kiếm trong toàn bộ phần tử (2) có tag là label
         * -> Đây chính là phần tử mà chúng ta cần tìm
         */

        String fullNameLabelXPath = "//input[@placeholder='Full Name']/ancestor::div[@id='userName-wrapper']//label";
        WebElement fullNameEls = webDriver.findElement(By.xpath(fullNameLabelXPath));

        // TODO: 10/05/2023 : Tìm kiếm với nhiều kịch bản position khác nhau cho cùng component
        //ancestor
        String ancestorEmail = "//input[@placeholder='name@example.com']/ancestor::div[@class='col-md-9 col-sm-12']";
        WebElement ancestorEmailEle = webDriver.findElement(By.xpath(ancestorEmail));
        System.out.println("ancestor Email: "+ancestorEmailEle.getAttribute("class"));
        //descendant
        String descendantPermanentAddress = "//div[@class='col-md-9 col-sm-12']/descendant::textarea[@id='permanentAddress']";
        WebElement descendantPermanentAddressEle = webDriver.findElement(By.xpath(descendantPermanentAddress));
        System.out.println("descendant Permanent Address: "+descendantPermanentAddressEle.getAttribute("placeholder"));
        //preceding
        String precedingEmail = "//input[@type='email']/preceding::div[@id='userName-wrapper']";
        WebElement precedingEmailEle = webDriver.findElement(By.xpath(precedingEmail));
        System.out.println("preceding Email: "+precedingEmailEle.getAttribute("id"));
        //following
        String followingCurrentAdd = "//textarea[@placeholder='Current Address']/following::div[@id='permanentAddress-wrapper']";
        WebElement followingCurrentAddEle = webDriver.findElement(By.xpath(followingCurrentAdd));
        System.out.println("following Current Address: "+followingCurrentAddEle.getAttribute("id"));
        //preceding-sibling
        String precedingSibFullName = "//div[@id='currentAddress-wrapper']/preceding-sibling::div[@id='userEmail-wrapper']";
        WebElement precedingSibFullNameEle = webDriver.findElement(By.xpath(precedingSibFullName));
        System.out.println("preceding-sibling:"+precedingSibFullNameEle.getAttribute("id"));
        //following-sibling
        String followingSibFullName = "//div[@class='mt-2 justify-content-end row']/following-sibling::div[@id='output']";
        WebElement followingSibFullNameEle = webDriver.findElement(By.xpath(followingSibFullName));
        System.out.println("following-sibling: "+followingSibFullNameEle.getAttribute("id"));

        // TODO: 10/05/2023 Làm tương tự với các đối tượng khác
        //ancestor
        String ancestorFullName = "//input[@placeholder='Full Name']/ancestor::div[@class='col-md-9 col-sm-12']";
        WebElement ancestorFullNameEle = webDriver.findElement(By.xpath(ancestorFullName));
        System.out.println("ancestor FullName: "+ancestorFullNameEle.getAttribute("class"));
        //descendant
        String descendantFullName = "//div[@class='col-md-9 col-sm-12']/descendant::input[@placeholder='Full Name']";
        WebElement descendantFullNameEle = webDriver.findElement(By.xpath(descendantFullName));
        System.out.println("descendant FullName: "+descendantFullNameEle.getAttribute("placeholder"));
        //preceding
        String precedingFullName = "//input[@placeholder='Full Name']/preceding::div[@class='col-md-3 col-sm-12']";
        WebElement precedingFullNameEle = webDriver.findElement(By.xpath(precedingFullName));
        System.out.println("preceding FullName: "+precedingFullNameEle.getAttribute("class"));
        //following
        String followingFullName = "//input[@placeholder='Full Name']/following::div[@id='userEmail-wrapper']";
        WebElement followingFullNameEle = webDriver.findElement(By.xpath(followingFullName));
        System.out.println("following FullName: "+followingFullNameEle.getAttribute("id"));
        //preceding-sibling
        String precedingSibEmail = "//div[@id='userEmail-wrapper']/preceding-sibling::div[@id='userName-wrapper']";
        WebElement precedingSibEmailEle = webDriver.findElement(By.xpath(precedingSibEmail));
        System.out.println("preceding-sibling:"+precedingSibEmailEle.getAttribute("id"));
        //following-sibling
        String followingSibSubmit = "//div[@id='userEmail-wrapper']/following-sibling::div[@id='currentAddress-wrapper']";
        WebElement followingSibSubmitEle = webDriver.findElement(By.xpath(followingSibSubmit));
        System.out.println("following-sibling: "+followingSibSubmitEle.getAttribute("id"));
    }


    /**
     * Go to DemoQA Website
     */
    private void gotoDemoQAWebsite() {
        String url = "https://demoqa.com/text-box";
        webDriver.get(url);
    }


}
