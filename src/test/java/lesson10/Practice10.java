package lesson10;

import lesson09.TestListener;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;


@Listeners(TestListener.class)
public class Practice10 {
    private WebDriver mWebDriver;
    private WebDriverWait webDriverWait;
    private String statusInfo = "Review";

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
            webDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(20));
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        //Truy cập website : https://rise.fairsketch.com
        gotoWebsite();
    }

    @Test(testName = "practice10_TC1")
    public void practice10_TC() {
        // Click [Task] tại left menu
        WebElement taskMenuEle = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Tasks']")));
        clickElement(taskMenuEle);

        // Click [Add a task]
        WebElement addTaskEle = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Add task' and @class='btn btn-default']")));
        clickElement(addTaskEle);

        // Nhập đầy đủ thông tin task > Save
        inputInfo("input", "Title", "Lesson10");
        inputInfo("textarea", "Description", "Practice10");
        selectInfo("Related to", "Project");
        selectInfo("Project", "Online");
        selectInfo("Points", "1 Point");
        selectInfo("Milestone", "Release");
        selectInfo("Assign to", "John");
        collabAndLabelInsert("Collaborators", "John");
        selectInfo("Status", statusInfo);
        selectInfo("Priority", "Critical ");
        collabAndLabelInsert("Labels", "Bug");
        timeline("Start date", "27-10-2023");
        timeline("Deadline", "27-10-2023");
        recurringOpt("5", "Day(s)", "4");

        //Upload file log
//        WebElement uploadBtn = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Upload File']")));
//        uploadBtn.sendKeys("src/test/resources/driver/image/wallpaperflare.com_wallpaper.jpg");

        //Save task
        WebElement saveAndShowEle = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='save-and-show-button']")));
        clickElement(saveAndShowEle);

        //Get Task ID
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Edit task']")));
        WebElement taskIDEle = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[@id='ajaxModalTitle']")));
        String taskID = taskIDEle.getText().substring(taskIDEle.getText().length()-4);

        //Close new task
        WebElement closeTask = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Close']")));
        clickElement(closeTask);

        // Tìm kiếm task vừa add và change status sang Inprogress + thêm 2 comment vào phần bình luận
        checkAndEditTaskStatus(taskID);
    }
    public void checkAndEditTaskStatus(String taskID){
        //Click to Kanban Tab
        WebElement kanbanEle = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Kanban']")));
        clickElement(kanbanEle);

        //Search Task -> Click Task
        WebElement searchTaskEle = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='search']")));
        clickElement(searchTaskEle);
        searchTaskEle.clear();
        searchTaskEle.sendKeys(taskID);


        String taskID_FORM = "//a[@data-post-id='%s']";
        WebElement searchtaskIDEle =  webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(taskID_FORM,taskID))));
        clickElement(searchtaskIDEle);

        //Add comment for task
        addComment("Comment 1");
        addComment("Comment 2");

        //Change Task status Review -> In Progress
        WebElement statusOfTask = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-act-type='status_id']")));
        clickElement(statusOfTask);

        String statusXPath = "//a[@class='select2-choice']//span[normalize-space()='%s']";
        WebElement status = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(statusXPath,statusInfo))));
        clickElement(status);

        WebElement newStatus = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[normalize-space()='In progress']")));
        clickElement(newStatus);

        //Close task info after change status
        WebElement closeTaskInfo = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Close']")));
        clickElement(closeTaskInfo);


        //Verify task after change status Review -> In progress
        WebElement searchTaskAfterEditEle = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='search']")));
        searchTaskAfterEditEle.clear();
        searchTaskAfterEditEle.sendKeys(taskID);
        sleep(5);
        WebElement inProgressCount = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()=' In progress ']//span")));
        int inProgressNum = Integer.parseInt(inProgressCount.getText());
        Assert.assertEquals(inProgressNum,1);

    }
    public void addComment(String comment){
        WebElement commentEle = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']")));
        clickElement(commentEle);
        commentEle.sendKeys(comment);
        WebElement postCmtBtn = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Post Comment']")));
        clickElement(postCmtBtn);
    }

    private void selectInfo(String valueOfAttribute, String textInput) {
        String DYNAMIC_SELECTION = "//label[normalize-space()='%s']/..//span[@class='select2-chosen']";
        //Click to selection
        WebElement element = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(DYNAMIC_SELECTION, valueOfAttribute))));
        clickElement(element);

        //Search and select Info
        searchAndSelect(textInput);
    }

    private void searchAndSelect(String textInputs) {
        //Click to search area
        WebElement searchEle = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='select2-drop']//input[contains(@class,'select2-input')]")));
        clickElement(searchEle);

        //Input information for selection
        searchEle.clear();
        searchEle.sendKeys(textInputs);
        searchEle.sendKeys(Keys.ENTER);
    }
    public void collabAndLabelInsert(String valueOfAttribute, String textInput){
        String collabAndLabelXPath = "//label[normalize-space()='%s']/../input";
        WebElement element = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(collabAndLabelXPath,valueOfAttribute))));
        clickElement(element);
        element.clear();
        element.sendKeys(textInput);
        element.sendKeys(Keys.ENTER);
    }
    private void timeline(String timeType, String time) {
        String timelineXPath = "//label[normalize-space()='%s']/..//input";
        WebElement timeline = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(timelineXPath, timeType))));
        clickElement(timeline);
        timeline.sendKeys(time);
        timeline.sendKeys(Keys.ENTER);
    }

    public void recurringOpt(String repeat, String frequency, String cycles) {
        //Checkbox recurringOpt
        WebElement recurringOpt = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='recurring']")));
        clickElement(recurringOpt);

        //Repeat every
        WebElement repeatEle = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[normalize-space()='Repeat every']/..//input[@id='repeat_every']")));
        clickElement(repeatEle);
        repeatEle.clear();
        repeatEle.sendKeys(repeat);

        //Frequency
        WebElement freqEle = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[normalize-space()='Repeat every']/..//span[@class='select2-chosen']")));
        clickElement(freqEle);

        WebElement selectFreq = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='select2-drop']//input[contains(@class,'select2-input')]")));
        clickElement(selectFreq);
        selectFreq.sendKeys(frequency);
        selectFreq.sendKeys(Keys.ENTER);

        //Cycles
        inputInfo("input", "Cycles", cycles);

    }

    @AfterClass(enabled = false)
    public void closeDriver() {
        // Quite Chrome Driver
        if (mWebDriver != null) {
            mWebDriver.quit();
            mWebDriver = null;
        }
    }

    private void gotoWebsite() {

        String url = "https://rise.fairsketch.com";
        mWebDriver.get(url);
        //Input Email & password
        inputInfo("input", "Email", "admin@demo.com");
        inputInfo("input", "Password", "riseDemo");

        //Click login button
        WebElement loginBtn = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Sign in']")));
        clickElement(loginBtn);

        // Wait for user icon shown
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='avatar-xs avatar me-1']")));
    }

    private void inputInfo(String tagName, String valueOfAttribute, String textInput) {
        String LOGIN_FORM = "//%s[@placeholder='%s']";
        WebDriverWait webDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
        WebElement element = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(LOGIN_FORM, tagName, valueOfAttribute))));
        element.clear();
        element.sendKeys(textInput);
        System.out.println("Input text: " + textInput + " to " + element);
        System.out.println("===============================================");
    }

    public void clickElement(WebElement element) {
        element.click();
        // Bo sung them thong tin bao cao
        System.out.println("Click to " + element);
        System.out.println("===============================================");
    }
//    private void clickElement(WebElement element) {
//        JavascriptExecutor js = (JavascriptExecutor) mWebDriver;
//        js.executeScript("arguments[0].click();",element);
//
//        // Bo sung them thong tin bao cao
//        System.out.println("Click to " + element);
//        System.out.println("===============================================");
//    }
    public void sleep(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
