
package tests.edge;


import org.testng.annotations.Test;

import BI.automation.autoit.dssjobscheduler.DSSJobScheduler;
import BI.automation.common.AutomationTestClass;
//import BI.automation.common.AutomationTestClass;
import BI.automation.common.PropertiesStore;
import BI.automation.util.EmailSender;
import BI.automation.selenium.edge.hiui.HIUIHomePage;
import BI.automation.selenium.edge.emt.EMTHomePage;
import BI.automation.selenium.edge.wpb.WPBHomePage;
import BI.automation.selenium.edge.analyticslibrary.AnalyticsLibraryHomePage;
import BI.automation.selenium.edge.bo.BOHomePage;

public class BIEnvironmentCheckout  extends AutomationTestClass{
	HIUIHomePage homepage;
	BOHomePage biLaunchpadhomepage;
	WPBHomePage wpbhomepage;
	AnalyticsLibraryHomePage analyticshomepage;
	EMTHomePage emeasuretool;
	EmailSender email;
	
// open HIUI home page
	@Test(priority=1)
	public void openHIUIHomePage() {
		homepage=new HIUIHomePage();
		homepage.openHIUIHomePage();
	}

	@Test(priority=2)
	public void logInToHIUIHomePage()	{
		homepage.logIn(PropertiesStore.getUserPropertyValue("userid"),
				PropertiesStore.getUserPropertyValue("password"));
	}
// Open Analytics Library
	@Test(priority=3)
	public void clickOnAppsForAnalyticsLibrary(){
		homepage.clickOnApps();
	}

	@Test(priority=4)
	public void openAnalyticsLibrary(){
		homepage.openAnalyticsLibrary();
		analyticshomepage=new AnalyticsLibraryHomePage(homepage);
	}

	@Test(priority=5)
	public void openAnalyticsLibraryLReports(){
		analyticshomepage.openALReports(PropertiesStore.getUserPropertyValue("ALlistofreports"));
	}
	
// Open BI Launch Pad
	@Test(priority=6)
	public void clickOnAppsForBILaunchPad(){
		homepage.clickOnApps();		
	}
	@Test(priority=7)
	public void openBILaunnchPad()	{
		homepage.openBILaunchPad();
		biLaunchpadhomepage=new BOHomePage(homepage);
	}
	
	@Test(priority=8)
	public void openBiLaunchPadFolder()	{
		biLaunchpadhomepage.openFolder();
	}
	
	@Test(priority=9)
	public void openBiLaunchPadReports(){
		biLaunchpadhomepage.openReports(PropertiesStore.getUserPropertyValue("BOlistofreports"));
	}
	
	@Test(priority=10)
	public void logOffFromBiLaunchPad(){
		biLaunchpadhomepage.logOffBO();
	}
		
	@Test(priority=11)
	public void clickOnAppsForWPB(){
		homepage.clickOnApps();
	}

	@Test(priority=12)
	public void openWPB(){
		homepage.openWPBApplication();
		wpbhomepage=new WPBHomePage(homepage);
	}

	@Test(priority=13)
	public void openWPBReports(){
		wpbhomepage.openReports(PropertiesStore.getUserPropertyValue("listofreports"));
	}

	@Test(priority=14)
	public void closeWPB(){
		wpbhomepage.closeWPB();
	}

	
	
	@Test(priority=15)
	public void clickOnAppsForEMT(){
		homepage.clickOnApps();
	}
	
	@Test(priority=16)
	public void openEmeasureTool(){
		homepage.openEmeasureTool();
		emeasuretool=new EMTHomePage(homepage);
	}
	
	@Test(priority=17)
	public void clickOnSetupTab(){
		emeasuretool.clickonSetupTab();
		emeasuretool.checkAllSetupTabs();
	}
	
	@Test(priority=18)
	public void clickOnMapTab(){
		emeasuretool.clickonMapTab();
		emeasuretool.checkAllMapTabs();
	}
	
	@Test(priority=19)
	public void clickOnToolsTab(){
		emeasuretool.clickonToolsTab();
		emeasuretool.checkAllTools();
	}
	
	@Test(priority=20)
	public void clickOnAdministrationTab(){
		emeasuretool.clickonAdministrationTab();
		emeasuretool.checkAllAdministration();
	}
	
	@Test(priority=21)
	public void clickOnSubmittersTab(){
		emeasuretool.clickonSubmittersTab();
	}

	@Test(priority=22)
	public void closeEMT(){
		emeasuretool.closeEMT();
	}
	
	@Test(priority=23)
	public void logOutFromHIUIHomePage(){
		homepage.logOutFromHIUI();
	}
	
	@Test(priority=24)
	public void validateDSSJobScheduler(){
		DSSJobScheduler dssjobscheduler=new DSSJobScheduler(PropertiesStore.getUserPropertyValue("dreexepath"),
				PropertiesStore.getUserPropertyValue("dssdbname"),
				PropertiesStore.getUserPropertyValue("dssscheduleruserid"),
				PropertiesStore.getUserPropertyValue("dssschedulerpassword"));
		dssjobscheduler.startDSSSchedulerChecking();
	}
}