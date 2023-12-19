package lesson02;

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
    private String baseURL = "https://demoqa.com/elements";

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
    @Test(description = "Xác định phần tử với các phương pháp tìm kiếm cơ bản")
    public void btvn_findLocator_Basic_02() {
        gotoDemoQAWebsite();

        // Full Name Component
        //First Name
        String firstNameID = "firstName";
        WebElement firstNameEle = webDriver.findElement(By.id(firstNameID));
        System.out.println("ID: "+ firstNameEle.getAttribute("id"));

        // TODO: 10/05/2023 : Tìm kiếm với nhiều kịch bản khác nhau cho cùng component
        //Last Name - ID
        String lastNameId = "lastName";
        WebElement lastNameEleId = webDriver.findElement(By.id(lastNameId));
        System.out.println("ID - Last Name: "+lastNameEleId.getAttribute("id"));


        // TODO: 10/05/2023 Làm tương tự với các đối tượng khác
        //Email
        String emailId = "userEmail";
        WebElement emailEle = webDriver.findElement(By.id(emailId));
        System.out.println("ID - Email: "+emailEle.getAttribute("id"));
        //Mobile
        String mobileId = "userNumber";
        WebElement mobileEle = webDriver.findElement(By.id(mobileId));
        System.out.println("ID - Mobile: "+mobileEle.getAttribute("id"));
        //Date of Birth
        String doBId = "dateOfBirthInput";
        WebElement doBEle = webDriver.findElement(By.id(doBId));
        System.out.println("ID - Date of Birth: "+doBEle.getAttribute("id"));
        //Subjects
        String subjectId = "subjects-label";
        WebElement subjectEle = webDriver.findElement(By.id(subjectId));
        System.out.println("ID - Subjects: "+subjectEle.getAttribute("id"));
        //Picture
        String pictureId = "uploadPicture";
        WebElement pictureEle = webDriver.findElement(By.id(pictureId));
        System.out.println("ID - Picture: "+pictureEle.getAttribute("id"));
        //Current Address
        String addressId = "currentAddress";
        WebElement addressEle = webDriver.findElement(By.id(addressId));
        System.out.println("ID - Current Address: "+addressEle.getAttribute("id"));
        //Button Submit
        String submitButtonId = "submit";
        WebElement submitButtonEle = webDriver.findElement(By.id(submitButtonId));
        System.out.println("ID - Button Submit: "+submitButtonEle.getAttribute("id"));

    }

    /**
     * Description:
     */
    @Test(description = "Xác định phần tử với XPath")
    public void btvn_findLocator_XPath_02() {
        gotoDemoQAWebsite();

        // Full Name Component
        String firstNamePlaceHolderXPath = "//input[@placeholder='First Name']";
        WebElement firstNameEle = webDriver.findElement(By.xpath(firstNamePlaceHolderXPath));

        String firstNameIDXPath = "//input[@id ='firstName']";
        firstNameEle = webDriver.findElement(By.xpath(firstNameIDXPath));


        // TODO: 10/05/2023 : Tìm kiếm với nhiều kịch bản xpath khác nhau cho cùng component
        //Last Name - Place holder
        String lastNameXPathPlaceHolder = "//input[@placeholder='Last Name']";
        WebElement lastNameElePlaceHolder = webDriver.findElement(By.xpath(lastNameXPathPlaceHolder));
        System.out.println("Place holder - Last Name: "+ lastNameElePlaceHolder.getAttribute("placeholder"));
        //Last Name - ID
        String lastNameXPathId = "//input[@id='lastName']";
        WebElement lastNameEleId = webDriver.findElement(By.xpath(lastNameXPathId));
        System.out.println("ID - Last Name: "+lastNameEleId.getAttribute("id"));


        // TODO: 10/05/2023 Làm tương tự với các đối tượng khác
        //Email
        String emailXPath = "//input[@placeholder='name@example.com']";
        WebElement emailEle = webDriver.findElement(By.xpath(emailXPath));
        System.out.println("Place holder - Email: "+emailEle.getAttribute("placeholder"));
        //Mobile
        String mobileXPath = "//input[@id='userNumber']";
        WebElement mobileEle = webDriver.findElement(By.xpath(mobileXPath));
        System.out.println("ID - Mobile: "+mobileEle.getAttribute("id"));
        //Date of Birth
        String doBXPath = "//input[@id='dateOfBirthInput']";
        WebElement doBEle = webDriver.findElement(By.xpath(doBXPath));
        System.out.println("ID - Date of Birth: "+doBEle.getAttribute("id"));
        //Subjects
        String subjectXPath = "//label[@id='subjects-label']";
        WebElement subjectEle = webDriver.findElement(By.xpath(subjectXPath));
        System.out.println("ID - Subjects: "+subjectEle.getAttribute("id"));
        //Picture
        String pictureXPath = "//input[@id='uploadPicture']";
        WebElement pictureEle = webDriver.findElement(By.xpath(pictureXPath));
        System.out.println("ID - Picture: "+pictureEle.getAttribute("id"));
        //Current Address
        String addressXPath = "//textarea[@placeholder='Current Address']";
        WebElement addressEle = webDriver.findElement(By.xpath(addressXPath));
        System.out.println("Place holder - Current Address: "+addressEle.getAttribute("placeholder"));
        //Button Submit
        String submitButtonXPath = "//button[@id='submit']";
        WebElement submitButtonEle = webDriver.findElement(By.xpath(submitButtonXPath));
        System.out.println("ID - Button Submit: "+submitButtonEle.getAttribute("id"));
//        //Hobbies
//        String hobbiesSportXPath = "//label[@for='hobbies-checkbox-1']//after";
//        WebElement hobbiesSportEle = webDriver.findElement(By.xpath(hobbiesSportXPath));
//        System.out.println("Label - Hobbies Sport: "+ hobbiesSportEle.getAttribute("id"));
    }

    /**
     * Description:
     */
    @Test(description = "Xác định phần tử với CSS")
    public void btvn_findLocator_CSS_03() {
        gotoDemoQAWebsite();

        // Full Name Component
        String firstNamePlaceHolderCss = "input[placeholder='First Name']";
        WebElement firstNameEle = webDriver.findElement(By.cssSelector(firstNamePlaceHolderCss));

        String firstNameIDCss = "input[id ='firstName']";
        firstNameEle = webDriver.findElement(By.cssSelector(firstNameIDCss));


        // TODO: 10/05/2023 : Tìm kiếm với nhiều kịch bản css khác nhau cho cùng component
        //Last Name - Place holder
        String lastNameCssPlaceHolder = "input[placeholder='Last Name']";
        WebElement lastNameElePlaceHolder = webDriver.findElement(By.cssSelector(lastNameCssPlaceHolder));
        System.out.println("Place holder - Last Name: "+ lastNameElePlaceHolder.getAttribute("placeholder"));
        //Last Name - ID
        String lastNameCssId = "input[id='lastName']";
        WebElement lastNameEleId = webDriver.findElement(By.cssSelector(lastNameCssId));
        System.out.println("ID - Last Name: "+lastNameEleId.getAttribute("id"));

        // TODO: 10/05/2023 Làm tương tự với các đối tượng khác
        //Email
        String emailCss = "input[placeholder='name@example.com']";
        WebElement emailEle = webDriver.findElement(By.cssSelector(emailCss));
        System.out.println("Place holder - Email: "+emailEle.getAttribute("placeholder"));
        //Mobile
        String mobileCss = "input[id='userNumber']";
        WebElement mobileEle = webDriver.findElement(By.cssSelector(mobileCss));
        System.out.println("ID - Mobile: "+mobileEle.getAttribute("id"));
        //Date of Birth
        String doBCss = "input[id='dateOfBirthInput']";
        WebElement doBEle = webDriver.findElement(By.cssSelector(doBCss));
        System.out.println("ID - Date of Birth: "+doBEle.getAttribute("id"));
        //Subjects
        String subjectCss = "label[id='subjects-label']";
        WebElement subjectEle = webDriver.findElement(By.cssSelector(subjectCss));
        System.out.println("ID - Subjects: "+subjectEle.getAttribute("id"));
        //Picture
        String pictureCss = "input[id='uploadPicture']";
        WebElement pictureEle = webDriver.findElement(By.cssSelector(pictureCss));
        System.out.println("ID - Picture: "+pictureEle.getAttribute("id"));
        //Current Address
        String addressCss = "textarea[placeholder='Current Address']";
        WebElement addressEle = webDriver.findElement(By.cssSelector(addressCss));
        System.out.println("Place holder - Current Address: "+addressEle.getAttribute("placeholder"));
        //Button Submit
        String submitButtonCss = "button[id='submit']";
        WebElement submitButtonEle = webDriver.findElement(By.cssSelector(submitButtonCss));
        System.out.println("ID - Button Submit: "+submitButtonEle.getAttribute("id"));
    }


    /**
     * Go to DemoQA Website
     */
    private void gotoDemoQAWebsite() {
        String url = "https://demoqa.com/automation-practice-form";
        webDriver.get(url);
    }



}
