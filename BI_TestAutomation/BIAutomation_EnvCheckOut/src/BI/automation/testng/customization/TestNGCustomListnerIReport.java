package BI.automation.testng.customization;

import java.io.File;
import java.util.List;

import org.testng.IReporter;
import org.testng.xml.XmlSuite;
import org.testng.ISuite;

import BI.automation.common.BILogger;
import BI.automation.common.PropertiesStore;
import BI.automation.common.ReportBuilder;
import BI.automation.common.StatusStore;
import BI.automation.util.EmailSender;
import BI.automation.util.ZipUtils;

public class TestNGCustomListnerIReport implements IReporter{

	String customtestreport="";
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outdir) {
		
		BILogger.writeInfoToLog(this.getClass(), "---- TEST REPORTING STARTED----");
		customtestreport=ReportBuilder.getReportContentInHtml();
		sendAllTestStatusInEmail();
		BILogger.writeInfoToLog(this.getClass(), "---- TEST REPORTING FINISHED----");
	}
	public void sendAllTestStatusInEmail(){
		if(PropertiesStore.getUserPropertyValue("SendReportInEmail").equalsIgnoreCase("Y") ||
				PropertiesStore.getUserPropertyValue("SendReportInEmail").equalsIgnoreCase("Yes")){
			if(PropertiesStore.getUserPropertyValue("EmailScreenshotAttachment").equalsIgnoreCase("Y") ||
					PropertiesStore.getUserPropertyValue("EmailScreenshotAttachment").equalsIgnoreCase("Yes")){
				String foldername=PropertiesStore.getCurrentScreenshotFolder();
				//Zip the screenshot folder
				try {
					File f = new File(foldername);
					if(f.isDirectory()){
						//Zip the Folder
						ZipUtils appZip = new ZipUtils(foldername,foldername+"\\"+".zip");
						appZip.generateFileList(new File(foldername));
						appZip.zipIt(foldername+"\\"+"Screenshots.zip");
						new File(foldername+"\\"+"Screenshots.zip");
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}			//EOF Zip the screenshot folder

				EmailSender.sendMail(
						PropertiesStore.getSystemPropertyValue("emailfrom"),
						PropertiesStore.getUserPropertyValue("EmailTo"), 
						PropertiesStore.getUserPropertyValue("EmailCc"),
						PropertiesStore.getUserPropertyValue("EmailSubject")+"-"+" PASSED:"+StatusStore.getCountOfStatusResult("PASSED")+" "+" FAILED:"+StatusStore.getCountOfStatusResult("FAILED"),
						customtestreport,
						foldername+"\\"+"Screenshots.zip");
			}else{
				EmailSender.sendMail(
						PropertiesStore.getSystemPropertyValue("emailfrom"),
						PropertiesStore.getUserPropertyValue("EmailTo"), 
						PropertiesStore.getUserPropertyValue("EmailCc"),
						PropertiesStore.getUserPropertyValue("EmailSubject")+"-"+" PASSED:"+StatusStore.getCountOfStatusResult("PASSED")+" "+" FAILED:"+StatusStore.getCountOfStatusResult("FAILED"),
						customtestreport,
						"");
			}
		}
	}

}
