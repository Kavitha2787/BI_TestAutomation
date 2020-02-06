package BI.automation.selenium.edge.bo;



import java.util.List;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import BI.automation.selenium.edge.common.OpenBrowser;

import BI.automation.selenium.edge.common.CommonActions;
import BI.automation.selenium.edge.common.HomePage;
import BI.automation.selenium.edge.hiui.HIUIHomePage;
import BI.automation.common.CommonPageClass;
import BI.automation.common.PropertiesStore;
import BI.automation.common.StatusStore;

public class BOHomePage extends CommonPageClass{
	WebDriver driver;
	WebDriverWait wait;
	HomePage homepage;
	int waittime =Integer.parseInt(PropertiesStore.getSystemPropertyValue("BOHomePage_WaitTime"));
	Actions action;
	WebElement WelementForReport;
	boolean closeTab=false;

	String mainiframeofthispage="servletBridgeIframe";

	String framename="";

	public BOHomePage(HIUIHomePage homepage){
		//Constructor of the class
		//initialize(homepage);
		driver=homepage.getDriver();

		wait=new WebDriverWait(driver,waittime);
	}
	public BOHomePage(String browsertype,String browserdriver,String url ){
		initializeDirectURL(browsertype, browserdriver,url);
	}

	public WebDriver getWebDriver(){
		return driver;
	}
	private void initializeDirectURL(String browsertype,String browserdriver,String url){
		//Open the URL  in browser
		StatusStore.updateStatus("initializeDirectURL");

		try{
			OpenBrowser openbrowser= new OpenBrowser(browsertype,browserdriver);
			openbrowser.openURL(url);
			driver=openbrowser.getWebDriver();
			wait=new WebDriverWait(driver,waittime);
			this.TakeScreenshot(driver);
			StatusStore.updateStatus("initializeDirectURL", "PASSED", "AS EXPECTED", "", "","");
		}
		catch(Exception e){

			this.TakeScreenshot(driver);

			StatusStore.updateStatus("initializeDirectURL", "FAILED", "", e.getMessage(), "","");
		}
	}


