package lesson11.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.Objects;
import java.util.logging.Logger;

public class TasksPage {
    Logger logger = Logger.getLogger(TasksPage.class.getName());
    WebDriver mWebDriver;
    WebDriverWait mWebDriverWait;
    public TasksPage(WebDriver mWebDriver) {
        this.mWebDriver = mWebDriver;
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
    }

    /**
     * Verify Tasks dashboard
     */
    public void verifyTasksDashboard(){
        logger.info("verifyTasksPaged");
        String taskMenuXPath = "//div[@class='title-button-group']//a[normalize-space()='%s']";

        //Verify Manage labels
        WebElement manageLabelsEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(taskMenuXPath,"Manage labels"))));
        Assert.assertTrue(Objects.nonNull(manageLabelsEle),"Verify Manage labels");

        //Verify Import tasks
        WebElement importTasksEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(taskMenuXPath,"Import tasks"))));
        Assert.assertTrue(Objects.nonNull(importTasksEle),"Verify Import tasks");

        //Verify Add multiple tasks
        WebElement addMuliTaskEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(taskMenuXPath,"Add multiple tasks"))));
        Assert.assertTrue(Objects.nonNull(addMuliTaskEle),"Verify Add multiple tasks");

        //Verify Add task
        WebElement addTaskEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(taskMenuXPath,"Add task"))));
        Assert.assertTrue(Objects.nonNull(addTaskEle),"Verify Add task");
    }

    /**
     * Go to Task List page
     * @return  Add task page
     */
    public AddTaskPageViaList goToAddTaskPageViaList() {
        String addTaskXPath = "//a[normalize-space()='Add task' and @class='btn btn-default']";
        String listTaskXPath = "//a[@href='https://rise.fairsketch.com/tasks/all_tasks/']";

        WebElement listTaskEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(listTaskXPath)));
        listTaskEle.click();

        WebElement addTaskEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(addTaskXPath)));
        addTaskEle.click();
        return new AddTaskPageViaList(mWebDriver);
    }

    /**
     * Go to Kanban page
     * @return Add task page
     */
    public AddTaskPageViaKanban goToAddTaskPageViaKanban() {
        String addTaskXPath = "//a[normalize-space()='Add task' and @class='btn btn-default']";
        String kanbanTaskXPath = "//a[@href='https://rise.fairsketch.com/tasks/all_tasks_kanban/']";

        WebElement kanbanTaskEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(kanbanTaskXPath)));
        kanbanTaskEle.click();

        WebElement addTaskEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(addTaskXPath)));
        addTaskEle.click();
        return new AddTaskPageViaKanban(mWebDriver);
    }
}
