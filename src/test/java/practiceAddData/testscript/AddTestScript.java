package practiceAddData.testscript;

import org.testng.annotations.BeforeClass;
import practiceAddData.pages.HomePage;
import practiceAddData.pages.LoginPage;
import practiceAddData.provider.AddTaskProvider;
import org.testng.annotations.Test;
import practiceAddData.common.TestBase;
import practiceAddData.pages.AddTaskPage;

import java.awt.*;
import java.util.HashMap;

public class AddTestScript extends TestBase {
    private LoginPage loginPage;
    private HomePage homePage;
    private AddTaskPage addTaskPage;

    @BeforeClass
    public void beforeClass(){
        loginPage = new LoginPage(mWebDriver);
        loginPage.gotoWebsite(baseURL);
        homePage = loginPage.login("admin@demo.com", "riseDemo");
    }

    @Test(description = "Thực hiện add 3 task", dataProvider = "RISE_AddTask_Data_CSV", dataProviderClass = AddTaskProvider.class)
    public void RISE_AddTask_CSV(HashMap<String, String> data) throws AWTException {

        addTaskPage = homePage.gotoAddTaskPage();

        addTaskPage.inputTaskInfo(data);

        // Verify task just added - processing
    }

    @Test(description = "Thực hiện add 3 task", dataProvider = "RISE_AddTask_Data_Excel", dataProviderClass = AddTaskProvider.class)
    public void RISE_AddTask_Excel(HashMap<String, String> data) throws AWTException {

        addTaskPage = homePage.gotoAddTaskPage();

        addTaskPage.inputTaskInfo(data);

        // Verify task just added - processing
    }
}
