package BI.automation.common;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import BI.automation.util.ReadStream;


public class LoadMachineDetails {
	ReadStream s1;
	ReadStream s2;
	Process p;
	String loadcommand;


	public LoadMachineDetails(String command){
		loadcommand=command;
	}

	public void load() throws Exception {

		//try {

			p = Runtime.getRuntime().exec(loadcommand) ;  
			s1 = new ReadStream("stdin", p.getInputStream ());
			s2 = new ReadStream("stderr", p.getErrorStream ());
			s1.start ();
			s2.start ();
			p.waitFor();    

		//} catch (Exception e) {  
			//e.printStackTrace();  
		//} finally {
			if(p != null)
				p.destroy();
		//}
	}

	public String getDetails(String lookup,String delimeter){
		String retval = "";
		try{
			String line;
			String strAry[];
			BufferedReader br = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(loadcommand+".txt"))));

			while ((line = br.readLine()) != null) {
				strAry=line.split(delimeter);
				if(strAry.length==2){
					if(strAry[0].trim().equalsIgnoreCase(lookup)){
						retval=strAry[1].trim();
					}
				}
			}
			br.close();
		}catch(Exception e){
			System.out.println(e);
		}

		return retval;
	}
	public String getDetails(String lookup){
		String retval = "";
		try{
			String line;
			String strAry[];
			BufferedReader br = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(loadcommand+".txt"))));

			while ((line = br.readLine()) != null) {
				strAry=line.split(":");
				if(strAry.length==2){
					if(strAry[0].trim().equalsIgnoreCase(lookup)){
						retval=strAry[1].trim();
					}
				}
			}
			br.close();
		}catch(Exception e){
			System.out.println(e);
		}

		return retval;
	}
	/*
	public static void main(String args[]){
		//LoadMachineDetails lmd= new LoadMachineDetails("C:\\WorkDrive\\BI_Work\\BIAutomation_Latestfiles\\system\\GETMACHINEINFO.bat");
		//lmd.load();
		
		
		//System.out.println(lmd.getDetails("OS Name",":"));
		//System.out.println(lmd.getDetails("OS Version",":"));
		
		
	}*/
}
