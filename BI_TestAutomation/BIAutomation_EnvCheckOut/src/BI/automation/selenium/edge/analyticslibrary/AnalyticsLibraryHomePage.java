package BI.automation.selenium.edge.analyticslibrary;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import BI.automation.common.CommonPageClass;
import BI.automation.common.PropertiesStore;
import BI.automation.common.StatusStore;
import BI.automation.selenium.edge.common.CommonActions;
import BI.automation.selenium.edge.common.HomePage;
import BI.automation.selenium.edge.hiui.HIUIHomePage;


public class AnalyticsLibraryHomePage extends CommonPageClass{
	WebDriver driver;
	WebDriverWait wait;
	HomePage homepage;
	int waittime =Integer.parseInt(PropertiesStore.getSystemPropertyValue("AnalyticsLibHomePage_WaitTime"));
	Actions action;
	WebElement WelementForReport;
	boolean closeTab=false;
	String parentHandle="";
	String mainiframeofthispage="servletBridgeIframe";

	String framename="";
	

	public AnalyticsLibraryHomePage(HIUIHomePage homepage){
		//Constructor of the class
		//initialize(homepage);
		driver=homepage.getDriver();
		
		wait=new WebDriverWait(driver,waittime);
		System.out.println("AnalyticsLibraryHomePage");
		getParentHandle();
		
	}
	
	
	private boolean gotoFolder(String pathName)
	{
		boolean retVal=false;
		try{
		String[] listoffolder=pathName.split("/");
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//body/div[last()]//iframe")));
		System.out.println("Frame Switched");
		
		for(int i=0;i<listoffolder.length;i++)
		{      
			
			System.out.println("In loop");
			System.out.println(listoffolder[i]);
			//Start loop
			if (i==listoffolder.length-1)
			{
				System.out.println("In Last folder: "+listoffolder[i]);
				String xpath3string="//span[contains(text(),'"+listoffolder[i]+"')]/parent::div/parent::li[@Class='ng-scope tree-leaf']";
				WebElement folderlink=driver.findElement(By.xpath(xpath3string));
				System.out.println("click folder");
				CommonActions.click(driver, folderlink);
				System.out.println("Final folder clicked");
			}
			else
			{
			String xpath1string="//span[contains(text(),'"+listoffolder[i]+"')]/parent::div/parent::li[@Class='ng-scope tree-expanded']";
			String xpathstring="//span[contains(text(),'"+listoffolder[i]+"')]/parent::div/parent::li[@Class='ng-scope tree-collapsed']";
			System.out.println(driver.findElements(By.xpath(xpathstring)).size());
			if(driver.findElements(By.xpath(xpathstring)).size() > 0)
			{
				//If folder is in collapsed form Look for arrow button next to folder and click 
				System.out.println("Found collapsed: "+listoffolder[i]);
				String xpath2string="//span[contains(text(),'"+listoffolder[i]+"')]/parent::div/parent::li[@Class='ng-scope tree-collapsed']/i[1]";
				WebElement folderlink=driver.findElement(By.xpath(xpath2string));
				System.out.println("click arrow");
				CommonActions.click(driver, folderlink);
				System.out.println("Arrow clicked");
			}
			else if(driver.findElements(By.xpath(xpath1string)).size() > 0)
			{
				System.out.println("Found expanded: "+listoffolder[i]);
				
			}
			else 
			{
				System.out.println(listoffolder[i]+": Not Found");
			}
			
		}
		retVal=true;
		}
		}
		catch(Exception e){
			retVal=false;
		
		}
		return retVal;
	}

