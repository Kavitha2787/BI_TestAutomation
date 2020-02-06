package BI.automation.selenium.ie.emt;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import BI.automation.common.CommonPageClass;
import BI.automation.common.PropertiesStore;
import BI.automation.common.StatusStore;

import BI.automation.selenium.ie.wpb.WPBHomePage;
import BI.automation.selenium.ie.common.OpenBrowser;
import BI.automation.selenium.ie.hiui.HIUIHomePage;

public class EMTHomePage extends CommonPageClass {
	WebDriver driver;
	WPBHomePage wpbhomepage;
	WebDriverWait wait;
	//int waittime =this.getWaitTime();
	int waittime =Integer.parseInt(PropertiesStore.getSystemPropertyValue("EMTHomePage_WaitTime"));

	public EMTHomePage(HIUIHomePage homepage){
		driver=homepage.getDriver();
		wait=new WebDriverWait(driver,waittime);
	}

	public EMTHomePage(String browsertype,String browserdriver,String url){
		//Open the url
		OpenTheEmeasuretoolURL(browsertype, browserdriver,url);
	}
	private void OpenTheEmeasuretoolURL(String browsertype,String browserdriver,String url){
		StatusStore.updateStatus("OpenTheURL");

		try{
		OpenBrowser openbrowser= new OpenBrowser(browsertype,browserdriver);
		openbrowser.openURL(url);
		driver=openbrowser.getWebDriver();
		wait=new WebDriverWait(driver,waittime);
		this.TakeScreenshot(driver);
		StatusStore.updateStatus("OpenTheURL", "PASSED", "AS EXPECTED", "", "","");
	}
	catch(Exception e){

		this.TakeScreenshot(driver);

		StatusStore.updateStatus("OpenTheURL", "FAILED", "", e.getMessage(), "","");
	}
	}
		
	
	
	public void directEmeasureToolLogin(String userid,String password)
	{

			  //Wait untill the username field is visible
		StatusStore.updateStatus("directEmeasureToolLogin");

		try{
			  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("username")));
			  
			  WebElement userNameInputField=driver.findElement(By.id("username"));
			  WebElement passwordInputField=driver.findElement(By.id("password"));
			
			  
			  userNameInputField.sendKeys(userid);
			  passwordInputField.sendKeys(password);
			  driver.findElement(By.className("loginButton")).click();
				this.TakeScreenshot(driver);
				StatusStore.updateStatus("directEmeasureToolLogin", "PASSED", "AS EXPECTED", "", "","");
			}
			catch(Exception e){

				this.TakeScreenshot(driver);

				StatusStore.updateStatus("directEmeasureToolLogin", "FAILED", "", e.getMessage(), "","");
			}
			  
	}
/*	
	
	
	private void switchwindow()
	{
		for(String winHandle : driver.getWindowHandles()) {
			  driver.switchTo().window(winHandle);
		  }
	}
	*/
public void clickonSetupTab()
{
	
	
	//wpbhomepage.getNodeLocator();
	StatusStore.updateStatus("clickonSetupTab");

	try{
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='current']/div/a")));
	//driver.findElement(By.xpath("//li[@id='current']/div/a[text()='Setup']")).click();
	driver.findElement(By.xpath(".//*[@id='current']/div/a")).click();
	this.TakeScreenshot(driver);
	StatusStore.updateStatus("clickonSetupTab", "PASSED", "AS EXPECTED", "", "","");
}
catch(Exception e){

	this.TakeScreenshot(driver);

	StatusStore.updateStatus("clickonSetupTab", "FAILED", "", e.getMessage(), "","");
}
}

public void checkAllSetupTabs(){
	
	checkDataSourceGroups();
	checkDBConenections();
	checkQueries();
	checkFields();
	checkQueryparameters();
	checkQueryvariables();
	
}
public void checkDataSourceGroups(){
	StatusStore.updateStatus("checkDataSourceGroups");

	try{
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='datasourceTabsUList']/li[1]/a")));
	driver.findElement(By.xpath("//ul[@id='datasourceTabsUList']/li[1]/a")).click();
	this.TakeScreenshot(driver);
	StatusStore.updateStatus("checkDataSourceGroups", "PASSED", "AS EXPECTED", "", "","");
}
catch(Exception e){

	this.TakeScreenshot(driver);

	StatusStore.updateStatus("checkDataSourceGroups", "FAILED", "", e.getMessage(), "","");
}
}