	public void AgainLogin(String userid,String password)
	{

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("ctlusernameText__")));
		WebElement userNameInputField=driver.findElement(By.id("ctlusernameText__"));
		WebElement passwordInputField=driver.findElement(By.id("ctlpasswordText__"));
		userNameInputField.sendKeys(userid);
		passwordInputField.sendKeys(password);
		driver.findElement(By.id("ctllogonBtnButton__")).click();
	}
	public void directBILaunchPadLogin(String userid,String password){
		StatusStore.updateStatus("directBILaunchPadLogin");
		try{
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("ctlusernameText__")));
			WebElement userNameInputField=driver.findElement(By.id("ctlusernameText__"));
			WebElement passwordInputField=driver.findElement(By.id("ctlpasswordText__"));
			userNameInputField.sendKeys(userid);
			passwordInputField.sendKeys(password);                             
			driver.findElement(By.id("ctllogonBtnButton__")).click();
			StatusStore.updateStatus("directBILaunchPadLogin", "PASSED", "AS EXPECTED", "", "","");
			this.TakeScreenshot(driver);
		}catch(Exception e){
			StatusStore.updateStatus("directBILaunchPadLogin", "FAILED", "", e.getMessage(), "","");
			this.TakeScreenshot(driver);
		}

	}

	public void openFolder(){
		try{
			StatusStore.updateStatus("openFolder");

			clickOnDocuments();
			clickOnFolders();
			//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@id='accordionNavigationView_drawer1_treeView_treeNode1_name']/parent::a/parent::td/preceding-sibling::td/a")));
			WebElement e2=driver.findElement(By.xpath("//span[@id='accordionNavigationView_drawer1_treeView_treeNode1_name']/parent::a/parent::td/preceding-sibling::td/a"));
			CommonActions.doubleClick(driver, e2);

			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("ListingURE_footer__objectCountLabel")));

			StatusStore.updateStatus("openFolder", "PASSED", "AS EXPECTED", "", "","");
			this.TakeScreenshot(driver);
		}catch(Exception e){
			StatusStore.updateStatus("openFolder", "FAILED", "", e.getMessage(), "","");
			System.out.println(e);
			this.TakeScreenshot(driver);
		}
	}

	public void openReports(String listOfReportWithPathDelimitedbyComma){
		clickOnDocuments();
		clickOnFolders();

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
					//Open the report
					clickOnReport(reportName);
				}
				closeReportTab();
				Thread.sleep(3000);
				clickOnDocuments();
				CommonActions.switchToLastFrame(driver);
				StatusStore.updateStatus(lisofreports[i].trim(), "PASSED", "AS EXPECTED","" , "","");
			}catch(Exception e){
				closeReportTab();
				StatusStore.updateStatus(lisofreports[i].trim(), "FAILED", "", e.getMessage(), "","");
				System.out.println(e);
				this.TakeScreenshot(driver);
			}
			lastreportpath=reportPath;
		}
	}

	public void logOffBO(){
		StatusStore.updateStatus("logOffBO");
		try{
			CommonActions.switchToFrame(driver, mainiframeofthispage);

			//Click on the Log off button
			String idstring="logoffLink-button";
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(idstring)));
			WebElement logout=driver.findElement(By.id(idstring));
			System.out.println(driver.getTitle());
			logout.click();
			System.out.println("logoffLink-button >>"+"Clicked");
			//CommonActions.click(driver, logout);
			//Make sure logged off correctly
			wait.until(ExpectedConditions.numberOfWindowsToBe(2));
			wait.until(ExpectedConditions.urlContains("DSSBI_Logoff.jsp"));
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='You have been logged off of the Healthcare Query application. You may now close the tab or Window.']")));
			this.TakeScreenshot(driver);
			StatusStore.updateStatus("logOffBO", "PASSED", "AS EXPECTED", "", "","");
			driver.close();
		}catch(Exception e){
			this.TakeScreenshot(driver);
			driver.close();
			System.out.println(e);
			StatusStore.updateStatus("logOffBO", "FAILED", "", e.getMessage(), "","");
		}
	}



	private void clickOnDocuments(){
		System.out.println("BOHomePage - "+"Try to click on Documents - "+"STARTED");
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name(mainiframeofthispage)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Documents']")));
		WebElement doclink=driver.findElement(By.xpath("//a[@title='Documents']"));
		CommonActions.click(driver, doclink);
		System.out.println("BOHomePage - "+"Try to click on Documents - "+"FINISHED");
	}

	private void clickOnFolders(){
		System.out.println("BOHomePage - "+"Try to click on Folders - "+"STARTED");
		CommonActions.switchToLastFrame(driver,mainiframeofthispage);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Folders']")));
		driver.findElement(By.xpath("//a[text()='Folders']")).click();
		System.out.println("BOHomePage - "+"Try to click on Folders - "+"FINISHED");
	}

	private void clickOnReport(String Reportname){
		String xpath="";
		driver.switchTo().defaultContent();                                         
		driver.switchTo().frame(mainiframeofthispage);
		CommonActions.switchToLastFrame(driver, mainiframeofthispage);

		xpath="//table[@id='ListingURE_detailView_listMainTable']//div[contains(text(),'"+Reportname+"')]";
		System.out.println("clickOnReport - Looking for xpath - "+xpath);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		System.out.println("clickOnReport - found xpath - "+xpath);

		WebElement element1=driver.findElement(By.xpath(xpath));
		CommonActions.click(driver, element1);
		this.TakeScreenshot(driver);

		//Find out the type of Report
		xpath="//div[contains(text(), '"+ Reportname +"')]/parent::td/following-sibling::td";
		String reportType = driver.findElement(By.xpath(xpath)).getText();
		System.out.println("Report Type is: "+reportType);
		CommonActions.doubleClick(driver, element1);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(mainiframeofthispage);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//body/div[last()]//iframe")));

		switch(reportType){
		case "Web Intelligence":
			//Open report as Web Intelligent
			closeTab=true;
			openWEBIReport(Reportname);
			break;
		case "Analysis Workspace":
			closeTab=true;
			openAWSReport(Reportname);
			break;
		case "Dashboards":
			closeTab=true;
			break;
		case "Adobe Acrobat":
			closeTab=true;
			break;
		}
	}

	private void openWEBIReport(String reportname){
		this.TakeScreenshot(driver);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("webiViewFrame")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("RealBtn_OK_BTN_promptsDlg")));
		driver.findElement(By.id("RealBtn_OK_BTN_promptsDlg")).click();
		this.TakeScreenshot(driver);
		driver.findElement(By.id("RealBtn_OK_BTN_alertDlg")).click();
		this.TakeScreenshot(driver);

	}

	private void openAWSReport(String reportname){
		this.TakeScreenshot(driver);
		String xpath="";
		//Check for warning box appeared or not if appeared then click ok
		xpath="//span[text()='OK']";
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
		driver.findElement(By.xpath(xpath)).click();
		this.TakeScreenshot(driver);

		//Check for final AWS opened or not
		xpath="//div[@id='designerForm:Crosstab1_container_cell_data'][@class='ct_scroll_cell_data_container_div']"; 
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
		this.TakeScreenshot(driver);
	}

	@SuppressWarnings("unused")
	private void openDashboard(String reportname){
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//object[@type=\"application/x-shockwave-flash\"]")));
		List<WebElement> listflash = driver.findElements(By.xpath("//object[@type=\"application/x-shockwave-flash\"]"));
		for (WebElement flash: listflash)
		{
			System.out.println("Coordinates: " + flash.getLocation().x + ", " + flash.getLocation().y);
			System.out.println("Size: " + flash.getSize().width + ", " + flash.getSize().height);
		}
	}

	private void closeReportTab(){
		if(closeTab){
			driver.switchTo().defaultContent();
			driver.switchTo().frame(mainiframeofthispage);
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[@title='Close this tab']")));
			WebElement ele=driver.findElement(By.xpath("//a[@title='Close this tab']"));
			ele.click();
			closeTab=false;
		}

	}


	private boolean gotoFolder(String pathName){
		boolean retVal=false;
		String publicfolderdivId="";
		String parentdivid="";
		String childdivId="";

		try{

			String[] listoffolder=pathName.split("/");
			driver.switchTo().defaultContent();
			driver.switchTo().frame(mainiframeofthispage);
			printListofFrame();
			CommonActions.switchToLastFrame(driver,mainiframeofthispage);


			//Double click on the Public Folders
			String xPathOfPublicFolder="//span[@id='accordionNavigationView_drawer1_treeView_treeNode1_name']/parent::a/parent::td/preceding-sibling::td/a";
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xPathOfPublicFolder)));
			WebElement elementPublicFolder=driver.findElement(By.xpath(xPathOfPublicFolder));
			CommonActions.doubleClick(driver, elementPublicFolder);

			//Capture the div id of public folder
			String xpath="//span[@id='accordionNavigationView_drawer1_treeView_treeNode1_name']/parent::a/parent::td/parent::tr/parent::tbody/parent::table/parent::div";
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
			WebElement e1= driver.findElement(By.xpath(xpath));
			//String divClass=e1.getAttribute("class");
			publicfolderdivId=e1.getAttribute("id");




			//Prepare the parent and child divid and click
			parentdivid=publicfolderdivId;
			childdivId=parentdivid.substring(0, 4).trim()+"c"+parentdivid.substring(4, parentdivid.length()).trim();
			for(int i=0;i<listoffolder.length;i++){              
				System.out.println("Parent Div ID>> "+parentdivid);
				System.out.println("Child Div ID>> "+childdivId);
				System.out.println("Wanted to click on folder is>> "+listoffolder[i]);

				String xpathofFolder="//div[@class='ygtvchildren'][contains(@id,'"+childdivId+"')]//span[contains(text(),'"+listoffolder[i]+"')]";
				//String xpathofModel="//span[starts-with(text(),'"+listoffolder[i]+"']";
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpathofFolder)));
				WebElement folder= driver.findElement(By.xpath(xpathofFolder));
				System.out.println("Found xpath of folder>>"+xpathofFolder);
				CommonActions.doubleClick(driver, folder);


				String xpathofParentDiv="//span[contains(text(),'"+listoffolder[i]+"')]/parent::a/parent::td/parent::tr/parent::tbody/parent::table/parent::div";

				WebElement e5= driver.findElement(By.xpath(xpathofParentDiv));



				parentdivid=e5.getAttribute("id");
				childdivId=parentdivid.substring(0, 4).trim()+"c"+parentdivid.substring(4, parentdivid.length()).trim();





				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("ListingURE_footer__objectCountLabel")));
				//WebElement e3=driver.findElement(By.id("ListingURE_footer__objectCountLabel"));

				/*String[] strarray=e2.getText().split(" ");
				if (Integer.parseInt(strarray[1])<=0){
					break;
				}*/
			}
			retVal=true;
			this.TakeScreenshot(driver);
		}catch(Exception e){
			System.out.println(e.getMessage());
			retVal=false;
			this.TakeScreenshot(driver);
		}
		return retVal;
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

}
