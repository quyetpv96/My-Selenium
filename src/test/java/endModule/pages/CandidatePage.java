package endModule.pages;

import endModule.common.BasePage;
import org.apache.poi.ss.formula.atp.Switch;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.HashMap;
import java.util.logging.Logger;

public class CandidatePage extends BasePage {
    Logger logger = Logger.getLogger(HomePage.class.getName());
    WebDriver mWebDriver;
    WebDriverWait mWebDriverWait;

    protected CandidatePage(WebDriver mWebDriver) {
        super(mWebDriver);
        this.mWebDriver = mWebDriver;
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
    }

    /**
     * verify Access to CandidatePage
     */
    public void verifyAccessCandidatePage() {
        logger.info("verifyAccessCandidatePage");
        WebElement candidatePageTitle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[normalize-space()='Add Candidate']")));
        verifyInfo(candidatePageTitle);
    }

    /**
     * Input candidate info
     */
    public void addCandidateInfo(HashMap<String, String> data) throws AWTException {
        logger.info("addCandidateInfo");
        //Input Candidate info
        insertTextTo("Full Name", "First Name", data.getOrDefault("firstName", null));
        insertTextTo("Full Name", "Middle Name", data.getOrDefault("middleName", null));
        insertTextTo("Full Name", "Last Name", data.getOrDefault("lastName", null));
        insertTextTo("Email", "Type here", data.getOrDefault("email", null));
        insertTextTo("Contact Number", "Type here", data.getOrDefault("contactNumber", null));
        insertTextTo("Keywords", "Enter comma seperated words...", data.getOrDefault("keywords", null));
        inputDate(data);

        // Input Note
        WebElement noteEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@placeholder='Type here']")));
        noteEle.clear();
        noteEle.sendKeys(data.getOrDefault("note", null));

        // Input Vacancy
        selectVacancy(data.getOrDefault("vacancy", null));

        // Attach resume
        attachFile(data);

        // Check box consent Data
        if (data.get("consentDataCheckBox").equals("checked")) {
            WebElement consentDataCheckBox = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='oxd-checkbox-input oxd-checkbox-input--active --label-right oxd-checkbox-input']")));
            clickElement(consentDataCheckBox);
        }

        // Click to [Save] candidate info
        logger.info("Click to [Save]");
        WebElement saveCandidateBtn = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        clickElement(saveCandidateBtn);

        // Verify Candidate just added
        verifyNewCandidate(data);

    }

    /**
     * Verify candidate info just added after click [Save]
     *
     * @param data
     */
    public void verifyNewCandidate(HashMap<String, String> data) {
        logger.info("verifyNewCandidate");
        // Wait candidate added
        WebElement waitCandidateAdded = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[normalize-space()='Candidate Profile']")));
        verifyInfo(waitCandidateAdded);

        // Verify FullName of Candidate
        WebElement nameCandidate = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='" + data.getOrDefault("firstName", null) + " " + data.getOrDefault("middleName", null) + " " + data.getOrDefault("lastName", null) + "']")));
        verifyInfo(nameCandidate);

        // Verify Vacancy
        WebElement vacancy = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='"+data.getOrDefault("vacancy", null)+"']")));
        verifyInfo(vacancy);

        // Verify resume
        WebElement resume = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@title='"+data.getOrDefault("resume",null)+"']")));
        verifyInfo(resume);

        // Get Hiring Manager name
        WebElement managerEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[normalize-space()='Hiring Manager']/../following-sibling::div//p")));
        System.setProperty("manager",managerEle.getText());

        // Get status Candidate
        WebElement statusCandidate = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='oxd-text oxd-text--p oxd-text--subtitle-2']")));
        System.setProperty("status",statusCandidate.getText().substring(8));
    }

    /**
     * input date of Application
     *
     */
    private void inputDate(HashMap<String, String> data) {
        WebElement doA = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[normalize-space()='Date of Application']/../following-sibling::div//input[@class='oxd-input oxd-input--active']")));
        String dateType = doA.getAttribute("placeholder");
        clickElement(doA);
        doA.sendKeys(Keys.CONTROL, "a");
        doA.sendKeys(Keys.DELETE);
        String Type = null;
        if (dateType.equals("dd-mm-yyyy")){
            Type = System.setProperty("dateType","dd-mm-yyyy");
            doA.sendKeys(data.getOrDefault("doAD", null));
        }
        if (dateType.equals("yyyy-mm-dd")){
            Type = System.setProperty("dateType","yyyy-mm-dd");
            doA.sendKeys(data.getOrDefault("doAY", null));
        }
        if (dateType.equals("mm-dd-yyyy")){
            Type = System.setProperty("dateType","mm-dd-yyyy");
            doA.sendKeys(data.getOrDefault("doAM", null));
        }
        clickElement(doA);
    }

    /**
     * Attach file resume
     *
     * @throws AWTException
     */
    private void attachFile(HashMap<String, String> data) throws AWTException {
        String addResumeXPath = "//div[normalize-space()='Browse']";
        WebElement element = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(addResumeXPath)));
        element.click();
        String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\endModule\\data\\"+data.getOrDefault("resume",null);
        System.out.println(filePath);
        sleep(5);
        // creating object of Robot class
        Robot rb = null;
        try {
            rb = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }

        // copying File path to Clipboard
        StringSelection str = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

        // press Contol+V for pasting
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);

        // release Contol+V for pasting
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);

        // for pressing and releasing Enter
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
        sleep(5);
    }

    /**
     * Select vacancy
     */
    private void selectVacancy(String vacancy){
        WebElement vacancyEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[normalize-space()='Vacancy']/../following-sibling::div")));
        clickElement(vacancyEle);

        WebElement vacancyMember = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[normalize-space()='" + vacancy + "']")));
        clickElement(vacancyMember);
    }
}
