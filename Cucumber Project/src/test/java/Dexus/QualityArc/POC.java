package Dexus.QualityArc;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import pageObjects.AddPOPage;
//import pageObjects.AddPOPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.PODetailsPage;
import pageObjects.WorkflowDashboardPage;
import resources.Base;

public class POC extends Base {

	@Test
	public void POCtest() throws IOException, InterruptedException {
		driver = driverintialisation();
		String url = prop.getProperty("url");
		String workflow = prop.getProperty("workflow");
		String userid = prop.getProperty("orignatorid");
		String password = prop.getProperty("originatorpassword");
		String env = prop.getProperty("environment");
		String pmname = prop.getProperty("PropertyManagerName");
		String pmuserid = prop.getProperty("PropertyManagerID");
		String pmpassword = prop.getProperty("PropertyManagerPassword");
		String vendor = prop.getProperty("VendorCode");
		String expensetype = prop.getProperty("ExpenseType");
		String podescription = prop.getProperty("PODescription");
		String propertycode = prop.getProperty("PropertyCode");
		String propertydescription = prop.getProperty("PropertyDescription");
		String glaccountCode = prop.getProperty("GLAccountCode");
		String unitprice = prop.getProperty("UnitPrice");
		String orderqty = prop.getProperty("OrderQty");
		String budgetdescription = prop.getProperty("BudgetDescription");
		String fundmanagerid = prop.getProperty("FundManagerUserID");
		String fundmanagarename = prop.getProperty("FundManagerName");
		String fundmanagerpassword = prop.getProperty("FundManagerPassword");
		String objecttype = "Purchase Orders";

		LoginPage lp = new LoginPage(driver,log);
		HomePage hp = new HomePage(driver,log);
		AddPOPage ap = new AddPOPage(driver,log);
		WorkflowDashboardPage wdp = new WorkflowDashboardPage(driver,log);
		PODetailsPage podp = new PODetailsPage(driver,log);

		// login as originator and generate a PO for with the workflow
		// login
		driver.get(url);
		driver.manage().window().maximize();
		lp.enteruserid(userid);
		lp.enterpassword(password);
		lp.selectenvironment(env);
		lp.clicklogin();
		// Add PO
		hp.clickrole();
		hp.clickpurchasing();
		hp.clickpurchaseorder();
		hp.clickAddPO();
		ap.switchtoaddppoiframe();
		ap.entervendor(vendor);
		ap.selectexpensetype(expensetype);
		ap.enterpodescription(podescription);
		ap.clickpropertycodelookup();
		ap.switchtoiframe();
		ap.enterpropertycode(propertycode);
		ap.clickfindcode();
		ap.clickcheckbox();
		ap.clickok();
		ap.switchtoaddppoiframe();
		ap.enterpropertydescription(propertydescription);
		ap.clickglaccountcodelookup();
		ap.switchtoiframe();
		ap.enterglaccountcode(glaccountCode);
		ap.clickfindcode();
		ap.clickcheckbox();
		ap.clickok();
		ap.switchtoaddppoiframe();
		ap.enterquantityorder(orderqty);
		ap.enterunitprice(unitprice);
		ap.clicksave();
		ap.enterbudgetdescription(budgetdescription);
		ap.clicksave();
		ap.verifypostatus("InProcess");
		ap.verifypocurrentstatus("Enter Details");
		ap.assertworkflowassigned(workflow);

		// send for PM review
		ap.selectponextstatus("Send for PM Review");
		ap.clicksave();
		ap.clicksave();
		ap.verifypocurrentstatus("Send for PM Review");
		ap.verifypostatus("InProcess");
		ap.getpurchaseorder();
		ap.clickapproverstab();
		ap.rowsintable();
		ap.verifyandcheckpreferredpropertymanagername(pmname);
		ap.clicksave();

		ap.switchtobrowser();
		ap.signoff();
		// Login as fund manager
		driver.get(url);
		lp.enteruserid(pmuserid);
		lp.enterpassword(pmpassword);
		lp.selectenvironment(env);
		lp.clicklogin();
		// navigate to created PO
		hp.clickdashboard();
		hp.clickworkflowdashboard();
		wdp.switchtoframefilter();
		wdp.selectobjecttype(objecttype);
		wdp.enterpofromvalue(ap.PONumber);
		wdp.enterpotovalue(ap.PONumber);
		wdp.clickdisplay();
		wdp.clickponumber();
		podp.switchtopodetailspage();
		podp.verifyvendorcode(vendor);
		podp.verifyexpensetype(expensetype);
		podp.verifypodesc(podescription);
		podp.verifyworkflow(workflow);
		podp.verifycurrentstep("Send for PM Review");
		podp.verifypropcode(propertycode);
		podp.verifypropdesc(propertydescription);
		podp.verifyglaccount(glaccountCode);
		podp.verifyordqty(orderqty);
		podp.verifyunitprice(unitprice);
		// send for fund manager approval
		podp.enternextstep("Send for Fund Manager Approval");
		podp.clicksave();
		podp.clickapprovertab();
		podp.selectapprover(fundmanagarename);
		podp.clicksave();
		podp.switchtomainwindow();
		wdp.clicksignout();

		// login as fund manager
		driver.get(url);
		lp.enteruserid(fundmanagerid);
		lp.enterpassword(fundmanagerpassword);
		lp.clicklogin();
		// navigate to po details page
		hp.clickdashboard();
		hp.clickworkflowdashboard();
		wdp.switchtoframefilter();
		wdp.selectobjecttype(objecttype);
		wdp.enterpofromvalue(ap.PONumber);
		wdp.enterpotovalue(ap.PONumber);
		wdp.clickdisplay();
		wdp.clickponumber();
		// Verify PO Details
		podp.switchtopodetailspage();
		podp.verifyvendorcode(vendor);
		podp.verifyexpensetype(expensetype);
		podp.verifypodesc(podescription);
		podp.verifyworkflow(workflow);
		podp.verifycurrentstep("Send for Fund Manager Approval");
		podp.verifypropcode(propertycode);
		podp.verifypropdesc(propertydescription);
		podp.verifyglaccount(glaccountCode);
		podp.verifyordqty(orderqty);
		podp.verifyunitprice(unitprice);
		podp.enternextstep("Approve PO");
		podp.clicksave();
		podp.switchtomainwindow();
		wdp.clicksignout();
	}
	
	@AfterTest
	public void teardown() {
		driver.close();
		driver = null;
		log = null;
	}

}
