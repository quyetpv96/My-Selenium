package practiceHome.testscript;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import practiceHome.common.DriverManager;
import practiceHome.common.TestBase;
import practiceHome.pages.PIMPage;

public class TestScript extends TestBase {
    private PIMPage pimPage;

    @AfterClass
    public void afterClass(){
        DriverManager.quit();
    }

    @Test
    public void testLogin(){
        pimPage = homePage.gotoPIMPage();
    }
}
