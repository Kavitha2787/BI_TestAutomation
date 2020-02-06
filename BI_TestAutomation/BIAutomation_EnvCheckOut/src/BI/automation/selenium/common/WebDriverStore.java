package BI.automation.selenium.common;

import java.util.LinkedHashMap;
import org.openqa.selenium.WebDriver;

import BI.automation.common.StatusStore;

public class WebDriverStore {

	private static LinkedHashMap<String,WebDriver> webdriverstore = new LinkedHashMap<String,WebDriver>();
	
	public static void putWebDriverInStore(String key,WebDriver webdriver){
		webdriverstore.put(key, webdriver);
		}
	
	public static WebDriver getWebDriverFromStore(String key){
		return (WebDriver) webdriverstore.get(key);
	}
	
	public static void closeAllWebDriverInStore(){
		
		for (Object key: webdriverstore.keySet()) {
			if(webdriverstore.get(key)!=null){
				webdriverstore.get(key).close();
				webdriverstore.get(key).quit();
			}
				
		}
		webdriverstore.clear();
		
	}
	
	

	public static void main(String args[]){
		StatusStore.updateStatus("result","comments","exception","screenshotpath","filterkey");
		
		System.out.println(StatusStore.getStatus("prepareDefaultKey,prepareStatus,updateStatus,main").getKey());
		System.out.println(StatusStore.getStatus("prepareDefaultKey,prepareStatus,updateStatus,main").getDefaultkey());
		System.out.println(StatusStore.getStatus("prepareDefaultKey,prepareStatus,updateStatus,main").getEntryDateTime());
		System.out.println(StatusStore.getStatus("prepareDefaultKey,prepareStatus,updateStatus,main").getLastUpdatedDateTime());
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StatusStore.updateStatus("result","comments","exception","screenshotpath","filterkey");

		System.out.println(StatusStore.getStatus("prepareDefaultKey,prepareStatus,updateStatus,main").getKey());
		System.out.println(StatusStore.getStatus("prepareDefaultKey,prepareStatus,updateStatus,main").getDefaultkey());
		System.out.println(StatusStore.getStatus("prepareDefaultKey,prepareStatus,updateStatus,main").getEntryDateTime());
		System.out.println(StatusStore.getStatus("prepareDefaultKey,prepareStatus,updateStatus,main").getLastUpdatedDateTime());
		
	}

}
