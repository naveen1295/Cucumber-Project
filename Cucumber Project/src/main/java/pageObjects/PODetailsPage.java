package pageObjects;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class PODetailsPage {

	WebDriver driver;
	String mainWindowHandle;
	Set<String> handles;
	public Logger log;

	public PODetailsPage(WebDriver driver, Logger log) {
		this.driver = driver;
		this.log = log;

	}

	// locators
	By txtvendor = By.name("VendorLookup:LookupCode");
	By ddexpensetype = By.id("ExpTypeList_lblExpTypeList");
	By txtpodescription = By.id("Desctxt_TextBox");
	By txttotalamount = By.id("TotalAmt_TextBox");
	By ddworkflow = By.id("CommonWorkflow_WorkFlowID_DropDownList");
	By txtpostatus = By.id("CommonWorkflow_lblWorkflowStatus_Label");
	By txtcurrentstep = By.id("CommonWorkflow_txtCurrentStep_Label");
	By ddnextstep = By.id("CommonWorkflow_NextStepList2");
	By txtpropertycode = By.id("DetailsGrid_DataTable_row0_ReadOnlyColPropId");
	By txtpropertydesc = By.id("DetailsGrid_DataTable_row0_podetdesc");
	By txtglaccount = By.id("DetailsGrid_DataTable_row0_ReadOnlyColAcctId");
	By txtqtyord = By.id("DetailsGrid_DataTable_row0_qty");
	By txtunitprice = By.id("DetailsGrid_DataTable_row0_tranunitprice");
	By btnsave = By.id("savebutton_Button");
	By tabapprover = By.xpath("//span[text()='Approvers']");
	By tblapprovers = By.xpath("//table[@id='CommonWorkflowApprovers_WfApprovers_DataTable']//tr");

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
		
		public List<WebElement> getwebelements(By locator,String field) {
			
			try {
			log.info("Identifying Element:'"+field+"'");
			return driver.findElements(locator);
			}catch(Exception e) {
				log.error("Not able to identify webelements:'"+field+"'");
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

		//Select Value	
		public void selectvalue(By locator, String value,String field) {
			
			try {
			Select dropdown = new Select(getwebelement(locator,field));
			dropdown.selectByVisibleText(value);
			log.info(value+" selected for "+field);
			}catch(Exception e) {
				log.error("Not able to select '"+value+"' value for field "+field);
				log.error(e);
			}
		}
	// switch to new window

	public void getwindowhandles() {
		mainWindowHandle = driver.getWindowHandle();
		handles = driver.getWindowHandles();
	}

	public void switchtopodetailspage() throws InterruptedException {
		getwindowhandles();
		for (String childWindowHandle : handles) {
			// If window handle is not main window handle then close it
			if (!childWindowHandle.equals(mainWindowHandle)) {
				driver.switchTo().window(childWindowHandle);
				//Thread.sleep(2000);
			}
		}

	}

	public void switchtomainwindow() {
		for (String windowHandle : handles) {
			if (!windowHandle.equals(mainWindowHandle)) {
				driver.switchTo().window(windowHandle);
				driver.close(); // closing child window
				driver.switchTo().window(mainWindowHandle); // cntrl to parent window
			}
		}
	}

	// assert function
	public void verifyvalue(By locator, String expectedvalue, String field) {
		String s = getwebelement(locator,field).getText().trim();
		DecimalFormat decimalFormat;
		float f;
		if (field.equals("Order Qty")) {
			f = Float.parseFloat(expectedvalue);
			decimalFormat = new DecimalFormat("#,###,###.0000");
			expectedvalue = decimalFormat.format(f);
		} else if (field.equals("Unit Price")||field.equals("Total Amount")) {
			f = Float.parseFloat(expectedvalue);
			decimalFormat = new DecimalFormat("#,###,###.00");
			expectedvalue = decimalFormat.format(f);
		} 
		
		try {
		Assert.assertEquals(s, expectedvalue,
				field + " value is not a Match. Expected value:'" + expectedvalue + "', Actual Value :" + s + "'");
		log.info(field + " value is a Match. Expected value:'" + expectedvalue + "', Actual Value :'" + s + "'");
		}catch(Exception e) {
			log.error(field + " value is not a Match. Expected value:'" + expectedvalue + "', Actual Value :'" + s + "'");
			log.error(e);
		}
	}

	// check vendor
	public void verifyvendorcode(String expectedvalue) {
		try {
		Assert.assertEquals(getwebelement(txtvendor,"Vendor").getAttribute("value"), expectedvalue, "Vendor is not a Match.");
		log.info("Vendor value is a Match. Expected value:'" + getwebelement(txtvendor,"Vendor").getAttribute("value") + "', Actual Value :'" + expectedvalue + "'");
		}catch(Exception e) {
			log.error("Vendor value is not a Match. Expected value:'" + getwebelement(txtvendor,"Vendor").getAttribute("value") + "', Actual Value :'" + expectedvalue + "'");
			log.error(e);
		}
	}

	// check expense type
	public void verifyexpensetype(String expectedvalue) {
		verifyvalue(ddexpensetype, expectedvalue, "Expense Type");
	}

	// check PO description
	public void verifypodesc(String expectedvalue) {
		verifyvalue(txtpodescription, expectedvalue, "PO description");
	}

	// check Total Amount
	public void verifytotalamount(String expectedvalue) {
		verifyvalue(txttotalamount, expectedvalue, "Total Amount");
	}

	// check Workflow
	public void verifyworkflow(String expectedvalue) {
		verifyvalue(ddworkflow, expectedvalue, "Workflow");
	}

	// check PO status
	public void verifypostatus(String expectedvalue) {
		verifyvalue(txtpostatus, expectedvalue, "PO status");
	}

	// check current step
	public void verifycurrentstep(String expectedvalue) {
		verifyvalue(txtcurrentstep, expectedvalue, "Current Step");
	}

	// check Property Code
	public void verifypropcode(String expectedvalue) {
		verifyvalue(txtpropertycode, expectedvalue, "Property Code");
	}

	// check property description
	public void verifypropdesc(String expectedvalue) {
		verifyvalue(txtpropertydesc, expectedvalue, "Property description");
	}

	// check GL Account
	public void verifyglaccount(String expectedvalue) {
		verifyvalue(txtglaccount, expectedvalue, "GL Account");
	}

	// check Order Qty
	public void verifyordqty(String expectedvalue) {
		verifyvalue(txtqtyord, expectedvalue, "Order Qty");
	}

	// check Unit Price
	public void verifyunitprice(String expectedvalue) {
		verifyvalue(txtunitprice, expectedvalue, "Unit Price");
	}

	// put next step value
	public void enternextstep(String s) {
		entervalue(ddnextstep,s,"PO Next Step");
	}

	// save
	public void clicksave() {
		clickelement(btnsave,"Save");
	}

	public int rowcountofapproverstable(By locator,String field) {
		return getwebelements(locator,field).size();
	}

	public void selectapprover(String value) {
		if (rowcountofapproverstable(tblapprovers,"Approvers Table") > 1) {
		} else {
		}
	}

	public void clickapprovertab() {
		clickelement(tabapprover,"Approvers Tab");
	}

}
