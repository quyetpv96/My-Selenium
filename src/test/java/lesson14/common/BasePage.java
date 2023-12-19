package lesson14.common;


import lesson14.report.ExtentReportManager;
import lesson14.report.ExtentTestManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.MessageFormat;
import java.time.Duration;
import java.util.Objects;

public class BasePage {

    private WebDriver mWebDriver;
    private WebDriverWait mWebDriverWait;
    private JavascriptExecutor javascriptExecutor;


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
     * Create a javascript executor
     */
    public JavascriptExecutor getJavascriptExecutor() {
        return Objects.nonNull(javascriptExecutor) ? javascriptExecutor : (JavascriptExecutor) mWebDriver;
    }

    /**
     * Input the text to web element
     *
     * @param webObject
     * @param value
     */
    public void inputTextTo(Object webObject, String value) {
        WebElement element = findElement(webObject);

        element.clear();
        element.sendKeys(value);

        // Add Report
        addReportInfo(LogType.INFO, MessageFormat.format("Input text {0} to {1}", value, element));
    }

    /**
     * Click to element
     * @param webObject
     */
    public void clickElement(Object webObject) {
        WebElement element = findElement(webObject);

        getWebWaitDriver().until(ExpectedConditions.elementToBeClickable(element));
        element.click();

        // Add report
        addReportInfo(LogType.INFO, MessageFormat.format("Clicked  {0}", element));
    }

    /**
     * Find the web element by object
     * If WebElement Object : Return
     * If By Object : Wait and finding via Selenium
     *
     * @param webObject : Object
     * @return : WebElement or Null
     */
    private WebElement findElement(Object webObject) {
        // Find - WebElement Object -> return
        if (webObject instanceof WebElement) return (WebElement) webObject;

        // Find - By object -> Wait and return a WebElement
        if (webObject instanceof By) {
            return getWebWaitDriver().until(ExpectedConditions.visibilityOfElementLocated((By) webObject));
        }
        return null;
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

    /**
     * Verify information on page
     */
    public void verifyInfo(WebElement element){
        if (Objects.nonNull(element)){
            addReportInfo(LogType.VERIFY, "Verify element info");
        } else {
            Assert.fail("Info is wrong");
        }
    }
    public void inputTextTo(String valueOfAttribute, String value){
        String INPUT_FORM = "//input[@placeholder='%s']";
        WebElement element = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(INPUT_FORM, valueOfAttribute))));
        clickElement(element);
        element.clear();
        element.sendKeys(value);
        //element.sendKeys(Keys.ENTER);
    }
    public void description(String description){
        WebElement element = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@placeholder='Description']")));
        clickElement(element);

        WebElement descriptionEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='note-editable']")));
        descriptionEle.clear();
        descriptionEle.sendKeys(description);
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
    public void sleep(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