public void checkDBConenections(){
	StatusStore.updateStatus("checkDBConenections");

	try{
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='datasourceTabsUList']/li[2]/a")));
	driver.findElement(By.xpath("//ul[@id='datasourceTabsUList']/li[2]/a")).click();
	this.TakeScreenshot(driver);
	StatusStore.updateStatus("checkDBConenections", "PASSED", "AS EXPECTED", "", "","");
}
catch(Exception e){

	this.TakeScreenshot(driver);

	StatusStore.updateStatus("checkDBConenections", "FAILED", "", e.getMessage(), "","");
}

}
public void checkQueries(){
	StatusStore.updateStatus("checkQueries");
	try{
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='datasourceTabsUList']/li[3]/a")));
	driver.findElement(By.xpath("//ul[@id='datasourceTabsUList']/li[3]/a")).click();
	this.TakeScreenshot(driver);
	StatusStore.updateStatus("checkQueries", "PASSED", "AS EXPECTED", "", "","");
}
catch(Exception e){

	this.TakeScreenshot(driver);

	StatusStore.updateStatus("checkQueries", "FAILED", "", e.getMessage(), "","");
}
	
}
public void checkFields(){
	StatusStore.updateStatus("checkFields");
	try{
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='datasourceTabsUList']/li[4]/a")));
	driver.findElement(By.xpath("//ul[@id='datasourceTabsUList']/li[4]/a")).click();
	this.TakeScreenshot(driver);
	StatusStore.updateStatus("checkFields", "PASSED", "AS EXPECTED", "", "","");
}
catch(Exception e){

	this.TakeScreenshot(driver);

	StatusStore.updateStatus("checkFields", "FAILED", "", e.getMessage(), "","");
}
}
public void checkQueryparameters(){
	StatusStore.updateStatus("checkQueryparameters");
	try{
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='datasourceTabsUList']/li[5]/a")));
	driver.findElement(By.xpath(".//*[@id='datasourceTabsUList']/li[5]/a")).click();
	this.TakeScreenshot(driver);
	StatusStore.updateStatus("checkQueryparameters", "PASSED", "AS EXPECTED", "", "","");
}
catch(Exception e){

	this.TakeScreenshot(driver);

	StatusStore.updateStatus("checkQueryparameters", "FAILED", "", e.getMessage(), "","");
}
}
public void checkQueryvariables(){
	StatusStore.updateStatus("checkQueryvariables");
	try{
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='datasourceTabsUList']/li[6]/a")));
	driver.findElement(By.xpath(".//*[@id='datasourceTabsUList']/li[6]/a")).click();
	this.TakeScreenshot(driver);
	StatusStore.updateStatus("checkQueryvariables", "PASSED", "AS EXPECTED", "", "","");
}
catch(Exception e){

	this.TakeScreenshot(driver);

	StatusStore.updateStatus("checkQueryvariables", "FAILED", "", e.getMessage(), "","");
}
}

