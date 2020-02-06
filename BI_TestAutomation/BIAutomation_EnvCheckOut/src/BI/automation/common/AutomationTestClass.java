package BI.automation.common;


import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;

import BI.automation.selenium.common.WebDriverStore;
import BI.automation.util.FileInfo;
import BI.automation.util.WindowsActions;

public class AutomationTestClass {


	protected WebDriver webdriver;

	HWND hwnd =null;
	String msgfromwebpage=null;
	HWND HWNDCertificateErrorFromIE =null;
	String certificateerrormessagefromIE="Certificate Error: Navigation Blocked - Internet Explorer";
	String handlecertificateerrormessagefromIE=null;

	@Parameters({ "userconfigfilename" })
	@BeforeClass
	public void testBaseSetup(String userconfigfilename) {
		initializeTestBaseSetup(userconfigfilename);
	}

	@AfterClass
	public void tearDown() {
		webdriver.quit();
		try {
			BILogger.writeInfoToLog(this.getClass(), "tearDown-Sleep of 5 seconds");
					Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			BILogger.writeInfoToLog(this.getClass(), "tearDown-Sleep of 5 seconds"+e);
		}
		
		String browserType=PropertiesStore.getUserPropertyValue("browsertype");
		String driverpath=System.getProperty("user.dir")+"\\"+"..\\"+"drivers"+"\\"+PropertiesStore.getSystemPropertyValue("selenium_browserdriver_path");
		driverpath=driverpath.replace("\\", "/");
		String drivername= FilenameUtils.getName(new File(driverpath).getName());

		 
		switch (browserType.toUpperCase()){
		case "IE":
			//Cleanup any existing IEDriver runs for the user
			WindowsActions.killTask(System.getProperty("user.name"), drivername);
			WindowsActions.killTask(System.getProperty("user.name"), "iexplore.exe");
			break;
		case "EDGE":
			//Cleanup any existing IEDriver runs for the user
			WindowsActions.killTask(System.getProperty("user.name"), drivername);
			WindowsActions.killTask(System.getProperty("user.name"), "MicrosoftEdge.exe");
			WindowsActions.killTask(System.getProperty("user.name"), "MicrosoftEdgeCP.exe");
			break;			
		case "FIREFOX":
			
			break;
		case "CHROME":
			
			break;	
		default:
			throw new IllegalArgumentException("Invalid browser type it would be IE/FIREFOX/CHROME/EDGE " + browserType);
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void initializeTestBaseSetup(String userconfigfilename){
		try{
			BILogger.writeInfoToLog(this.getClass(), "initializeUserAndMachineDetails >>"+"Started..");
			initializeUserAndMachineDetails();
			BILogger.writeInfoToLog(this.getClass(), "initializeUserAndMachineDetails >>"+"Completed..");
		
			BILogger.writeInfoToLog(this.getClass(), "initializeUserConfig >>"+"Started..");
			initializeUserConfig(userconfigfilename);
			BILogger.writeInfoToLog(this.getClass(), "initializeUserConfig >>"+"Completed..");
		
			BILogger.writeInfoToLog(this.getClass(), "initializeSystemConfig >>"+"Started..");
			initializeSystemConfig();
			BILogger.writeInfoToLog(this.getClass(), "initializeSystemConfig >>"+"Completed");

		
			BILogger.writeInfoToLog(this.getClass(), "startSnifingForMessgeFromWebPage >>"+"Started..");
			startSnifingForMessgeFromWebPage();
			BILogger.writeInfoToLog(this.getClass(), "startSnifingForMessgeFromWebPage >>"+"Completed");
		
			BILogger.writeInfoToLog(this.getClass(), "startSnifingCertificateErrorMessageFromIE >>"+"Started..");
			startSnifingCertificateErrorMessageFromIE();
			BILogger.writeInfoToLog(this.getClass(), "startSnifingCertificateErrorMessageFromIE >>"+"Completed");
			
			startBrowser();
		}catch (Exception e){
			BILogger.writeErrorToLog(this.getClass(), e);
		}
		
	}
	
	private void initializeUserAndMachineDetails() throws Exception{
		BILogger.writeInfoToLog(this.getClass(),"Wait... system information reading");
		LoadMachineDetails lmd=new LoadMachineDetails(System.getProperty("user.dir")+"\\"+"..\\"+"system"+"\\"+"GETMACHINEINFO.bat");
		lmd.load();
		String currentmachine=lmd.getDetails("Host Name",":");
		String currentdomain=lmd.getDetails("Domain",":");
		String osname=lmd.getDetails("OS Name",":");
		String osbuild=lmd.getDetails("OS Version",":");
		PropertiesStore.setCurrentuser(System.getProperty("user.name"));
		//PropertiesStore.setCurrentmachine(InetAddress.getByName(InetAddress.getLocalHost().getHostName()).getCanonicalHostName());
		//PropertiesStore.setCurrentdomainname(PropertiesStore.getCurrentmachine().substring(PropertiesStore.getCurrentmachine().indexOf(".")+1));
		PropertiesStore.setCurrentmachine(currentmachine+"."+currentdomain);
		PropertiesStore.setCurrentdomainname(currentdomain);
		PropertiesStore.setOsname(osname);
		PropertiesStore.setOsbuild(osbuild);
		BILogger.writeInfoToLog(this.getClass(),"Completed... system information reading");
		//PropertiesStore.setCurrentuserAndMachine("<b>"+"Current System User: "+"</b>" +System.getProperty("user.name")+"<b>"+" Current System Host: "+"</b>"+InetAddress.getByName(InetAddress.getLocalHost().getHostName()).getCanonicalHostName());

	}

	private void initializeUserConfig(String userconfigfilename) throws Exception {
		BILogger.writeInfoToLog(this.getClass(),"Current User folder>>"+System.getProperty("user.dir"));
		BILogger.writeInfoToLog(this.getClass(),"Current User folder>>"+userconfigfilename);
		PropertiesStore.loadUserProperties(System.getProperty("user.dir")+"//"+userconfigfilename);
	}

	private void initializeSystemConfig() throws Exception{
		//Determine the browser type requested and load systems accordingly
		String systempropertylocation=System.getProperty("user.dir")+"\\"+"..\\"+"system"+"\\";
		BILogger.writeInfoToLog(this.getClass(), "System property location should be under>>"+systempropertylocation);
		String browserType=PropertiesStore.getUserPropertyValue("browsertype");
		switch (browserType.toUpperCase()){
		case "IE":
			systempropertylocation=systempropertylocation+"systemconfig_IE.properties";
			PropertiesStore.loadSystemProperties(systempropertylocation);
			break;
		case "EDGE":
			systempropertylocation=systempropertylocation+"systemconfig_EDGE.properties";
			PropertiesStore.loadSystemProperties(systempropertylocation);
			break;
		case "FIREFOX":
			systempropertylocation=systempropertylocation+"systemconfig_FIREFOX.properties";
			PropertiesStore.loadSystemProperties(systempropertylocation);
			break;
		case "CHROME":
			systempropertylocation=systempropertylocation+"systemconfig_CHROME.properties";
			PropertiesStore.loadSystemProperties(systempropertylocation);
			break;	
		default:
			throw new IllegalArgumentException("Invalid browser type it would be IE/FIREFOX/CHROME/EDGE " + browserType);
		}
		BILogger.writeInfoToLog(this.getClass(), "Actual system property location>>"+systempropertylocation);
	}



	/*public static void main(String[] args){
		BIAutomationTestClass bac=new BIAutomationTestClass();
		System.out.println(PropertiesStore.getUserPropertyValue("url"));
	}*/

	public void quitBrowser(WebDriver driver)
	{
		driver.close();
		driver.quit();

	}
	private void startSnifingForMessgeFromWebPage() throws Exception{
		msgfromwebpage=System.getProperty("user.dir")+"\\"+"..\\"+"drivers"+"\\"+"autoit"+"\\"+PropertiesStore.getSystemPropertyValue("msgfromwebpageexe");
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				while(true){
					try{
						hwnd = User32.INSTANCE.FindWindow(null, "Message from webpage"); // window title
						if (hwnd == null) {

						}else{
							System.out.println("Message from WebPage came");
							Runtime.getRuntime().exec(msgfromwebpage);
						}
						Thread.sleep(500);
					}catch(Exception e){
						System.out.println(e.getMessage());
					}
				}
			}
		});  
		t1.start();
	}

	private void startSnifingCertificateErrorMessageFromIE() throws Exception{
		handlecertificateerrormessagefromIE=System.getProperty("user.dir")+"\\"+"..\\"+"drivers"+"\\"+"autoit"+"\\"+PropertiesStore.getSystemPropertyValue("handleiecertificateerror");
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				while(true){
					try{
						HWNDCertificateErrorFromIE = User32.INSTANCE.FindWindow(null, certificateerrormessagefromIE); // window title

						if (HWNDCertificateErrorFromIE == null) {

						}else{
							System.out.println("Message from IE came"+"Executing>"+handlecertificateerrormessagefromIE);
							Runtime.getRuntime().exec(handlecertificateerrormessagefromIE);
						}
						Thread.sleep(4000);
					}catch(Exception e){
						System.out.println(e.getMessage());
					}
				}
			}
		});  
		t1.start();
	}

	private void startBrowser(){
		String browsertype=PropertiesStore.getUserPropertyValue("browsertype");
		String IEDriverPath=System.getProperty("user.dir")+"\\"+"..\\"+"drivers"+"\\"+PropertiesStore.getSystemPropertyValue("selenium_browserdriver_path");
		IEDriverPath=IEDriverPath.replace("\\", "/");
		String url = PropertiesStore.getUserPropertyValue("url");

		switch (browsertype.toUpperCase()){
		case "IE":		
			BI.automation.selenium.ie.common.OpenBrowser openIEbrowser= new BI.automation.selenium.ie.common.OpenBrowser(browsertype,IEDriverPath);
			openIEbrowser.openURL(url);
			webdriver=openIEbrowser.getWebDriver();
			WebDriverStore.putWebDriverInStore(PropertiesStore.getCurrentScreenshotFolder(), webdriver);
			break;
		case "EDGE":
			//For Edge before process make sure the version of driver matched with version of OS
			if(!PropertiesStore.getOsbuild().contains(Integer.toString(FileInfo.getBuildOfProgram(IEDriverPath)))){
				BILogger.writeInfoToLog(this.getClass(),"Version of Driver is not matching with version of OS");
				BILogger.writeInfoToLog(this.getClass(),"Not able to proceed further...Quiting....");
				System.exit(0);
			}
			BI.automation.selenium.edge.common.OpenBrowser openEDGEbrowser= new BI.automation.selenium.edge.common.OpenBrowser(browsertype,IEDriverPath);
			openEDGEbrowser.openURL(url);
			webdriver=openEDGEbrowser.getWebDriver();
			WebDriverStore.putWebDriverInStore(PropertiesStore.getCurrentScreenshotFolder(), webdriver);
			break;
		}

	}

	public WebDriver getWebDriver(){
		return webdriver;
	}



}


