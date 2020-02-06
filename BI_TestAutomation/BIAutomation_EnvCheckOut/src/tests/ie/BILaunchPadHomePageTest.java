package tests.ie;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import BI.automation.common.AutomationTestClass;
import BI.automation.common.PropertiesStore;
import BI.automation.util.EmailSender;
import BI.automation.selenium.ie.hiui.HIUIHomePage;
import BI.automation.selenium.ie.bo.BOHomePage;

public class BILaunchPadHomePageTest extends AutomationTestClass {
	HIUIHomePage homepage;
	BOHomePage biLaunchpadhomepage;
	EmailSender email;
	 @Parameters({ "userconfigfilename" })
	 @Test(priority=1)
	public BILaunchPadHomePageTest(String userconfigfilename) {
		
		// TODO Auto-generated constructor stub
	}

	 @BeforeSuite
	  public void openHomePage() throws InterruptedException 
	  {
			homepage=new HIUIHomePage();
			homepage.openHIUIHomePage();
	  }

	 @Test(priority=1)
	  public void logIn()
	  {
		 homepage.logIn(PropertiesStore.getUserPropertyValue("userid"),
					PropertiesStore.getUserPropertyValue("password"));
	  }
	 @Test(priority=2)
	 public void clickOnApps()
	 {
		 homepage.clickOnApps();
	 }
	 
	 @Test(priority=3)
	 public void OpenBILaunnchPad()
	 {
		 homepage.openBILaunchPad();
		 biLaunchpadhomepage=new BOHomePage(homepage);
		 
	 }
	 @Test(priority=4)
	 public void Openfolderstructure()
	 {
		 biLaunchpadhomepage.openFolder();
		 
	 }
	 @Test(priority=5)
	 public void openReports(){
		 biLaunchpadhomepage.openReports(PropertiesStore.getUserPropertyValue("BOlistofreports"));
		}
	@Test(priority=6)
	 public void closeBO(){
		 biLaunchpadhomepage.logOffBO();
		}
	 @Test(priority=7)
	 public void LogoutFromHIUI(){
		 homepage.logOutFromHIUI();
		}
	 @Test(priority=8)
		public void closeBrowser(){
			homepage.closeBrowser();
		}

}
