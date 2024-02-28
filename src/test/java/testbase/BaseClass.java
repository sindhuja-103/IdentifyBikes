package testbase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseClass
{
	public static WebDriver driver;
	public Properties p;
	public static Logger logger;
	
	@BeforeTest (groups= {"sanity","smoke","regression"})
	@Parameters({"os","browser","url"})
	public void driverSetUp(String os,String br,String appUrl) throws IOException
	{
		logger = LogManager.getLogger(this.getClass());
		if(getProperties().getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities cap=new DesiredCapabilities();
			if(os.equalsIgnoreCase("windows")) {
				cap.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac")) {
				cap.setPlatform(Platform.MAC);
			}
			else {
				System.out.println("No matching os.....");
				return;
			}
			switch(br.toLowerCase()) {
			case "chrome":cap.setBrowserName("chrome");
						break;
			case "edge":cap.setBrowserName("MicrosoftEdge");
						break;
			default:System.out.println("No matching browser....");
						return;
			}
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		}
		else if(getProperties().getProperty("execution_env").equals("local")) {
			switch(br) 
			{
			case "chrome": driver=new ChromeDriver();
			                break;
			case "edge": driver=new EdgeDriver();
			                 break;
			case "firefox":driver=new FirefoxDriver();
			                 break;
			}
		}	
		driver.manage().deleteAllCookies();
		driver.get(appUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	@AfterTest(groups= {"sanity","smoke","regression"})
	public void tearDown()
	{
		driver.quit();
	}
	
	public String takeScreenshot(String name) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File fileobj = ts.getScreenshotAs(OutputType.FILE);
    	String targetFile = System.getProperty("user.dir")+"\\screenshots\\"+name+".png";
    	File targetfiles = new File(targetFile);
    	FileUtils.copyFile(fileobj, targetfiles);
    	return targetFile;
	}
	
	 public Properties getProperties() throws IOException
	 {
		 FileReader file=new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
	     p=new Properties();
		 p.load(file);
		 return p;
	  }
}
