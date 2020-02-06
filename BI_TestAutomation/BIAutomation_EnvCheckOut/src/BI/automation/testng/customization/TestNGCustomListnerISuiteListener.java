package BI.automation.testng.customization;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.testng.ISuite;
import org.testng.ISuiteListener;

import BI.automation.common.BILogger;
import BI.automation.common.PropertiesStore;
import BI.automation.util.DateTimeUtility;

public class TestNGCustomListnerISuiteListener implements ISuiteListener{

	@Override
	public void onStart(ISuite isuite) {
		//System.out.println("Test Suit Start"+isuite.getName());
		setupCustomTestOutputFolder(isuite.getXmlSuite().getFileName());
		BILogger.writeInfoToLog(this.getClass(), "---- TEST SUITE STARTED----");
		BILogger.writeInfoToLog(this.getClass(), "Suit>>"+isuite.getName()+">>"+"Current ouputfolder>>"+PropertiesStore.getCurrentScreenshotFolder());
	}

	@Override
	public void onFinish(ISuite isuite) {
		//System.out.println("Test Suit Finish"+isuite.getName());
		BILogger.writeInfoToLog(this.getClass(), "Suit>>"+isuite.getName()+">>"+"Current ouputfolder>>"+PropertiesStore.getCurrentScreenshotFolder());
		BILogger.writeInfoToLog(this.getClass(), "---- TEST SUITE FINISHED----");
	}



	private void setupCustomTestOutputFolder(String foldername){
		foldername = FilenameUtils.getBaseName(new File(foldername).getName());
		foldername = System.getProperty("user.dir")+"\\"+"outputfolder"+"\\"+foldername + "_"+DateTimeUtility.getCurrentTimeStamp("yyyyMMddHHmmsssss");

		File folder = new File (foldername);
		if(!folder.exists()){
			folder.mkdirs();
		}
		PropertiesStore.setCurrentScreenshotFolder(folder.getAbsolutePath());	
	}
}
