package lesson16.testscript;


import lesson16.common.DriverManager;
import lesson16.common.TestBase;
import lesson16.pages.ClientPage;
import lesson16.pages.HomePage;
import lesson16.pages.LoginPage;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class CodeGymTest extends TestBase {
    private WebDriver mWebDriver;
    private WebDriverWait webDriverWait;
    private String baseURL = "https://james.codegym.vn/login/index.php";
    private LoginPage loginPage;
    private HomePage homePage;
    private ClientPage clientPage;

    @BeforeClass
    public void beforeClass() {
        mWebDriver = DriverManager.getWebDriver();
        homePage = new HomePage(mWebDriver);

        mWebDriver.get(baseURL);

        WebElement btnCodeGymIDEle = homePage.waitForElementVisible(By.xpath("//a[@title='CodeGym ID']"));
        homePage.clickElementByJS(btnCodeGymIDEle);

        WebElement txtUserNameEle = homePage.waitForElementVisible(By.id("username"));
        txtUserNameEle.sendKeys("duy.bui@codegym.vn");

        WebElement txtPasswordEle = homePage.waitForElementVisible(By.id("password"));
        txtPasswordEle.sendKeys("#2131Adfasdfna432");

        WebElement btnLoginEle = homePage.waitForElementVisible(By.id("kc-login"));
        homePage.clickElementByJS(btnLoginEle);

        WebElement btnDashboardTypeEle = homePage.waitForElementVisible(By.cssSelector("button[data-preference='drawer-open-nav']"));
        String btnExpandedValue = btnDashboardTypeEle.getAttribute("aria-expanded");
        if (btnExpandedValue.equalsIgnoreCase("false")) homePage.clickElementByJS(btnDashboardTypeEle);

        WebElement txvDashboardEle = homePage.waitForElementVisible(By.cssSelector("a[data-key='myhome'] span[class='media-body']"));
        homePage.clickElementByJS(txvDashboardEle);

        WebElement spaViewTypeEle = homePage.waitForElementVisible(By.cssSelector("#displaydropdown span"));
        String spaViewTypeValue = spaViewTypeEle.getText();
        if (!spaViewTypeValue.toUpperCase().contains("LIST")) homePage.clickElementByJS(spaViewTypeEle);

    }

    @AfterClass
    public void afterClass() {
        DriverManager.quit();
    }

    @SneakyThrows
    @Test(description = "Verify the login function")
    public void RISE_Client_001_VerifyDashboard() {
        // Get List course
        List<WebElement> txtCourseNameListEle;
        int countCourse = 0;
        do {


            txtCourseNameListEle = homePage.getWebWaitDriver().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#page-container-1 .list-group .coursename")));
            WebElement txtCourseEle = txtCourseNameListEle.get(countCourse);

            String txtCourseValue = txtCourseEle.getText().replace("Course name", "").trim();
            //String expCourseValue = "[Tester 2023] Tester Fundamentals";
            if (txtCourseValue.contains("Tester")) {

                File courseFolder = new File(System.getProperty("user.dir") + "/src/test/java/cheatsheet/course/" + txtCourseValue);
                if (!courseFolder.exists()) courseFolder.mkdirs();
                System.out.println("Course: "+txtCourseValue);
                homePage.clickElementByJS(txtCourseEle);
                homePage.waitForElementVisible(By.xpath(String.format("//h1[contains(@class,'page-title') and normalize-space()='%s']", txtCourseValue)));

                // Get all topics
                int countTopic = 1;
                List<WebElement> txtTopicsListEle;
                do {
                    txtTopicsListEle = homePage.getWebWaitDriver().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[role='main'] ul.topics > li")));
                    WebElement txtTopicEle = txtTopicsListEle.get(countTopic);
                    String txtTopicNameValue = txtTopicEle.getAttribute("aria-label");

                    System.out.println("\t"+txtTopicNameValue);
                    File topicFolder = new File(courseFolder.getAbsolutePath() + File.separator + txtTopicNameValue);
                    if (!topicFolder.exists()) {
                        topicFolder.mkdirs();
                    }

                    if (txtTopicNameValue.equalsIgnoreCase("General")) {
//                    WebElement folSlideEle = txtTopicEle.findElement(By.xpath(".//span[@class='instancename' and text()='Slides']"));
//                    homePage.clickElementByJS(folSlideEle);
//
//                    List<WebElement> fileSlidesEle = homePage.getWebWaitDriver().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='folder_tree0']//span[@class='fp-filename-icon']")));
//                    fileSlidesEle.forEach(file ->  {
//                        homePage.clickElementByJS(file.findElement(By.tagName("a")));
//                    });
                    } else {
                        int countSession = 0;
                        List<WebElement> sectionListEle;
                        do {
                            txtTopicsListEle = homePage.getWebWaitDriver().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[role='main'] ul.topics > li")));
                            txtTopicEle = txtTopicsListEle.get(countTopic);

                            sectionListEle = txtTopicEle.findElements(By.xpath(".//ul/li"));
                            if (sectionListEle.size() == 0) {
                                continue;
                            }
                            WebElement section = sectionListEle.get(countSession);
                            WebElement txtSectionTitleEle = section.findElement(By.xpath(".//span[@class='instancename']"));
                            String txtSectionTitleValue = txtSectionTitleEle.getAttribute("textContent");
                            System.out.println("\t\t"+txtSectionTitleValue);

                            homePage.clickElementByJS(section.findElement(By.tagName("a")));
                            homePage.waitForElementVisible(By.xpath("//div[@role='main']/span[@id='maincontent']"));

                            WebElement txtContentEle = homePage.waitForElementVisible(By.xpath("//div[@role='main']"));
                            String txtContentValue = txtContentEle.getText();

                            List<WebElement> imgListEle = txtContentEle.findElements(By.tagName("img"));
                            String prefixTitle = "";
                            if (imgListEle.size() > 0) {
                                StringBuffer buffer = new StringBuffer(txtContentValue);
                                buffer.append("\n\nImage :   \n\n");
                                imgListEle.forEach(v -> buffer.append(v.getAttribute("src") + "\n"));
                                txtContentValue = buffer.toString();
                                prefixTitle = "IM_" + imgListEle.size() + "_";
                            }
                            File sesionFile = new File(topicFolder.getAbsolutePath() + File.separator + prefixTitle + txtSectionTitleValue.replace("/", "_").replace("\\", "_"));
                            if (!sesionFile.exists()) {
                                sesionFile.createNewFile();
                            }

                            FileWriter fileWriter = new FileWriter(sesionFile);
                            fileWriter.write(txtContentValue);
                            fileWriter.close();
                            mWebDriver.navigate().back();

                            countSession++;
                        } while (countSession < sectionListEle.size());
                    }
                    countTopic++;
                } while (countTopic < txtTopicsListEle.size());
            }
            homePage.clickElementByJS(homePage.waitForElementVisible(By.xpath("//span[@class='navbar-brand-logo']")));
            countCourse++;
        } while (countCourse < txtCourseNameListEle.size());
    }


}
