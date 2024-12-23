package listeners;


import java.io.IOException;
import utilities.ExtentReporter;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.Base;

public class Listeners extends Base implements ITestListener {

	WebDriver driver = null;
	ExtentReports extentReport = ExtentReporter.getExtentReport();
	ExtentTest extentTest;
	ThreadLocal<ExtentTest> extentTestthread = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
		
		String testName = result.getName();
		extentTest = extentReport.createTest(testName+"Test One");
		extentTestthread.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		String testName = result.getName();
		//extentTest.log(Status.PASS, testName+"got passed");
		extentTestthread.get().log(Status.PASS, testName+"got passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		
		String testname = result.getName();
		
		//extentTest.fail(result.getThrowable());
		extentTestthread.get().fail(result.getThrowable());
		
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			String screenshotPath = takingScreenshots(testname,driver);
			extentTestthread.get().addScreenCaptureFromPath(screenshotPath, testname);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		
	}

	@Override
	public void onStart(ITestContext context) {
		
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
		extentReport.flush();
	}

}
