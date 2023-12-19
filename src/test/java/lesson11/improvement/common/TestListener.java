package lesson11.improvement.common;


import lesson11.pages.LoginPage;
import org.apache.logging.log4j.util.Strings;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.logging.Logger;

/*
 * Purpose: Implement the testing listener
 */
public class TestListener implements ITestListener, ISuiteListener {
    Logger logger = Logger.getLogger(LoginPage.class.getName());
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
        logger.info("== quyetpv : Start TestSuite");
        // On Start Suite
        // Show suite info to trace in CI/CD or init report
    }

    @Override
    public void onFinish(ISuite iSuite) {
        logger.info("== quyetpv : Finish TestSuite");
        // Show info finish to trace in CI/CD
        System.out.println("================ TEST SUMMARY =================");
        System.out.println("Total TC: "+ totalTCs + "\n Pass: "+ passedTCs + "\n Failure: "+ failedTCs + "\n Skipped: "+ skippedTCs);
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        // TestListener: START TC
        logger.info("== quyetpv : START TC "+ getTestName(iTestResult));
        totalTCs++;
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        //TestListener: COMPLETED TC: %s - PASS %s
        logger.info(String.format("== quyetpv : COMPLETED TC %s: PASS",iTestResult.isSuccess()));
        passedTCs++;
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        logger.info(String.format("== quyetpv : COMPLETED TC %s: FAILED",iTestResult.isSuccess()));
        failedTCs++;
        // Capture failure screen
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        logger.info(String.format("== quyetpv : COMPLETED TC %s: SKIPPED",iTestResult.isSuccess()));
        skippedTCs++;
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }
}
