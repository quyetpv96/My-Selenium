package endModule.pages;

import endModule.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    /**
     * Access to Recruitment on menu
     * @return RecruitmentPage
     */
    public RecruitmentPage gotoRecruitmentPage(){
        logger.info("gotoRecruitmentPage");
        String recruitmentXPath = "//span[normalize-space()='Recruitment']";
        WebElement recruitmenEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(recruitmentXPath)));
        clickElement(recruitmenEle);
        return new RecruitmentPage(mWebDriver);
    }
}
