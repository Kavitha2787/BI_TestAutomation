package BI.automation.selenium.ie.common;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import BI.automation.common.BILogger;
import BI.automation.common.PropertiesStore;

public class OpenBrowser{

	WebDriver driver;
	WebDriverWait wait;
	int waittime =Integer.parseInt(PropertiesStore.getSystemPropertyValue("BrowserLaunch_WaiTime"));
	

	public OpenBrowser(String browserType,String driverPath){
		
		switch (browserType.toUpperCase()){
		case "IE":
			boolean IEEnableProtectedMode=Boolean.parseBoolean(PropertiesStore.getSystemPropertyValue("IEEnableProtectedMode"));
			BILogger.writeInfoToLog(this.getClass(),"Passed Browser Type >> "+browserType);
			BILogger.writeInfoToLog(this.getClass(),"Passed Browser Path >> "+driverPath);
			//Cleanup any existing IEDriver runs for the user
			//WindowsActions.killTask(System.getProperty("user.name"), "iexplore.exe");
			//WindowsActions.killTask(System.getProperty("user.name"), "IEDriverServer.exe");
			
			
			//Set path to driver executable
			System.setProperty("webdriver.ie.driver",driverPath);
			BILogger.writeInfoToLog(this.getClass(),"Passed System.setProperty");
			
			//Open the browser
			driver = new InternetExplorerDriver(getAndConfigureIECaps());
			BILogger.writeInfoToLog(this.getClass(),"Passed Initiate Driver");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			BILogger.writeInfoToLog(this.getClass(),"Passed Initiate Wait");
			

			//Set zoom level to 100%
			driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, "0"));
			driver.manage().window().maximize();
			BILogger.writeInfoToLog(this.getClass().getCanonicalName(),"Browsertype"+"|"+browserType);
			BILogger.writeInfoToLog(this.getClass().getCanonicalName(),"Browserdriver"+"|"+driverPath);
			

			break;
		case "FIREFOX":
			break;
		default:
			throw new IllegalArgumentException("Invalid browser type it would be IE/FIREFOX/CHROME" + browserType);
		}
		wait=new WebDriverWait(driver,getWaitTime());
		setBrowserdetails();
	}
	private void setBrowserdetails(){
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = cap.getBrowserName().toLowerCase();
		String v = cap.getVersion().toString();
		PropertiesStore.setBrowserProperty(browserName+" "+ v);
	}

	public int getWaitTime(){
		return waittime;
	}
	public WebDriver getWebDriver(){
		return driver;
	}

	public void openURL(String url){
		//Navigate to the application URL
		driver.get(url);
		wait=new WebDriverWait(driver,waittime);
	}

	public void clickToAvoidCertificateError(){
		//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("overridelink")));
		driver.navigate().to("javascript:document.getElementById('overridelink').click()"); //Java script to click the link
		System.out.println("Certificate error overcome Success");
	}

	public static void forceCloseIEDriver(){
		try{
			Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
			Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
		}catch(IOException ie){
			System.out.println(ie.getMessage());
		}
	}
	
	private DesiredCapabilities getAndConfigureIECaps(){
		DesiredCapabilities ieCaps=null;
		ieCaps=DesiredCapabilities.internetExplorer();
		ieCaps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		ieCaps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		//ieCaps.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
		//ieCaps.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "about:blank");
		
		ieCaps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		
		//ieCaps.setCapability(CapabilityType.ACCEPT_SSL_CERTS , true);

		return ieCaps;
	}


	/*public static void main(String [] args)
	{
		OpenBrowser openbrowser= new OpenBrowser(new ReadPropertyValue("C:/JavaPrograms/DSSWebPublisher1/properties/config.properties").getPropValue("browsertype"),
				new ReadPropertyValue("C:/JavaPrograms/DSSWebPublisher1/properties/config.properties").getPropValue("selenium_IE_browserdriver_path"));
		openbrowser.openURL(new ReadPropertyValue("C:/JavaPrograms/DSSWebPublisher1/properties/config.properties").getPropValue("url"));

	}*/
}
