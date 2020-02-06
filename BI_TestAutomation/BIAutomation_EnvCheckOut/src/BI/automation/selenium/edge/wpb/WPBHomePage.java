package BI.automation.selenium.edge.wpb;

import java.util.ArrayList;
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
import BI.automation.selenium.edge.common.OpenBrowser;
import BI.automation.selenium.edge.hiui.HIUIHomePage;

public class WPBHomePage extends CommonPageClass {

	WebDriver driver;
	WebDriverWait wait;
	int waittime =Integer.parseInt(PropertiesStore.getSystemPropertyValue("WPBHomePage_WaitTime"));
	ArrayList<String> safereporttoconsider = new ArrayList<String>();
	ArrayList<String> logtime=new ArrayList<String>();
	Actions action;
	WebElement WelementForReport;
	public WPBHomePage(HIUIHomePage homepage){
		driver=homepage.getDriver();

		//wait=new WebDriverWait(driver,homepage.getWaitTime());
		wait=new WebDriverWait(driver,waittime);
		

	}
	public WPBHomePage(String browsertype,String browserdriver,String url ){
		//Open the url
		StatusStore.updateStatus("WPBHomePage");
		try{
			OpenBrowser openbrowser= new OpenBrowser(browsertype,browserdriver);
			openbrowser.openURL(url);
			driver=openbrowser.getWebDriver();
			wait=new WebDriverWait(driver,waittime);
			StatusStore.updateStatus("WPBHomePage", "PASSED", "AS EXPECTED", "", "","");
			this.TakeScreenshot(driver);
		}catch(Exception e){
			StatusStore.updateStatus("WPBHomePage", "FAILED", "", e.getMessage(), "","");
		}

	}

