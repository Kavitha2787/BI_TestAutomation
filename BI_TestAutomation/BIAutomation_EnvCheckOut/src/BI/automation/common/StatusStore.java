package BI.automation.common;

import java.util.LinkedHashMap;

import BI.automation.util.DateTimeUtility;


public class StatusStore {
	private static LinkedHashMap<String,Status> store = new LinkedHashMap<String,Status>();

	public static void updateStatus(){
		putInStore(prepareStatus());
	}
	public static void updateStatus(String key){
		putInStore(prepareStatus(key));
	}
	public static void updateStatus(String key,String result,String comments,String exception,String screenshotpath,String filterkey){
		putInStore(prepareStatus(key,result,comments,exception, screenshotpath,filterkey));
	}
	public static void updateStatus(String result,String comments,String exception,String screenshotpath,String filterkey){
		putInStore(prepareStatus(result,comments,exception, screenshotpath,filterkey));
	}
	
	
	private static Status prepareStatus(){
		Status status=new Status();
		status.setDefaultkey(prepareDefaultKey());
		status.setKey(prepareDefaultKey());
		return status;
	}
	private static Status prepareStatus(String key){
		Status status=new Status();
		status.setDefaultkey(prepareDefaultKey());
		status.setKey(key);
		return status;
	}
	private static Status prepareStatus(String key,String result,String comments,String exception,String screenshotpath,String filterkey){
		Status status=new Status();
		status.setDefaultkey(prepareDefaultKey());
		status.setKey(key);
		status.setResult(result);
		status.setComments(comments);
		status.setException(exception);
		status.setScreenshotPath(screenshotpath);
		status.setFilterkey(filterkey);
		return status;
	}

	private static Status prepareStatus(String result,String comments,String exception,String screenshotpath,String filterkey){
		Status status=new Status();
		status.setDefaultkey(prepareDefaultKey());
		status.setKey(prepareDefaultKey());
		status.setResult(result);
		status.setComments(comments);
		status.setException(exception);
		status.setScreenshotPath(screenshotpath);
		status.setFilterkey(filterkey);
		return status;
	}
	
	public static Status getStatus(String Key){
		return (Status) store.get(Key);
	}

	
	private static String prepareDefaultKey(){
		String returnString="";
		String methodname="";
		Exception e= new Exception();
		StackTraceElement ste[]= e.getStackTrace();

		for (int i=0;i<ste.length;i++){
			methodname=ste[i].getMethodName();
			if (methodname.equalsIgnoreCase("invoke0")){
				break;
			}
			returnString=appendWithDelimiter(returnString,methodname,",");
		}
		//System.out.println("DefaultKey:"+returnString);
		return returnString;
	}

	private static void putInStore(Status status){
		if (store.containsKey(status.getKey())){
				//Status object present in store
				Status statusfromstore=(Status) store.get(status.getKey());
				String entrydatetime=statusfromstore.getEntryDateTime();
				statusfromstore=status;
				statusfromstore.setEntryDateTime(entrydatetime);
				statusfromstore.setLastUpdatedDateTime(DateTimeUtility.getCurrentTimeStamp("yyyy-MM-dd HH:mm:ss"));
				
				statusfromstore.setElapsedTimeInSeconds(
						DateTimeUtility.getDateDiffSeconds(
							statusfromstore.getEntryDateTime(),
							statusfromstore.getLastUpdatedDateTime()));
				store.put(statusfromstore.getKey(), statusfromstore);
			}
		else{
				//Status object not present in store thus put the object only
				status.setEntryDateTime(DateTimeUtility.getCurrentTimeStamp("yyyy-MM-dd HH:mm:ss"));
				status.setLastUpdatedDateTime(DateTimeUtility.getCurrentTimeStamp("yyyy-MM-dd HH:mm:ss"));
				
				status.setElapsedTimeInSeconds(
						DateTimeUtility.getDateDiffSeconds(
								status.getEntryDateTime(),
								status.getLastUpdatedDateTime()));
	
				store.put(status.getKey(), status);
			}
	}
	

	public static Status getFromStatusStore(String key){
		return (Status) store.get(key);
	}
	public static Status[] getAllFromStatusStore(){
		Status retStatus[] = new Status[store.size()];
		int posn=0;
		for (String key: store.keySet()) {
			retStatus[posn]=(Status)store.get(key);
			posn++;
		}
		return retStatus;
	}
	
	public static int getCountOfStatusResult(String resuttype){
		int retVal=0;
		try{
			for (String key: store.keySet()) {
				if (((Status)store.get(key)).getResult().equalsIgnoreCase(resuttype)){
					retVal++;
				}
			}
		}catch(Exception e){
			retVal=0;
		}

		return retVal;
	}

	private static String appendWithDelimiter( String original, String addition, String delimiter ) {
		if ( original.equals( "" ) ) {
			return addition;
		} else {
			return original + delimiter + addition;
		}
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

