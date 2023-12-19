package lesson13.pages;

import lesson13.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.logging.Logger;

public class AddPaymentPage extends BasePage {
    Logger logger = Logger.getLogger(TasksPage.class.getName());
    WebDriver mWebDriver;
    WebDriverWait mWebDriverWait;
    public AddPaymentPage(WebDriver mWebDriver) {
        super(mWebDriver);
        this.mWebDriver = mWebDriver;
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
    }

    /**
     * Verify access to Add Payment Page
     */
    public void verifyAddPaymentPage(){
        logger.info("Verify access to Add Payment Page");
        // Verify page title
        WebElement addPaymentPageTitle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[normalize-space()='Add payment']")));
        verifyValue(addPaymentPageTitle);
    }

    /**
     * input Payment Info
     * @param data : payment info
     */
    public void inputPaymentInfo(HashMap<String, String> data){
        logger.info("inputPaymentInfo");
        // Input Invoice information
        selectInfo("Invoice",data.getOrDefault("invoice",null));
        inputTextTo("input","Payment date",data.getOrDefault("paymentDate",null));
        description(data.getOrDefault("description",null));
        inputTextTo("input","Amount",data.getOrDefault("amount",null));

        // Click [Save]
        WebElement saveBtn = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        clickElement(saveBtn);

        // Click [Close]
        WebElement closeBtn = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button' and text()=' Close' ]")));
        clickElement(closeBtn);

        // Click [x] to close add payment
        WebElement closeAddPayment = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[normalize-space()='Add payment']/following-sibling::button")));
        clickElement(closeAddPayment);
    }
}
