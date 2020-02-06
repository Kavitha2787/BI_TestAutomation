package BI.automation.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ReadPropertyValue 
{
	Properties prop;
	public ReadPropertyValue(String propertyfile)
	{
		try
		{
			prop = new Properties();
		
			InputStream inputStream = new FileInputStream(propertyfile);
			prop.load(inputStream);
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}

	}
	
	public String  getPropValue(String propertyname)  
	{
					
		String result = "";
		result=prop.getProperty(propertyname);
		System.out.println("propertyname>> "+propertyname+" value>> "+result);
		
		
		return result;
	}
	
	public String[] getAllPropValue(){
		String retString[]=new String[prop.size()];
		int posn=0;
		for (Object key: prop.keySet()) {
			retString[posn]=key.toString()+"|"+prop.getProperty((String) key);
			posn++;
		}
		return retString;
	}

	/*public static void main(String[] args)
	{
		ReadPropertyValue gpv=new ReadPropertyValue("C:/JavaPrograms/DSSWebPublisher1/properties/config.properties");
		
		for (int i=0;i<gpv.getAllPropValue().length;i++){
			System.out.println(gpv.getAllPropValue()[i]);
		}
	}*/

}
