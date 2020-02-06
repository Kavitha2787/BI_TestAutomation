package BI.automation.selenium.edge.common;


/**
 * @author "Pradip Patra"
 * @date "Mar 07, 2014"
 * @version "1.0"
 * This class is for managing some common tasks associated
 * with multiple pages. Methods will be used by several "PageObject"
 * classes.
 *
 */
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class CommonTask {
	//static Logger logger=LogManager.getLogger(CommonTask.class);
	static String IEDriverPath;
	static boolean protectedModeSetting;
		

	
	/**
	 * @purpose 
	 * To switch to well area frame (wellFrame)
	 */
	public static void switchToWellFrame(WebDriver driver){
		driver.switchTo().defaultContent();
		WebDriverWait wait=new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("sframeInner")));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("wellFrame")));
	}
	

	 
	/**
	 * @param igoneProtectedMode -- "true" or "false"
	 * @param pathToDriver -- absolute path of the driver executable
	 * @purpose 
	 * To initialize the driver and open a browser.
	 */
	 public static WebDriver launchBrowser(String pathToDriver, boolean igoneProtectedMode){
		 IEDriverPath=pathToDriver;
		 protectedModeSetting=igoneProtectedMode;
		 File file = new File(pathToDriver);
	     System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
	     WebDriver driver=null;
	     try{
	    	 if(igoneProtectedMode){
			     DesiredCapabilities ieCaps=DesiredCapabilities.internetExplorer();
			     ieCaps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			     ieCaps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			     ieCaps.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
	   	      	 ieCaps.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "about:blank");
	   	      	 ieCaps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			     driver = new InternetExplorerDriver(ieCaps);
			     System.out.println("Launched IE ignoring protected mode settings");
			     }
			 else{			 
				 DesiredCapabilities ieCaps=DesiredCapabilities.internetExplorer();
	   		  	 ieCaps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
	   		  	 ieCaps.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
	   		  	 ieCaps.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "about:blank");
	   		  	 ieCaps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
	   		  	 driver = new InternetExplorerDriver(ieCaps);
	   		  System.out.println("Launched IE without ignoring protected mode settings");			  
			 }
	    	//Now set zoom level to 100%
		    driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, "0"));
	     }catch(Exception e){
	    	 System.out.println("Error launching Internet Explorer");
	    	 System.out.println(e);	    	 	    	 
	     }	     
		 return driver;
	 }
	 
	  /**
	   * @purpose 
	   * To navigate to the application URL
	   */
	 public static void navigateToURL(WebDriver driver, String appURL){
		 try{
			 driver.get(appURL);
			 System.out.println("Navigated successfully to the URL");
		 }catch(WebDriverException e){
			 System.out.println("Exception at get(appURL):"+e.getMessage());
			 System.out.println("Retrying navigating to URL again");
	    	 driver.quit();
	    	 System.out.println("Closed browser after unsuccessful attept to navigate to URL");
	    	 try{
	    		 System.out.println("Waiting for 2 sec before relaunching browser");
	    		 Thread.sleep(2000);
	    	  }catch(InterruptedException ie){
	    		 ie.printStackTrace();
	    	  }
	    	  driver=launchBrowser(IEDriverPath,protectedModeSetting);
	    	  driver.get(appURL);
		 }
		 		 
	 }
	 
	 //Scroll to an element to bring in view using Javascript
	 public static void scrollIntoView(WebDriver driver, WebElement element){
		 JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);",element);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.out.println("Interrupted");
			}		 
	 }
	 
	 
	 
	 public static void forceCloseIEDriver(){
		 try{
			 Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
			 Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
		 }catch(IOException ie){
			 System.out.println(ie.getMessage());
		 }
	 }
	 
	 
	 	 

}

