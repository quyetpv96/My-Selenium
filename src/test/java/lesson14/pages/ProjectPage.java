package lesson14.pages;

import lesson14.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.logging.Logger;

public class ProjectPage extends BasePage {
    Logger logger = Logger.getLogger(ClientPage.class.getName());
    WebDriver mWebDriver;
    WebDriverWait mWebDriverWait;
    public ProjectPage(WebDriver mWebDriver) {
        super(mWebDriver);
        this.mWebDriver = mWebDriver;
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
    }

    /**
     * verifyProjectDashboard
     */
    public void verifyProjectDashboard(){
        logger.info("verifyProjectDashboard");
        // Verify page title
        WebElement projectPageTitle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[normalize-space()='Projects']")));
        verifyInfo(projectPageTitle);
    }

    /**
     * Access ManageLabelPage
     * @return ManageLabelPage
     */
    public ManageLabelPage gotoManageLabelPage(){
        logger.info("Click to Manage labels");
        String manageLabelXPath = "//a[@class='btn btn-default' and normalize-space()='Manage labels']";
        clickElement(By.xpath(manageLabelXPath));
        return new ManageLabelPage(mWebDriver);
    }

    /**
     * Access AddProjectPage
     * @return AddProjectPage
     */
    public AddProjectPage gotoAddProjectPage(){
        logger.info("Click to Add project");
        String addProjectXPath = "//a[@class='btn btn-default' and normalize-space()='Add project']";
        clickElement(By.xpath(addProjectXPath));
        return new AddProjectPage(mWebDriver);
    }

    /**
     * verifyNewProject
     * @param data : project
     */
    public void verifyNewProject(HashMap<String, String> data){
        logger.info("verifyNewProject");
        WebElement searchProject = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='search']")));
        clickElement(searchProject);
        searchProject.clear();
        searchProject.sendKeys(data.get("title"));
        searchProject.sendKeys(Keys.ENTER);
        sleep(3);

        // Verify Title project
        WebElement titleProject = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='"+data.get("title")+"']")));
        verifyInfo(titleProject);

        // Verify Client
        if (data.get("projectType").equals("Client Project")) {
            WebElement clientProject = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='"+data.get("client")+"']")));
            verifyInfo(clientProject);
        }else {
            WebElement clientProjectDefault = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='"+data.get("title")+"']"+"/../following-sibling::td[normalize-space()='-']")));
            verifyInfo(clientProjectDefault);
        }
        /**
         *  Coding Processing
         */
        // Verify Price

        // Verify start date/deadline

        // Verify status
    }
}
