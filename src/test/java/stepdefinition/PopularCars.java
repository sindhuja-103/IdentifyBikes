package stepdefinition;
import java.io.IOException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import pagefactory.CucumberBaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.UsedCarsPage;
import utilities.ExcelUtils;

public class PopularCars
{
	public WebDriver driver=CucumberBaseClass.getDriver();
	UsedCarsPage carspage = new UsedCarsPage(driver);
	
	@Given("user will navigate to ZigWheels Website")
	public void user_will_navigate_to_zig_wheels_website() 
	{
	    System.out.println("User Navigated to zigwheels Website");
	    carspage.navigateToHome();
	}

	@When("user hover on used cars")
	public void user_hover_on_used_cars()
	{
		carspage.moveToUsedCars();
	}

	@Then("click on chennai")
	public void click_on_chennai() 
	{
	    carspage.selectChennai();
	}

	@Then("get the information about popular models and print the same in excel")
	public void get_the_information_about_popular_models_and_print_the_same_in_excel() throws IOException 
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView(true);",carspage.scroll_upto_popularmodels);
		ExcelUtils.excelOutput(carspage.getPopularModels());
	}
}
