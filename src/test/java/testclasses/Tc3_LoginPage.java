package testclasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.LoginPage;
import testbase.BaseClass;

public class Tc3_LoginPage extends BaseClass
{
	LoginPage loginpage;
	
	@Test(priority=1, groups= {"sanity"})
	public void loginPage() throws IOException, InterruptedException
	{
		driver.navigate().back();
		driver.navigate().refresh();
		loginpage = new LoginPage(driver);
		loginpage.loginPage();
		takeScreenshot("Sign in page");
		logger.info("Login Page is displayed");
		Thread.sleep(2000);
	}
	@Test(priority=2, groups= {"sanity"}, dependsOnMethods = {"loginPage"})
	public void signInWithGoogle() throws InterruptedException
	{
		loginpage.signInGoogle();
		Set<String> windowids = driver.getWindowHandles();
		List<String> windowid =new ArrayList<String>(windowids);
		driver.switchTo().window(windowid.get(1));
		Thread.sleep(2000);
	}
	@Test(priority=3,groups= {"regression"}, dependsOnMethods = {"signInWithGoogle"})
	public void setEmailData() throws InterruptedException, IOException
	{
		loginpage.setEmail(getProperties().getProperty("email"));
		takeScreenshot("Email Data");
		logger.info("Entered Inavalid email account");
		Thread.sleep(5000);
	}
	@Test(priority=4,groups= {"regression"}, dependsOnMethods = {"setEmailData"} )
	public void clickNext() throws IOException, InterruptedException
	{
		loginpage.clickOnNext();
		Thread.sleep(5000);
		System.out.println(loginpage.captureErrorMessage());
		takeScreenshot("Error Message");
		logger.info("Error Message as Inavalid Data is displayed");
	}
	
}
