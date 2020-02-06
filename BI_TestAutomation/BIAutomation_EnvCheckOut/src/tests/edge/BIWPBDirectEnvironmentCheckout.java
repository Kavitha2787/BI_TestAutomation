
package tests.edge;



import org.testng.annotations.Test;

import BI.automation.common.AutomationTestClass;
//import BI.automation.common.AutomationTestClass;
import BI.automation.common.PropertiesStore;
import BI.automation.selenium.edge.wpb.WPBHomePage;

public class BIWPBDirectEnvironmentCheckout  extends AutomationTestClass{
	
	WPBHomePage wpbhomepage;
	
	@Test(priority=1)
	public void initializeWPBPage(){
		wpbhomepage=new WPBHomePage(PropertiesStore.getSystemPropertyValue("IE")
				,PropertiesStore.getSystemPropertyValue("selenium_IE_browserdriver_path")
				,PropertiesStore.getUserPropertyValue("url"));
	}
	
	@Test(priority=2)
	public void loginWPB(){
		wpbhomepage.directWPBLogin(PropertiesStore.getUserPropertyValue("userid"),
				PropertiesStore.getUserPropertyValue("password"));
		wpbhomepage.directWPBreportclick();
	}
	
		
	
	@Test(priority=5)
	public void openWPBReports(){
		wpbhomepage.openReports(PropertiesStore.getUserPropertyValue("listofreports"));
	}

	@Test(priority=6)
	public void closeWPB(){
		wpbhomepage.closeWPB();
	}

}