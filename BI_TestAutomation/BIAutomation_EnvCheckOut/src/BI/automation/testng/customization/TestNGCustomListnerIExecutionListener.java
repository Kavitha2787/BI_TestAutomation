package BI.automation.testng.customization;

import org.testng.IExecutionListener;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.xml.XmlSuite;

import BI.automation.common.BILogger;
import BI.automation.common.PropertiesStore;
import BI.automation.selenium.common.WebDriverStore;

public class TestNGCustomListnerIExecutionListener implements IExecutionListener {
	
	@Override
	public void onExecutionStart() {
		BILogger.writeInfoToLog(this.getClass(), "---- TEST STARTED----");		
	}
	
	@Override
	public void onExecutionFinish() {
		
		BILogger.writeInfoToLog(this.getClass(), "---- TEST FINISHED----");
	}


	


}
