package lesson15.common;


import lesson15.report.ExtentReportManager;
import lesson15.report.ExtentTestManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        addReportInfo(lesson15.common.LogType.INFO, MessageFormat.format("Input text {0} to {1}", value, element));
    }

    /**
     * Click to element
     *
     * @param webObject
     */
    public void clickElement(Object webObject) {
        WebElement element = findElement(webObject);

        getWebWaitDriver().until(ExpectedConditions.elementToBeClickable(element));
        element.click();

        // Add report
        addReportInfo(lesson15.common.LogType.INFO, MessageFormat.format("Clicked  {0}", element));
    }

    /**
     * Find the web element by object
     * If WebElement Object : Return
     * If By Object : Wait and finding via Selenium
     *
     * @param webObject : Object
     * @return : WebElement or Null
     */
    public WebElement findElement(Object webObject) {
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
    public static void addReportInfo(lesson15.common.LogType logType, String extMsg) {
        // Add for Extent Report
        if (ExtentTestManager.getExtentTest() != null) {
            if (logType.equals(LogType.INFO)) ExtentReportManager.info(extMsg);
            else ExtentReportManager.pass(extMsg);
        }
    }

    public WebElement waitForElementVisible(By byObject) {
        return mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(byObject));
    }

    public void scrollDown(int distance) {
        getJSExecutor().executeScript(String.format("window.scrollBy(0,%s)", distance));
    }

    public JavascriptExecutor getJSExecutor() {
        return Objects.isNull(this.javascriptExecutor) ? ((JavascriptExecutor) mWebDriver) : this.javascriptExecutor;
    }

    /**
     * @param element : Phần tử chứa scroller
     */
    public void scrollToEndOfPage(WebElement element) {
        getJSExecutor().executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", element);
    }
}
