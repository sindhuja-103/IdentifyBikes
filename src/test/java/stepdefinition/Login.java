package stepdefinition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pagefactory.CucumberBaseClass;
import pageobjects.LoginPage;

public class Login 
{
	public WebDriver driver=CucumberBaseClass.getDriver();
	LoginPage loginpage = new LoginPage(driver);
	
	@Given("navigate to homepage of zigwheels")
	public void navigate_to_homepage_of_zigwheels()
	{
	    driver.navigate().back();
	    driver.navigate().refresh();
	}

	@When("user clicks on login button")
	public void user_clicks_on_login_button()
	{
	    loginpage.loginPage();
	}

	@Then("click on google")
	public void click_on_google() 
	{
	    loginpage.signInGoogle();
	}

	@Then("provide invalid email-id")
	public void provide_invalid_email_id() throws IOException
	{
		Set<String> windowids = driver.getWindowHandles();
		List<String> windowid = new ArrayList<String>(windowids);
		driver.switchTo().window(windowid.get(1));
	    loginpage.setEmail(CucumberBaseClass.getProperties().getProperty("email"));
	}

	@Then("click on next")
	public void click_on_next() throws InterruptedException
	{
	    loginpage.clickOnNext();
	    Thread.sleep(5000);
	}

	@Then("capture the error message")
	public void capture_the_error_message() 
	{
	    System.out.println(loginpage.captureErrorMessage());
	}
}
