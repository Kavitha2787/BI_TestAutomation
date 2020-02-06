package BI.automation.util;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtility {

	//Method to get current date/time in "yyyy-MM-dd HH:mm:ss" format
	public static String getCurrentTimeStamp(String dateTimeFormat) {
		//MM/dd/yyyy HH:mm:ss
		SimpleDateFormat sdfDate = new SimpleDateFormat(dateTimeFormat);
		Date now = new Date();
		String strDate = sdfDate.format(now);
		return strDate;
	}

	//Method to get current date - Day
	public static String getCurrentDateTime_Day(){
		Calendar now=Calendar.getInstance();
		String day=String.valueOf(now.get(Calendar.DATE));
		if(day.length()==1)
			day=0+day; 
		return day;			
	}

	//Method to get current date - Month
	public static String getCurrentDateTime_Month(){
		Calendar now=Calendar.getInstance();
		String month=String.valueOf(now.get(Calendar.MONTH)+1);
		if(month.length()==1)
			month=0+month; 
		return month;	
	}

	//Method to get current date - Year
	public static String getCurrentDateTime_Year(){
		String year="";
		Calendar now=Calendar.getInstance();
		year=String.valueOf(now.get(Calendar.YEAR));
		return year;	
	}

	//Method to get current date - Hour
	public static String getCurrentDateTime_Hour(){
		String hour="";
		Calendar now=Calendar.getInstance();
		hour=String.valueOf(now.get(Calendar.HOUR_OF_DAY));
		if(hour.length()==1)
			hour=0+hour;
		return hour;
	}

	//Method to get current date - Second
	public static String getCurrentDateTime_Second(){
		String sec="";
		Calendar now=Calendar.getInstance();
		sec=String.valueOf(now.get(Calendar.SECOND));
		if(sec.length()==1)
			sec=0+sec;
		return sec;
	}


	//Method to get current date - Minute
	public static String getCurrentDateTime_Minute(){
		String min="";
		Calendar now=Calendar.getInstance();
		min=String.valueOf(now.get(Calendar.MINUTE));
		if(min.length()==1)
			min=0+min;
		return min;
	}

	//Get the month from the supplied date
	public static String getMonth(String dateInMMDDCCYYFormat){
		String month="";
		if(dateInMMDDCCYYFormat.length()==10)
			month=dateInMMDDCCYYFormat.substring(0, 2);
		else
			month=dateInMMDDCCYYFormat.substring(0, 2);	 
		return month;
	}

	//Get the date from the supplied date
	public static String getDay(String dateInMMDDCCYYFormat){
		String day="";
		if(dateInMMDDCCYYFormat.length()==10)
			day=dateInMMDDCCYYFormat.substring(3, 5);
		else
			day=dateInMMDDCCYYFormat.substring(2, 4);	 
		return day;
	}

	//Get the year from the supplied date
	public static String getYear(String dateInMMDDCCYYFormat){
		String year="";
		if(dateInMMDDCCYYFormat.length()==10)
			year=dateInMMDDCCYYFormat.substring(6, 10);
		else
			year=dateInMMDDCCYYFormat.substring(4, 8);	 
		return year;
	}
	
	//Get the different of two dates in seconds
	public static long getDateDiffSeconds(String startDate, String endDate){
		
		//System.out.println(startDate+"  "+endDate);
		
		long elapsedSeconds=0;
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = format.parse(startDate);
			date2 = format.parse(endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		//milliseconds
		long different = date2.getTime()-date1.getTime();
		
		long secondsInMilli = 1000;
		long minutesInMilli = secondsInMilli * 60;
		long hoursInMilli = minutesInMilli * 60;
		long daysInMilli = hoursInMilli * 24;

		long elapsedDays = different / daysInMilli;
		different = different % daysInMilli;
		
		long elapsedHours = different / hoursInMilli;
		different = different % hoursInMilli;
		
		long elapsedMinutes = different / minutesInMilli;
		different = different % minutesInMilli;
		
		elapsedSeconds = different / secondsInMilli;
		//System.out.println(elapsedSeconds);
		return elapsedSeconds;
	}
	


}

