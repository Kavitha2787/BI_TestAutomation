//Actions action = new Actions(driver);
//action.moveToElement(driver.findElement(By.xpath("//table/tbody/tr//[2]/td/div/div/table/tbody/tr[10]/td[1]"))).doubleClick().perform();

package BI.automation.selenium.ie.hiui;

//Actions action = new Actions(driver);
//action.moveToElement(driver.findElement(By.xpath("//table/tbody/tr//[2]/td/div/div/table/tbody/tr[10]/td[1]"))).doubleClick().perform();



import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



import BI.automation.common.BILogger;
import BI.automation.common.CommonPageClass;
import BI.automation.common.PropertiesStore;
import BI.automation.common.StatusStore;
import BI.automation.selenium.common.WebDriverStore;
import BI.automation.selenium.ie.common.CommonActions;


public class HIUIHomePage extends CommonPageClass{
	WebDriver driver;
	WebDriverWait wait;
	int waittime =Integer.parseInt(PropertiesStore.getSystemPropertyValue("HIUIHomePage_WaitTime"));
	String homepagewindowhandle="";
	File tempreportlogfile=null;

	public HIUIHomePage(){
		BILogger.writeInfoToLog(this.getClass(),"Getting Driver");
		driver=WebDriverStore.getWebDriverFromStore(PropertiesStore.getCurrentScreenshotFolder());
		BILogger.writeInfoToLog(this.getClass(),"Driver title>>"+driver.getTitle());
		
	}

