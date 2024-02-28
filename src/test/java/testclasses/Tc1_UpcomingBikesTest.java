package testclasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.BikesPage;
import testbase.BaseClass;
import utilities.ExcelUtils;

public class Tc1_UpcomingBikesTest extends BaseClass 
{
	public BikesPage bikepage;
	public static List<Integer> index_values = new ArrayList<Integer>();
	
	@Test(priority=1, groups= {"sanity"})
	public void  NewBikes() throws IOException, InterruptedException
	{
		bikepage=new BikesPage(driver);
		takeScreenshot("Home Page");
		String title = driver.getTitle();
		String expectedtitle = getProperties().getProperty("homeurl");
		Assert.assertEquals(expectedtitle,title);
		bikepage.moveToNewBikes();
		takeScreenshot("NewBikes");
	}
	@Test(priority=2, groups= {"sanity"}, dependsOnMethods ={"NewBikes"})
	public void upcomingBikes() throws IOException
	{
		bikepage.upcomingBikes();
		logger.info("Successfully selected on upcoming bikes");
	}
	@Test(priority=3, groups= {"regression"}, dependsOnMethods ={"upcomingBikes"})
	public void manufacturerType() throws IOException, InterruptedException
	{
		bikepage.manufacturer();
		Thread.sleep(3000);
		takeScreenshot("Dropdownlist");
		bikepage.typeOfManufacturer();
		bikepage.clickOnReadMore();
	}
	
	
	@Test(priority=4, groups= {"regression"},dependsOnMethods ={"manufacturerType"})
	public void scrollBikes() throws IOException
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,370);");
		takeScreenshot("Upcoming Bikes List");
		logger.info("Bikes are displayed");
	}
	@Test(priority=5,groups= {"regression"}, dependsOnMethods = {"scrollBikes"})
	public void sortBikesPrices() throws IOException
	{
		int sizeofalist = bikepage.getPriceValues().size();
		for(int i=0;i<sizeofalist;i++)
		{
			String numerical_value =bikepage.getPriceValues().get(i).replaceAll("[^\\d]", "");
			int value = Integer.parseInt(numerical_value) * 1000;
			if((value<400000 &&  !(bikepage.getPriceValues().get(i).contains("crore")))||!bikepage.getPriceValues().get(i).contains(" Lakh"))
			{
				index_values.add(i);
			}
		}
		ExcelUtils.excelOutput(bikepage.getmodelNames(),bikepage.getPriceValues(),bikepage.getLaunchDates());
	}
}
