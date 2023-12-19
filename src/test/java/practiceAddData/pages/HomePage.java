package practiceAddData.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import practiceAddData.common.BasePage;

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

    public void gotoAddMenu(){
        logger.info("Click [+] quick add button]");
        WebElement quickAddEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='quick-add-icon']")));
        clickElement(quickAddEle);
    }
    public AddTaskPage gotoAddTaskPage(){
        gotoAddMenu();
        WebElement addTaskEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Add task']")));
        clickElement(addTaskEle);
        return new AddTaskPage(mWebDriver);
    }
    public AddMultiTaskPage gotoAddMultiTaskPage(){
        WebElement addMultiTaskEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Add multiple tasks']")));
        clickElement(addMultiTaskEle);
        return new AddMultiTaskPage(mWebDriver);
    }
    public AddProjectTimePage gotoAddProjectTimePage(){
        WebElement addProjectTimeEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Add project time']")));
        clickElement(addProjectTimeEle);
        return new AddProjectTimePage(mWebDriver);
    }
    public AddEventPage gotoAddEventPage(){
        WebElement addEventEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Add event']")));
        clickElement(addEventEle);
        return new AddEventPage(mWebDriver);
    }
    public AddNotePage gotoAddNotePage(){
        WebElement addNoteEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Add note']")));
        clickElement(addNoteEle);
        return new AddNotePage(mWebDriver);
    }
    public AddToDoPage gotoAddToDoPage(){
        WebElement addTodoEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Add to do']")));
        clickElement(addTodoEle);
        return new AddToDoPage(mWebDriver);
    }
    public AddTicketPage gotoAddTicketPage(){
        WebElement addTicketEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Add ticket']")));
        clickElement(addTicketEle);
        return new AddTicketPage(mWebDriver);
    }
}