	public void directWPBLogin(String userid,String password)
	{

		//Wait untill the username field is visible
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("ctlusernameText__")));

		WebElement userNameInputField=driver.findElement(By.id("ctlusernameText__"));
		WebElement passwordInputField=driver.findElement(By.id("ctlpasswordText__"));


		userNameInputField.sendKeys(userid);
		passwordInputField.sendKeys(password);
		driver.findElement(By.id("ctllogonBtnButton__")).click();
	}
	public void directWPBreportclick(){
		for(String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}

		System.out.println(">>" + driver.getTitle());
		driver.switchTo().defaultContent();
		driver.switchTo().frame("sframeInner");

		driver.switchTo().frame("wellFrame");
		driver.switchTo().frame("tabWell0");
		driver.findElement(By.xpath("//a[text()='Web Publishing']")).click();
	}
	public void clickToAvoidCertificateError() throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("overridelink")));
		//driver.findElement(By.partialLinkText("Continue to this website (not recommended).")).click();

		driver.navigate().to("javascript:document.getElementById('overridelink').click()"); //Java script to click the link
		Thread.sleep(5000);
		System.out.println("Success");

	}


	public void openReports(String listOfReportWithPathDelimitedbyComma){
		String lastrepotname="";
		String lastreportpath="";
		boolean isFolderExists=false;
		int isReportOpenedSuccesfully=0;

		String lisofreports[]=listOfReportWithPathDelimitedbyComma.split(",");
		for(int i=0;i<lisofreports.length;i++){
			StatusStore.updateStatus(lisofreports[i]);

			int index = lisofreports[i].lastIndexOf("/");
			String reportName = lisofreports[i].substring(index + 1);
			String reportPath=lisofreports[i].substring(0, index);

			System.out.println("Trying to open report>>"+lisofreports[i]);

			//Select the folder
			if(lastreportpath.isEmpty()||!lastreportpath.matches(reportPath)){
				System.out.println("The report Path is not matching");
				isFolderExists=gotoFolder(reportPath);
			}

			//Check the existence of folder
			if(isFolderExists){
				//Check report is current or not
				if(isReportCurrent(reportName,isReportOpenedSuccesfully)){
					//Try to open the report
					if(isCurrentReportOpened(reportName,isReportOpenedSuccesfully)){

						//Try to click on the done button 
						if(cliickOnDoneButton()){
							StatusStore.updateStatus(lisofreports[i], "PASSED", "AS EXPECTED", "", "","");
						}else{
							StatusStore.updateStatus(lisofreports[i], "FAILED", "", "Issue While clicking done button", "","");
						}
						//EOF Try to click on the done button 
					}else{
						StatusStore.updateStatus(lisofreports[i], "FAILED", "", "Report Is not opened successfully", "","");
						//Try to click on the done button 
						if(!cliickOnDoneButton()){
							StatusStore.updateStatus(lisofreports[i], "FAILED", "", "Issue While clicking done button", "","");
						}
						//EOF Try to click on the done button 
					}
					//EOF Try to open the report

					isReportOpenedSuccesfully++;
				}else{
					StatusStore.updateStatus(lisofreports[i], "FAILED", "", "Report Is Not Available", "","");
				}
				//EOF Check report is current or not

			}else{
				StatusStore.updateStatus(lisofreports[i], "FAILED", "", "Report Folder not exists", "","");
			}


			lastrepotname=reportName;
			lastreportpath=reportPath;
		}
	}

	private boolean gotoFolder(String pathName){
		boolean retVal=false;
		try{
			System.out.println(pathName);
			String[] listoffolder=pathName.split("/");
			System.out.println("???????????????????????????????"+listoffolder.length);
			action = new Actions(driver);
			for(int i=0;i<listoffolder.length;i++){
				if(listoffolder[i].equals("Publications")){				
					driver.switchTo().defaultContent();
					driver.switchTo().frame("sframeInner");
					
					driver.switchTo().frame("wellFrame");
					driver.switchTo().frame("tabWell0");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class, 'electedClass')][text()=' Publications']")));
					WebElement element= driver.findElement(By.xpath("//span[contains(@class, 'electedClass')][text()=' Publications']"));
					//System.out.println("//span[@class='tvwUnselectedClass'][text()='"+listoffolder[i]+"']");
					action.doubleClick(element).perform();
				}	
				else{	

					driver.switchTo().defaultContent();
					wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("sframeInner")));
					//driver.switchTo().frame("sframeInner");
					wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("wellFrame")));
					wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("tabWell0")));
					//driver.switchTo().frame("wellFrame");
				
					//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//div[@id='crvCrystalReportViewer__UI']//td[@class='dialogzone']//iframe")));
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class, 'electedClass')][text()='"+listoffolder[i]+"']")));

					WebElement element= driver.findElement(By.xpath("//span[contains(@class, 'electedClass')][text()='"+listoffolder[i]+"']"));
					//System.out.println("//span[@class='tvwUnselectedClass'][text()='"+listoffolder[i]+"']");
					action.doubleClick(element).perform();

					//driver.findElement(By.xpath("//span[@class='tvwUnselectedClass'][text()='General Accounting']")).click();
				
			}
			}
			retVal=true;
			this.TakeScreenshot(driver);
		}catch(Exception e){
			retVal=false;
			this.TakeScreenshot(driver);
			System.out.println("Under GotoFolder>>"+e.getMessage());
		}
		return retVal;
	}

	private boolean openCurrentReport(String Reportname,int reportposition){
		boolean isPassed=false;
		try{
			if(isReportCurrent(Reportname,reportposition)){
				if(isCurrentReportOpened(Reportname,reportposition)){
					isPassed=cliickOnDoneButton();
				}else{
					isPassed=cliickOnDoneButton();
				}
			}
		}catch(Exception e){
			isPassed=false;
		}
		return isPassed;
	}

	private boolean isReportCurrent(String Reportname,int reportposition) {
		boolean returnvalue=false;
		try{
			System.out.println(Reportname+" Under iscurrent");
			action = new Actions(driver);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'"+Reportname+"')]")));
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+Reportname+"']")));
			System.out.println(" Element found");
			WelementForReport=driver.findElement(By.xpath("//span[contains(text(), '"+ Reportname +"')]"));
			action.moveToElement(WelementForReport).click().perform();
			WebElement element2 = driver.findElement(By.xpath("//span[contains(text(), '"+ Reportname +"')]/parent::td/following-sibling::td"));
			String text=element2.getText();
			System.out.println(Reportname+"type is"+text);
			if(text.equals("Current") || text.endsWith("AM")||text.endsWith("PM")){	

				returnvalue=true;

			}else{	
				returnvalue=false;
			}
			this.TakeScreenshot(driver,Reportname+"_Available");
		}catch(Exception e){
			System.out.println("isReportCurrent>>>>>"+e.getMessage());
			returnvalue = false;
			this.TakeScreenshot(driver);

		}
		return returnvalue;
	}

	private boolean isCurrentReportOpened(String Reportname,int reportposition) {
		boolean returnvalue=false;
		try{
			action.doubleClick(WelementForReport).perform();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("sframeInner");

			driver.switchTo().frame("wellFrame");
			driver.switchTo().frame("tabWell"+String.valueOf(reportposition+1).trim());

			//Switch to crystal report content frame
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("fraCRViewer")));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//div[@id='crvCrystalReportViewer__UI']//td[@class='dialogzone']//iframe"))); 
			returnvalue=true;
			this.TakeScreenshot(driver,Reportname);
		}catch(Exception e){
			returnvalue=false;
			this.TakeScreenshot(driver,Reportname);
			System.out.println("isCurrentReportOpened"+">>"+e.getMessage());
		}
		return returnvalue;
	}

	private boolean cliickOnDoneButton() {
		boolean returnvalue=false;
		try{
			driver.switchTo().defaultContent();
			driver.switchTo().frame("sframeInner");
			driver.switchTo().frame("wellFrame");
			driver.findElement(By.id("doneButtonType2")).click();


			driver.switchTo().defaultContent();
			driver.switchTo().frame("sframeInner");

			driver.switchTo().frame("wellFrame");
			driver.switchTo().frame("tabWell0");
			returnvalue=true;
		}catch(Exception e){
			returnvalue=false;
		}
		return returnvalue;
	}

	public void openReports(String reportfoldername,int noofreports){

		//Goto the desired folder
		StatusStore.updateStatus(reportfoldername);
		if(gotoFolder(reportfoldername)){
			StatusStore.updateStatus(reportfoldername, "PASSED", "AS EXPECTED", "", "","");
			//Gathered the safe report i.e. current report
			listTheCurrentReport(noofreports);
			if(safereporttoconsider.size()<=0){
				StatusStore.updateStatus(reportfoldername, "FAILED", "", "Report Is Not Available", "","");
			}else{
				for(int i=0;i<safereporttoconsider.size();i++){
					String reportnamewithpath=reportfoldername+safereporttoconsider.get(i);
					openReports(reportnamewithpath);
				}
			}
			//EOF Gathered the safe report i.e. current report
		}
		else{
			StatusStore.updateStatus(reportfoldername, "FAILED", "", "Report Folder not exists", "","");
		}
		//EOF Goto the desired folder

	}



	private void listTheCurrentReport(int howmanyreportstolist){
		try{
			// Grab the tabl
			WebElement table = driver.findElement(By.xpath("//div[@class='xdvTableBody']/table")); 

			// Now get all the TR elements from the table 
			List<WebElement> allRows = table.findElements(By.tagName("tr")); 

			// And iterate over them, getting the cells and putting the report those are in current
			int counter=0;
			for (WebElement row : allRows){
				List<WebElement> cells = row.findElements(By.tagName("td"));
				if(cells.get(cells.size()-1).getText().equals("Current") || cells.get(cells.size()-1).getText().endsWith("AM")||cells.get(cells.size()-1).getText().endsWith("PM")){
					String reportname=cells.get(cells.size()-2).getText();
					System.out.println(reportname);
					safereporttoconsider.add(reportname);
					counter++;
				}
				if (counter==howmanyreportstolist){
					break;	
				}
			}
		}catch(Exception e){
			
		}
	}
	public void closeWPB(){
		StatusStore.updateStatus("closeWPB");
		try{
		driver.switchTo().defaultContent();
		driver.switchTo().frame("sframeInner");

		driver.switchTo().frame("wellFrame");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logoffImg"))).click();		
		StatusStore.updateStatus("closeWPB", "PASSED", "AS EXPECTED", "", "","");
		driver.close();
		
		}catch(Exception e){
			//StatusStore.updateStatus("closeWPB", "FAILED", "", e.getMessage(), "","");
			this.TakeScreenshot(driver);
			driver.close();
			System.out.println(e.getMessage());
			StatusStore.updateStatus("closeWPB", "FAILED", "", e.getMessage(), "","");
			
		}
		
	}



}




