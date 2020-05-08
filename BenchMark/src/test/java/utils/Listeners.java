package utils;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

import static utils.Utility.*;

/**
 * @author vloparevich
 */
public class Listeners implements ITestListener {
    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public static ExtentTest test;
    private Logger logger;

    private String shortTestName(ITestResult result) {
        String[] testName = result.getInstanceName().split("\\.");
        String shortTestName = testName[testName.length - 2] + "." + testName[testName.length - 1];
        return shortTestName;
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        logger = Logger.getLogger(this.getClass().getName());
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/reports/MyExtentReport.html");
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("UI Testing Report");
        htmlReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Project Name", "Norwegian");
        extent.setSystemInfo("Host name", "Remote Server");
        extent.setSystemInfo("Environment", "Production");
        extent.setSystemInfo("User", "Vadim");

        logger.info("Test Suite is launched log4j");

        test = extent.createTest(iTestContext.getName());
        test.info("Test Suite is launched extentreport");

    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("\033[0;32mTest: " + shortTestName(iTestResult) + " is in progress\033[0m");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("\033[0;95m" + iTestResult.getName() + "\033[0;32m performed successfully!\033[0m");
        test = extent.createTest(iTestResult.getName());
        test.log(Status.PASS, "Passed TC: " + iTestResult.getName());
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        String shortTestName = shortTestName(iTestResult);
        String longTestName = iTestResult.getName();
        test = extent.createTest(longTestName);
        test.log(Status.FAIL, "Failed test case: " + longTestName);

        try {
            getScreenshot(shortTestName, longTestName);
            logger.error("Assertion Failed: " + shortTestName + "." + longTestName);
           /* test.addScreenCaptureFromPath(screenshotPath);*/

            test.fail(iTestResult.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        logger.error("\033[0;32mListener says that \033[0m" + shortTestName(iTestResult) + " was skipped!");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        extent.flush();
        logger.info("Test Suite is finished");
    }
}
