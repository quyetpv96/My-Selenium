package practiceTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import practiceAddData.common.DriverManager;

import java.time.Duration;
import java.util.Objects;

public class BasePage {
    private WebDriver mWebDriver;
    private WebDriverWait mWebDriverWait;

    protected BasePage(WebDriver driver) {
        mWebDriver = Objects.isNull(driver) ? DriverManager.getWebDriver() : driver;
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
    }

    /**
     * Input the text to web element
     *
     * @param value
     */
    public void inputTextTo(String valueOfAttribute, String value) {
        String INPUT_FORM = "//input[@placeholder='%s']";
        WebElement element = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(INPUT_FORM,valueOfAttribute))));

        element.clear();
        element.sendKeys(value);
    }
}
