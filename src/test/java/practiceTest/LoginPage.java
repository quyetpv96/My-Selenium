package practiceTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Objects;


public class LoginPage extends BasePage{
    WebDriver mWebDriver;
    WebDriverWait mWebDriverWait;

    /**
     * Constructor for Login
     */
    public LoginPage(WebDriver webDriver) {
        super(webDriver);
        this.mWebDriver = webDriver;
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
    }

    /**
     * Goto the website
     *
     * @param url : The website link
     */
    public void gotoWebsite(String url) {
        mWebDriver.get(url);
    }

    /**
     * Login the website
     *
     * @param username : The username
     * @param password : The password
     * @return @HomePage
     */
    public HomePage login(String username, String password) {
        String LOGIN_FORM = "//input[@placeholder='%s']";

        // Wait for login shown
        WebElement loginEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(LOGIN_FORM, "Email"))));
        Assert.assertTrue(Objects.nonNull(loginEle), "Verify that accessing the website");

        // Input username, password
        inputTextTo("Email",username);
        inputTextTo("Password",password);

        // Click login button
        WebElement loginButton = mWebDriver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();

        // Wait for user icon shown
        mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='user-dropdown']//img")));

        // TODO: Verify the title of the page
        return new HomePage(mWebDriver);
    }
}
