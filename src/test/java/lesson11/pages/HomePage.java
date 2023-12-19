package lesson11.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

public class HomePage {
    Logger logger = Logger.getLogger(HomePage.class.getName());
    WebDriver mWebDriver;
    WebDriverWait mWebDriverWait;
    public HomePage(WebDriver mWebDriver) {
        this.mWebDriver = mWebDriver;
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
    }

    /**
     * Access Client Page
     * @return
     */
    public ClientPage gotoClientsPage() {
        logger.info("Click Client Element");
        String clientXPath ="//a[@href='https://rise.fairsketch.com/clients']";
        WebElement clientEle = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(clientXPath)));
        clientEle.click();

        return new ClientPage(mWebDriver);
    }
    public TasksPage gotoTasksPage() {
        logger.info("Click Tasks Element");
        String tasksXPath ="//a[@href='https://rise.fairsketch.com/tasks/all_tasks']";
        WebElement tasksEle = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(tasksXPath)));
        tasksEle.click();

        return new TasksPage(mWebDriver);
    }
}
