package lesson13.pages;

import lesson12.pages.ClientPage;
import lesson13.common.BasePage;
import lesson13.common.LogType;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.MessageFormat;
import java.time.Duration;
import java.util.HashMap;
import java.util.logging.Logger;

public class AddEventPage extends BasePage {
    Logger logger = Logger.getLogger(ClientPage.class.getName());
    WebDriver mWebDriver;
    WebDriverWait mWebDriverWait;

    public AddEventPage(WebDriver mWebDriver) {
        super(mWebDriver);
        this.mWebDriver = mWebDriver;
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
    }
    public void inputEventInfo(HashMap<String, String> data) {

        logger.info("Input Event information");
        inputTextTo("input", "Title", data.getOrDefault("title", null));
        description(data.getOrDefault("description", null));
        inputTextTo("input", "Start date", data.getOrDefault("startDate", null));
        inputTextTo("input", "End date", data.getOrDefault("endDate", null));
        inputTextTo("input", "Start time", data.getOrDefault("startTime", null));
        inputTextTo("input", "End time", data.getOrDefault("endTime", null));
        inputTextTo("input",  "Location", data.getOrDefault("location", null));
        clientSelection(data.getOrDefault("client", null));
        shareWithSelection(data.getOrDefault("shareWith", null));
        repeatOpt(data.getOrDefault("repeat", null), data.getOrDefault("frequency", null), data.getOrDefault("cycle", null));
        colorSelection(data.getOrDefault("color", null));

        //Save event
        WebElement saveEventEle = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        clickElement(saveEventEle);

        sleep(3);
    }
    private void shareWithSelection(String selection){
        String selectionXPath = "//label[normalize-space()='%s']";
        WebElement element = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(selectionXPath, selection))));
        clickElement(element);
    }
    private void clientSelection(String selection){
        WebElement element = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[normalize-space()='Client']/..//span[@class='select2-chosen']")));
        clickElement(element);

        WebElement searchEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='select2-drop']//input[contains(@class,'select2-input')]")));
        clickElement(searchEle);
        searchEle.clear();
        searchEle.sendKeys(selection);
        searchEle.sendKeys(Keys.ENTER);
    }

    private void colorSelection(String color){
        String colorXPath = "//span[@data-color='%s']";
        WebElement colorEle = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(colorXPath,color))));
        clickElement(colorEle);
    }
}
