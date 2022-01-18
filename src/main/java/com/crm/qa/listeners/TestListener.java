package com.crm.qa.listeners;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.crm.qa.base.TestBase;

import ExtentReporterNG.ExtentReporterNG;

public class TestListener extends TestBase implements ITestListener {
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.PASS, result.getMethod().getMethodName() + ":-Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.FAIL, result.getMethod().getMethodName() + ":-Test Failed");
		extentTest.get().fail(result.getThrowable());
		String testCaseName = result.getMethod().getMethodName();
		/*
		 * WebDriver driver = null; try { driver = (WebDriver)
		 * result.getTestClass().getRealClass().getDeclaredField("driver")
		 * .get(result.getInstance()); } catch (IllegalArgumentException |
		 * IllegalAccessException | NoSuchFieldException | SecurityException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 */
		takeScreenshotOnFailure(testCaseName);
		extentTest.get().addScreenCaptureFromPath(takeScreenshotOnFailure(testCaseName),
				result.getMethod().getMethodName());

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.SKIP, result.getMethod().getMethodName() + ":-Test Passed");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		String filepath=System.getProperty("user.dir")+"\\report";
File file = new File(filepath);
try {
	
for(File subfile: file.listFiles())
{
	subfile.delete();
	file.delete();
	}}
catch (Exception e) {
	
}
}

	@Override
	public void onFinish(ITestContext context) {

		extent.flush();
	}

}
