package practice2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage{
    private WebDriver mWebDriver;
    private WebDriverWait mWebDriverWait;
    public HomePage(WebDriver webDriver) {
        super(webDriver);
        this.mWebDriver = webDriver;
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
    }
}
