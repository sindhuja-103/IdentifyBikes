package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage
{
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	
	//Locators
	
	@FindBy(id="des_lIcon")
	WebElement login;
	
	@FindBy(xpath="//span[text()='Google']")
	WebElement google;
	
	@FindBy(xpath="//input[@autocomplete=\"username\"]")
	WebElement email;
	
	@FindBy(xpath="//span[text()=\"Next\"]")
	WebElement next;
	
	@FindBy(xpath="//div[text()='Couldn’t find your Google Account']")
	WebElement warning_message;
	
	//action methods
	
	public void loginPage()
	{
		login.click();
	}
	
	public void signInGoogle()
	{
		google.click();
	}
	
	public void setEmail(String emaildata)
	{
		email.sendKeys(emaildata);
	}
	
	public void clickOnNext()
	{
		next.click();
	}
	
	public String captureErrorMessage()
	{
		String error_message = warning_message.getText();
		return error_message;
	}
}
