package lesson16.pages;

import lesson16.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.logging.Logger;

public class SignUpPage extends BasePage {
    Logger logger = Logger.getLogger(LoginPage.class.getName());
    WebDriver mWebDriver;
    WebDriverWait mWebDriverWait;
    protected SignUpPage(WebDriver mWebDriver) {
        super(mWebDriver);
        this.mWebDriver = mWebDriver;
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
    }

    /**
     * Verify Access Sign Up Page
     */
    public void verifyAccessSignUpPage(){
        // Wait sign up page loaded
        WebElement signUpPageEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Create an account as a new client.']")));
        verifyInfo(signUpPageEle);
    }

    /**
     * Fill account info > Creat account
     * @param data
     */
    public void creatAccount(HashMap<String, String> data){
        logger.info("Fill account info");
        insertTextTo("first_name",data.get("firstName"));
        insertTextTo("last_name",data.get("lastName"));
        if (data.get("type").equals("organization")){
            WebElement typeOrganization = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='type_organization']")));
            clickElement(typeOrganization);
            insertTextTo("company_name",data.get("company"));
        } else {
            WebElement typePerson = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='type_person']")));
            clickElement(typePerson);
        }
        insertTextTo("email",data.get("email"));
        insertTextTo("password",data.get("password"));
        insertTextTo("retype_password",data.get("retypePw"));

        // Click [Sign up]
        WebElement signUpBtn = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Sign up']")));
        clickElement(signUpBtn);

        // Verify creat account success
        WebElement creatAccountNoti = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Your account has been created successfully! ']")));
        verifyInfo(creatAccountNoti);
    }

    /**
     * Back to Login Page
     * @return LoginPage
     */
    public LoginPage backtoLoginPage(){
        WebElement signUpBtn = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='https://rise.fairsketch.com/signin']")));
        clickElement(signUpBtn);
        return new LoginPage(mWebDriver);
    }
}
