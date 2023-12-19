package lesson12.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.HashMap;
import java.util.logging.Logger;

public class AddTaskPage {
    Logger logger = Logger.getLogger(ClientPage.class.getName());
    WebDriver mWebDriver;
    WebDriverWait mWebDriverWait;

    public AddTaskPage(WebDriver mWebDriver) {
        this.mWebDriver = mWebDriver;
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
    }
    public void inputTaskInfo(HashMap<String, String> data) {

        logger.info("Input task information");
        inputInfo("input", "Title", data.getOrDefault("title", null));
        inputInfo("textarea", "Description", data.getOrDefault("description", null));
        selectInfo("Related to", data.getOrDefault("relatedTo", null));
        selectInfo("Project", data.getOrDefault("project", null));
        selectInfo("Points", data.getOrDefault("points", null));
        selectInfo("Milestone", data.getOrDefault("milestone", null));
        selectInfo("Assign to", data.getOrDefault("assignTo", null));
        collabAndLabelInsert("Collaborators", data.getOrDefault("collaborators", null));
        selectInfo("Status", data.getOrDefault("status", null));
        selectInfo("Priority", data.getOrDefault("priority", null));
        collabAndLabelInsert("Labels", data.getOrDefault("labels", null));
        timeline("Start date", data.getOrDefault("startDate", null));
        timeline("Deadline", data.getOrDefault("deadLine", null));
        recurringOpt(data.getOrDefault("repeat", null), data.getOrDefault("frequency", null), data.getOrDefault("cycle", null));

        //Save task
        WebElement saveAndShowEle = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='save-and-show-button']")));
        clickElement(saveAndShowEle);

        //Close new task
        WebElement closeTask = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Close']")));
        clickElement(closeTask);
    }

    private void inputInfo(String tagName, String valueOfAttribute, String textInput) {
        String LOGIN_FORM = "//%s[@placeholder='%s']";
        WebDriverWait webDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
        WebElement element = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(LOGIN_FORM, tagName, valueOfAttribute))));
        element.clear();
        element.sendKeys(textInput);
        System.out.println("Input text: " + textInput + " to " + element);
    }
    private void timeline(String timeType, String time) {
        String timelineXPath = "//label[normalize-space()='%s']/..//input";
        WebElement timeline = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(timelineXPath, timeType))));
        clickElement(timeline);
        timeline.sendKeys(time);
        timeline.sendKeys(Keys.ENTER);
    }
    public void recurringOpt(String repeat, String frequency, String cycles) {
        //Checkbox recurringOpt
        WebElement recurringOpt = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='recurring']")));
        clickElement(recurringOpt);

        //Repeat every
        WebElement repeatEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[normalize-space()='Repeat every']/..//input[@id='repeat_every']")));
        clickElement(repeatEle);
        repeatEle.clear();
        repeatEle.sendKeys(repeat);

        //Frequency
        WebElement freqEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[normalize-space()='Repeat every']/..//span[@class='select2-chosen']")));
        clickElement(freqEle);

        WebElement selectFreq = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='select2-drop']//input[contains(@class,'select2-input')]")));
        clickElement(selectFreq);
        selectFreq.sendKeys(frequency);
        selectFreq.sendKeys(Keys.ENTER);

        //Cycles
        inputInfo("input", "Cycles", cycles);
    }
    private void searchAndSelect(String textInputs) {
        //Click to search area
        WebElement searchEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='select2-drop']//input[contains(@class,'select2-input')]")));
        clickElement(searchEle);

        //Input information for selection
        searchEle.clear();
        searchEle.sendKeys(textInputs);
        searchEle.sendKeys(Keys.ENTER);
    }
    private void selectInfo(String valueOfAttribute, String textInput) {
        String DYNAMIC_SELECTION = "//label[normalize-space()='%s']/..//span[@class='select2-chosen']";
        //Click to selection
        WebElement element = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(DYNAMIC_SELECTION, valueOfAttribute))));
        clickElement(element);

        //Search and select Info
        searchAndSelect(textInput);
    }
    public void collabAndLabelInsert(String valueOfAttribute, String textInput){
        String collabAndLabelXPath = "//label[normalize-space()='%s']/../input";
        WebElement element = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(collabAndLabelXPath,valueOfAttribute))));
        clickElement(element);
        element.clear();
        element.sendKeys(textInput);
        element.sendKeys(Keys.ENTER);
    }
    public void clickElement(WebElement element) {
        element.click();
        // Bo sung them thong tin bao cao
        System.out.println("Click to " + element);
    }
}
