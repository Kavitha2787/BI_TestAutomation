package BI.automation.common;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import BI.automation.util.DateTimeUtility;

public class CommonPageClass {

	private Exception e=null;
	private String method="";
	private int filenameSequencer=1;
	private String format=".jpeg";

	public File createFile(String filename){
		File f = null;
		try {
			f= new File(PropertiesStore.getCurrentScreenshotFolder()+"\\"+filename+"_"+String.valueOf(filenameSequencer)+format);
			if (!f.exists()){
				//System.out.println("Going to Create File1>>"+f.getAbsolutePath());
				f.createNewFile();
			}else{
				while (true){
					f= new File(PropertiesStore.getCurrentScreenshotFolder()+"\\"+filename+"_"+String.valueOf(filenameSequencer+1)+format);
					filenameSequencer++;
					if (!f.exists()){
						//System.out.println("Going to Create File2>>"+f.getAbsolutePath());
						f.createNewFile();
						break;
					}
				}
			}




		} catch (IOException e) {
			e.printStackTrace();

		}
		return f;
	}
	public void TakeScreenshot(WebDriver driver){
		if(PropertiesStore.getUserPropertyValue("TakeScreenshot").equalsIgnoreCase("Y")){
			e = new Exception();
			StackTraceElement[] ste= e.getStackTrace();

			if (ste.length>=3){
				method=ste[1].getMethodName().toString();
				method=method.substring(method.lastIndexOf(".")+1,method.length());
				method=method+"_"+DateTimeUtility.getCurrentTimeStamp("yyyyMMddHHmmss");
			}
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			// Now you can do whatever you need to do with it, for example copy somewhere
			try {
				FileUtils.copyFile(scrFile,this.createFile(method));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Dont Take screenshot");
		}
	}
	public void TakeScreenshot(WebDriver driver,String filename){

		if(PropertiesStore.getUserPropertyValue("TakeScreenshot").equalsIgnoreCase("Y")){
			filename=filename+"_"+DateTimeUtility.getCurrentTimeStamp("yyyyMMddHHmmss");
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			// Now you can do whatever you need to do with it, for example copy somewhere
			try {
				FileUtils.copyFile(scrFile,this.createFile(filename));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Dont Take screenshot");
		}
	}



	public int getWaitTime(){
		return (Integer.parseInt(PropertiesStore.getSystemPropertyValue("CommonClass_WaitTime")));
	}

	public void sendTestStatusAll(){

	}
	

}
