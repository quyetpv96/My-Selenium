package lesson11.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Objects;
import java.util.logging.Logger;

public class ClientPage {
    Logger logger = Logger.getLogger(ClientPage.class.getName());
    WebDriver mWebDriver;
    WebDriverWait mWebDriverWait;

    public ClientPage(WebDriver mWebDriver) {
        this.mWebDriver = mWebDriver;
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
    }

    /**
     * Verify the client dashboard
     */
    public void verifyClientDashboard() {
        logger.info("verifyClientDashboard");
        String nameEleXPath = "//div[contains(@class ,'card-body')]//span[normalize-space()='%s']";
        String titleELeXPath = "//div[contains(@class ,'card-body')]//strong[normalize-space()='%s']";

        // Verify total clients
        String totalClientXPath = String.format(nameEleXPath, "Total clients");
        WebElement totalClient = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(totalClientXPath)));
        Assert.assertTrue(Objects.nonNull(totalClient), "Verify Total Client");


        // Verify total contacts
        String totalContactsXPath = String.format(nameEleXPath, "Total contacts");
        WebElement otalContacts = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(totalContactsXPath)));
        Assert.assertTrue(Objects.nonNull(otalContacts), "Verify Total contacts");

        // Verify Clients has unpaid invoices
        String clientUnPaidXPath = String.format(titleELeXPath, "Clients has unpaid invoices");
        WebElement clientUnPaid = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(clientUnPaidXPath)));
        Assert.assertTrue(Objects.nonNull(clientUnPaid), "Verify Clients has unpaid invoices");
    }

    /**
     * Go to Client Detail Page
     * @return
     */
    public ClientDetailPage goToClientDetail() {
        String clientXPath = "//ul[@role='tablist']//a[text()='Clients']";
        WebElement clientEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(clientXPath)));
        clientEle.click();
        return new ClientDetailPage(mWebDriver);
    }


    /**
     * Go to Contact Page
     * @return
     */
    public ClientContactPage goToContactDetail() {
        String contactXPath = "//ul[@role='tablist']//a[text()='Contacts']";
        WebElement contactEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(contactXPath)));
        contactEle.click();
        return new ClientContactPage(mWebDriver);
    }
}
