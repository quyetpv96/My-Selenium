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
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class AddProjectPage extends BasePage {
    Logger logger = Logger.getLogger(ClientPage.class.getName());
    WebDriver mWebDriver;
    WebDriverWait mWebDriverWait;
    protected AddProjectPage(WebDriver mWebDriver) {
        super(mWebDriver);
        this.mWebDriver = mWebDriver;
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
    }

    /**
     * Verify new label Integration on Add Project
     * @param data : label
     */
    public void verifyNewLabelIntegrationOnAddProject(HashMap<String, String> data){
        logger.info("verifyNewLabelIntegrationOnAddProject");
        WebElement labelIntegration = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[normalize-space()='Labels']/..//input[@class='select2-input select2-default']")));
        clickElement(labelIntegration);

        WebElement labelEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='select2-result-label' and normalize-space()='"+data.getOrDefault("label",null)+"']")));
        verifyInfo(labelEle);
        clickElement(labelEle);

        WebElement closeAddProject = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h4[normalize-space()='Add project']/../following-sibling::div//button[@class='btn btn-default']")));
        clickElement(closeAddProject);
    }

    /**
     * Input project info
     * @param data : practice14Data.json
     */
    public void inputProjectInfo(HashMap<String, String> data) {
        logger.info("inputProjectInfo");
        inputTextTo("Title", data.get("title"));
        selectInfo("Project type", data.get("projectType"));
        description(data.get("description"));
        inputTextTo("Start date", data.get("startDate"));
        inputTextTo("Deadline", data.get("deadline"));
        inputTextTo("Price", data.get("price"));

        // Select label
        WebElement labelIntegration = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[normalize-space()='Labels']/..//input[@class='select2-input select2-default']")));
        clickElement(labelIntegration);

        WebElement labelEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='select2-result-label' and normalize-space()='" + data.getOrDefault("label", null) + "']")));
        clickElement(labelEle);

        // Select Client info depend on Project type
        if (data.get("projectType").equals("Client Project")) {
            selectInfo("Client", data.get("client"));
        }

//        // Click [Save]
//        WebElement saveBtn = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
//        clickElement(saveBtn);
//
//        // Click [Close]
//        WebElement closeBtn = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button' and text()=' Close' ]")));
//        clickElement(closeBtn);
//
//        // Click [x] to close add project
//        WebElement closeAddProject = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[normalize-space()='Add project']/following-sibling::button")));
//        clickElement(closeAddProject);

        // Click [Save & continue]
        WebElement saveAndContinueBtn1 = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='save-and-continue-button']")));
        clickElement(saveAndContinueBtn1);

    }
    public void addNewMemberProject(HashMap<String, String> data){
        // Add more member ?
        String addMoreMemberXPath = "//a[@id='add-more-user']";
        WebElement addMoreMember = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(addMoreMemberXPath)));
        if (data.get("addMoreMember").equals("true") && Objects.nonNull(addMoreMember)){
            int add = Integer.parseInt(data.get("totalMember"));
            while(add!=0){
                clickElement(addMoreMember);
                for (int i =0; i< add ;i++){
                    List<WebElement> members = mWebDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//label[normalize-space()='Member']/..//span[@class='select2-chosen']")));
                    WebElement memberSelection = members.get(i);
                    clickElement(memberSelection);
                }
                WebElement searchEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='select2-drop']//input[contains(@class,'select2-input')]")));
                searchEle.clear();
                searchEle.sendKeys(data.get("member"));
                searchEle.sendKeys(Keys.ENTER);
                add--;
            }
        } else {
            selectInfo("Member",data.get("member"));
        }
        // Click [Save & continue]
        WebElement saveAndContinueBtn2 = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='save-and-continue-button']")));
        clickElement(saveAndContinueBtn2);

        // Click [Close]
        WebElement closeBtn = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button' and text()=' Close' ]")));
        clickElement(closeBtn);

        // Click [x] to close Add multiple tasks notice
        WebElement closeAddProject = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[normalize-space()='Add multiple tasks']/following-sibling::button")));
        clickElement(closeAddProject);
    }
}
