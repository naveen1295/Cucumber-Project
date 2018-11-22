package pageObjects;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import resources.Base;

public class HomePage extends Base {

	WebDriver driver;
	Actions action;
	public Logger log;

	public HomePage(WebDriver driver, Logger log) {
		this.driver = driver;
		action = new Actions(driver);
		this.log = log;
	}

	// locators

	// links
	By lnkdashboard = By.xpath("//a[text()='Dashboards']");
	By lnkworkflowdashboard = By.xpath("//a[text()='Workflow Dashboard']");
	By lnkrole = By.xpath("//a[text()='Roles']");
	By lnkpurchasing = By.xpath("//a[text()='Purchasing']");
	By lnkpurchaseorder = By.xpath("//a[text()='Purchase Orders']");
	By lnkAddPO = By.xpath("//a[text()='Add PO']");

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

		//*************Actions
	public void clickrole() {
		clickelement(lnkrole,"Roles");
	}

	public void clickpurchasing() {
		clickelement(lnkpurchasing,"Purchasing");
	}

	public void clickpurchaseorder() {
		clickelement(lnkpurchaseorder,"Purchase Order");
	}

	public void clickAddPO() {
		clickelement(lnkAddPO,"Add PO");
	}

	public void clickdashboard() {
		clickelement(lnkdashboard,"Dashboard");
	}

	public void clickworkflowdashboard() {
		clickelement(lnkworkflowdashboard,"Workflow Dashboard");
	}
}
