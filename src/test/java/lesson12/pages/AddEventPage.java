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

public class AddEventPage {
    Logger logger = Logger.getLogger(ClientPage.class.getName());
    WebDriver mWebDriver;
    WebDriverWait mWebDriverWait;

    public AddEventPage(WebDriver mWebDriver) {
        this.mWebDriver = mWebDriver;
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
    }
    public void inputEventInfo(HashMap<String, String> data) {

        logger.info("Input Event information");
        inputInfo( "Title", data.getOrDefault("title", null));
        description(data.getOrDefault("description", null));
        inputInfo( "Start date", data.getOrDefault("startDate", null));
        inputInfo( "End date", data.getOrDefault("endDate", null));
        inputInfo( "Start time", data.getOrDefault("startTime", null));
        inputInfo( "End time", data.getOrDefault("endTime", null));
        inputInfo( "Location", data.getOrDefault("location", null));
        clientSelection(data.getOrDefault("client", null));
        shareWithSelection(data.getOrDefault("shareWith", null));
        repeatOpt(data.getOrDefault("repeat", null), data.getOrDefault("frequency", null), data.getOrDefault("cycle", null));
        colorSelection(data.getOrDefault("color", null));


        //Save event
        WebElement saveEventEle = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        clickElement(saveEventEle);

        sleep(3);
    }
    private void inputInfo(String valueOfAttribute, String textInput) {
        String LOGIN_FORM = "//input[@placeholder='%s']";
        WebElement element = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(LOGIN_FORM, valueOfAttribute))));
        element.clear();
        element.sendKeys(textInput);
        System.out.println("Input text: " + textInput + " to " + element);
    }
    private void description(String description){
        WebElement element = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@placeholder='Description']")));
        clickElement(element);

        WebElement descriptionEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='note-editable']")));
        descriptionEle.clear();
        descriptionEle.sendKeys(description);
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
    public void repeatOpt(String repeat, String frequency, String cycles) {
        //Checkbox repeat option
        WebElement repeatOpt = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='event_recurring']")));
        clickElement(repeatOpt);

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
        inputInfo("Cycles", cycles);
    }
    private void colorSelection(String color){
        String colorXPath = "//span[@data-color='%s']";
        WebElement colorEle = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(colorXPath,color))));
        clickElement(colorEle);
    }
    public void clickElement(WebElement element) {
        element.click();
        // Bo sung them thong tin bao cao
        System.out.println("Click to " + element);
    }
    public void sleep(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
