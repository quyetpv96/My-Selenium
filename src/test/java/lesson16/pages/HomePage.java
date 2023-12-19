package lesson16.pages;

import lesson16.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;
import java.time.Duration;
import java.util.HashMap;
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
        //WebElement clientEle = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(clientXPath)));
        clickElement(By.xpath(clientXPath));

        return new ClientPage(mWebDriver);
    }

    /**
     * Access Project Page
     * @return
     */
    public ProjectPage gotoProjectPage() {
        logger.info("Click  Element");
        String clientXPath ="//a[@href='https://rise.fairsketch.com/projects/all_projects']";
        //WebElement clientEle = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(clientXPath)));
        clickElement(By.xpath(clientXPath));

        return new ProjectPage(mWebDriver);
    }

    /**
     * Verify New Account info
     * @param data
     */
    public void verifyAccount(HashMap<String, String> data){
        logger.info("Verify New Account info");
        WebElement profileEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='user-name ml10']")));
        //verifyInfo(profileEle.getText(),data.get("lastName")+" "+data.get("firstName"));
        verifyInfo(profileEle.getText(),data.get("firstName")+" "+data.get("lastName"));
    }

    /**
     * Go to My profile
     * @return MyProfilePage
     */
    public MyProfilePage gotoMyProfilePage(){
        logger.info("Go to My profile");
        WebElement profileEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='user-name ml10']")));
        clickElement(profileEle);

        WebElement myProfile = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='dropdown-menu dropdown-menu-end w200 user-dropdown-menu show']//a[normalize-space()='My Profile']")));
        clickElement(myProfile);
        return new  MyProfilePage(mWebDriver);
    }
}
