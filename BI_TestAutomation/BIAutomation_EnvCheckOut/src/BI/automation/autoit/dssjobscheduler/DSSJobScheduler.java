package BI.automation.autoit.dssjobscheduler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import BI.automation.common.AutomationTestClass;
import BI.automation.common.PropertiesStore;
import BI.automation.common.StatusStore;


@SuppressWarnings("unused")
public class DSSJobScheduler extends AutomationTestClass{

	private String dreexepath;
	private String dssdbname;
	private String dssjobscheduleruserid;
	private String dssjobschedulerpassword;
	
	private int nooftimes=Integer.parseInt(PropertiesStore.getSystemPropertyValue("nooftimestocheckforcompletion"));
	private long waitime=Integer.parseInt(PropertiesStore.getSystemPropertyValue("waitinseconds"))*1000;
	private File file1=new File(PropertiesStore.getCurrentScreenshotFolder()+"\\"+PropertiesStore.getSystemPropertyValue("dssstatuscheckerfile")+".F");
	private File file2=new File(PropertiesStore.getCurrentScreenshotFolder()+"\\"+PropertiesStore.getSystemPropertyValue("dssstatuscheckerfile")+".E");
	private String dssjobschedulercheckerexe=System.getProperty("user.dir")+"\\"+"drivers"+"\\"+"autoit"+"\\"+PropertiesStore.getSystemPropertyValue("dssjobschedulercheckerexe");
	private String logfile=PropertiesStore.getCurrentScreenshotFolder()+"\\"+PropertiesStore.getSystemPropertyValue("autoitlogfile");
	private boolean completionstatus=false;
	
	public DSSJobScheduler(String pathofdreexe,
			String dssdbname,String dssjobscheduleruserid, String dssjobschedulerpassword){
		
		this.dreexepath=pathofdreexe;
		this.dssdbname=dssdbname;
		this.dssjobscheduleruserid=dssjobscheduleruserid;
		this.dssjobschedulerpassword=dssjobschedulerpassword;
		
	}
	
	public void startDSSSchedulerChecking(){
		StatusStore.updateStatus("startDSSSchedulerChecking");
		System.out.println(file1.getAbsolutePath());
		System.out.println(file2.getAbsolutePath());
		
		
		String commandstring=dssjobschedulercheckerexe+" -dreexe "+"\""+dreexepath+"\""+" -logfile "+"\""+logfile+"\""+" -dssdbname "+"\""+this.dssdbname+"\""+" -userid "+"\""+this.dssjobscheduleruserid+"\""+" -password "+"\""+this.dssjobschedulerpassword+"\"";
		System.out.println(commandstring);
		
		
		try {
			Runtime runtime = Runtime.getRuntime();
			runtime.exec(commandstring);
			for(int i=0;i<=nooftimes;i++){
				if(file1.exists()){
					completionstatus=true;
					reportStatus(file1);
					break;
				}
				if(file2.exists()){
					completionstatus=true;
					reportStatus(file2);
					break;
				}
				Thread.sleep(waitime);
			}
			if(!completionstatus){
				System.out.println("Checkout did not happens");
				StatusStore.updateStatus("startDSSSchedulerChecking", "FAILED", "", "", parseLogLine(readLastLineFromFile(this.logfile)),"");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void reportStatus(File f){
		if (completionstatus){
			System.out.println("Checkout happens");
			if(getFileExtension(f).equalsIgnoreCase("F")){
				System.out.println("Finished Successfully");
				StatusStore.updateStatus("startDSSSchedulerChecking", "PASSED", "AS EXPECTED", "", "","");
			}
			if(getFileExtension(f).equalsIgnoreCase("E")){
				System.out.println("Finished UnSuccessfully");
				StatusStore.updateStatus("startDSSSchedulerChecking", "FAILED", "",parseLogLine(readLastLineFromFile(this.logfile)) ,"", "");
			}
		}
	}
	
	private String parseLogLine(String logline){
		String retString="";
		String strAry[]=logline.split("\\|");
		retString=strAry[strAry.length-2]+">>"+strAry[strAry.length-1];
		
		return retString;
	}
	
	private String getFileExtension(File file) {
	    String name = file.getName();
	    try {
	        return name.substring(name.lastIndexOf(".") + 1);
	    } catch (Exception e) {
	        return "";
	    }
	}
	
	private String readLastLineFromFile(String filepath ){
		String retString="";
		try{
			BufferedReader input = new BufferedReader(new FileReader(filepath));
		    String line;

		    while ((line = input.readLine()) != null) {
		    	retString = line;
		    }
		    input.close();

		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return retString;
	}

}

