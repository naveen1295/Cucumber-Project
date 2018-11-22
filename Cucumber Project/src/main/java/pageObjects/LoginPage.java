package pageObjects;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import resources.Base;

public class LoginPage extends Base {

	public WebDriver driver;
	Actions actions;
	public Logger log;
	

	public LoginPage(WebDriver driver, Logger log) {
		this.driver = driver;
		actions = new Actions(driver);
		this.log = log;
		
	}

	By txtuserid = By.id("Username");
	By txtpassword = By.id("Password_Text");
	By ddenvironment = By.id("Destination");
	By btnlogin = By.id("cmdLogin1");

	
	//***************Basic Functions*****************
	
	
    //Find Web Element
		public WebElement getwebelement(By locator, String field) {
			try{
			log.info("Identifying Element:'"+field+"'");
			return driver.findElement(locator);
			}
			catch(Exception e) {
				log.error("Not able to identify Element:'"+field+"'");
				log.error(e);
				return null;
			}
			
		}
	
	//Enter or Select value
	public void entervalue(By locator, String value, String field) {
		
		try {
		getwebelement(locator,field).sendKeys(value);
		log.info("Value is entered/selected for:'"+field+"'");
		}catch(Exception e) {
			log.error("Not able to enter/select Value for Field:'"+field+"'");
			log.error(e);
		}
	}
	
	// Click Web Element
	public void clickelement(By locator, String field) {
		
		try {
		getwebelement(locator,field).click();
		log.info("Clicked Element:'"+field+"'");
		}catch(Exception e) {
			log.error("Not able to click Element:'"+field+"'");
			log.error(e);
		}
	}

	
	// **************Actions
	public void enteruserid(String userid) {
		entervalue(txtuserid,userid,"User ID Text Box");
	}

	public void enterpassword(String password) {
		actions.moveToElement(getwebelement(txtpassword,"Password Text Box"));
		actions.click().sendKeys(password).build().perform();
	}

	public void selectenvironment(String env) {
		entervalue(ddenvironment,env, "Environment");
	}

	public void clicklogin() {
		clickelement(btnlogin,"Login Button");
	}

}
