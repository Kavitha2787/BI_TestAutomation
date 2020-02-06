
package BI.automation.selenium.edge.common;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;







import BI.automation.common.CommonPageClass;
import BI.automation.common.PropertiesStore;
import BI.automation.common.StatusStore;
import BI.automation.selenium.edge.common.OpenBrowser;

public class HomePage extends CommonPageClass{
	WebDriver driver;
	WebDriverWait wait;
	int waittime =Integer.parseInt(PropertiesStore.getSystemPropertyValue("HomePage_WaitTime"));
	String homepagewindowhandle="";
	File tempreportlogfile=null;

	public HomePage(){
		//Can do someting here
	}

	public void openHomePage(String browsertype,String browserdriver,String url)  
	{
		//Open the Browser
		StatusStore.updateStatus("openHomePage");
		try{
			//System.out.println("Openbrowser1");
			OpenBrowser openbrowser= new OpenBrowser(browsertype,browserdriver);
			//System.out.println("Openbrowser2");


			//Open the url
			openbrowser.openURL(url);
			driver=openbrowser.getWebDriver();
			homepagewindowhandle=driver.getWindowHandle();
			StatusStore.updateStatus("openHomePage");
			wait=new WebDriverWait(driver,waittime);
			StatusStore.updateStatus("openHomePage", "PASSED", "AS EXPECTED", "", "","");
			this.TakeScreenshot(driver);

			//ThreadedWindowChecker twc=new ThreadedWindowChecker(driver);
			//twc.start();


		}catch(Exception e){
			StatusStore.updateStatus("openHomePage", "FAILED", "", e.getMessage(), "","");
			this.TakeScreenshot(driver);
		}
	}

	private void clickToAvoidCertificateError(){
		if (driver.getTitle().contains("Certificate Error")){
			//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("overridelink")));
			//driver.findElement(By.partialLinkText("Continue to this website (not recommended).")).click();
			driver.navigate().to("javascript:document.getElementById('overridelink').click()"); //Java script to click the link
			//driver.findElement(By.id("overridelink")).click();
		}
	}

	public int getWaitTime(){
		return waittime;
	}
	public WebDriver getDriver(){
		return driver;
	}

