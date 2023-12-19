package lesson06;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * Dap an cho BTVN
 */
public class Practice06 {
    private WebDriver mWebDriver;
    private String baseURL = "https://demoqa.com/automation-practice-form";
    private String DYNAMIC_INPUT_PLACEHOLDER_FORM = "//%s[@placeholder='%s']";
    private String DYNAMIC_SELECTION = "//label[normalize-space()='%s']";
    private String test2URL = "https://demoqa.com/elements";
    private String DYNAMIC_CHECKBOX = "//label[contains(@for,'tree-node-')]//span[normalize-space()='%s']";

    @BeforeMethod
    public void beforeTestMethod() {
        // Step 01: Setup file thực thi chrome driver cho system
        // Cách 01: Cài đặt driver cho chrome thông qua file thực thi
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

    @Test(description = "quyetpv thuc hanh test6_1")
    public void Practice6_Test1() {
        WebDriverWait webDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
        mWebDriver.get(baseURL);
        //Input information
        inputInfo("input", "First Name", "Quyet");
        inputInfo("input", "Last Name", "Pham Van");
        inputInfo("input", "name@example.com", "quyepv.96@gmai.com");
        inputInfo("input", "Mobile Number", "0987654321");
        //Gender
        selectInfo("Male");
        //selectInfo("Female");
        //selectInfo("Other");

        //Input dob
        String doBXPath = "//input[@id='dateOfBirthInput']";
        WebElement doBEle = mWebDriver.findElement(By.xpath(doBXPath));
        doBEle.click();
        doBEle.sendKeys(Keys.CONTROL, "a");
        doBEle.sendKeys("01 Oct 1996");
        doBEle.sendKeys(Keys.ENTER);

        //Hobbies
        selectInfo("Sports");
        selectInfo("Reading");
        selectInfo("Music");

        //Picture
        // Fix-me: Fix image path
        String image = "src/test/resources/driver/image/wallpaperflare.com_wallpaper.jpg";
        mWebDriver.findElement(By.xpath("//input[@id='uploadPicture']")).sendKeys(image);

        //Current Address
        inputInfo("textarea", "Current Address", "Minh Khai - Bac Tu Liem");

        //Subjects
//        WebElement subjectEle = mWebDriver.findElement(By.xpath("//div[contains(@class,'subjects-auto-complete__input')]"));
//        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'subjects-auto-complete__input')]")));
//        subjectEle.click();
//        String[] subjects = {"Hindi","English","Maths","Physics","Chemistry","Biology","Computer Science","Commerce",
//                             "Accounting","Economics","Arts","Social Studies","History","Civics"};
//        subjectEle.sendKeys("a");
//        mWebDriver.findElement(By.xpath("//div[normalize-space()='Maths']")).click();

        /**
         * Loi Ads
         */
//        JavascriptExecutor js = (JavascriptExecutor) mWebDriver;
//        //Setup windown zoom 80%
//        js.executeScript("document.body.style.zoom='80%'");
//        //State and City
//        String stateXPath = "//div[normalize-space()='Select State']";
//        String cityXPath = "//label[normalize-space()='State and City']/../following-sibling::div//div[@id='city']//div[@class=' css-tlfecz-indicatorContainer']";
//        WebElement stateEle = mWebDriver.findElement(By.xpath(stateXPath));
//        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(stateXPath)));
//        stateEle.click();
//        mWebDriver.findElement(By.xpath("//div[normalize-space()='Haryana']")).click();
    }

    @Test(description = "quyetpv thuc hanh test6_2")
    public void Practice6_Test2() {
        mWebDriver.get(test2URL);
        WebDriverWait webDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
        //Access CheckBox menu
        mWebDriver.findElement(By.xpath("//span[normalize-space()='Check Box']")).click();
        //Open all checkbox
        mWebDriver.findElement(By.xpath("//button[@title='Expand all']")).click();
        //Checkbox Commands, Angular, Private, Excel File.doc
        checkBox("Commands");
        checkBox("Angular");
        checkBox("Private");
        checkBox("Excel File.doc");
    }

    @Test(description = "Thuc hanh 01: Tuong tac Text box va Button")
    public void insertUserForm() {
        // Truy cap dia chi chinh
        baseURL = "https://demoqa.com/text-box";
        mWebDriver.get(baseURL);
        sleep(2);

        String DYNAMIC_INPUT_PLACEHOLDER_FORM = "//input[@placeholder='%s']";
        // Input Full Name
        String fullNameXPath = String.format(DYNAMIC_INPUT_PLACEHOLDER_FORM, "Full Name");
        WebElement fullNameEle = mWebDriver.findElement(By.xpath(fullNameXPath));
        fullNameEle.sendKeys("Vincent");

        // Input email
        String emailXPath = String.format(DYNAMIC_INPUT_PLACEHOLDER_FORM, "name@example.com");
        WebElement emailEle = mWebDriver.findElement(By.xpath(emailXPath));
        emailEle.sendKeys("vincent@gmail.com");


        String DYNAMIC_TEXTAREA_ID_FORM = "//textarea[@id='%s']";
        // Input current address
        String currentXPath = String.format(DYNAMIC_TEXTAREA_ID_FORM, "currentAddress");
        WebElement currentEle = mWebDriver.findElement(By.xpath(currentXPath));
        currentEle.sendKeys("KĐT Nam Trung Yên, P. Yên Hòa, Q. Cầu Giấy, TP Hà Nội");

        // Input Permanent address
        String permanentXPath = String.format(DYNAMIC_TEXTAREA_ID_FORM, "permanentAddress");
        WebElement permanentEle = mWebDriver.findElement(By.xpath(permanentXPath));
        permanentEle.sendKeys("Q. Cầu Giấy, TP Hà Nội");

        // Click to Submit
        String buttonXPath = "//button[@id='submit']";
        WebElement submitEle = mWebDriver.findElement(By.xpath(buttonXPath));
        submitEle.click();
    }

    /**
     * Action co ban cho cac WebElement
     */
    @Test(description = "Thuc hanh 02: Tuong tac Radio button")
    public void testRadioButtonChecked() {
        // click
        baseURL = "https://demoqa.com/radio-button";
        mWebDriver.get(baseURL);

        // 1. Tuong tac vs phan tu
        String radYesXPath = "//label[text()='Yes']";
        WebElement radYesLabelEle = mWebDriver.findElement(By.xpath(radYesXPath));
        WebElement radYesButtonEle = mWebDriver.findElement(By.id("yesRadio"));
        WebElement radImpButtonEle = mWebDriver.findElement(By.id("impressiveRadio"));
        WebElement radNoButtonEle = mWebDriver.findElement(By.id("noRadio"));

        // Kiem tra Yes check chua ? chua thi check va kiem tra trang thai
        if (!radYesButtonEle.isSelected()) {
            radYesLabelEle.click();
        }

        // 2. Kiem tra xem da duoc chon chua
        boolean isYesChecked = radYesButtonEle.isSelected();
        boolean isImpChecked = radImpButtonEle.isSelected();
        boolean isNoChecked = radNoButtonEle.isSelected();
        System.out.println("Yes button - Checked: " + isYesChecked);
        System.out.println("Impressive button - Checked: " + isImpChecked);
        System.out.println("No button - Checked: " + isNoChecked);
    }

    @Test
    public void insertFormInformation() {
        mWebDriver.get(baseURL);
        sleep(2);

        // Input First Name
        String firstNameXPath = String.format(DYNAMIC_INPUT_PLACEHOLDER_FORM, "First Name");
        WebElement firstNameEle = mWebDriver.findElement(By.xpath(firstNameXPath));
        firstNameEle.sendKeys("Vincent");

        // Input Last Name
        String lastNameXPath = String.format(DYNAMIC_INPUT_PLACEHOLDER_FORM, "Last Name");
        WebElement lastNameEle = mWebDriver.findElement(By.xpath(lastNameXPath));
        lastNameEle.sendKeys("T");

        // Input Email
        String emailXPath = String.format(DYNAMIC_INPUT_PLACEHOLDER_FORM, "name@example.com");
        WebElement emailEle = mWebDriver.findElement(By.xpath(emailXPath));
        emailEle.sendKeys("vincent@gmail.com");

        // Input Genders
        String maleAttr = "gender-radio-1";
        String DYNAMIC_RADIO_LABEL_FORMAT = "//label[@for='%s']";
        String maleLabel = String.format(DYNAMIC_RADIO_LABEL_FORMAT, maleAttr);
        WebElement maleLabelElement = mWebDriver.findElement(By.xpath(maleLabel));
        maleLabelElement.click();

        // Input Mobile Number
        String mobileXPath = String.format(DYNAMIC_INPUT_PLACEHOLDER_FORM, "Mobile Number");
        WebElement mobileEle = mWebDriver.findElement(By.xpath(mobileXPath));
        mobileEle.sendKeys("84946210891");

        // Input Date of Birthday
        String dateXPath = "//input[@id='dateOfBirthInput']";
        WebElement dateOfBirthEle = mWebDriver.findElement(By.xpath(dateXPath));
        dateOfBirthEle.click();
        String dateXP = "//div[@role='listbox']//div[@tabindex='0']";
        mWebDriver.findElement(By.xpath(dateXP)).click();


        // Input subject
        String subjectXPath = "//input[@id='subjectsInput']";
        WebElement subjectEle = mWebDriver.findElement(By.xpath(subjectXPath));
        subjectEle.sendKeys("Computer Science");
        String dropDownValueXPath = "//div[contains(@class,'subjects-auto-complete__menu')]//div[normalize-space()='Computer Science']";
        mWebDriver.findElement(By.xpath(dropDownValueXPath)).click();

        // Input Hobbies
        String sportAttr = "hobbies-checkbox-1";
        String DYNAMIC_CHECKBOX_LABEL_FORMAT = "//label[@for='%s']";
        String sportLabel = String.format(DYNAMIC_CHECKBOX_LABEL_FORMAT, sportAttr);
        WebElement sportLabelElement = mWebDriver.findElement(By.xpath(sportLabel));
        sportLabelElement.click();

        // Input Current Address
        String currentAddressXPath = "//textarea[@id='currentAddress']";
        WebElement currentAddressEle = mWebDriver.findElement(By.xpath(currentAddressXPath));
        currentAddressEle.sendKeys("No 1 Pham Van Bach, Yen Hoa, Cau Giay, HN");

        sleep(10);      // For Debug
    }

    /**
     * Wait for loading elements
     *
     * @param seconds : The waiting time in seconds
     */
    private void sleep(long seconds) {
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Input textbox with Atrribute placeholder
     *
     * @param tagName       :
     * @param textAttribute :
     * @param textInput     :
     */
    public void inputInfo(String tagName, String textAttribute, String textInput) {
        WebElement element = mWebDriver.findElement(By.xpath(String.format(DYNAMIC_INPUT_PLACEHOLDER_FORM, tagName, textAttribute)));
        element.clear();
        element.sendKeys(textInput);

        System.out.println("Input text " + textInput + " to " + element);
    }

    /**
     * Slect checkbox/radio button
     *
     * @param selectOption
     */
    public void selectInfo(String selectOption) {
        WebElement element = mWebDriver.findElement(By.xpath(String.format(DYNAMIC_SELECTION, selectOption)));
        clickElement(element);

        //Check status of selection and select
//        if (!mWebDriver.findElement(By.xpath(String.format(DYNAMIC_SELECTION,selectOption))).isSelected()){
//            mWebDriver.findElement(By.xpath(String.format(DYNAMIC_SELECTION,selectOption))).click();
//        }
    }

    private void clickElement(WebElement element) {
        element.click();
        // Bo sung them thong tin bao cao
        System.out.println("Click to " + element);
    }

    /**
     * Select checkbox
     *
     * @param textBox
     */
    public void checkBox(String textBox) {
        JavascriptExecutor js = (JavascriptExecutor) mWebDriver;
        //Scroll website to find element
        js.executeScript("arguments[0].scrollIntoView();", mWebDriver.findElement(By.xpath(String.format(DYNAMIC_CHECKBOX, textBox))));
        //Click element
        mWebDriver.findElement(By.xpath(String.format(DYNAMIC_CHECKBOX, textBox))).click();
    }
}
