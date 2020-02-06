package tests.ie;

import org.testng.annotations.Test;

import BI.automation.autoit.dssjobscheduler.DSSJobScheduler;
import BI.automation.common.AutomationTestClass;
import BI.automation.common.PropertiesStore;

//import wpb.common.WPBHomePage;

public class DSSJobSchedulerTest extends AutomationTestClass {
	@Test()
	public void validateDSSJobScheduler(){
		DSSJobScheduler dssjobscheduler=new DSSJobScheduler(PropertiesStore.getUserPropertyValue("dreexepath"),
															PropertiesStore.getUserPropertyValue("dssdbname"),
															PropertiesStore.getUserPropertyValue("dssscheduleruserid"),
															PropertiesStore.getUserPropertyValue("dssschedulerpassword"));
		dssjobscheduler.startDSSSchedulerChecking();
	}
	
		

}