public void clickonMapTab()
{
	StatusStore.updateStatus("clickonMapTab");
	try{

  
	
	//System.out.println(">>" + driver.getCurrentUrl());
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()=' Maps ']")));
	//driver.findElement(By.xpath("//li[@id='current']/div/a[text()='Setup']")).click();
	driver.findElement(By.xpath("//a[text()=' Maps ']")).click();
	this.TakeScreenshot(driver);
	StatusStore.updateStatus("clickonMapTab", "PASSED", "AS EXPECTED", "", "","");
}
catch(Exception e){

	this.TakeScreenshot(driver);

	StatusStore.updateStatus("clickonMapTab", "FAILED", "", e.getMessage(), "","");
}
}
	public void checkAllMapTabs()
	{
		checkCodeMaps();
		checkObservationMaps();
	}

	public void checkCodeMaps(){
		StatusStore.updateStatus("checkCodeMaps");
		try{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='codeObservationTabsUList']/li[1]/a")));
		driver.findElement(By.xpath(".//*[@id='codeObservationTabsUList']/li[1]/a")).click();
		this.TakeScreenshot(driver);
		StatusStore.updateStatus("checkCodeMaps", "PASSED", "AS EXPECTED", "", "","");
	}
	catch(Exception e){

		this.TakeScreenshot(driver);

		StatusStore.updateStatus("checkCodeMaps", "FAILED", "", e.getMessage(), "","");
	}
	}
	public void checkObservationMaps(){
		StatusStore.updateStatus("checkObservationMaps");
		try{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='codeObservationTabsUList']/li[2]/a")));
		driver.findElement(By.xpath(".//*[@id='codeObservationTabsUList']/li[2]/a")).click();
		this.TakeScreenshot(driver);
		StatusStore.updateStatus("checkObservationMaps", "PASSED", "AS EXPECTED", "", "","");
	}
	catch(Exception e){

		this.TakeScreenshot(driver);

		StatusStore.updateStatus("checkObservationMaps", "FAILED", "", e.getMessage(), "","");
	}
	}

	public void clickonToolsTab()
	{
		StatusStore.updateStatus("clickonToolsTab");
		try{
		
	  
		
		//System.out.println(">>" + driver.getCurrentUrl());
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()=' Tools ']")));
		//driver.findElement(By.xpath("//li[@id='current']/div/a[text()='Setup']")).click();
		driver.findElement(By.xpath("//a[text()=' Tools ']")).click();
		this.TakeScreenshot(driver);
		StatusStore.updateStatus("clickonToolsTab", "PASSED", "AS EXPECTED", "", "","");
	}
	catch(Exception e){

		this.TakeScreenshot(driver);

		StatusStore.updateStatus("clickonToolsTab", "FAILED", "", e.getMessage(), "","");
	}
		
	}
		public void checkAllTools(){
			checkQDMRepository();
			checkEMTStatistics();
		}
	public void checkQDMRepository(){
		
		StatusStore.updateStatus("checkQDMRepository");
		try{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='toolstab']/li[1]/a")));
		driver.findElement(By.xpath(".//*[@id='toolstab']/li[1]/a")).click();
		this.TakeScreenshot(driver);
		StatusStore.updateStatus("checkQDMRepository", "PASSED", "AS EXPECTED", "", "","");
	}
	catch(Exception e){

		this.TakeScreenshot(driver);

		StatusStore.updateStatus("checkQDMRepository", "FAILED", "", e.getMessage(), "","");
	}
	}
	public void checkEMTStatistics(){
		
		StatusStore.updateStatus("checkEMTStatistics");
		try{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='toolstab']/li[2]/a")));
		driver.findElement(By.xpath(".//*[@id='toolstab']/li[2]/a")).click();
		this.TakeScreenshot(driver);
		StatusStore.updateStatus("checkEMTStatistics", "PASSED", "AS EXPECTED", "", "","");
	}
	catch(Exception e){

		this.TakeScreenshot(driver);

		StatusStore.updateStatus("checkEMTStatistics", "FAILED", "", e.getMessage(), "","");
	}
	}


	public void clickonAdministrationTab()
	{
		StatusStore.updateStatus("clickonAdministrationTab");
		try{

		
	  
		
		//System.out.println(">>" + driver.getCurrentUrl());
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()=' Administration ']")));
		//driver.findElement(By.xpath("//li[@id='current']/div/a[text()='Setup']")).click();
		driver.findElement(By.xpath("//a[text()=' Administration ']")).click();
		this.TakeScreenshot(driver);
		StatusStore.updateStatus("clickonAdministrationTab", "PASSED", "AS EXPECTED", "", "","");
	}
	catch(Exception e){

		this.TakeScreenshot(driver);

		StatusStore.updateStatus("clickonAdministrationTab", "FAILED", "", e.getMessage(), "","");
	}
		
	}
		public void checkAllAdministration(){
			checkConfigurationProperties();
			checkActivityLog();
			checkImportExportMapping();
			checkImportExportMeasures();
			checkImportExportQRDA();
			checkImportExportValueSets();
		}
	public void checkConfigurationProperties(){
		StatusStore.updateStatus("checkConfigurationProperties");
		try{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='admin_subModNavbar']/li[1]/a")));
		driver.findElement(By.xpath(".//*[@id='admin_subModNavbar']/li[1]/a")).click();
		this.TakeScreenshot(driver);
		StatusStore.updateStatus("checkConfigurationProperties", "PASSED", "AS EXPECTED", "", "","");
	}
	catch(Exception e){

		this.TakeScreenshot(driver);

		StatusStore.updateStatus("checkConfigurationProperties", "FAILED", "", e.getMessage(), "","");
	}
	}
	public void checkActivityLog(){
		StatusStore.updateStatus("checkActivityLog");
		try{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='admin_subModNavbar']/li[2]/a")));
		driver.findElement(By.xpath(".//*[@id='admin_subModNavbar']/li[2]/a")).click();
		this.TakeScreenshot(driver);
		StatusStore.updateStatus("checkActivityLog", "PASSED", "AS EXPECTED", "", "","");
	}
	catch(Exception e){

		this.TakeScreenshot(driver);

		StatusStore.updateStatus("checkActivityLog", "FAILED", "", e.getMessage(), "","");
	}
	}
	public void checkImportExportMapping(){
		StatusStore.updateStatus("checkImportExportMapping");
		try{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='admin_subModNavbar']/li[3]/a")));
		driver.findElement(By.xpath(".//*[@id='admin_subModNavbar']/li[3]/a")).click();
		this.TakeScreenshot(driver);
		StatusStore.updateStatus("checkImportExportMapping", "PASSED", "AS EXPECTED", "", "","");
	}
	catch(Exception e){

		this.TakeScreenshot(driver);

		StatusStore.updateStatus("checkImportExportMapping", "FAILED", "", e.getMessage(), "","");
	}
	}
	public void checkImportExportMeasures(){
		StatusStore.updateStatus("checkImportExportMeasures");
		try{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='admin_subModNavbar']/li[4]/a")));
		driver.findElement(By.xpath(".//*[@id='admin_subModNavbar']/li[4]/a")).click();
		this.TakeScreenshot(driver);
		StatusStore.updateStatus("checkImportExportMeasures", "PASSED", "AS EXPECTED", "", "","");
	}
	catch(Exception e){

		this.TakeScreenshot(driver);

		StatusStore.updateStatus("checkImportExportMeasures", "FAILED", "", e.getMessage(), "","");
	}
	}
	public void checkImportExportQRDA(){
		StatusStore.updateStatus("checkImportExportQRDA");
		try{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='admin_subModNavbar']/li[5]/a")));
		driver.findElement(By.xpath(".//*[@id='admin_subModNavbar']/li[5]/a")).click();
		this.TakeScreenshot(driver);
		StatusStore.updateStatus("checkImportExportQRDA", "PASSED", "AS EXPECTED", "", "","");
	}
	catch(Exception e){

		this.TakeScreenshot(driver);

		StatusStore.updateStatus("checkImportExportQRDA", "FAILED", "", e.getMessage(), "","");
	}
	}
	public void checkImportExportValueSets(){
		StatusStore.updateStatus("checkImportExportValueSets");
		try{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='admin_subModNavbar']/li[6]/a")));
		driver.findElement(By.xpath(".//*[@id='admin_subModNavbar']/li[6]/a")).click();
		this.TakeScreenshot(driver);
		StatusStore.updateStatus("checkImportExportValueSets", "PASSED", "AS EXPECTED", "", "","");
	}
	catch(Exception e){

		this.TakeScreenshot(driver);

		StatusStore.updateStatus("checkImportExportValueSets", "FAILED", "", e.getMessage(), "","");
	}
	}
	

	public void clickonSubmittersTab()
	{
		StatusStore.updateStatus("clickonSubmittersTab");
	
	try{
		
		
	  
		
		//System.out.println(">>" + driver.getCurrentUrl());
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()=' Submitters ']")));
		//driver.findElement(By.xpath("//li[@id='current']/div/a[text()='Setup']")).click();
		driver.findElement(By.xpath("//a[text()=' Submitters ']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='submittersViewHeader']")));
		driver.findElement(By.xpath(".//*[@id='submittersViewHeader']")).click();
		this.TakeScreenshot(driver);
		StatusStore.updateStatus("clickonSubmittersTab", "PASSED", "AS EXPECTED", "", "","");
	}
	catch(Exception e){

		this.TakeScreenshot(driver);

		StatusStore.updateStatus("clickonSubmittersTab", "FAILED", "", e.getMessage(), "","");
	}
	}
	public void closeEMT(){
		StatusStore.updateStatus("closeEMT");
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logoutButton"))).click();
			this.TakeScreenshot(driver);
			
			StatusStore.updateStatus("closeEMT", "PASSED", "AS EXPECTED", "", "","");
			driver.close();
			
		}catch(Exception e){
			StatusStore.updateStatus("closeEMT", "FAILED", "", e.getMessage(), "","");
			this.TakeScreenshot(driver);
		}
		
	}
}

