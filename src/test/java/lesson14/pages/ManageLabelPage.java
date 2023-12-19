package lesson14.pages;

import lesson12.pages.ClientPage;
import lesson14.common.BasePage;
import lesson14.common.LogType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.HashMap;
import java.util.Objects;
import java.util.logging.Logger;

public class ManageLabelPage extends BasePage {
    Logger logger = Logger.getLogger(ClientPage.class.getName());
    WebDriver mWebDriver;
    WebDriverWait mWebDriverWait;
    public ManageLabelPage(WebDriver mWebDriver) {
        super(mWebDriver);
        this.mWebDriver = mWebDriver;
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
    }

    /**
     * Verify manage label page
     */
    public void verifyManageLabelPage(){
        logger.info("verifyManageLabelPage");
        WebElement manageLabelPageTitle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[normalize-space()='Manage labels']")));
        verifyInfo(manageLabelPageTitle);
    }

    /**
     * Input label information
     * @param data : label
     */
    public void inputLabelInfo(HashMap<String, String> data){
        logger.info("Input Label information");
        // Input label info
        WebElement labelEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Label']")));
        inputTextTo(labelEle,data.getOrDefault("label",null));

        // Select color for label
        String colorPaletXPath = "//span[@data-color='%s' and @class='color-tag clickable mr15 ']";
        WebElement colorPaletEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(colorPaletXPath,data.getOrDefault("colorPalet",null)))));
        clickElement(colorPaletEle);

        // Click to save label
        WebElement saveBtn = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        clickElement(saveBtn);

        // Verify new label on Manage label page
        WebElement newLabelEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='"+data.getOrDefault("label",null)+"' and @style='background-color: "+data.getOrDefault("colorPalet",null)+"']")));
        verifyInfo(newLabelEle);

        // Click to close Manage label page
        WebElement closeBtn = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button' and normalize-space()='Close']")));
        clickElement(closeBtn);
    }
}
