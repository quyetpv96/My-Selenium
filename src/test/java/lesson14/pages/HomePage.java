package lesson14.pages;

import lesson14.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
     * Access Projects Page
     * @return ProjectPage
     */
    public ProjectPage gotoProjectsPage(){
        logger.info("Click Projects Element");
        String projectXPath = "//a[@href='https://rise.fairsketch.com/projects/all_projects']";
        clickElement(By.xpath(projectXPath));
        return new ProjectPage(mWebDriver);
    }
}
