package lesson11.testscript;

import lesson11.improvement.common.DriverManager;
import lesson11.improvement.common.TestBase;
import lesson11.pages.HomePage;
import lesson11.pages.LoginPage;
import lesson11.pages.TasksPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class TaskTest extends TestBase {
    private WebDriver mWebDriver;
    private String baseURL = "https://rise.fairsketch.com";
    private LoginPage loginPage;
    private HomePage homePage;
    private TasksPage tasksPage;

    @BeforeClass
    public void beforeTestMethod() {
        mWebDriver = DriverManager.getWebDriver();
        loginPage = new LoginPage(mWebDriver);
        loginPage.gotoWebsite(baseURL);
    }

    @AfterClass
    public void afterMethod() {
        DriverManager.quit();
    }

    @Test(description = "Verify the Task")
    public void RISE_Client_001_VerifyDashboard() {
        // Login website với tài khoản hợp lệ -> Thành công truy cập vào HomePage
        homePage = loginPage.login("admin@demo.com", "riseDemo");

        // Click [Tasks] -> Thành công truy cập vào TasksPage
        tasksPage = homePage.gotoTasksPage();

        // Thực hiện các hành động trong Task Page
        tasksPage.verifyTasksDashboard();

        // Verify Add Task trong List Tab
        tasksPage.goToAddTaskPageViaList().verifyAddTask();

        // Verify Add Task trong Kanban Tab
        //tasksPage.goToAddTaskPageViaKanban().verifyAddTask();
    }
}
