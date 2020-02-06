package tests.edge;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import BI.automation.common.AutomationTestClass;
import BI.automation.common.PropertiesStore;
import BI.automation.selenium.edge.emt.EMTHomePage;
import BI.automation.selenium.edge.hiui.HIUIHomePage;

//import wpb.common.WPBHomePage;

public class EMTTestViaHIUI extends AutomationTestClass {
	HIUIHomePage homepage;
	EMTHomePage emeasuretool;
	@Parameters({ "userconfigfilename" })


	public EMTTestViaHIUI(String userconfigfilename) {

	}


	@BeforeSuite
	public void openHomePage(){
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
	public void logIn(){
		homepage.logIn(PropertiesStore.getUserPropertyValue("userid"),
				PropertiesStore.getUserPropertyValue("password"));
	}
	@Test(priority=2)
	public void clickOnApps(){
		homepage.clickOnApps();
	}
	@Test(priority=3)
	public void OpenEmeasureTool(){
		homepage.openEmeasureTool();
		emeasuretool=new EMTHomePage(homepage);
	}
	@Test(priority=4)
	public void clickonSetuptab(){
		emeasuretool.clickonSetupTab();
		emeasuretool.checkAllSetupTabs();
	}
	@Test(priority=5)
	public void clickonMapTab(){
		emeasuretool.clickonMapTab();
		emeasuretool.checkAllMapTabs();
	}
	@Test(priority=6)
	public void clickonToolsTab(){
		 emeasuretool.clickonToolsTab();
		 emeasuretool.checkAllTools();
		 
	}
	@Test(priority=7)
	public void clickonAdministrationTab(){
		 emeasuretool.clickonAdministrationTab();
		 emeasuretool.checkAllAdministration();
		 
	}
	@Test(priority=8)
	public void clickonSubmittersTab(){
		 emeasuretool.clickonSubmittersTab();
		 
	}

	@Test(priority=9)
	public void closeEMT(){
		emeasuretool.closeEMT();

	}

	@Test(priority=10)
	public void logout(){
		homepage.logOutFromHIUI();

	}

	@AfterTest
	public void closeBrowser(){
		homepage.closeBrowser();
	}


}


