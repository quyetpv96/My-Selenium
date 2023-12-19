package lesson13.pages;

import lesson13.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.logging.Logger;

public class AddTaskPage extends BasePage {
    Logger logger = Logger.getLogger(ClientPage.class.getName());
    WebDriver mWebDriver;
    WebDriverWait mWebDriverWait;
    String[] arrTask = new String[0];

    public AddTaskPage(WebDriver mWebDriver) {
        super(mWebDriver);
        this.mWebDriver = mWebDriver;
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
    }
    public void inputTaskInfo(HashMap<String, String> data) {

        logger.info("Input task information");
        inputTextTo("input", "Title", data.getOrDefault("title", null));
        inputTextTo("textarea", "Description", data.getOrDefault("description", null));
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
        repeatOpt(data.getOrDefault("repeat", null), data.getOrDefault("frequency", null), data.getOrDefault("cycle", null));

        // Save task
        WebElement saveAndShowEle = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='save-and-show-button']")));
        clickElement(saveAndShowEle);

        //Get Task ID
        mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Edit task']")));
        WebElement taskIDEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[@id='ajaxModalTitle']")));
        String taskID = taskIDEle.getText().substring(taskIDEle.getText().length()-4);
        System.setProperty("myTaskID",taskID);

        // Close new task
        WebElement closeTask = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Close']")));
        clickElement(closeTask);

    }
    private void timeline(String timeType, String time) {
        String timelineXPath = "//label[normalize-space()='%s']/..//input";
        WebElement timeline = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(timelineXPath, timeType))));
        clickElement(timeline);
        timeline.sendKeys(time);
        timeline.sendKeys(Keys.ENTER);
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
    private void collabAndLabelInsert(String valueOfAttribute, String textInput){
        String collabAndLabelXPath = "//label[normalize-space()='%s']/../input";
        WebElement element = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(collabAndLabelXPath,valueOfAttribute))));
        clickElement(element);
        element.clear();
        element.sendKeys(textInput);
        element.sendKeys(Keys.ENTER);
    }
}
