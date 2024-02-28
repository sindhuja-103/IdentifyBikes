package stepdefinition;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.*;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import pagefactory.CucumberBaseClass;
 
 
public class Hooks {
 
	public static  WebDriver driver;
	 static Properties p;
	@BeforeAll
    public static void beforeAll() throws IOException
    {
    	driver=CucumberBaseClass.initilizeBrowser();
    	
    	
    	p=CucumberBaseClass.getProperties();
    	driver.get(p.getProperty("appurl"));
    	driver.manage().window().maximize();

	}

    @AfterAll
    public  static void afterAll() {
       driver.quit();
    }

 
   @AfterStep
    public void addScreenshot(Scenario scenario) 
   {
    	TakesScreenshot ts=(TakesScreenshot) driver;
        byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png",scenario.getName());
   }
}
