package BI.automation.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import BI.automation.common.BILogger;

public class WindowsActions {

	public static void getTaskListPids(String user,String programname){
		
		
	}

	public static void killTask(String userid,String programtokill) {
		String finaltaskkillcommand="taskkill /F /FI "+"\"USERNAME eq "+userid+"\""+" /IM "+programtokill;
		BILogger.writeInfoToLog("WindowsActions",finaltaskkillcommand);
		try{
			Runtime.getRuntime().exec(finaltaskkillcommand);
			BILogger.writeInfoToLog("WindowsActions","Windows Action Taskkill Success...");
			Thread.sleep(3000);
			
		}catch(IOException | InterruptedException ie){
			BILogger.writeInfoToLog("WindowsActions",ie);
		}
	}
	
	public static List<String> listRunningProcesses() {
	    List<String> processes = new ArrayList<String>();
	    try {
	      String line;
	      String userid="DB040488";
	      String programname="chra*";
	      String finaltasklistcommand="tasklist /FI "+"\"USERNAME eq "+userid+"\""+" /M "+programname+" /FO csv /NH";
	      System.out.println(finaltasklistcommand);
	      Process p = Runtime.getRuntime().exec(finaltasklistcommand);
	      //tasklist /FI "USERNAME eq DB040488" /M chre* /FO csv /NH
	      BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
	      while ((line = input.readLine()) != null) {
	    	  System.out.println(line);
	          if (!line.trim().equals("")) {
	              // keep only the process name
	              line = line.substring(1);
	              //processes.add(line.substring(0, line.indexOf(""")));
	          }

	      }
	      input.close();
	    }
	    catch (Exception err) {
	      err.printStackTrace();
	    }
	    return processes;
	  }
	
	public static void main(String args[]){
		WindowsActions.listRunningProcesses();
	}
}
