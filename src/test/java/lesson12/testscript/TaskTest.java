package lesson12.testscript;

import lesson12.common.DriverManager;
import lesson12.common.TestBase;
import lesson12.pages.*;
import lesson12.provider.AddTaskProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.HashMap;

/**
 * [Bài tập] Thực hiện tạo mới 03 task
 * 1. Truy cập [Tasks] menu  > Truy cập [List] Tab > Add task
 * Nhập thông tin chi tiết (Thông tin chi tiết cho task, bạn có thể nhập theo ý tưởng cá nhân mỗi người).
 */
public class TaskTest extends TestBase {
    private WebDriver mWebDriver;
    private String baseURL = "https://rise.fairsketch.com";
    private LoginPage loginPage;
    private HomePage homePage;
    private TasksPage tasksPage;
    private AddTaskPage addTaskPage;

    @BeforeClass
    public void beforeTestMethod() {
        mWebDriver = DriverManager.getWebDriver();
        loginPage = new LoginPage(mWebDriver);
        loginPage.gotoWebsite(baseURL);

        // Login website với tài khoản hợp lệ -> Thành công truy cập vào HomePage
        homePage = loginPage.login("admin@demo.com", "riseDemo");

        // Click [Tasks] -> Thành công truy cập vào TasksPage
        tasksPage = homePage.gotoTasksPage();
    }

    @AfterClass
    public void afterMethod() {
        DriverManager.quit();
    }

    @Test(description = "Thực hiện add 3 task", dataProvider = "RISE_AddTask_Data", dataProviderClass = AddTaskProvider.class)
    public void RISE_AddTask_001_DataDriven(HashMap<String, String> data) {

        addTaskPage = tasksPage.goToAddTaskPage();
        // Thực hiện add 3 task
        addTaskPage.inputTaskInfo(data);
    }
}
