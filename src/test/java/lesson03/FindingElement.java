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

import java.util.List;
import java.util.Objects;

import static java.lang.Thread.sleep;

public class FindingElement {
    private String baseURL = "https://demoqa.com/elements";
    // The driver for interacting with the webpage
    private WebDriver webDriver;

    /**
     * Method thực thi trước mỗi class, tiến hành cấu hình Chrome Driver và khởi tạo
     */
    @BeforeClass
    public void beforeClass() {
        //region Chrome Driver configuration
        /**
         * 1. Check your Chrome version
         * 2. Download chrome driver at link: https://chromedriver.chromium.org/downloads
         * 3. Create a folder and save chrome driver in there
         * 4. config chrome driver in your source code
         */
        String chromeDriverPath = "C:\\Users\\Admin\\codegym-01-selenium\\src\\test\\resources\\driver\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

       //WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        //WebDriverManager.chromiumdriver().setup();
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
     * Liệt kê các phương thức tìm kiếm nâng cao
     * 1. Thực hành tìm kiếm thủ công trên website
     * 2. Phát triển các method để kiểm tra xem có xác định được phần tử không ?
     **/

    @Test
    public void testPractice(){
        // Truy cập website
        gotoDemoQAWebsite();
        //Contains
        String containsXPath = "//textarea[contains(@placeholder,'Current Address')]";
        WebElement containsEle = webDriver.findElement(By.xpath(containsXPath));
        System.out.println("Contains: "+containsEle.getAttribute("placeholder"));
        //Start-with
        String startWithXPath = "//textarea[starts-with(@placeholder,'Current')]";
        WebElement startWithEle = webDriver.findElement(By.xpath(startWithXPath));
        System.out.println("Start-with: "+startWithEle.getAttribute("placeholder"));
        //text()
        String textXPath = "//label[text() ='Current Address']";
        WebElement textEle = webDriver.findElement(By.xpath(textXPath));
        System.out.println("Text(): "+textEle.getText());
        //normalize-space
        String normalizeSpaceXPath = "//label[normalize-space() ='Current Address']";
        WebElement normalizeSpaceEle = webDriver.findElement(By.xpath(normalizeSpaceXPath));
        System.out.println("normalize-space: "+normalizeSpaceEle.getText());
        //ancestor
        String ancestorXPath = "//textarea[@placeholder='Current Address']/ancestor::div[@class='col-md-9 col-sm-12']";
        WebElement ancestorEle = webDriver.findElement(By.xpath(ancestorXPath));
        System.out.println("ancestor: "+ancestorEle.getAttribute("class"));
        //descendant
        String descendantXPath = "//div[@class='col-md-9 col-sm-12']/descendant::textarea";
        WebElement descendantEle = webDriver.findElement(By.xpath(descendantXPath));
        System.out.println("descendant: "+descendantEle.getAttribute("class"));
        //preceding
        String precedingXPath = "//textarea[@placeholder='Current Address']/preceding::div[@class='col-md-3 col-sm-12']";
        WebElement precedingEle = webDriver.findElement(By.xpath(precedingXPath));
        System.out.println("preceding: "+precedingEle.getAttribute("class"));
        //following
        String followingXPath = "//textarea[@placeholder='Current Address']/following::div[id='permanentAddress-wrapper']";
        WebElement followingEle = webDriver.findElement(By.xpath(followingXPath));
        System.out.println("following: "+followingEle.getAttribute("id"));
    }
    @Test(enabled = true, description = "Phương thức tìm kiếm XPath nâng cao")
    public void test_XPath_Advance() {
        // Truy cập website
        gotoDemoQAWebsite();

        // region Basic expression
        String formXPath = "//form[@id='userForm']";
        WebElement formElement = webDriver.findElement(By.xpath(formXPath));

        WebElement currentFormNode = formElement.findElement(By.xpath("."));
        System.out.printf("Current node: id - %s%n", currentFormNode.getAttribute("id"));

        WebElement parentFormNode = formElement.findElement(By.xpath(".."));
        System.out.printf("Parent node: class - %s", parentFormNode.getAttribute("class"));
        //endregion

        //region Type of Functions
        String emailXpath = "//input[contains(@placeholder,'name@example.com')]";
        WebElement emailElement = webDriver.findElement(By.xpath(emailXpath));

        String emailStartWithXpath = "//input[starts-with(@placeholder,'name')]";
        emailElement = webDriver.findElement(By.xpath(emailStartWithXpath));

        String emailPositionXPath = "//div[@id='userEmail-wrapper']//div[contains(@class,'col-md')][position()=1]";
        emailElement = webDriver.findElement(By.xpath(emailPositionXPath));

        String txtXPath = "//span[text()='Text Box']";
        String txtNormalizeXPath = "//span[normalize-space()='Text Box']";
        WebElement textBoxElement = webDriver.findElement(By.xpath(txtXPath));
        // endregion

        // region Find with ancestor & descendant
        // Tìm kiếm phần tử div có id userEmail-wrapper thông qua ancestor
        String ancestorXPath = "//input[@id='userEmail']/ancestor::div[@id='userEmail-wrapper']";
        WebElement ancestorElement = webDriver.findElement(By.xpath(ancestorXPath));

        String descendantXPath = "//div[@id='userEmail-wrapper']/descendant::input";
        WebElement descendantElement = webDriver.findElement(By.xpath(descendantXPath));
        //endregion

        // region Find with preceding & following
        String precedingXPath = "//*[@id='userEmail']/preceding::div[@id='Ad.Plus-728x90']";
        WebElement precedingElement = webDriver.findElement(By.xpath(precedingXPath));

        String followingXPath = "//*[@id='userEmail']/following::div[@id='currentAddress-wrapper']";
        WebElement followingElement = webDriver.findElement(By.xpath(followingXPath));
        //endregion

        // region Sibling Element
        String followingSiblingXpath = "//*[@id='userEmail-wrapper']/following-sibling::div[@id='currentAddress-wrapper']";
        WebElement followingSiblingElement = webDriver.findElement(By.xpath(followingSiblingXpath));

        String precedingSiblingXpath = "//*[@id='userEmail-wrapper']/preceding-sibling::div[@id='userName-wrapper']";
        WebElement precedingSiblingElement = webDriver.findElement(By.xpath(precedingSiblingXpath));
        //endregion

        // region Child - Parent
        String childXPath = "//*[@id='userEmail-wrapper']/child::div";
        String parentXPath = "//*[@id='userEmail']/parent::div";
        List<WebElement> childElements = webDriver.findElements(By.xpath(childXPath));
        WebElement parentElement = webDriver.findElement(By.xpath(parentXPath));
        // endregion

        // region Root
        String rootXPath = "//*[@id='userEmail']";
        parentElement = webDriver.findElement(By.xpath(rootXPath)).findElement(By.xpath(".."));
        //endregion
        System.out.println("End!");
        //endregion
    }



    /**
     * Create a xpath expression
     * @param format : Expression format
     * @param values : Values for the expression
     * @return : The xpath
     */
    private String getStringXPath(String format, String... values) {
        return String.format(format, values);
    }


    /**
     * Sleep for debugging
     *
     * @param milliseconds : number of milliseconds
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
