package lesson13.pages;

import lesson13.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

public class HomePage extends BasePage {
    Logger logger = Logger.getLogger(HomePage.class.getName());
    WebDriver mWebDriver;
    WebDriverWait mWebDriverWait;
    public HomePage(WebDriver mWebDriver) {
        super(mWebDriver);
        this.mWebDriver = mWebDriver;
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
    }

    /**
     * Access Client Page
     * @return
     */
    public ClientPage gotoClientsPage() {
        logger.info("Click Client Element");
        String clientXPath ="//a[@href='https://rise.fairsketch.com/clients']";
        WebElement clientEle = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(clientXPath)));
        clickElement(clientEle);

        return new ClientPage(mWebDriver);
    }
    public TasksPage gotoTasksPage() {
        logger.info("Click Tasks Element");
        String tasksXPath ="//a[@href='https://rise.fairsketch.com/tasks/all_tasks']";
        WebElement tasksEle = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(tasksXPath)));
        clickElement(tasksEle);

        return new TasksPage(mWebDriver);
    }
    public EventPage gotoEventPage() {
        logger.info("Click Events Element");
        String eventsXPath ="//a[@href='https://rise.fairsketch.com/events']";
        WebElement eventsEle = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(eventsXPath)));
        clickElement(eventsEle);

        return new EventPage(mWebDriver);
    }

    /**
     * Access InvoicesPage
     * @return InvoicesPage
     */
    public InvoicesPage gotoInvoicesPage(){
        logger.info("Click Sale Element");
        String saleXPath = "//span[normalize-space()='Sales']";
        WebElement saleEle = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(saleXPath)));
        clickElement(saleEle);

        logger.info("Click Invoice Element");
        String invoiceXPath = "//a[@href='https://rise.fairsketch.com/invoices']";
        WebElement invoiceEle = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(invoiceXPath)));
        clickElement(invoiceEle);
        return new InvoicesPage(mWebDriver);
    }
    public PaymentPage gotoPaymentPage(){
        logger.info("Click Sale Element");
        String saleXPath = "//span[normalize-space()='Sales']";
        WebElement saleEle = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(saleXPath)));
        clickElement(saleEle);

        logger.info("Click Payment Element");
        String paymentXPath = "//a[@href='https://rise.fairsketch.com/invoice_payments']";
        WebElement paymentEle = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(paymentXPath)));
        clickElement(paymentEle);
        return new PaymentPage(mWebDriver);
    }
}
