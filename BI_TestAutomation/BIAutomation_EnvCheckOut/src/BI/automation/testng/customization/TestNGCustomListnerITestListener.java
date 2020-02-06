package BI.automation.testng.customization;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import BI.automation.common.BILogger;
import BI.automation.common.PropertiesStore;
import BI.automation.util.DateTimeUtility;

import org.apache.commons.io.FilenameUtils;




public class TestNGCustomListnerITestListener implements ITestListener  {

	@Override
	public void onStart(ITestContext iTestContext) {
		BILogger.writeInfoToLog(this.getClass(), "---- TEST STARTED----");
		BILogger.writeInfoToLog(this.getClass(), "Test>>"+iTestContext.getCurrentXmlTest().getName());
	}

	@Override
	public void onFinish(ITestContext arg0) {
		BILogger.writeInfoToLog(this.getClass(), "Test>>"+arg0.getCurrentXmlTest().getName());
		BILogger.writeInfoToLog(this.getClass(), "---- TEST FINISHED----");
	}



	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		Throwable t=arg0.getThrowable();
		PrintWriter pw;
		//System.out.println(t.getMessage());
		try {
			//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			//Date date = new Date();
			//String timeNow = dateFormat.format(date)
			pw= new PrintWriter(new FileWriter("TestLog.log", true));
			pw.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.ms").format(new Date()).toString()+"|");
			t.printStackTrace(pw);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub

	}




}
