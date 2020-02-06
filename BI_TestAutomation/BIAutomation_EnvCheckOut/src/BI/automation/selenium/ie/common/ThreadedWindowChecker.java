package BI.automation.selenium.ie.common;

import org.openqa.selenium.WebDriver;

public class ThreadedWindowChecker extends Thread{

	WebDriver driver;
	
	public ThreadedWindowChecker(WebDriver driver){
		driver=driver;
	}
	
	public void run(){
        while(true){
            try{
            	//synchronized(driver){
            		clickToAvoidCertificateError();
            	//}
                Thread.sleep(3000);
            } catch (InterruptedException iex) {
                System.out.println("Exception in thread: "+iex.getMessage());
            }
        }
    }
	private void clickToAvoidCertificateError(){
		if (driver.getTitle().contains("Certificate Error"))
		{
			//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("overridelink")));
			//driver.findElement(By.partialLinkText("Continue to this website (not recommended).")).click();
			driver.navigate().to("javascript:document.getElementById('overridelink').click()"); //Java script to click the link
			
			//driver.findElement(By.id("overridelink")).click();
		}
	}
	
	public static void main(String args[]){
		System.out.println(System.getProperty("user.dir")+"\\"+"drivers"+"\\");
	}
}