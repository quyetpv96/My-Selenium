package lesson12.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

public class EventPage {
    Logger logger = Logger.getLogger(TasksPage.class.getName());
    WebDriver mWebDriver;
    WebDriverWait mWebDriverWait;
    public EventPage(WebDriver mWebDriver) {
        this.mWebDriver = mWebDriver;
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
    }
    public AddEventPage goToAddEventPage() {
        logger.info("Go to Add Event Page");
        String addEventXPath = "//a[normalize-space()='Add event' and @class='btn btn-default add-btn']";

        WebElement addEventEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(addEventXPath)));
        clickElement(addEventEle);
        return new AddEventPage(mWebDriver);
    }
    public void clickElement(WebElement element) {
        element.click();
        // Bo sung them thong tin bao cao
        System.out.println("Click to " + element);
    }
}
