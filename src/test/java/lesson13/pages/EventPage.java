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
import java.util.Objects;
import java.util.logging.Logger;

public class EventPage extends BasePage {
    Logger logger = Logger.getLogger(TasksPage.class.getName());
    WebDriver mWebDriver;
    WebDriverWait mWebDriverWait;
    public EventPage(WebDriver mWebDriver) {
        super(mWebDriver);
        this.mWebDriver = mWebDriver;
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
    }

    /**
     * Go to Add Event Page
     * @return AddEventPage
     */
    public AddEventPage goToAddEventPage() {
        logger.info("Go to Add Event Page");
        String addEventXPath = "//a[normalize-space()='Add event' and @class='btn btn-default add-btn']";

        WebElement addEventEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(addEventXPath)));
        clickElement(addEventEle);
        return new AddEventPage(mWebDriver);
    }

    /**
     * Verify new Event
     * @param data
     */
    public void verifyNewEvent(HashMap<String, String> data){
        String dateStart = data.getOrDefault("startDate", null).substring(0,2);
        String titleEvent = data.getOrDefault("title", null);
        String eventXPath = "//a[normalize-space()='"+ dateStart +"']/../following-sibling::div//span//span[normalize-space()='"+titleEvent+"']";
        WebElement eventEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(eventXPath)));
        Assert.assertTrue(Objects.nonNull(eventEle),"Verify that new Event");

        // Add report
        addReportInfo(LogType.VERIFY,"Verify new Event");
    }
}
