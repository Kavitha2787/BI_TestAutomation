package BI.automation.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import BI.automation.common.PropertiesStore;
import BI.automation.util.WriteToFile;

public class AutomationCommandExecutor {
	
	static String logfilename=System.getProperty("user.dir")+"//"+"logs//"+PropertiesStore.getSystemPropertyValue("AutomationExecutorServerLog");
	
	
	public static void executeCommand(String command){
	      Process theProcess = null;
	      BufferedReader inStream = null;
	      
	      try{
	          WriteToFile.writeAsLog(logfilename,"AutomationCommandExecutor-"+command);
	    	  theProcess = Runtime.getRuntime().exec(command);
	    	  
	      }
	      catch(IOException e){
	    	  WriteToFile.writeAsLog(logfilename,"AutomationCommandExecutor-"+"Error on exec() method");
	    	  WriteToFile.writeAsLog(logfilename,"AutomationCommandExecutor-"+e.getMessage()); 
	      }
	        
	      // read from the called program's standard output stream
	      try{
	         inStream = new BufferedReader(
	                                new InputStreamReader( theProcess.getInputStream() ));  
	         
	         WriteToFile.writeAsLog(logfilename,"AutomationCommandExecutor-System.out.println from executed command-"+inStream.readLine()); 
	      }
	      catch(IOException e){
	         WriteToFile.writeAsLog(logfilename,"AutomationCommandExecutor-Error System.out.println from executed command-"+e.getMessage());  
	      }
	   } 
}
