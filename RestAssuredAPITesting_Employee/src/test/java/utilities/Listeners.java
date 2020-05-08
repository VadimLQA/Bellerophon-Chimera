package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {
    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest test;

    public void onStart(ITestContext iTestContext) {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/reports/MyExtentReport.html");
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Rest API Testing Report");
        htmlReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Project Name", "Employee");
        extent.setSystemInfo("Host name", "localhost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("user", "Vadim");

    }

    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("\033[0;95m" + iTestResult.getName() + "\033[0;32m performed successfully!\033[0m");
        test = extent.createTest(iTestResult.getName());
        test.log(Status.PASS, "Passed TC: " + iTestResult.getName());
    }

    public void onTestStart(ITestResult iTestResult) {

    }

    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("Test Failed. Something should be verified! Screenshot is taken.");
        String className2 = iTestResult.getInstanceName();
        String[] classNameShort = className2.split("\\.");
        String newOne = classNameShort[classNameShort.length - 1];

        test = extent.createTest(iTestResult.getName());

        test.log(Status.FAIL, "Failed test case: " + iTestResult.getName());
        test.log(Status.FAIL, "Failed test case: " + iTestResult.getThrowable());
    }

    public void onTestSkipped(ITestResult iTestResult) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onFinish(ITestContext iTestContext) {
        extent.flush();
    }
}
