package BI.automation.common;

import BI.automation.util.ReadPropertyValue;


public class PropertiesStore {
	private static String[] userpropertiesstore;
	private static String[] systempropertiesstore;
	private static String[] automationexecutorservercommand;
	private static String browserproperty="Not Applicable";
	private static String currentuserandmachine;
	private static String currentuser;
	private static String currentmachine;
	private static String currentscreenshotfolder;
	private static String currentdomainname;
	private static String osname;
	private static String osbuild;
	
	

	public static String getCurrentScreenshotFolder() {
		return currentscreenshotfolder;
	}
	public static void setCurrentScreenshotFolder(String currentscreenshotfolder) {
		PropertiesStore.currentscreenshotfolder = currentscreenshotfolder;
	}
	public static String getBrowserProperty() {
		return browserproperty;
	}
	public static String getCurrentuserAndMachine() {
		return currentuserandmachine;
	}
	public static void setCurrentuserAndMachine(String currentuserandmachine) {
		PropertiesStore.currentuserandmachine = currentuserandmachine;
	}
	public static void setBrowserProperty(String browserproperty) {
		PropertiesStore.browserproperty = browserproperty;
	}
	public static void  loadUserProperties(String propertypath){
		ReadPropertyValue gpv=new ReadPropertyValue(propertypath);
		userpropertiesstore=gpv.getAllPropValue();
	}
	public static void  loadSystemProperties(String propertypath){
		ReadPropertyValue gpv=new ReadPropertyValue(propertypath);
		systempropertiesstore=gpv.getAllPropValue();
	}
	
	public static void  loadAutomationExecutorServerProperties(String propertypath){
		ReadPropertyValue gpv=new ReadPropertyValue(propertypath);
		automationexecutorservercommand=gpv.getAllPropValue();
	}
	
	public static String getUserPropertyValue(String propertyName){
		String retstring=null;
		for(int i=0;i<userpropertiesstore.length;i++){
			String tmpStr=userpropertiesstore[i].toString();
			String tmpProperty[]=tmpStr.split("\\|");
			if(tmpProperty.length==2){
				String tmppropertyname=tmpProperty[0];
				String tmppropertyvalue=tmpProperty[1];
				if(tmppropertyname.equals(propertyName)){
					retstring = tmppropertyvalue;
				}	
			}
			
		}
		return retstring;
	}

	public static String getSystemPropertyValue(String propertyName){
		String retstring=null;
		for(int i=0;i<systempropertiesstore.length;i++){
			String tmpStr=systempropertiesstore[i].toString();
			String tmpProperty[]=tmpStr.split("\\|");
			String tmppropertyname=tmpProperty[0];
			String tmppropertyvalue=tmpProperty[1];
			if(tmppropertyname.equals(propertyName)){
				retstring = tmppropertyvalue;
			}
		}
		return retstring;
	}
	
	public static String getAutomationExecutorServerCommand(String propertyName){
		String retstring=null;
		for(int i=0;i<automationexecutorservercommand.length;i++){
			String tmpStr=automationexecutorservercommand[i].toString();
			String tmpProperty[]=tmpStr.split("\\|");
			String tmppropertyname=tmpProperty[0];
			String tmppropertyvalue=tmpProperty[1];
			if(tmppropertyname.equals(propertyName)){
				retstring = tmppropertyvalue;
			}
		}
		return retstring;
	}
	
	public static String getUserPropertyValueofURL(){
		String retstring=null;
		for(int i=0;i<userpropertiesstore.length;i++){
			String tmpStr=userpropertiesstore[i].toString();
			String tmpProperty[]=tmpStr.split("\\|");
			String tmppropertyname=tmpProperty[0];
			String tmppropertyvalue=tmpProperty[1];
			if(tmppropertyname.equalsIgnoreCase("url")){
				retstring = tmppropertyvalue;
			}
		}
		return retstring;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public static String getCurrentuser() {
		return currentuser;
	}
	public static void setCurrentuser(String currentuser) {
		PropertiesStore.currentuser = currentuser;
	}
	public static String getCurrentmachine() {
		return currentmachine;
	}
	public static void setCurrentmachine(String currentmachine) {
		PropertiesStore.currentmachine = currentmachine;
	}
	public static String getCurrentdomainname() {
		return currentdomainname;
	}
	public static void setCurrentdomainname(String currentdomainname) {
		PropertiesStore.currentdomainname = currentdomainname;
	}
	public static String getOsname() {
		return osname;
	}
	public static void setOsname(String osname) {
		PropertiesStore.osname = osname;
	}
	public static String getOsbuild() {
		return osbuild;
	}
	public static void setOsbuild(String osbuild) {
		//some special treatment required for Windows build
		int posn=osbuild.indexOf("N/A");
		if(posn>0){
		osbuild = osbuild.substring(0, posn-1);	
		}
		PropertiesStore.osbuild = osbuild;
	}

}
