package utils;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

import static utils.Utility.getScreenshot;

/**
 * @author vloparevich
 */
public class Listeners implements ITestListener {
    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest test; // ??
    private Logger logger;

    private String shortTestName(ITestResult result) {
        String[] testName = result.getInstanceName().split("\\.");
        String shortTestName = testName[testName.length - 2] + "." + testName[testName.length - 1].toString();
        return shortTestName;
    }

    public void onStart(ITestContext iTestContext) {
        logger = Logger.getLogger(this.getClass().getName());
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/reports/MyExtentReport.html");
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Rest API Testing Report");
        htmlReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Project Name", "Norwegian");
        extent.setSystemInfo("Host name", "Remote Server");
        extent.setSystemInfo("Environment", "Production");
        extent.setSystemInfo("user", "Vadim");

        logger.info("Test Suite is launched");
        System.out.println("Test Started");

    }

    public void onTestStart(ITestResult iTestResult) {
        System.out.println("\033[0;32mTest: " + shortTestName(iTestResult) + " is in progress\033[0m");
    }

    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("\033[0;95m" + iTestResult.getName() + "\033[0;32m performed successfully!\033[0m");
        test = extent.createTest(iTestResult.getName());
        test.log(Status.PASS, "Passed TC: " + iTestResult.getName());
    }

    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("\033[0;32mTest Failed. Something should be verified! Screenshot is taken on test failure.\033[0m");
        try {
            getScreenshot(shortTestName(iTestResult), iTestResult.getName());
            logger.error("Assertion Failed: " + shortTestName(iTestResult) + "." + iTestResult.getName());
        } catch (IOException e) {
            System.out.println("Something went wrong. Screenshot has not been taken on test failure!");
            e.printStackTrace();
        }

        String className2 = iTestResult.getInstanceName();
        String[] classNameShort = className2.split("\\.");
        String newOne = classNameShort[classNameShort.length - 1];

        test = extent.createTest(iTestResult.getName());

        test.log(Status.FAIL, "Failed test case: " + iTestResult.getName());
        test.log(Status.FAIL, "Failed test case: " + iTestResult.getThrowable());
    }

    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("\033[0;32mListener says that \033[0m" + shortTestName(iTestResult) + " was skipped!");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    public void onFinish(ITestContext iTestContext) {
        extent.flush();
        logger.info("Test Suite is finished");
        System.out.println("Test Finished");
    }
}
