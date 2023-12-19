package practice2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class BasePage {
    private WebDriver mWebDriver;
    private WebDriverWait mWebDriverWait;

    protected BasePage(WebDriver webDriver){
        mWebDriver = Objects.isNull(webDriver) ? DriverManager.getWebDriver() : webDriver;
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
    }

    public void inputText(String attribute, String value){
        String INPUT_FORM = "//input[@placeholder='%s']";
        WebElement element = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(INPUT_FORM, attribute))));
        element.clear();
        element.sendKeys(value);
    }
}
