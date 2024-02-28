package utilities;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import testbase.BaseClass;
 
public class ExtentReportManager implements ITestListener  //or TestListenerAdapter class
{
	public ExtentSparkReporter sparkReporter;  // UI of the report
	public ExtentReports extent;  //populate common info on the report
	public ExtentTest test;
	String repName;
	BaseClass base = new BaseClass();// creating test case entries in the report and update status of the test methods
	public void onStart(ITestContext context)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
		repName = "Test-Report-" + timeStamp + ".html";
		sparkReporter=new ExtentSparkReporter(".\\report\\"+repName);//specify location of the report
		sparkReporter.config().setDocumentTitle("Automation Report"); // TiTle of report
		sparkReporter.config().setReportName("ZigWheels"); // name of the report
		sparkReporter.config().setTheme(Theme.DARK);		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);	
		extent.setSystemInfo("Computer Name","localhost");
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("Tester Name","Sindhuja");
		extent.setSystemInfo("os","Windows11");
		extent.setSystemInfo("Browser name","Chrome,Edge");
	}
 
 
	public void onTestSuccess(ITestResult result) 
	{
		test = extent.createTest(result.getName()); // create a new entry in the report
		test.log(Status.PASS, "TEST CASE PASSED IS  :" + result.getName()); // update status p/f/s
		try 
		{
			String screenshotpath = base.takeScreenshot(result.getName());
			test.addScreenCaptureFromPath(screenshotpath);
		} 
		catch (IOException e)
		{
			
			e.printStackTrace();
		}
		
		
	}
 
	public void onTestFailure(ITestResult result)
	{
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "Test case FAILED is:" + result.getName());
		test.log(Status.INFO, "Test Case FAILED cause is: " + result.getThrowable().getMessage());
	}
 
	public void onTestSkipped(ITestResult result)
	{
 
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test case SKIPPED is:" + result.getName());
		test.log(Status.INFO, "Test Case Skipped cause is: " + result.getThrowable().getMessage());
	}
 
	
	public void onFinish(ITestContext context) {
		extent.flush();
		String pathOfExtentReport = System.getProperty("user.dir")+"\\report\\"+repName;
		File extentReport = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

