package tests.edge;


import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import BI.automation.common.AutomationTestClass;
import BI.automation.common.PropertiesStore;
import BI.automation.util.EmailSender;
import BI.automation.selenium.edge.hiui.HIUIHomePage;
import BI.automation.selenium.edge.wpb.WPBHomePage;

public class WPBTestViaHIUIWithNoOfReportsToOpen extends AutomationTestClass {

	HIUIHomePage homepage;
	WPBHomePage wpbhomepage;
	EmailSender email = new EmailSender();
	@Parameters({ "userconfigfilename" })
	public WPBTestViaHIUIWithNoOfReportsToOpen(String userconfigfilename) {
		
	}

	
	@BeforeSuite
	public void openHomePage() {

		String browsertype=PropertiesStore.getSystemPropertyValue("browsertype");
		String IEDriverPath=System.getProperty("user.dir")+"\\"+"drivers"+"\\"+PropertiesStore.getSystemPropertyValue("selenium_IE_browserdriver_path");
		IEDriverPath=IEDriverPath.replace("\\", "/");
		String url = PropertiesStore.getUserPropertyValue("url");
		System.out.println(browsertype);
		System.out.println(IEDriverPath);
		System.out.println(url);

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
	public void openReports(){
		wpbhomepage.openReports(PropertiesStore.getUserPropertyValue("listofreports"));
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


