package pageObjects;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import resources.Base;

public class AddPOPage extends Base {

	WebDriver driver;
	public List<WebElement> rows;
	public String PONumber;
	public Logger log;

	public AddPOPage(WebDriver driver, Logger log) {
		this.driver = driver;
		this.log = log;
	}

	// Locators
	// links
	By signoff = By.xpath("//a[text()='Sign Off']");

	// text-box editable
	By txtvendor = By.id("VendorLookup_LookupCode");
	By txtpodescription = By.id("Desctxt_TextBox");
	By txtpropcode = By.id("LookupProperty_txtCode_TextBox");
	By txtpropdesc = By.id("DetailsGrid_DataTable_row0_podetdesc");
	By txtglaccountcode = By.id("LookupAccountType_txtCode_TextBox");
	By txtqtyorder = By.id("DetailsGrid_DataTable_row0_qty");
	By txtunitprice = By.id("DetailsGrid_DataTable_row0_tranunitprice");
	By txtbudgetdescription = By.id("OverBudgetNarrtxt_TextBox");

	// text-box read-only
	By txtpostatus = By.id("CommonWorkflow_lblWorkflowStatus_Label");
	By txtpocurrentstatus = By.id("CommonWorkflow_txtCurrentStep_Label");
	By txtpototalamount = By.id("TotalAmt_TextBox");
	By txtpurchaseordernumber = By.xpath("//*[text()[contains(.,'Purchase Order ')]]");
//	By txtpropertymanagername = By.xpath

	// drop-down editable
	By ddponextstatus = By.id("CommonWorkflow_NextStepList2");
	By ddexpensetype = By.id("ExpTypeList_DropDownList");

	// drop-down read-only
	By ddworkflow = By.id("CommonWorkflow_WorkFlowID_DropDownList");

	// buttons
	By btnpropertycodelookup = By.id("DetailsGrid_DataTable_row0_PropIdLookup_PropId_Button");
	By btnfindcode = By.id("cmdFind_Button");
	By btnokcode = By.id("cmdOK_Button");
	By btnglaccountlookup = By.id("DetailsGrid_DataTable_row0_AcctIdLookup_AcctId_Button");
	By btnsavepo = By.id("savebutton_Button");

	// check-box
	By chkbxcode = By.xpath("//*[@id=\"row1\"]/td[1]/input");

	// tab
	By tabapprovers = By.id("YsiTabStrip1_tab4");

	// table
//	By tableapprovers = By.id("CommonWorkflowApprovers_WfApprovers_DataTable");
	By tableapprovers = By.xpath("//table[@id='CommonWorkflowApprovers_WfApprovers_DataTable']//tr");
	// *[@id="CommonWorkflowApprovers_WfApprovers_DataTable"]
	// *[@id="CommonWorkflowApprovers_WfApprovers_DataTable_row0"]

	
	
	
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
//		dropdown.selectByValue(value);
		dropdown.selectByVisibleText(value);
		log.info(value+" selected for "+field);
		}catch(Exception e) {
			log.error("Not able to select '"+value+"' value for field "+field);
			log.error(e);
		}
	}
	
	// switch to frame
	public void switchtoframe(String value, String field) {
		try{
			driver.switchTo().frame(value);
			log.info("Switched to Frame:'"+field+"'");
		}catch(Exception e) {
			log.error("Not able to switch to frame:'"+field+"'");
			log.error(e);
		}
		
	}
	

	// verify function
	
	public String gettext(By locator, String field) {
		return getwebelement(locator,field).getText().trim();
	}

	public void verifyvalue(By locator, String value, String field) {
		String actual=null;
		try {
			actual = gettext(locator,field);
			Assert.assertEquals(actual, value, field + " value is not matching. Actual:'"+actual+"' and Expected:'"+value+"'");
			log.info(field + " value is matching. Actual:'"+actual+"' and Expected:'"+value+"'");
		}catch(Exception e) {
			log.error(field + " value is not matching. Actual:'"+actual+"' and Expected:'"+value+"'");
			log.error(e);
		}
	}
	
	
	//*************Actions
	
	
	public void entervendor(String value) {
		entervalue(txtvendor,value,"Vendor");
	}

	public void enterpodescription(String value) {
		entervalue(txtpodescription, value, "PO Description");
	}

	public void enterpropertycode(String value) {
		entervalue(txtpropcode, value, "Property Code");
	}

	public void enterpropertydescription(String value) {
		entervalue(txtpropdesc, value, "Property Desciption");
	}

	public void enterglaccountcode(String value) {
		entervalue(txtglaccountcode, value, "GL Account");
	}

	public void enterquantityorder(String value) {
		entervalue(txtqtyorder, value, "Quantity Order");
	}

	public void enterunitprice(String value) {
		entervalue(txtunitprice, value, "Unit Price");
	}

	public void enterbudgetdescription(String value) {
		entervalue(txtbudgetdescription, value, "Budget Description");
	}

	// select value from drop-down

	public void selectponextstatus(String value) {
		selectvalue(ddponextstatus, value, "PO Next Status");
	}

	public void selectexpensetype(String value) {
		selectvalue(ddexpensetype, value, "Expense Type");
	}

	// click button
	public void clickpropertycodelookup() {
		clickelement(btnpropertycodelookup,"Property Code lookup");
	}

	public void clickfindcode() throws InterruptedException {
		clickelement(btnfindcode,"Find Code");
		Thread.sleep(2000);
	}

	public void clickok() {
		clickelement(btnokcode,"OK");
	}

	public void clickglaccountcodelookup() {
		clickelement(btnglaccountlookup,"GL Account Lookup");
	}

	public void clicksave() {
		clickelement(btnsavepo,"Save");
	}

	//Switch frames

	public void switchtoaddppoiframe() {
		switchtoframe("filter","Body Frame");
	}

	public void switchtoiframe() {
		switchtoframe("popupiframe","PO Details");
	}

	public void switchtobrowser() {
		driver.switchTo().defaultContent();
	}

	// select check box
	public void clickcheckbox() {
		clickelement(chkbxcode,"Check Box");
	}


	// verify workflow assigned
	public void assertworkflowassigned(String value) {
		verifyvalue(ddworkflow, value, "Workflow");
	}

	// verify po status
	public void verifypostatus(String value) {
		verifyvalue(txtpostatus, value, "PO Status");
	}

	// Verifying Purchase order current status
	public void verifypocurrentstatus(String value) {
		verifyvalue(txtpocurrentstatus, value, "PO Current Step");
	}

	// Verifying total amount of purchase order
	public void verifytotalamount(String value) {
		verifyvalue(txtpototalamount, value, "Total Amount");
	}

	// store purchase order number
	public void getpurchaseorder() {
		String s = getwebelement(txtpurchaseordernumber,"Purchase Order").getText();
		PONumber = s.replace("Purchase Order ", "");
		log.info("Purchase Order Number is "+PONumber);
	}

	// click approvers tab
	public void clickapproverstab() {
		clickelement(tabapprovers,"Apprivers Tab");
	}

	// check rows in approver table
	public void rowsintable() {
		rows = getwebelements(tableapprovers,"Approver Data Table");
	}

	// check if the property manager name matches
	public void verifyandcheckpreferredpropertymanagername(String value) {
		//System.out.println("expected value" + value);
		for (WebElement e : rows) {
			// System.out.println(getwebelement());
			//System.out.println(e); 
		}
	}

	public void signoff() {
		clickelement(signoff,"Sign Off");
	}

}
