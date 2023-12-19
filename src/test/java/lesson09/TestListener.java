package lesson09;


import org.apache.logging.log4j.util.Strings;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

/*
 * Purpose: Implement the testing listener
 */
public class TestListener implements ITestListener, ISuiteListener {
    int totalTCs = 0, passedTCs = 0, skippedTCs = 0, failedTCs = 0;

    /**
     * Get test case name
     */
    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }


    /**
     * Get test case description
     */
    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : Strings.EMPTY;
    }

    @Override
    public void onStart(ISuite iSuite) {
        // On Start Suite
        // Show suite info to trace in CI/CD or init report
        System.out.println("Thực hiện test ");
    }

    @Override
    public void onFinish(ISuite iSuite) {
        // On Finish Suite
        // Show info finish to trace in CI/CD
        System.out.println("================ TEST SUMMARY =================");
        System.out.println("Total TC: "+ totalTCs + "\n Pass: "+ passedTCs + "\n Failure: "+ failedTCs + "\n Skipped: "+ skippedTCs);
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        // TestListener: START TC
        totalTCs++;
        System.out.println("Test case: "+ getTestName(iTestResult));
        System.out.println("Description: "+ iTestResult.getMethod().getDescription());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        //TestListener: COMPLETED TC: %s - PASS %s
        passedTCs++;
        System.out.println("Test case: "+ getTestName(iTestResult) + " thực thi thành công!");
        System.out.println("===============================================");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        // TestListener: COMPLETED TC: %s - FAIL %s
        failedTCs++;
        // Capture failure screen
        System.out.println("Test case: "+ getTestName(iTestResult) + " thực thi thất bại!");
        System.out.println("===============================================");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        // TestListener: COMPLETED TC: %s - SKIP %s
        skippedTCs++;
        System.out.println("Test case: "+ getTestName(iTestResult) + " skipped!");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }
}
