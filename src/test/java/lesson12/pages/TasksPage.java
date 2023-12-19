package lesson12.pages;

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
    public void clickElement(WebElement element) {
        element.click();
        // Bo sung them thong tin bao cao
        System.out.println("Click to " + element);
    }
}
