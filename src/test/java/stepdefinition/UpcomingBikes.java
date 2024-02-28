package stepdefinition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pagefactory.CucumberBaseClass;
import pageobjects.BikesPage;
import utilities.ExcelUtils;


public class UpcomingBikes
{
	public WebDriver driver=CucumberBaseClass.getDriver();
	public static List<Integer> index_values = new ArrayList<Integer>();
	BikesPage bikepage = new BikesPage(driver);
	
	@Given("user navigated to ZigWheels Website")
	public void user_navigated_to_zig_wheels_website() 
	{
		System.out.println("User Successfully Navigated");
	}

	@When("user hover on NewBikes")
	public void user_hover_on_new_bikes() 
	{
		bikepage.moveToNewBikes();
	}

	@Then("click on upcoming bikes")
	public void click_on_upcoming_bikes()
	{
	    bikepage.upcomingBikes();
	}

	@Then("click on manufacturer")
	public void click_on_manufacturer()
	{
	    bikepage.manufacturer();
	}

	@Then("select honda")
	public void select_honda() 
	{
	   bikepage.typeOfManufacturer();
	}

	@Then("click on readmore")
	public void click_on_readmore()
	{
	   bikepage.clickOnReadMore();
	}

	@Then("get the information about upcoming bikes and print the same in excel")
	public void get_the_information_about_upcoming_bikes_and_print_the_same_in_excel() throws IOException 
	{
	   
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,370);");
		int sizeofalist = bikepage.getPriceValues().size();
		for(int i=0;i<sizeofalist;i++)
		{
			//System.out.println(bikepage.getPriceValues().size());
			String numerical_value =bikepage.getPriceValues().get(i).replaceAll("[^\\d]", "");
			int value = Integer.parseInt(numerical_value) * 1000;
			if((value<400000 &&  !(bikepage.getPriceValues().get(i).contains("crore")))||!bikepage.getPriceValues().get(i).contains(" Lakh"))
			{
				index_values.add(i);
			}
		}
		ExcelUtils.excelOutput(bikepage.getmodelNames(),bikepage.getPriceValues(),bikepage.getLaunchDates());
		/*//ExcelUtils.excelOutput(bikepage.getmodelNames(),bikepage.getPriceValues(),bikepage.getLaunchDates());
		//System.out.println("Upcoming bikes less than 4 lakhs are listed below : ");
		
		for(int i=0;i<sizeofalist;i++)
		{
			if(index_values.contains(i))
			{
				//ExcelUtils.excelOutput(bikepage.getmodelNames(),bikepage.getPriceValues(),bikepage.getLaunchDates());
				System.out.println(bikepage.getmodelNames().get(i)+" -> "+bikepage.getPriceValues().get(i)+" -> "+bikepage.getLaunchDates().get(i));
			}
		}*/
		
	}
}
