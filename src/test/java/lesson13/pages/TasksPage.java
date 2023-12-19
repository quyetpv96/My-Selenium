package lesson13.pages;

import lesson13.common.BasePage;
import lesson13.common.LogType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.MessageFormat;
import java.time.Duration;
import java.util.HashMap;
import java.util.logging.Logger;

public class TasksPage extends BasePage {
    Logger logger = Logger.getLogger(TasksPage.class.getName());
    WebDriver mWebDriver;
    WebDriverWait mWebDriverWait;
    public TasksPage(WebDriver mWebDriver) {
        super(mWebDriver);
        this.mWebDriver = mWebDriver;
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
    }
    /**
     * Go to Task List page
     * @return  Add task page
     */
    public AddTaskPage goToAddTaskPage() {
        logger.info("Go to Add Task Page");
        String addTaskXPath = "//a[normalize-space()='Add task' and @class='btn btn-default']";

        WebElement addTaskEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(addTaskXPath)));
        clickElement(addTaskEle);
        return new AddTaskPage(mWebDriver);
    }

    /**
     *  Show all filter on Tasks Page
     */
    public void showAllFilter(){
        // Show all filter on Tasks Page
        WebElement showAllFilterEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='btn btn-default show-filter-form-button']")));
        clickElement(showAllFilterEle);

        // Select member
        WebElement memberEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='select2-chosen-12']")));
        clickElement(memberEle);

        // Click to team member
        WebElement teamMemberEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[normalize-space()='- Team member -']")));
        clickElement(teamMemberEle);
    }

    /**
     * Verify new Task
     * @param data
     */
    public void verifyNewTask(HashMap<String, String> data){
        String taskID = System.getProperty("myTaskID");
        // Search task
        WebElement searchTaskEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='search']")));
        clickElement(searchTaskEle);
        searchTaskEle.clear();
        searchTaskEle.sendKeys(taskID);
        sleep(3);

        // Verify task ID
        WebElement taskIDEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class=' w10p']")));
        verifyInfo(taskIDEle.getText(),taskID);
        //Assert.assertEquals(taskIDEle.getText(),taskID,"Verify the taskID");

        //Verify task title
        WebElement taskTitleEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-search='#"+taskID+"']")));
        verifyInfo(taskTitleEle.getText(),data.getOrDefault("title", null));
        //Assert.assertTrue(taskTitleEle.getText().contains(data.getOrDefault("title", null)),"Verify the task title");

        //Verify task Assigned
        WebElement taskAssignedEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class=' min-w150']//a")));
        verifyInfo(taskAssignedEle.getText(),data.getOrDefault("assignTo", null));
        //Assert.assertTrue(taskAssignedEle.getText().contains(data.getOrDefault("assignTo", null)),"Verify the task title");

        // Add report
        addReportInfo(LogType.VERIFY,"Verify new task");
    }
}
