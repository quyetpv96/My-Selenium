package lesson12.testscript;

import lesson12.common.DriverManager;
import lesson12.common.TestBase;
import lesson12.pages.*;
import lesson12.provider.AddEventProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.HashMap;

/**
 * [Bài tập] Thực hiện tạo mới 03 event
 * Thực hiện tạo mới 03 events theo thứ tự sau:
 * 1. Truy cập https://rise.fairsketch.com (client@demo.com / riseDemo)
 * 2. Truy cập [Events]
 * 3. Click [Add event]
 * 4. Nhập thông tin cho event
 */
public class EventTest extends TestBase {
    private WebDriver mWebDriver;
    private String baseURL = "https://rise.fairsketch.com";
    private LoginPage loginPage;
    private HomePage homePage;
    private EventPage eventPage;
    private AddEventPage addEventPage;

    @BeforeClass
    public void beforeTestMethod() {
        mWebDriver = DriverManager.getWebDriver();
        loginPage = new LoginPage(mWebDriver);
        loginPage.gotoWebsite(baseURL);

        // Login website với tài khoản hợp lệ -> Thành công truy cập vào HomePage
        homePage = loginPage.login("admin@demo.com", "riseDemo");

        // Click [Events] -> Thành công truy cập vào EventPage
        eventPage = homePage.gotoEventPage();
    }

    @AfterClass
    public void afterMethod() {
        DriverManager.quit();
    }

    @Test(description = "Thực hiện add 3 event", dataProvider = "RISE_AddEvent_Data", dataProviderClass = AddEventProvider.class)
    public void RISE_AddEvent_001_DataDriven(HashMap<String, String> data) {

        addEventPage = eventPage.goToAddEventPage();
        // Thực hiện add 3 event
        addEventPage.inputEventInfo(data);
    }
}
