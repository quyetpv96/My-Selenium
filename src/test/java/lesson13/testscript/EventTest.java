package lesson13.testscript;

import lesson13.common.DriverManager;
import lesson13.common.TestBase;
import lesson13.pages.AddEventPage;
import lesson13.pages.EventPage;
import lesson13.pages.HomePage;
import lesson13.pages.LoginPage;
import lesson13.provider.AddEventProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

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
    public void beforeTestClass() {
        mWebDriver = DriverManager.getWebDriver();
        loginPage = new LoginPage(mWebDriver);
        loginPage.gotoWebsite(baseURL);

        // Login website với tài khoản hợp lệ -> Thành công truy cập vào HomePage
        homePage = loginPage.login("admin@demo.com", "riseDemo");

        // Click [Events] -> Thành công truy cập vào EventPage
        eventPage = homePage.gotoEventPage();
    }

    @AfterClass
    public void afterClass() {
        DriverManager.quit();
    }

    @Test(description = "Thực hiện add 3 event", dataProvider = "RISE_AddEvent_Data", dataProviderClass = AddEventProvider.class)
    public void RISE_AddEvent_001_DataDriven(HashMap<String, String> data) {
        // Truy cập add Event
        addEventPage = eventPage.goToAddEventPage();

        // Thực hiện add 3 event
        addEventPage.inputEventInfo(data);

        // Verify Event vừa add
        eventPage.verifyNewEvent(data);
    }
}
