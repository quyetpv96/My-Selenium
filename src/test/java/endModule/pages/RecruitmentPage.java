package endModule.pages;

import endModule.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.logging.Logger;

public class RecruitmentPage extends BasePage {
    Logger logger = Logger.getLogger(HomePage.class.getName());
    WebDriver mWebDriver;
    WebDriverWait mWebDriverWait;
    protected RecruitmentPage(WebDriver mWebDriver) {
        super(mWebDriver);
        this.mWebDriver = mWebDriver;
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
    }

    /**
     * verify Access to RecruitmentPage
     */
    public void verifyAccessRecruitmentPage(){
        logger.info("verifyAccessRecruitmentPage");
        WebElement recruitmentPageTitle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[normalize-space()='Recruitment']")));
        verifyInfo(recruitmentPageTitle);
    }

    /**
     * Click to [+Add] candidate
     * @return CandidatePage
     */
    public CandidatePage gotoCandidatePage(){
        logger.info("Click to candidate");
        WebElement candidateMenu = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Candidates']")));
        clickElement(candidateMenu);

        logger.info("Click to [+Add] candidate");
        WebElement addCandidateBtn = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Add']")));
        clickElement(addCandidateBtn);
        return new CandidatePage(mWebDriver);
    }
    /**
     * Verify candidate info > delete candidate
     */
    public void verifyAndDeleteCandidate(HashMap<String, String> data){
        logger.info("Search Candidate Info");
        searchCandidateInfo(data);

        // Verify Vacancy
        WebElement vacancy = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Vacancy']/../../following-sibling::div//div//div//div/div[normalize-space()='"+data.getOrDefault("vacancy", null)+"']")));
        verifyInfo(vacancy);

        // Verify Candidate Name
        WebElement candidate = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Candidate']/../../following-sibling::div//div//div//div/div[normalize-space()='"+data.getOrDefault("firstName", null)+" "+data.getOrDefault("middleName", null)+" "+data.getOrDefault("lastName", null)+"']")));
        verifyInfo(candidate);

        // Verify Date of Application
        if (System.getProperty("dateType").equals("dd-mm-yyyy")){
            WebElement doAD = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Date of Application']/../../following-sibling::div//div//div//div/div[normalize-space()='"+data.getOrDefault("doAD", null)+"']")));
            verifyInfo(doAD);
        }
        if (System.getProperty("dateType").equals("yyyy-mm-dd")){
            WebElement doAY = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Date of Application']/../../following-sibling::div//div//div//div/div[normalize-space()='"+data.getOrDefault("doAY", null)+"']")));
            verifyInfo(doAY);
        }
        if (System.getProperty("dateType").equals("mm-dd-yyyy")){
            WebElement doAY = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Date of Application']/../../following-sibling::div//div//div//div/div[normalize-space()='"+data.getOrDefault("doAM", null)+"']")));
            verifyInfo(doAY);
        }

        // Verify attach file download icon
        WebElement attachFileIcon = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='oxd-icon bi-download']")));
        verifyInfo(attachFileIcon);

        // Verify review btn icon
        WebElement eyeIcon = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='oxd-icon bi-eye-fill']")));
        verifyInfo(eyeIcon);

        // Verify delete btn icon
        WebElement deleteCandidateIcon = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='oxd-icon bi-trash']")));
        verifyInfo(deleteCandidateIcon);

        // Delete Candidate
        clickElement(deleteCandidateIcon);

        // Click to button [Yes, Delete]
        WebElement deleteBtn = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes, Delete']")));
        clickElement(deleteBtn);
    }
    /**
     * Search candidate
     */
    public void searchCandidateInfo(HashMap<String, String> data){
        logger.info("Click to candidate");
        WebElement candidateMenu = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Candidates']")));
        clickElement(candidateMenu);

        WebElement searchBtn = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        if (!searchBtn.isDisplayed()){
            WebElement showCandidateBtn = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='oxd-icon bi-caret-down-fill']")));
            clickElement(showCandidateBtn);
        }

        // Select candidate vacancy
        WebElement selectVacancy = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[normalize-space()='Vacancy']/../following-sibling::div//div[@class='oxd-select-text--after']")));
        clickElement(selectVacancy);

        WebElement vacancy = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[normalize-space()='"+data.getOrDefault("vacancy", null)+"']")));
        clickElement(vacancy);

        // Select Hiring Manager
//        WebElement selectManager = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[normalize-space()='Hiring Manager']/../following-sibling::div//div[@class='oxd-select-text--after']")));
//        clickElement(selectManager);
//
//        WebElement manager = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[normalize-space()='"+System.getProperty("manager")+"']")));
//        clickElement(manager);

        // Select Status
        WebElement selectStatus = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[normalize-space()='Status']/../following-sibling::div//div[@class='oxd-select-text--after']")));
        clickElement(selectStatus);

        WebElement status = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[normalize-space()='"+System.getProperty("status")+"']")));
        clickElement(status);

        // Select Keywords
        WebElement keyWorkd = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter comma seperated words...']")));
        clickElement(keyWorkd);
        keyWorkd.sendKeys(data.getOrDefault("keywords", null));

        // Click to [Search] button
        logger.info("Click to [Search] button");
        clickElement(searchBtn);
    }
}
