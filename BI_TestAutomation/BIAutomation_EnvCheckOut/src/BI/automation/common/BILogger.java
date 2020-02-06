package BI.automation.common;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerRepository;

public class BILogger {

	public static Logger logger ;

	
	public static void writeInfoToLog(Class classname,Object message){
		logger= LogManager.getLogger(classname);
		logger.info(message);
	}
	public static void writeInfoToLog(String classname,Object message){
		logger= LogManager.getLogger(classname);
		logger.info(message);
	}

	public static void writeErrorToLog(Class classname,Object message){
		logger= LogManager.getLogger(classname);
		logger.error(message);
	}
	public static void writeErrorToLog(String classname,Object message){
		logger= LogManager.getLogger(classname);
		logger.error(message);
	}
	
	public static void writeWarnToLog(Class classname,Object message){
		logger= LogManager.getLogger(classname);
		logger.warn(message);
	}
	public static void writeWarnToLog(String classname,Object message){
		logger= LogManager.getLogger(classname);
		logger.warn(message);
	}
	

}