	public void openHIUIHomePage(){
		try{
			StatusStore.updateStatus("openHIUIHomePage");
			homepagewindowhandle=driver.getWindowHandle();
			wait=new WebDriverWait(driver,waittime);
			BILogger.writeInfoToLog(this.getClass(),"Open HIUI Login Homepage>>"+driver.getTitle());
			//Wait untill the username and password field and login button is visible
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("ctlusernameText__")));
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("ctlpasswordText__")));
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("ctllogonBtnButton__")));
			
			StatusStore.updateStatus("openHIUIHomePage", "PASSED", "AS EXPECTED", "", "","");
			this.TakeScreenshot(driver);
		}catch(Exception e){
			StatusStore.updateStatus("openHIUIHomePage", "FAILED", "", e.getMessage(), "","");
			this.TakeScreenshot(driver);
		}
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

			this.TakeScreenshot(driver);
			
			//Make sure login happen i.e. LOGG OFF should be there
			String xpathstring="//li/a[@Class='text-uppercase ng-binding']";
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathstring)));
			
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
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='dropdown-toggle']/span[text()='Apps']")));

			//Mouse over and click on the Apps
			WebElement webelement=driver.findElement(By.xpath("//a[@class='dropdown-toggle']/span[text()='Apps']"));
			CommonActions.click(driver, webelement);

			System.out.println("Click On Apps Clicked....");
			StatusStore.updateStatus("PASSED", "AS EXPECTED", "", "","");
			this.TakeScreenshot(driver);
		}catch(Exception e){
			StatusStore.updateStatus("FAILED", "", e.getMessage(), "","");
			this.TakeScreenshot(driver);
		}
	}
	public boolean openAnalyticsLibrary(){
		StatusStore.updateStatus("openAnalyticsLibrary");
		boolean retval=false;
		String parentHandle = driver.getWindowHandle();
		try{
			//Click on the WBP App link under Apps
			String xpathstring="//a[@class='ng-binding ng-scope'][text()='Analytics Library']";
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathstring)));
			WebElement analyticslibrarylink=driver.findElement(By.xpath(xpathstring));
			CommonActions.click(driver, analyticslibrarylink);
			System.out.println("Trying to maximize the window");
			//Make sure Analytics page appears properly
			//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//body/div[last()]//iframe"))); 
			driver.manage().window().maximize();
			System.out.println("Window Maximized");
			retval=true;
			StatusStore.updateStatus("openAnalyticsLibrary", "PASSED", "AS EXPECTED", "", "","");
			this.TakeScreenshot(driver);
		}
		catch(Exception e){
			e.printStackTrace();
			retval =false;
			String yourtext= driver.findElement(By.tagName("body")).getText() ;
			StatusStore.updateStatus("openAnalyticsLibrary", "FAILED", "", yourtext+"\n "+e.getMessage(), "","");
			this.TakeScreenshot(driver);
			driver.close();
			driver.switchTo().window(parentHandle);
		}

		return retval;
	}


	public boolean openWPBApplication(){
		StatusStore.updateStatus("openWPBApplication");
		boolean retval=false;
		String parentHandle = driver.getWindowHandle();
		try{
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
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class, 'electedClass')][text()=' Publications']")));
			retval=true;
			StatusStore.updateStatus("openWPBApplication", "PASSED", "AS EXPECTED", "", "","");
			this.TakeScreenshot(driver);
		}
		catch(Exception e){
			e.printStackTrace();
			retval =false;
			String yourtext= driver.findElement(By.tagName("body")).getText() ;
			StatusStore.updateStatus("openWPBApplication", "FAILED", "", yourtext+"\n "+e.getMessage(), "","");
			this.TakeScreenshot(driver);
			driver.close();
			driver.switchTo().window(parentHandle);
		}

		return retval;
	}

	public boolean openEmeasureTool(){
		StatusStore.updateStatus("openEmeasureTool");
		boolean retval=false;
		String parentHandle = driver.getWindowHandle();
		try{
			//Click on the Emeasure Tool link under Apps
			String xpathstring="//a[@class='ng-binding ng-scope'][text()='eMeasure Tool']";
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
			this.TakeScreenshot(driver);
		}
		catch(Exception e){
			e.printStackTrace();
			retval =false;
			String yourtext= driver.findElement(By.tagName("body")).getText() ;
			StatusStore.updateStatus("openWPBApplication", "FAILED", "", yourtext+"\n "+e.getMessage(), "","");
			StatusStore.updateStatus("openEmeasureTool", "FAILED", "", e.getMessage(), "","");
			this.TakeScreenshot(driver);
			driver.close();
			driver.switchTo().window(parentHandle);
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
		String parentHandle = driver.getWindowHandle();
		try{
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
			driver.switchTo().defaultContent();
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("servletBridgeIframe")));  
			retval=true;
			StatusStore.updateStatus("openBILaunchPad", "PASSED", "AS EXPECTED", "", "","");
			this.TakeScreenshot(driver);
		}
		catch(Exception e){
			e.printStackTrace();
			retval =false;
			String yourtext= driver.findElement(By.tagName("body")).getText() ;
			StatusStore.updateStatus("openBILaunchPad", "FAILED", "", yourtext+"\n "+e.getMessage(), "","");
			this.TakeScreenshot(driver);
			driver.close();
			driver.switchTo().window(parentHandle);
		}

		return retval;
	} 

	public void logOutFromHIUI() 
	{
		StatusStore.updateStatus("logOutFromHIUI");
		try{
			wait.until(ExpectedConditions.numberOfWindowsToBe(1));
			driver.switchTo().window(homepagewindowhandle);
			driver.switchTo().defaultContent();
			System.out.println("logOutfromHIUI-Current Window Title>>" + driver.getTitle());
			String xpathstring="//li/a[@Class='text-uppercase ng-binding']";
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathstring)));
			WebElement logoff=driver.findElement(By.xpath(xpathstring));
			logoff.click();
			//CommonActions.doubleClick(driver, logoff);
			//CommonActions.tryClicking(logoff, 1);
			System.out.println("logOutfromHIUI-LOG OFF Clicked");

			//Make Sure Log off happen
			//
			wait.until(ExpectedConditions.numberOfWindowsToBe(1));
			wait.until(ExpectedConditions.urlContains("logoff"));
			
			StatusStore.updateStatus("logOutFromHIUI", "PASSED", "AS EXPECTED", "", "","");
			this.TakeScreenshot(driver);
			driver.close();

		}catch(Exception e){
			StatusStore.updateStatus("logOutFromHIUI", "FAILED", "", e.getMessage(), "","");
			System.out.println("logOutFromHIUI>>"+e.getMessage());
			this.TakeScreenshot(driver);
			driver.close();
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

	@SuppressWarnings("unused")
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
}