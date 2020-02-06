package BI.automation.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ReadFromFile {

	String filePath;
	public ReadFromFile(String filePath){
		this.filePath=filePath;
	}
	public ArrayList getListOfLines(){
		List<String> linesArray = new ArrayList<String>();
		try {
    		File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                linesArray.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return (ArrayList) linesArray;
	}
	
	public static void main(String args[]){
		ReadFromFile rff=new ReadFromFile("D:\\BOReports.txt");
		List<String> linesArray=rff.getListOfLines();
		System.out.println(linesArray.size());
		for(int i=0;i<linesArray.size();i++){
			System.out.println(linesArray.get(i).toString());
		}
			
		
	}
	
    
}