	public void logIn(String userid,String password){
		StatusStore.updateStatus("login");
		try{
			//Wait untill the username and password field and login button is visible
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("ctlusernameText__")));
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("ctlpasswordText__")));
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("ctllogonBtnButton__")));

			//Click and type the Username
			WebElement userNameInputField=driver.findElement(By.id("ctlusernameText__"));
			CommonActions.click(driver, userNameInputField);
			userNameInputField.sendKeys(userid);

			//Click and type the password
			WebElement passwordInputField=driver.findElement(By.id("ctlpasswordText__"));
			CommonActions.click(driver, passwordInputField);
			passwordInputField.sendKeys(password);


			//Click on the login button
			WebElement loginbutton=driver.findElement(By.id("ctllogonBtnButton__"));
			CommonActions.click(driver, loginbutton);
			//driver.navigate().to("javascript:document.getElementById('ctllogonBtnButton__').click()");


			StatusStore.updateStatus("login", "PASSED", "AS EXPECTED", "", "","");
			this.TakeScreenshot(driver);
		}catch(Exception e){
			StatusStore.updateStatus("login", "FAILED", "", e.getMessage(), "","");
			this.TakeScreenshot(driver);
		}
	}

	public void clickOnApps() {
		StatusStore.updateStatus();
		try{
			wait.until(ExpectedConditions.numberOfWindowsToBe(1));
			driver.switchTo().window(homepagewindowhandle);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='dropdown-toggle']/span[text()='Apps']/ancestor::li")));

			//Mouse over and click on the Apps
			WebElement webelement=driver.findElement(By.xpath("//a[@class='dropdown-toggle']/span[text()='Apps']/ancestor::li"));
			CommonActions.click(driver, webelement);

			System.out.println("Click On Apps Clicked....");
			StatusStore.updateStatus("PASSED", "AS EXPECTED", "", "","");

		}catch(Exception e){
			StatusStore.updateStatus("FAILED", "", e.getMessage(), "","");
			this.TakeScreenshot(driver);
		}
	}


	public boolean openWPBApplication(){
		StatusStore.updateStatus("openWPBApplication");
		boolean retval=false;
		try{
			String parentHandle = driver.getWindowHandle();

			//Click on the WBP App link under Apps
			String xpathstring="//a[@class='ng-binding ng-scope'][text()='Web Publishing']";
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathstring)));
			WebElement wpblink=driver.findElement(By.xpath(xpathstring));
			CommonActions.click(driver, wpblink);

			//Make sure WPB page appears properly
			wait.until(ExpectedConditions.numberOfWindowsToBe(2));
			for(String winHandle : driver.getWindowHandles()) {
				if(!parentHandle.equals(winHandle)){
					driver.switchTo().window(winHandle);
				}
			}
			driver.manage().window().maximize();
			retval=true;
			StatusStore.updateStatus("openWPBApplication", "PASSED", "AS EXPECTED", "", "","");
		}
		catch(Exception e){
			e.printStackTrace();
			retval =false;
			StatusStore.updateStatus("openWPBApplication", "FAILED", "", e.getMessage(), "","");
		}
		return retval;
	}

	public boolean openEmeasureTool(){
		StatusStore.updateStatus("openEmeasureTool");
		boolean retval=false;
		try{
			String parentHandle = driver.getWindowHandle();
			
			//Click on the Emeasure Tool link under Apps
			String xpathstring="//a[@class='ng-binding ng-scope'][contains(text(),'eMeasure Tool')]";
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathstring)));
			WebElement emeasuretoollink=driver.findElement(By.xpath(xpathstring));
			CommonActions.click(driver, emeasuretoollink);
			
			
			//Make Sure eMeasure Tool Window Opened
			wait.until(ExpectedConditions.numberOfWindowsToBe(2));
			for(String winHandle : driver.getWindowHandles()){
				if(!parentHandle.equals(winHandle)){
					System.out.println("Current Windows Name>>" + driver.getTitle());
					driver.switchTo().window(winHandle);
					System.out.println("Changed Windows Name>>" + driver.getTitle());
				}
			}
			driver.manage().window().maximize();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='current']/div/a")));
			retval=true;
			StatusStore.updateStatus("openEmeasureTool", "PASSED", "AS EXPECTED", "", "","");
		}
		catch(Exception e){
			e.printStackTrace();
			retval =false;
			StatusStore.updateStatus("openEmeasureTool", "FAILED", "", e.getMessage(), "","");
			//driver.close();
		}
		return retval;
	}

	public boolean openExecutiveViewNotification(){
		boolean retval=false;
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='navbar-collapse-menu']/ul[1]/li[3]/ul/li[3]/a"))).sendKeys(Keys.ENTER);
			//String winHandleBefore = driver.getWindowHandle();
			for(String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle);
			}
			System.out.println(".." + driver.getTitle());
			// Perform the actions on new window
			//driver.close();
			// Switch back to original browser (first window)
			//driver.switchTo().window(winHandleBefore);

			retval=true;
		}
		catch(Exception e){
			e.printStackTrace();
			retval =false;
		}
		return retval;
	}

	public boolean openBILaunchPad(){
		StatusStore.updateStatus("openBILaunchPad");
		boolean retval=false;
		try{

			String parentHandle = driver.getWindowHandle();

			//Click on the BI Launchpad Link under the Apps
			String xpathstring="//a[@class='ng-binding ng-scope'][text()='BI Launch Pad']";
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathstring)));
			WebElement bilaunchpadlink=driver.findElement(By.xpath(xpathstring));
			CommonActions.click(driver, bilaunchpadlink);

			//Make Sure BI Lanchpad window opens
			wait.until(ExpectedConditions.numberOfWindowsToBe(2));
			for(String winHandle : driver.getWindowHandles()) {
				if(!parentHandle.equals(winHandle)){
					System.out.println("Current Window Name: "+driver.getTitle());
					driver.switchTo().window(winHandle);
					System.out.println("Changed Window Name: " + driver.getTitle());
				}
			}
			driver.manage().window().maximize();
			retval=true;
			StatusStore.updateStatus("openBILaunchPad", "PASSED", "AS EXPECTED", "", "","");
			this.TakeScreenshot(driver);
		}
		catch(Exception e){
			e.printStackTrace();
			retval =false;
			StatusStore.updateStatus("openBILaunchPad", "FAILED", "", e.getMessage(), "","");
			this.TakeScreenshot(driver);
		}
		return retval;
	} 

	public void logOutfromHIUI() 
	{
		StatusStore.updateStatus("logOut");
		try{
			wait.until(ExpectedConditions.numberOfWindowsToBe(1));
			driver.switchTo().window(homepagewindowhandle);
			System.out.println(">>" + driver.getTitle());
			//driver.switchTo().defaultContent();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li/a[@Class='text-uppercase ng-binding']"))).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='You have been logged off of the application. Please close your browser window for the purpose of security.']")));
			StatusStore.updateStatus("logOut", "PASSED", "AS EXPECTED", "", "","");

		}catch(Exception e){
			System.out.println("logOutfromHIUI-Current Window Title>> " + driver.getTitle());
			System.out.println("logOutfromHIUI-No Of window>>"+Integer.toString(driver.getWindowHandles().size()));
			StatusStore.updateStatus("logOut", "FAILED", "", e.getMessage(), "","");
			this.TakeScreenshot(driver);
		}

	}

	public void closeBrowser(){
		StatusStore.updateStatus("closeBrowser");
		try{

			driver.close();
			driver.quit();
			StatusStore.updateStatus("closeBrowser", "PASSED", "AS EXPECTED", "", "","");
		}catch(Exception e){
			StatusStore.updateStatus("closeBrowser", "FAILED", "", e.getMessage(), "","");
		}
	}
}




