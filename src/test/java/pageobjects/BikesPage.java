package pageobjects;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class BikesPage extends BasePage
{
	
	public List<String> model_name = new ArrayList<>();
	public List<String> price_value = new ArrayList<>();
	public List<String> expectedlaunch_date = new ArrayList<>();
	public BikesPage(WebDriver driver)
	{
		super(driver);
	}
	
	//Locators
	
	//locating new bikes
	@FindBy(linkText="New Bikes")
	WebElement new_bikes;
	
	//locating upcoming bikes
	@FindBy(xpath="//span[text()='Upcoming Bikes']")
	WebElement upcoming_bikes;
	
	@FindBy(id="makeId")
	WebElement drop_down_manufacturer;
	
	@FindBy(xpath="//span[text()='...Read More']")
	WebElement read_more;
	
	@FindBy(xpath="//tr[@class='ml-15 ']//td[1]")
	List<WebElement> model_names;
	
	@FindBy(xpath="//tr[@class='ml-15 ']//td[2]")
	List<WebElement> prices;
	
	@FindBy(xpath="//tr[@class='ml-15 ']//td[3] ")
	List<WebElement> launch_dates;
	
	@FindBy(xpath="//h2[text()=\"Upcoming Honda Bikes in India 2024 - 2025 \"]")
	public WebElement scroll;
	//Action methods
	public void moveToNewBikes()
	{
		Actions action = new Actions(driver);
		action.moveToElement(new_bikes).perform();
	}
	
	public void upcomingBikes()
	{
		upcoming_bikes.click();
	}
	
	public void manufacturer()
	{
		drop_down_manufacturer.click();
	}
	
	public void typeOfManufacturer()
	{
		Select drop_down_element = new Select(drop_down_manufacturer);
		drop_down_element.selectByValue("53");
	}
	
	public void clickOnReadMore()
	{
		read_more.click();
	}
	public List<String> getmodelNames()
	{
		for(WebElement modelname : model_names)
		{
			model_name.add(modelname.getText());
		}
		return model_name;
	}
	
	public List<String> getPriceValues()
	{
		for(WebElement price : prices)
		{
			price_value.add(price.getText());
		}
		return price_value;
	}
	
	public List<String> getLaunchDates()
	{
		for(WebElement launchdate : launch_dates)
		{
			expectedlaunch_date.add(launchdate.getText());
		}
		return expectedlaunch_date;
	}
}
