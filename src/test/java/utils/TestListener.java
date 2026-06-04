package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.LoginTest;

import java.io.File;
import java.io.IOException;

public class TestListener implements ITestListener {

    private static ExtentReports extent = ExtentReportManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result){
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }
    @Override
    public void onTestSuccess(ITestResult result){
        test.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, result.getThrowable());

        Object testClass = result.getInstance();
        WebDriver driver = ((LoginTest) testClass).driver;

        TakesScreenshot ts = (TakesScreenshot) driver;
        String base64Screenshot = ts.getScreenshotAs(OutputType.BASE64);
        test.get().addScreenCaptureFromBase64String(base64Screenshot);
    }

    @Override
    public void onTestSkipped(ITestResult result){
        test.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext  context) {
        extent.flush();
    }

}
