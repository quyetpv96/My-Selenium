package endModule.testscript;

import endModule.common.DriverManager;
import endModule.common.TestBase;
import endModule.pages.CandidatePage;
import endModule.pages.RecruitmentPage;
import endModule.provider.CandidateProvider;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.HashMap;

public class TestScript extends TestBase {
    private RecruitmentPage recruitmentPage;
    private CandidatePage candidatePage;

    @AfterClass
    public void afterClass(){
        DriverManager.quit();
    }

    /**
     * Access to Recruitment Page
     */
    @BeforeMethod
    public void beforeMethod(){
        // Click to [Recruitment] on Left-Menu
        recruitmentPage = homePage.gotoRecruitmentPage();

        // Verify access to Recruitment Page success
        recruitmentPage.verifyAccessRecruitmentPage();
    }

    @Test(description ="Add 3 candidate > Verify new candidate > Delete Candidate", dataProvider = "OrangeHRM_Candidate_Data", dataProviderClass = CandidateProvider.class)
    public void OrangeHRM_Candidate_Test001(HashMap<String, String> data) throws AWTException {
        // Click to [+Add] to go to candidate page
        candidatePage = recruitmentPage.gotoCandidatePage();

        // Add 3 candidate
        candidatePage.addCandidateInfo(data);

        // Verify Candidate -> Delete Candidate
        recruitmentPage.verifyAndDeleteCandidate(data);
    }
}
