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

public class AddInvoicePage extends BasePage {
    Logger logger = Logger.getLogger(TasksPage.class.getName());
    WebDriver mWebDriver;
    WebDriverWait mWebDriverWait;
    protected AddInvoicePage(WebDriver mWebDriver) {
        super(mWebDriver);
        this.mWebDriver = mWebDriver;
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
    }

    /**
     * verify New Label Integration On AddInvoice
     * @param data : label
     */
    public void  verifyNewLabelIntegrationOnAddInvoice(HashMap<String, String> data){
        logger.info("verifyNewLabelIntegrationOnAddInvoice");
        WebElement labelIntegration = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[normalize-space()='Labels']/..//input[@class='select2-input select2-default']")));
        clickElement(labelIntegration);

        WebElement labelEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='select2-result-label' and normalize-space()='"+data.getOrDefault("label",null)+"']")));
        verifyValue(labelEle);
        clickElement(labelEle);

        WebElement closeAddInvoice = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h4[normalize-space()='Add invoice']/../following-sibling::div//button[@class='btn btn-default']")));
        clickElement(closeAddInvoice);
    }
}
