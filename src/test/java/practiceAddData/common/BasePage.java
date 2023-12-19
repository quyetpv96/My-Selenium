package practiceAddData.common;

import practiceAddData.common.LogType;
import practiceAddData.report.ExtentReportManager;
import practiceAddData.report.ExtentTestManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.MessageFormat;
import java.time.Duration;
import java.util.Objects;

public class BasePage {

    private WebDriver mWebDriver;
    private WebDriverWait mWebDriverWait;

    protected BasePage(WebDriver driver) {
        mWebDriver = Objects.isNull(driver) ? DriverManager.getWebDriver() : driver;
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
    }

    /**
     * Create a web wait driver
     *
     * @param secondTimeOuts : timeout
     * @return Object WebWaitDriver
     */
    public WebDriverWait getWebWaitDriver(long... secondTimeOuts) {
        Duration timeOut = secondTimeOuts.length > 0 ? Duration.ofSeconds(secondTimeOuts[0]) : Duration.ofSeconds(10);
        mWebDriverWait = new WebDriverWait(mWebDriver, timeOut);
        return mWebDriverWait;
    }
    /**
     * Input the text to web element
     *
     * @param value
     */
    public void inputTextTo(String tagName, String valueOfAttribute, String value) {
        String INPUT_FORM = "//%s[@placeholder='%s']";
        WebElement element = getWebWaitDriver().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(INPUT_FORM,tagName,valueOfAttribute))));

        element.clear();
        element.sendKeys(value);

        // Add Report
        addReportInfo(LogType.INFO, MessageFormat.format("Input text {0} to {1}", value, element));
    }

    /**
     * Click to element
     * @param element
     */
    public void clickElement(WebElement element) {

        getWebWaitDriver().until(ExpectedConditions.elementToBeClickable(element));
        element.click();

        // Add report
        addReportInfo(LogType.INFO, MessageFormat.format("Clicked  {0}", element));
    }
    /**
     * Add more information for Report: Including Extent
     * You can add more report at this function.
     *
     * @param extMsg
     */
    public static void addReportInfo(LogType logType, String extMsg) {
        // Add for Extent Report
        if (ExtentTestManager.getExtentTest() != null) {
            if (logType.equals(LogType.INFO)) ExtentReportManager.info(extMsg);
            else ExtentReportManager.pass(extMsg);
        }
    }
    public void sleep(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Input repeat info element
     * @param repeat
     * @param frequency
     * @param cycles
     */

    public void repeatOpt(String repeat, String frequency, String cycles) {
        //Checkbox repeat option
        WebElement repeatOpt = getWebWaitDriver().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@id,'recurring')]")));
        clickElement(repeatOpt);

        //Repeat every
        WebElement repeatEle = getWebWaitDriver().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[normalize-space()='Repeat every']/..//input[@id='repeat_every']")));
        clickElement(repeatEle);
        repeatEle.clear();
        repeatEle.sendKeys(repeat);

        //Frequency
        WebElement freqEle = getWebWaitDriver().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[normalize-space()='Repeat every']/..//span[@class='select2-chosen']")));
        clickElement(freqEle);

        WebElement selectFreq = getWebWaitDriver().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='select2-drop']//input[contains(@class,'select2-input')]")));
        clickElement(selectFreq);
        selectFreq.sendKeys(frequency);
        selectFreq.sendKeys(Keys.ENTER);

        //Cycles
        inputTextTo("input","Cycles", cycles);
    }

    /**
     * Verify info element
     * @param actu : data thực tế
     * @param exp : data mong đợi
     */
    public void verifyInfo(String actu, String exp){
        if (actu.equals(exp)){
            addReportInfo(LogType.VERIFY, "Verify element info");
        } else {
            Assert.assertEquals(actu,exp,"Info is wrong");
        }
    }

    /**
     * Verify value of element
     * @param element
     */
    public void verifyValue(WebElement element){
        if (Objects.nonNull(element)){
            addReportInfo(LogType.VERIFY, "Verify element info");
        } else {
            Assert.fail("Info is wrong");
        }
    }

    public void selectInfo(String valueOfAttribute, String value){
        String selectionXPath = "//label[normalize-space()='%s']/..//span[@class='select2-chosen']";
        WebElement element = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(selectionXPath, valueOfAttribute))));
        clickElement(element);

        WebElement searchEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='select2-drop']//input[contains(@class,'select2-input')]")));
        searchEle.clear();
        searchEle.sendKeys(value);
        searchEle.sendKeys(Keys.ENTER);
    }
    public void description(String description){
        WebElement element = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@placeholder='Description']")));
        clickElement(element);

        WebElement descriptionEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='note-editable']")));
        descriptionEle.clear();
        descriptionEle.sendKeys(description);
    }
    public void timeline(String timeType, String time) {
        String timelineXPath = "//label[normalize-space()='%s']/..//input";
        WebElement timeline = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(timelineXPath, timeType))));
        clickElement(timeline);
        timeline.sendKeys(time);
        timeline.sendKeys(Keys.ENTER);
    }
    public void searchAndSelect(String textInputs) {
        //Click to search area
        WebElement searchEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='select2-drop']//input[contains(@class,'select2-input')]")));
        clickElement(searchEle);

        //Input information for selection
        searchEle.clear();
        searchEle.sendKeys(textInputs);
        searchEle.sendKeys(Keys.ENTER);
    }
    public void collabAndLabelInsert(String valueOfAttribute, String textInput){
        String collabAndLabelXPath = "//label[normalize-space()='%s']/../input";
        WebElement element = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(collabAndLabelXPath,valueOfAttribute))));
        clickElement(element);
        element.clear();
        element.sendKeys(textInput);
        element.sendKeys(Keys.ENTER);
    }
}
