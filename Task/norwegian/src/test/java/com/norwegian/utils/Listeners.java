package com.norwegian.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

import static com.norwegian.basetestplatform.BaseTest.logger;
import static com.norwegian.utils.Utility.getScreenshot;

/**
 * @author vloparevich
 */
public class Listeners implements ITestListener {

    private String shortTestName(ITestResult result) {
        String[] testName = result.getInstanceName().split("\\.");
        ;
        String shortTestName = testName[testName.length - 2].toString() + "." + testName[testName.length - 1].toString();
        return shortTestName;
    }

    public void onTestStart(ITestResult iTestResult) {
        System.out.println("\033[0;32mTest: " + shortTestName(iTestResult) + " is in progress\033[0m");
    }

    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("\033[0;32m" + shortTestName(iTestResult) + " performed successfully!\033[0m");
    }

    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("\033[0;32mTest Failed. Something should be verified! Screenshot is taken on test failure.\033[0m");
        try {
            getScreenshot(shortTestName(iTestResult), iTestResult.getName());
            logger.error("Assertion Failed: " + shortTestName(iTestResult) + "." + iTestResult.getName());
        } catch (IOException e) {
            System.out.println("Something went wrong. Screenshot has not been made on test Failure!");
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("\033[0;32mListener says that \033[0m" + shortTestName(iTestResult) + " was skipped!");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    public void onStart(ITestContext iTestContext) {
        System.out.println("Test launched");
    }

    public void onFinish(ITestContext iTestContext) {
        System.out.println("Test Finished");
    }
}
