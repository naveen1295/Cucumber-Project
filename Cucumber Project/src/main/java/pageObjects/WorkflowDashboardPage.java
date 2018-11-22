package pageObjects;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WorkflowDashboardPage {

	WebDriver driver;
	public Logger log;

	public WorkflowDashboardPage(WebDriver driver,Logger log) {
		this.driver = driver;
		this.log = log;
	}

	// dropdown
	By ddobjectype = By.id("ObjectTypeDropDown_DropDownList");

	// textbox
	By txtpofrombox = By.id("poFrom_TextBox");
	By txtpotobox = By.id("poTo_TextBox");
	By txtponumber = By.id("WorkFlowGrid:DataTable:row0:RecordLink_anchor");

	// button
	By btndisplay = By.id("SubmitButton_Button");

	// links
	By lnksignout = By.xpath("//a[text()='Sign Out']");
	
	
	// Functions
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
		//Enter value
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


	public void selectobjecttype(String value) {
		entervalue(ddobjectype,value,"Object Type");
	}

	public void enterpofromvalue(String value) {
		entervalue(txtpofrombox, value, "PO From");
	}

	public void enterpotovalue(String value) {
		entervalue(txtpotobox, value,"PO TO");
	}

	public void clickdisplay() throws InterruptedException {
		clickelement(btndisplay,"Display");
		Thread.sleep(2000);
	}

	public void switchtoframefilter() {
		driver.switchTo().frame("filter");
	}

	public void clickponumber() {
		clickelement(txtponumber,"PO Number");
	}

	public void clicksignout() {
		clickelement(lnksignout,"Sign Out");
	}
}
