package testclasses;
import java.io.IOException;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.UsedCarsPage;
import testbase.BaseClass;
import utilities.ExcelUtils;

public class Tc2_PopularCars extends BaseClass
{
	public UsedCarsPage carspage;
	
	@Test(priority=1, groups= {"sanity"})
	public void navigateHome() throws IOException
	{
		carspage = new UsedCarsPage(driver);
		carspage.navigateToHome();
		String title = driver.getTitle();
		String expectedtitle = getProperties().getProperty("homeurl");
		Assert.assertEquals(expectedtitle,title);
	}
	@Test(priority=2, groups= {"sanity"}, dependsOnMethods = {"navigateHome"})
	public void hoverOnCars() throws IOException
	{
		carspage.moveToUsedCars();
		takeScreenshot("Cars Page");
		logger.info("Cars home Page");
	}
	@Test(priority=3, groups= {"sanity"},dependsOnMethods = {"hoverOnCars"})
	public void clickChennai() throws IOException
	{
		carspage.selectChennai();
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView(true);",carspage.scroll_upto_popularmodels);
		takeScreenshot("Used Cars Page");
	}
	
	@Test(priority=4,groups= {"regression"}, dependsOnMethods = {"clickChennai"})
	public  void getPopularCarsData() throws IOException 
	{
		takeScreenshot("Used Cars Page");
		ExcelUtils.excelOutput(carspage.getPopularModels());
		takeScreenshot("Popular Cars");
		logger.info("Popular Cars in Chennai");
	}
}
