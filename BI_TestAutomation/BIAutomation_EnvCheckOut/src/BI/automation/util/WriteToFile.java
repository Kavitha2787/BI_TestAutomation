package BI.automation.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {
	
	public static void write(String filename,String datatowrite){
		try{
    		File logfile = new File(filename);
        	// if file doesnt exists, then create it
			if (!logfile.exists()) {
				logfile.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(logfile.getAbsoluteFile(), true));
			bw.write(datatowrite);
    		bw.write("\n");
    		if (bw != null)
				bw.close();
		}catch(IOException e){
    		System.out.println(e.getMessage());
    	}
	}
	
	public static void writeAsLog(String filename,String datatowrite){
		try{
    		File logfile = new File(filename);
        	// if file doesnt exists, then create it
			if (!logfile.exists()) {
				logfile.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(logfile.getAbsoluteFile(), true));
			bw.write(DateTimeUtility.getCurrentTimeStamp("yyyy-MM-dd HH:mm:ss.sss")+"-"+datatowrite);
    		bw.write("\n");
    		if (bw != null)
				bw.close();
		}catch(IOException e){
    		System.out.println(e.getMessage());
    	}
	}

}
