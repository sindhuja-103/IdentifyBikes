package pageobjects;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class UsedCarsPage extends BasePage
{
	List<String> popular_carmodels = new ArrayList<String>();
	public UsedCarsPage(WebDriver driver)
	{
		super(driver);
	}
	
	//locators
	
	@FindBy(xpath="//img[@data-track-label='zw-header-logo']")
	WebElement home_page;
	
	@FindBy(linkText="Used Cars")
	WebElement used_cars;
	
	@FindBy(xpath="//span[@onclick=\"goToUrl('/used-car/Chennai')\"]")
	WebElement clickchennai;
	
	@FindBy(xpath="//label[@for=\"price5\"]")
	public WebElement scroll_upto_popularmodels;
	
	//@FindBy(xpath="//div[@class=\"gsc_thin_scroll\"]")
	//public WebElement slider_element;
	
	@FindBy(xpath="//ul[@class='zw-sr-secLev usedCarMakeModelList popularModels ml-20 mt-10']/li")
	List<WebElement> popular_models;
	
	
	//action methods
	public void navigateToHome()
	{
		home_page.click();
	}
	public void moveToUsedCars()
	{
		Actions action = new Actions(driver);
		action.moveToElement(used_cars).perform();;
		
	}
	
	public void selectChennai()
	{
		clickchennai.click();
	}
	
	public List<String> getPopularModels()
	{
		for(WebElement model : popular_models )
		{
			popular_carmodels.add(model.getText());
		}
		return popular_carmodels;
	}
}
