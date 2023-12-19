package practiceHome.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import practiceHome.common.BasePage;

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

    public PIMPage gotoPIMPage(){
        String pimXPath = "//span[normalize-space()='PIM']";
        WebElement pimEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pimXPath)));
        clickElement(pimEle);
     return new PIMPage(mWebDriver);
    }
}
