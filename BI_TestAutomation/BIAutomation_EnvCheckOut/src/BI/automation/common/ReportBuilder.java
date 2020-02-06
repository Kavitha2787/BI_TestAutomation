package BI.automation.common;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import BI.automation.util.DateTimeUtility;
import BI.automation.util.ReadPropertyValue;

public class ReportBuilder {



	@SuppressWarnings("resource")
	public static String readFromFile(String filepath){
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(filepath));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		StringBuffer stringBuffer = new StringBuffer();
		String line = null;

		try {
			while((line =bufferedReader.readLine())!=null){

				stringBuffer.append(line).append("\n");
			}
			bufferedReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}

		return stringBuffer.toString();
	}


	public static String getReportContentInHtml(){
		Status statusAry[]= StatusStore.getAllFromStatusStore();
		//PropertiesStore.loadSystemProperties(System.getProperty("user.dir")+"//"+"system//demo5.html");
		String reporttemplate=System.getProperty("user.dir")+"\\"+"..\\"+PropertiesStore.getSystemPropertyValue("Reportttemplatepath");
		String htmlcontent=readFromFile(reporttemplate);
		String tmpStr="";
		
		String tmptableheader[]= PropertiesStore.getSystemPropertyValue("TestReportStatusTableHeader").split(",");
		for(int i=0;i<tmptableheader.length;i++){
			tmpStr=tmpStr+"<td>"+tmptableheader[i]+"</td>";
		}

		tmpStr=tmpStr+"</tr>";

		for(int i=0;i<statusAry.length;i++){
			Status tmpSts=(Status) statusAry[i];
			//key=tmpSts.getKey().split(",")[tmpSts.getKey().split(",").length-1];
			
			tmpStr=tmpStr+"<tr bgcolor=\"#ffffff\">";
			tmpStr=tmpStr+
					//"<td>"+tmpSts.getKey()+"</td>"+
					"<td>"+tmpSts.getKey().split(",")[tmpSts.getKey().split(",").length-1]+"</td>"+
					"<td>"+tmpSts.getEntryDateTime()+"</td>"+
					"<td>"+tmpSts.getLastUpdatedDateTime()+"</td>"+
					"<td>"+String.valueOf(tmpSts.getElapsedTimeInSeconds())+"</td>";
			if(tmpSts.getResult()=="PASSED"){
				
				tmpStr=tmpStr+"<font color=#006600>"+"<td>"+tmpSts.getResult()+"</td>"+"</font>";
			}
			if(tmpSts.getResult()=="FAILED"){
				tmpStr=tmpStr+"<font color=#FF0000>"+"<td>"+tmpSts.getResult()+"</td>"+"</font>";
			}
					 
			tmpStr=tmpStr+"<td>"+tmpSts.getComments()+"</td>"+
					"<td>"+tmpSts.getException()+"</td>";
			tmpStr=tmpStr+"</tr>";
			
			//<tr style=\"border: 1px solid black;\">

		}
		
		String path=System.getProperty("user.dir");
		String agentropertyilename=PropertiesStore.getUserPropertyValue("HHRR")+"_"+
				PropertiesStore.getUserPropertyValue("DataMode")+"_"+	
				PropertiesStore.getUserPropertyValue("DataModeNumber")+".properties";
		if(path.indexOf("serverfolders")>0){
			path=path.substring(0,path.indexOf("serverfolders"));
			path=path+"serverfolders"+"\\"+"communicationreceived"+"\\"+agentropertyilename;
			System.out.println("ReportBuilder-getReportContentInHtml-AgentCommunicated"+path);
			if(new File(path).exists()){
				ReadPropertyValue rpv=new ReadPropertyValue(path);
				String reportfromagent=rpv.getPropValue("TestResultInHtmlTable");
				
				if(reportfromagent!=null && !reportfromagent.isEmpty()){
					String hhrrbuilddetlfromagent="<tr>"+
												"<td>"+"HHRR:"+rpv.getPropValue("HHRR")+"</td>"+
												"<td>"+"DataMode:"+rpv.getPropValue("DataMode")+"</td>"+
												"<td>"+"DataModeNumber:"+rpv.getPropValue("DataModeNumber")+"</td>"+
												"<td>"+"Version:"+rpv.getPropValue("Version")+"</td>"+
												"<td>"+"Build:"+rpv.getPropValue("INSTALLBuildLevel")+"</td>"+
							"</tr>";
					tmpStr=tmpStr+hhrrbuilddetlfromagent+reportfromagent;
				}
			}
		}
		
		htmlcontent=htmlcontent.replace("$tablerows", tmpStr);
		String testdescription=PropertiesStore.getUserPropertyValue("TestReportStatusDescription")+"("+"<b>"+"<font color=#006600>"+" PASSED:"+"</font>"+"</b>"+StatusStore.getCountOfStatusResult("PASSED")+" "+"<b>"+"<font color=#FF0000>"+" FAILED:"+"</font>"+"</b>"+StatusStore.getCountOfStatusResult("FAILED")+")";
		htmlcontent=htmlcontent.replace("$testdescription:",testdescription );
		htmlcontent=htmlcontent.replace("$date", DateTimeUtility.getCurrentTimeStamp("yyyy-MM-dd HH:mm:ss"));
		//htmlcontent=htmlcontent.replace("$Release", PropertiesStore.getUserPropertyValue("TestReportStatusRelease"));
		//htmlcontent=htmlcontent.replace("$build", PropertiesStore.getUserPropertyValue("TestReportStatusBuild"));
		htmlcontent=htmlcontent.replace("$browser", PropertiesStore.getBrowserProperty());
		htmlcontent=htmlcontent.replace("$URL", PropertiesStore.getUserPropertyValueofURL());
		htmlcontent=htmlcontent.replace("$currentosdetails", "<b>"+"Current OS: "+"</b>" +PropertiesStore.getOsname()+"<b>"+" Build: "+"</b>"+PropertiesStore.getOsbuild());
		htmlcontent=htmlcontent.replace("$currentuserandmachine", "<b>"+"Current System User: "+"</b>" +PropertiesStore.getCurrentuser()+"<b>"+" Current System Host: "+"</b>"+PropertiesStore.getCurrentmachine());
		htmlcontent=htmlcontent.replace("$USER", PropertiesStore.getUserPropertyValue("userid"));
		
		keepReportAsHtml(htmlcontent);
		return htmlcontent;
	}

	private static void keepReportAsHtml(String reportcontent){
		try{
			File f=new File(PropertiesStore.getCurrentScreenshotFolder()+"\\"+"TestReport.html");
			f.createNewFile();
			FileWriter fw=new FileWriter(f);
			fw.write(reportcontent);
			fw.flush();
			fw.close();
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	/*public static void main(String args[]){

		StatusStore.updateStatus("Row1", "result", "comme", "exception", "screenshotpath", "ilterkey");
		StatusStore.updateStatus("Row2", "result", "comme", "exception", "screenshotpath", "ilterkey");
		StatusStore.updateStatus("Row3", "result", "comme", "exception", "screenshotpath", "ilterkey");

		BIReportBuilder br=new BIReportBuilder();
		EmailSender.sendMail("mail.asp.cernerworks.com", 
				"environcheckoutbi@cerner.com", 
				"suman.kundu@cerner.com","",
				"Web Publisher Checkout Test",br.getReportContentInHtml(),"");

	}*/

}
