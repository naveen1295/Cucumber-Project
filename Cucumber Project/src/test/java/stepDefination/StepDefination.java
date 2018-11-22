package stepDefination;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.LoginPage;
import resources.Base;

public class StepDefination extends Base{
	
	
	LoginPage lp = new LoginPage(driver, log);
	
	@Given("^User is on landing page$")
	public void navigatetolandingpage() {
		lp.navigatetologinpage();
	}
	
	@When("User login with username <userid> and password <password>")
	public void enterusernameandpassword(String userid, String password) {
		lp.enteruserid(userid);
		lp.enterpassword(password);
		lp.clicklogin();
	}
	
	@Then("Home page is dispalyed")
	public void Hompage() {
		System.out.println("user is on homepage");
	}

}
