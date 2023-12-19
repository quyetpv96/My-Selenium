package practice2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage{
    private WebDriver mWebDriver;
    private WebDriverWait mWebDriverWait;
    public LoginPage(WebDriver webDriver) {
        super(webDriver);
        this.mWebDriver = webDriver;
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
    }

    public void gotoWebsite(String url){
        mWebDriver.get(url);
    }

    public HomePage login(String email, String password){

        inputText("Email",email);
        inputText("Password",password);

        WebElement loginBtn = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Sign in']")));
        loginBtn.click();

        return new HomePage(mWebDriver);
    }
}
