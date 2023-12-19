package practice2;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestScript extends TestBase{
    private LoginPage loginPage;
    private HomePage homePage;
    private String baseURL = "https://rise.fairsketch.com/signin";

    @BeforeClass
    public void beforeClass(){
        loginPage = new LoginPage(mWebDriver);
        loginPage.gotoWebsite(baseURL);

        homePage = loginPage.login("admin@demo.com","riseDemo");
    }

    @Test
    public void Test2(){
        System.out.println("Test2");
    }
}
