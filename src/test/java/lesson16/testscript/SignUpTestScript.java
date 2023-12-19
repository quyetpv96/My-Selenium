package lesson16.testscript;

import lesson16.common.TestBase;
import lesson16.pages.LoginPage;
import lesson16.pages.MyProfilePage;
import lesson16.pages.SignUpPage;
import lesson16.provider.AccountDataProvider;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

public class SignUpTestScript extends TestBase {
    private SignUpPage signUpPage;
    private MyProfilePage myProfilePage;
    @BeforeMethod
    public void beforeMethod(){
        loginPage = new LoginPage(mWebDriver);
        loginPage.gotoWebsite(baseURL);
    }
    @Test(description = "Creat Account", dataProvider = "RISE_Account_Data", dataProviderClass = AccountDataProvider.class)
    public void RISE_CreatAccount_DATADRIVEN_001(HashMap<String, String> data){
        // Access to Sign up Page to creat account
        signUpPage = loginPage.gotoSignUpPage();

        // Verify access to Sign up Page success
        signUpPage.verifyAccessSignUpPage();

        // Creat account
        signUpPage.creatAccount(data);

        // Login new account to verify
        signUpPage.backtoLoginPage();
        homePage = loginPage.login(data.get("email"),data.get("password"));

        // Verify account info at HomePage
        homePage.verifyAccount(data);

        // Verify account info at My profile Page
        myProfilePage = homePage.gotoMyProfilePage();


    }
}
