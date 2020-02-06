package BI.automation.selenium.ie.common;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class CommonActions {


	public static void click(WebDriver webdriver, WebElement webelement){

		//Mouse over and click on the element
		Actions actions = new Actions(webdriver);
		actions.moveToElement(webelement);
		actions.click(webelement);
		Action action=actions.build();
		action.perform();
		if(webelement.getText().isEmpty()){
			System.out.println("Under click action:>> "+webelement.getTagName());
		}else{
			System.out.println("Under click action:>> "+webelement.getText());
		}

		action=null;
		actions=null;

	}

	public static void doubleClick(WebDriver webdriver, WebElement webelement){

		//Mouse over and double click on the element
		Actions actions = new Actions(webdriver);
		actions.moveToElement(webelement);
		actions.doubleClick(webelement);
		Action action=actions.build();
		action.perform();
		if(webelement.getText().isEmpty()){
			System.out.println("Under doubleClick action:>> "+webelement.getTagName());
		}else{
			System.out.println("Under doubleClick action:>> "+webelement.getText());
		}
		action=null;
		actions=null;
		webelement=null;
	}

	public static void switchToFrame(WebDriver webdriver, String framename){
		webdriver.switchTo().defaultContent();
		webdriver.switchTo().frame(framename);
		System.out.println("Under switchToFrame action for frame name:>> "+framename);
	}

	public static void switchToLastFrame(WebDriver webdriver,String parentframe){
		//webdriver.switchTo().defaultContent();
		List<WebElement> ele = webdriver.findElements(By.tagName("iframe"));
		System.out.println("switchToLastFrame-TotalFramePresent- "+Integer.toString(ele.size()));
		webdriver.switchTo().frame(ele.size()-1);
		System.out.println("switchToLastFrame-SwitchedToLast- "+Integer.toString(ele.size()));

	}
	public static void switchToLastFrame(WebDriver webdriver){
		webdriver.switchTo().defaultContent();
		List<WebElement> ele = webdriver.findElements(By.tagName("iframe"));
		System.out.println("switchToLastFrame-TotalFramePresent- "+Integer.toString(ele.size()));
		webdriver.switchTo().frame(ele.size()-1);
		System.out.println("switchToLastFrame-SwitchedToLast- "+Integer.toString(ele.size()));

	}

	public static void tryClicking(WebElement element,int n){
		for(int i=0;i<=n;i++){
			try{
				System.out.println("tryClicking Attempt>>"+Integer.toString(i));
				element.click();	
			}catch(NoSuchElementException e){
				System.out.println("tryClicking Attempt Exception>>"+Integer.toString(i));
				break;
			}

		}
	}

	public static WebElement waitForElementByXpath(WebDriver driver, String xpath,int howmanytimes,int intervalinseconds){ 
		int counter=0;
		WebElement element=null;
		while (counter<=howmanytimes){
			System.out.println("I m from commonaction-waitForElementByXpath"+Integer.toString(counter));
			try{
				element=driver.findElement(By.xpath(xpath));
			}catch(NoSuchElementException nse){
				
			}
			
			try {
				TimeUnit.SECONDS.sleep(intervalinseconds);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(element!=null){
				System.out.println("waitForElementByXpath-Eelement found");
				break;
			}
			counter++;
		}
		if(element==null){
			System.out.println("waitForElementByXpath-Eelement not found");
		}
		return element;
	}


	public static WebElement waitForElementById(WebDriver driver, String id,int howmanytimes,int intervalinseconds){ 
		int counter=0;
		WebElement element=null;
		while (counter<=howmanytimes){
			System.out.println("I m from commonaction-waitForElementById"+Integer.toString(counter));
			try{
				element=driver.findElement(By.id(id));
			}catch(NoSuchElementException nse){
				
			}
			
			try {
				TimeUnit.SECONDS.sleep(intervalinseconds);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(element!=null){
				System.out.println("waitForElementById-Eelement found");
				break;
			}
			counter++;
		}
		if(element==null){
			System.out.println("waitForElementById-Eelement not found");
		}
		return element;
	}


}
