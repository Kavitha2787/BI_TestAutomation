package tests.edge;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import BI.automation.common.AutomationTestClass;
import BI.automation.common.PropertiesStore;
import BI.automation.util.EmailSender;
import BI.automation.selenium.edge.hiui.HIUIHomePage;
import BI.automation.selenium.edge.wpb.WPBHomePage;

public class WPBTestViaHIUIWithSpecficReportsToOpen extends AutomationTestClass {

	HIUIHomePage homepage;
	WPBHomePage wpbhomepage;
	EmailSender email = new EmailSender();
	
	public WPBTestViaHIUIWithSpecficReportsToOpen(String userconfigfilename) {
	}

	
	@BeforeSuite()
	public void openHomePage() {
		homepage=new HIUIHomePage();
		homepage.openHIUIHomePage();
	}

	@Test(priority=1)
	public void logIn() {
		homepage.logIn(PropertiesStore.getUserPropertyValue("userid"),
				PropertiesStore.getUserPropertyValue("password"));
	}

	@Test(priority=2)
	public void clickOnApps(){
		homepage.clickOnApps();
	}

	@Test(priority=3)
	public void OpenWPB(){
		homepage.openWPBApplication();
		wpbhomepage=new WPBHomePage(homepage);

	}

	@Test(priority=4)
	public void openReportsWithPath(){
		wpbhomepage.openReports(PropertiesStore.getUserPropertyValue("ReportscountFolder"),Integer.parseInt(PropertiesStore.getUserPropertyValue("Reportscount")));
	}
	
	@Test(priority=5)
	public void closeWPB(){
		wpbhomepage.closeWPB();

	}
	@Test(priority=6)
	public void logout(){
		homepage.logOutFromHIUI();

	}
	
	@AfterTest
	public void closeBrowser(){
		homepage.closeBrowser();
	}


}

