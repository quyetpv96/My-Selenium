package lesson13.pages;

import lesson13.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

public class InvoicesPage extends BasePage {
    Logger logger = Logger.getLogger(TasksPage.class.getName());
    WebDriver mWebDriver;
    WebDriverWait mWebDriverWait;
    protected InvoicesPage(WebDriver mWebDriver) {
        super(mWebDriver);
        this.mWebDriver = mWebDriver;
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
    }

    /**
     * verifyInvoicePage
     */
    public void verifyInvoicePage(){
        logger.info("Verify access to Invoice Page");
        // Verify page title
        WebElement invoicePageTitle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[normalize-space()='Invoices']")));
        verifyValue(invoicePageTitle);
    }

    /**
     * Access to ManageLabelPage
     * @return : ManageLabelPage
     */
    public ManageLabelPage gotoManageLabelPage(){
        logger.info("Click to Manage Labels Page");
        String manageLabelXPath = "//a[normalize-space()='Manage labels']";
        WebElement manageLabelEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(manageLabelXPath)));
        clickElement(manageLabelEle);
        return new ManageLabelPage(mWebDriver);
    }

    /**
     * Access to AddPaymentPage
     * @return : AddPaymentPage
     */
    public AddPaymentPage gotoAddPaymentPage(){
        logger.info("Click to Add payment");
        String addPaymentXPath = "//a[@title='Add payment' and @class='btn btn-default mb0']";
        WebElement addPaymentEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(addPaymentXPath)));
        clickElement(addPaymentEle);
        return new AddPaymentPage(mWebDriver);
    }
    public AddInvoicePage gotoAddInvoicePage(){
        logger.info("Click to Add invoice");
        String addInvoiceXPath = "//a[@title='Add invoice' and @class='btn btn-default mb0']";
        WebElement addInvoiceEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(addInvoiceXPath)));
        clickElement(addInvoiceEle);
        return new AddInvoicePage(mWebDriver);
    }
}
