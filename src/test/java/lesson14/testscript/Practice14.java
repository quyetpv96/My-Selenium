package lesson14.testscript;

import lesson14.common.DriverManager;
import lesson14.common.TestBase;
import lesson14.pages.*;
import lesson14.provider.Practice14Provider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.util.HashMap;

/**
 *  [Bài tập] Xây dựng kịch bản kiểm thử tự động: tạo mới projects
 *  Test case 01:
 * 1. Truy cập website https://rise.fairsketch.com
 * 2. Đăng nhập với tài khoản admin@demo.com / riseDemo
 * 3. Truy cập [Projects] menu
 * 4. Click [Manage labels]
 * 5. Add 2 labels (Áp dụng data driven)
 *
 * Test case 02:
 * 1. Truy cập website https://rise.fairsketch.com
 * 2. Đăng nhập với tài khoản admin@demo.com / riseDemo
 * 3. Truy cập [Projects] menu
 * 4. Click [Add project]
 * 5. Add 5 projects (Áp dụng data driven)
 * 6. Filter with the project title
 * 7. Verify the content in table
 */
public class Practice14 extends TestBase {
    private ProjectPage projectPage;
    private ManageLabelPage manageLabelPage;
    private AddProjectPage addProjectPage;
    @BeforeClass
    public void beforeClass() {
        // Click [Projects] -> Thành công truy cập vào ProjectPage
        projectPage = homePage.gotoProjectsPage();

        // Verify project page
        projectPage.verifyProjectDashboard();
    }

    @AfterClass
    public void afterClass() {
        DriverManager.quit();
    }

    @BeforeMethod
    public void beforeMethod(){

    }
    @AfterMethod
    public void afterMethod(){}

    @Test(enabled = false, priority = 1, description ="Add 2 label in Project menu", dataProvider = "RISE_Project_Data", dataProviderClass = Practice14Provider.class)
    public void RISE_Project_001_AddLabel_DataDriven(HashMap<String, String> data) {

        // Click [Manage labels]
        manageLabelPage = projectPage.gotoManageLabelPage();

        // Verify manageLabelPage
        manageLabelPage.verifyManageLabelPage();

        // Creat 2 labels
        manageLabelPage.inputLabelInfo(data);

        // Verify new label Integration on Add Project
        addProjectPage = projectPage.gotoAddProjectPage();
        addProjectPage.verifyNewLabelIntegrationOnAddProject(data);
    }

    @Test(enabled = false,priority = 2, description ="Add 5 projects in Project menu", dataProvider = "RISE_Project_Data", dataProviderClass = Practice14Provider.class)
    public void RISE_Project_001_Project_DataDriven(HashMap<String, String> data) {
        // Click [Projects] -> Thành công truy cập vào ProjectPage
        projectPage = homePage.gotoProjectsPage();

        // Verify project page
        projectPage.verifyProjectDashboard();

        // Click to add project
        addProjectPage = projectPage.gotoAddProjectPage();

        // Add 5 projects
        addProjectPage.inputProjectInfo(data);

        // Verify 5 project
        projectPage.verifyNewProject(data);
    }

    @Test(description ="Truy cập [Projects] Menu > [Add project] > Add 02 member bất kỳ > Close", dataProvider = "RISE_Project_Data", dataProviderClass = Practice14Provider.class)
    public void RISE_Project_002_Project_DataDriven(HashMap<String, String> data) {
        // Click [Projects] -> Thành công truy cập vào ProjectPage
        projectPage = homePage.gotoProjectsPage();

        // Verify project page
        projectPage.verifyProjectDashboard();

        // Click to add project
        addProjectPage = projectPage.gotoAddProjectPage();

        // Add 5 projects
        addProjectPage.inputProjectInfo(data);

        // Verify 5 project
        projectPage.verifyNewProject(data);
    }

    private void gotoWebsite() {
        loginPage.gotoWebsite(baseURL);
    }
}
