package lesson11.pages;

import lesson12.pages.ClientPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.logging.Logger;

public class AddTaskPage {
    Logger logger = Logger.getLogger(ClientPage.class.getName());
    WebDriver mWebDriver;
    WebDriverWait mWebDriverWait;

    public AddTaskPage(WebDriver mWebDriver) {
        this.mWebDriver = mWebDriver;
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
    }
    public void verifyAddTask() {
        // Verify the button Save & show
        String saveAndShowID = "save-and-show-button";
        WebElement saveAndShowEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(saveAndShowID)));
        Assert.assertTrue(saveAndShowEle.isDisplayed(),"The button Save & show displayed");
    }
}
