package lesson07;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Practice07 {
    private WebDriver mWebDriver;
    private String DYNAMIC_INPUT_PLACEHOLDER = "//input[@placeholder='%s']";

    @BeforeMethod
    public void beforeTestMethod() {
        // Step 01: Setup file thực thi chrome driver cho system
        String chromeDriverPath = "src/test/resources/driver/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

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
    @Test(description = "Test close tab 2 khi mở 3 tab")
    public void testTabID(){
        String url = "https://test.warface.codegym.vn";
        //Tab1
        mWebDriver.get(url);

        //Lấy id tab1
        String tab1ID = mWebDriver.getWindowHandle();

        //Mở tab2 và truy cập đăng nhập
        mWebDriver.switchTo().newWindow(WindowType.TAB);
        gotoTestWarface();

        //Lấy id tab2
        String tab2ID = mWebDriver.getWindowHandle();

        //Mở tab3
        mWebDriver.switchTo().newWindow(WindowType.TAB); // -> Hiện tại đang ở tab3 nên chỉ có thể tương tác với các phần tử trong màn hình tab3

        //Quay lại tab1 refresh
        mWebDriver.switchTo().window(tab1ID);
        mWebDriver.navigate().refresh();

        //Chuyển sang tab2 close
        mWebDriver.switchTo().window(tab2ID);
        mWebDriver.close();
    }

    /**
     * Xây dựng kịch bản kiểm thử cơ bản sử dụng Windows, Actions và Alerts
     */
    @Test
    public void practice07_Bai1() {
        String url01 = "https://test.warface.codegym.vn";
        //Truy cập website trang chủ
        mWebDriver.get(url01);

        String curWinID = mWebDriver.getWindowHandle();

        //Creat new tab -> vào trang login thực hiện đăng nhập
        mWebDriver.switchTo().newWindow(WindowType.TAB);
        gotoTestWarface();

        Set<String> winSet = mWebDriver.getWindowHandles();
        List<String> winList = new ArrayList<String>(winSet);
        String tabLogin = winList.get(1);

        //Quay lại tab trang chủ refresh website và đóng tab đăng nhập
        mWebDriver.switchTo().window(curWinID);
        mWebDriver.navigate().refresh();
        mWebDriver.switchTo().window(tabLogin);
        mWebDriver.close();
        mWebDriver.switchTo().window(curWinID);

        //Thực hiện add 02 mặt hàng đầu tiên trong danh mục điện thoại
        //oderProduct("add product");
        //oderProduct("add product");
        oderProduct("21339037");
        oderProduct("21339036");

        //Kiểm tra đơn hàng đã có trong giỏ hàng hay chưa
        validProductInCart("21339037");
        validProductInCart("21339036");
    }

    /**
     * Xây dựng kịch bản kiểm thử cơ bản sử dụng Alerts
     */
    @Test
    public void practice07_Bai2(){
        String url2 = "https://demo.guru99.com/test/delete_customer.php";
        //Truy cập website
        mWebDriver.get(url2);

        String DYNAMIC_INPUT = "//input[@type='%s']";
        WebElement customerID = mWebDriver.findElement(By.xpath(String.format(DYNAMIC_INPUT,"text")));
        customerID.clear();
        customerID.sendKeys("Vincent");

        //Submit customerID
        WebElement alertBtn = mWebDriver.findElement(By.xpath("//input[@value='Submit']"));
        alertBtn.click();

        //Chuyen sang alert
        Alert alert = mWebDriver.switchTo().alert();
        String text1 = alert.getText();
        System.out.println("qDebug - Kiểm tra thông tin alert khi ấn submit button: " + text1);
        alert.accept();  //Nhan OK
        //alert.dismiss(); //Nhan Cancel

        String text2 = alert.getText();
        System.out.println("qDebug - Kiểm tra thông tin alert sau khi ấn OK delete: " + text2);
        alert.accept();  //Nhan OK
    }

    /**
     * Method tiến hành mua sản phẩm
     * @param productID : ID của sản phẩm cần mua
     */
    private void oderProduct(String productID){
        //Kiểm tra trạng thái giỏ hàng trước khi thêm sản phẩm
        WebDriverWait webDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='badge']")));
        WebElement testBadgeBefore = mWebDriver.findElement(By.xpath("//span[@class='badge']"));
        String valueBadgeBefore = testBadgeBefore.getText();
        System.out.println("qDebug: Số lượng đơn hàng trước khi mua thêm sản phẩm: "+ valueBadgeBefore);

        //Tiến hành oder sản phẩm
        //String oder_FORM = "//h6[normalize-space()='%s']/../following-sibling::div//a[normalize-space()='Mua hàng']";
        String oder_FORM = "//a[contains(@href,'id=%s')]";
        WebElement oderProduct = mWebDriver.findElement(By.xpath(String.format(oder_FORM,productID)));
        clickElement(oderProduct);

        //Kiểm tra xem đã click thêm sản phẩm vào giỏ hàng thành công chưa ?
        WebElement alertEle = mWebDriver.findElement(By.xpath("//div[normalize-space()='Thêm sản phẩm vào giỏ hàng thành công!']"));
        System.out.println("Alert: "+alertEle.getText());
        alertEle.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='badge']")));
        WebElement testBadgeAfter = mWebDriver.findElement(By.xpath("//span[@class='badge']"));
        String valueBadgeAfter = testBadgeAfter.getText();
        System.out.println("qDebug: Số lượng đơn hàng trong giỏ hiện tại là: " + valueBadgeAfter);

        if (Integer.parseInt(valueBadgeBefore) < Integer.parseInt(valueBadgeAfter)){
            System.out.println("qDebug: Thêm sản phẩm thành công -> Truy cập giỏ hàng kiểm tra thông tin chi tiết!");
        }else System.out.println("qDebug: Thêm sản phẩm thất bại!");

    }

    /**
     * Truy cập website to login
     */
    private void gotoTestWarface(){
        String url = "https://test.warface.codegym.vn/login";
        mWebDriver.get(url);

        //Wait for login show
        WebDriverWait webDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(DYNAMIC_INPUT_PLACEHOLDER,"Email address"))));

        //Input Username
        inputInfo("Email address","quyet.pv96@gmail.com");

        //Input password
        inputInfo("Password","abc13579");

        //Click Đăng nhập button
        WebElement btnLogin = mWebDriver.findElement(By.xpath("//button[@type='submit']"));
        clickElement(btnLogin);

        // Wait for user icon shown
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='quyetpv']")));
        System.out.println("qDebug: Đăng nhập thành công !");
    }

    /**
     * Method điền thông tin login
     */
    private void inputInfo(String valueOfAttribute, String textInput){
        WebElement element = mWebDriver.findElement(By.xpath(String.format(DYNAMIC_INPUT_PLACEHOLDER,valueOfAttribute)));
        element.clear();
        element.sendKeys(textInput);
        System.out.println("Input text: " + textInput + " to " + element);
    }
    private void clickElement(WebElement element) {
        element.click();
        // Bo sung them thong tin bao cao
        System.out.println("Click to " + element);
    }

    /**
     * Lấy thông tin tên sản phẩm cần mua
     */
    public String getProductName(String productID){
        String url01 = "https://test.warface.codegym.vn";
        //Truy cập website trang chủ
        mWebDriver.get(url01);
        String getNameProductXPath = "//a[contains(@href,'id=%s')]/../preceding-sibling::div//h6[@class='text-truncate mb-3']";
        WebElement productNameEle = mWebDriver.findElement(By.xpath(String.format(getNameProductXPath,productID)));
        return productNameEle.getText();
    }

    /**
     * Kiểm tra thông tin sản phẩm trong giỏ hàng đã đúng với sản phẩm cần mua chưa ?
     */
    public void validProductInCart(String productID){
        String productName = getProductName(productID);
        String productInCartXPath = "//td[normalize-space()='%s']";
        accessBagde();
        WebElement productEle = mWebDriver.findElement(By.xpath(String.format(productInCartXPath,productName)));
        if (productEle != null){
            System.out.println("Sản phẩm "+ productName+ " đã có trong giỏ hàng");
        }
    }

    /**
     * Truy cập giỏ hàng
     */
    public void accessBagde(){
        //Access giỏ hàng
        WebElement accessBadge = mWebDriver.findElement(By.xpath("//span[@class='badge']"));
        clickElement(accessBadge);

        //Wait giỏ hàng show
        WebDriverWait webDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[normalize-space()='Thông tin giỏ hàng']")));
        System.out.println("qDebug: Truy cập giỏ hàng thành công");
    }
//    @AfterMethod
//    public void afterMethod() {
//        if (mWebDriver != null) {
//            mWebDriver.quit();
//            mWebDriver = null;
//        }
//    }

    /**
     * Wait for
     *
     * @param seconds : waiting time
     */
    public void sleep(long seconds) {
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
