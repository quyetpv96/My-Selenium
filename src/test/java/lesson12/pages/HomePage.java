package lesson12.pages;

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
        clickElement(clientEle);

        return new ClientPage(mWebDriver);
    }
    public TasksPage gotoTasksPage() {
        logger.info("Click Tasks Element");
        String tasksXPath ="//a[@href='https://rise.fairsketch.com/tasks/all_tasks']";
        WebElement tasksEle = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(tasksXPath)));
        clickElement(tasksEle);

        return new TasksPage(mWebDriver);
    }
    public EventPage gotoEventPage() {
        logger.info("Click Events Element");
        String eventsXPath ="//a[@href='https://rise.fairsketch.com/events']";
        WebElement eventsEle = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(eventsXPath)));
        clickElement(eventsEle);

        return new EventPage(mWebDriver);
    }
    public void clickElement(WebElement element) {
        element.click();
        // Bo sung them thong tin bao cao
        System.out.println("Click to " + element);
    }
}