	private void clickOnReport(String Reportname){
		
	
		try{
			returnToParentHandle();
			//Click on the Report link from Frame 2
			String xpath="";
			driver.switchTo().defaultContent();
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//body/div[last()]//iframe")));
			
			xpath="//span[contains(text(),'"+Reportname+"')]"; 
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			
			WebElement wpblink=driver.findElement(By.xpath(xpath));
			CommonActions.click(driver, wpblink);

			//Make sure Report page appears properly
			wait.until(ExpectedConditions.numberOfWindowsToBe(2));
			for(String winHandle : driver.getWindowHandles()) {
				if(!parentHandle.equals(winHandle)){
					driver.switchTo().window(winHandle);
				}
			}
			driver.manage().window().maximize();
			driver.switchTo().defaultContent();
			printListofFrame();
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("article-viewer-frame")));
			System.out.println("Frame opened");
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='crViewer__UI']")));
		
			printListofFrame();
			driver.switchTo().frame(0);
			System.out.println("First Frame opened");
			xpath="//span[contains(text(),'Confidential, Copyright © Cerner Health Services, Inc.  All rights reserved.')]"; 
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			
			System.out.println("Report opened");
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class, 'electedClass')][text()=' Publications']")));
			//retval=true;
			//StatusStore.updateStatus("openALApplication", "PASSED", "AS EXPECTED", "", "","");
			//this.TakeScreenshot(driver);
			driver.close();
			returnToParentHandle();
		}
		catch(Exception e){
			e.printStackTrace();
			//retval =false;
			//String yourtext= driver.findElement(By.tagName("body")).getText() ;
			//StatusStore.updateStatus("openWPBApplication", "FAILED", "", yourtext+"\n "+e.getMessage(), "","");
			this.TakeScreenshot(driver);
			driver.close();
			returnToParentHandle();
		}

	}
	public void printListofFrame(){
		List<WebElement> ele = driver.findElements(By.tagName("iframe"));
	    System.out.println("Number of frames in a page :" + ele.size());
	    for(WebElement el : ele){
	      //Returns the Id of a frame.
	        System.out.println("Frame Id :" + el.getAttribute("id"));
	      //Returns the Name of a frame.
	        System.out.println("Frame name :" + el.getAttribute("name"));
	        framename=el.getAttribute("id");
	    }
	} 
		
	public void openALReports(String listOfReportWithPathDelimitedbyComma){
		
		String lastreportpath="";
		boolean isFolderExists=false;
		String lisofreports[]=listOfReportWithPathDelimitedbyComma.trim().split(",");

		String reportName=null;
		String reportPath = null;
		for(int i=0;i<lisofreports.length;i++){
			try{
				StatusStore.updateStatus(lisofreports[i].trim());
				int index = lisofreports[i].trim().lastIndexOf("/");
				reportName = lisofreports[i].trim().substring(index + 1);
				reportPath=lisofreports[i].trim().substring(0, index);

				//Navigate to the report folder
				if(lastreportpath.isEmpty()||!lastreportpath.matches(reportPath)){
					isFolderExists=gotoFolder(reportPath);
				}
				//Check Navigation to folder unsuccessful
				if(!isFolderExists){
					throw new Exception("Report Folder Not Exists");
				}

				//Check Navigation to folder successful
				if(isFolderExists){
					System.out.println("Ready to open report");
					//Open the report
					clickOnReport(reportName);
				}
				this.TakeScreenshot(driver);
				//closeReportTab();
				//Thread.sleep(3000);
				//clickOnDocuments();
				//CommonActions.switchToLastFrame(driver);
				//driver.close();
				StatusStore.updateStatus(lisofreports[i].trim(), "PASSED", "AS EXPECTED","" , "","");
			}catch(Exception e){
				//closeReportTab();
				StatusStore.updateStatus(lisofreports[i].trim(), "FAILED", "", e.getMessage(), "","");
				System.out.println(e);
				this.TakeScreenshot(driver);
			}
			lastreportpath=reportPath;
		}
	}
	
	private void getParentHandle(){
		parentHandle = driver.getWindowHandle();
		
	}
	
	private void returnToParentHandle(){
		wait.until(ExpectedConditions.numberOfWindowsToBe(1));
		driver.switchTo().window(parentHandle);
	}
}
