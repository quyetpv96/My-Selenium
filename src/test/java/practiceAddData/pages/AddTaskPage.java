package practiceAddData.pages;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import practiceAddData.common.BasePage;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.StandardSocketOptions;
import java.time.Duration;
import java.util.HashMap;
import java.util.logging.Logger;

public class AddTaskPage extends BasePage {
    Logger logger = Logger.getLogger(HomePage.class.getName());
    WebDriver mWebDriver;
    WebDriverWait mWebDriverWait;

    protected AddTaskPage(WebDriver mWebDriver) {
        super(mWebDriver);
        this.mWebDriver = mWebDriver;
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
    }

    /**
     * using csv with 1 line
     * @param data
     */
    //    public void inputTaskInfo(int skipLine) throws IOException, CsvValidationException {
//        logger.info("Input task information");
//        String csvFilePath = System.getProperty("user.dir") + File.separator + "src/test/java/practiceAddData/data/AddTaskData.csv";
//
//        CSVReader reader = new CSVReader(new FileReader(csvFilePath));
//        for (int line = 0; line <(skipLine+1);line++){
//            reader.skip(line+1);
//        }
//        String[] col;
//        while ((col=reader.readNext())!= null){
//            for (int i = 0; i <1;i++){
//                String title = col[i];
//                String description = col[i+1];
//                String relatedTo = col[i+2];
//                String project = col[i+3];
//                String points = col[i+4];
//                String milestone = col[i+5];
//                String assignTo = col[i+6];
//                String collaborators = col[i+7];
//                String status = col[i+8];
//                String priority = col[i+9];
//                String labels = col[i+10];
//                String startDate = col[i+11];
//                String deadLine = col[i+12];
//                String repeat = col[i+13];
//                String frequency = col[i+14];
//                String cycle = col[i+15];
//
//                inputTextTo("input", "Title", title);
//                inputTextTo("textarea", "Description", description);
//                selectInfo("Related to", relatedTo);
//                selectInfo("Project", project);
//                selectInfo("Points", points);
//                selectInfo("Milestone", milestone);
//                selectInfo("Assign to", assignTo);
//                collabAndLabelInsert("Collaborators", collaborators);
//                selectInfo("Status", status);
//                selectInfo("Priority", priority);
//                collabAndLabelInsert("Labels", labels);
//                timeline("Start date", startDate);
//                timeline("Deadline", deadLine);
//                repeatOpt(repeat, frequency, cycle);
//            }
//        }
//
//        // Save task
//        WebElement saveAndShowEle = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='save-and-show-button']")));
//        clickElement(saveAndShowEle);
//
//        //Get Task ID
//        mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Edit task']")));
//        WebElement taskIDEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[@id='ajaxModalTitle']")));
//        String taskID = taskIDEle.getText().substring(taskIDEle.getText().length()-4);
//        System.setProperty("myTaskID",taskID);
//
//        // Close new task
//        WebElement closeTask = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Close']")));
//        clickElement(closeTask);
//    }
    public void inputTaskInfo(HashMap<String, String> data) throws AWTException {

        logger.info("Input task information");
        inputTextTo("input", "Title", data.getOrDefault("title", null));
        inputTextTo("textarea", "Description", data.getOrDefault("description", null));
        selectInfo("Related to", data.getOrDefault("relatedTo", null));
        selectInfo("Project", data.getOrDefault("project", null));
        selectInfo("Points", data.getOrDefault("points", null));
        selectInfo("Milestone", data.getOrDefault("milestone", null));
        selectInfo("Assign to", data.getOrDefault("assignTo", null));
        collabAndLabelInsert("Collaborators", data.getOrDefault("collaborators", null));
        selectInfo("Status", data.getOrDefault("status", null));
        selectInfo("Priority", data.getOrDefault("priority", null));
        collabAndLabelInsert("Labels", data.getOrDefault("labels", null));
        timeline("Start date", data.getOrDefault("startDate", null));
        timeline("Deadline", data.getOrDefault("deadLine", null));
        repeatOpt(data.getOrDefault("repeat", null), data.getOrDefault("frequency", null), data.getOrDefault("cycle", null));
        uploadFile(data.getOrDefault("uploadFile",null));

        // Save task
        WebElement saveAndShowEle = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='save-and-show-button']")));
        clickElement(saveAndShowEle);

        // Get Task ID
        mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Edit task']")));
        WebElement taskIDEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[@id='ajaxModalTitle']")));
        String taskID = taskIDEle.getText().substring(taskIDEle.getText().length() - 4);
        System.setProperty("myTaskID", taskID);

        // Close new task
        WebElement closeTask = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Close']")));
        clickElement(closeTask);

    }

    private void uploadFile(String fileName) throws AWTException {
        String uploadFileBtn = "//button[@class='btn btn-default upload-file-button float-start me-auto btn-sm round dz-clickable']";
        WebElement element = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(uploadFileBtn)));
        element.click();
        String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\practiceAddData\\data\\"+fileName;
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
}
