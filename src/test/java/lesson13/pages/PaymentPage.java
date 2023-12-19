package lesson13.pages;

import lesson13.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.logging.Logger;

public class PaymentPage extends BasePage {
    Logger logger = Logger.getLogger(HomePage.class.getName());
    WebDriver mWebDriver;
    WebDriverWait mWebDriverWait;
    public PaymentPage(WebDriver mWebDriver) {
        super(mWebDriver);
        this.mWebDriver = mWebDriver;
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
    }
    public void verifyPayment(HashMap<String, String> data){
        logger.info("Verify access to Payment Page");
        WebElement paymentPageTitle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[normalize-space()='Payment Received']")));
        verifyValue(paymentPageTitle);

        logger.info("Verify Payment info");
        WebElement searchPayment = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='search']")));
        clickElement(searchPayment);
        searchPayment.clear();
        searchPayment.sendKeys(data.get("invoice"));
        searchPayment.sendKeys(Keys.ENTER);

        // Verify Invoice ID
        WebElement invoiceID = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='"+data.get("invoice")+"']")));
        verifyInfo(invoiceID.getText(),data.getOrDefault("invoice",null));


        // Verify Note
        WebElement noteEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[normalize-space()='"+data.getOrDefault("description",null)+"']")));
        verifyInfo(noteEle.getText(),data.getOrDefault("description",null));

        // Verify Payment date
        WebElement paymentDateEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[normalize-space()='"+data.getOrDefault("description",null)+"']")));
        verifyInfo(paymentDateEle.getText(),data.getOrDefault("description",null));

        // Verify Payment Method
//        WebElement paymentMethodEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[normalize-space()='Cash']")));
//        verifyInfo(paymentDateEle.getText(),"Cash");
    }
}
