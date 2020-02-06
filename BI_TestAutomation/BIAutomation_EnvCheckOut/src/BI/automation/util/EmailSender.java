package BI.automation.util;
/**

 /**

 * This class is for sending e-mails to 
 * intended recipients
 *
 */
import java.util.Properties;




import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//Debasis
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.Multipart;







import BI.automation.common.BILogger;
import BI.automation.common.PropertiesStore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EmailSender {
	

	//with attachment public static  void sendMail(String sentFrom, String sentTo,String cc, String subject, String content, String foldername)  
	public static  void sendMail(String sentFrom, String sentTo,String cc, String subject, String msgbody,String filename){
		// *** CHANGED ***
		Session session;
		final String username = PropertiesStore.getSystemPropertyValue("emailuser");
		final String password = PropertiesStore.getSystemPropertyValue("emailpwd");
		final String mailServer=PropertiesStore.getSystemPropertyValue(PropertiesStore.getCurrentdomainname());
		final String bcc=PropertiesStore.getSystemPropertyValue("bcc");
		Properties props = new Properties();
		//props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "false");
		props.put("mail.smtp.host", mailServer);
		props.put("mail.smtp.port", "25");
		props.put("mail.smtp.auth.mechanisms","NTLM");

		//emailuser=
		//emailpwd=
		
		
		
		try {
			/*
			System.out.println("emailuser "+ username);
			System.out.println("password "+ password);
			System.out.println("mailserver "+ mailServer);
			*/
			if(username !=null && password !=null ){
				session = Session.getInstance(props,new EmailAuthenticator(username,password));
			}else{
				session = Session.getInstance(props);//,new EmailAuthenticator("",""));
			}
			
			Message message = new MimeMessage(session);            
			message.setFrom(new InternetAddress(sentFrom));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(sentTo));
			message.setRecipients(Message.RecipientType.CC,InternetAddress.parse(cc));
			message.setRecipients(Message.RecipientType.BCC,InternetAddress.parse(bcc));
			message.setSubject(subject);

			
			//Create message multi part
			Multipart multipart = new MimeMultipart();
			
			
			// creates message part - BODY
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(msgbody.toString(), "text/html");
			
			multipart.addBodyPart(messageBodyPart);
			
			// adds attachments
			if(!filename.isEmpty()){
				File attachment=new File(filename);
				if(!attachment.isDirectory()){
					MimeBodyPart attachPart = new MimeBodyPart();
					try {
						attachPart.attachFile(attachment);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					multipart.addBodyPart(attachPart);
				}
			}
			
			/*
			if(foldername!=null ||foldername.trim().length()<=0){
				MimeBodyPart attachPart = new MimeBodyPart();
				try {
					File f = new File(foldername);
					if(f.isDirectory()){
						//Zip the Folder
						ZipUtils appZip = new ZipUtils(foldername,foldername+"\\"+".zip");
						appZip.generateFileList(new File(foldername));
						appZip.zipIt(foldername+"\\"+"Screenshots.zip");
						attachPart.attachFile(new File(foldername+"\\"+"Screenshots.zip"));
					}
					else{
						if (f.isFile()) {
							attachPart.attachFile(f);
						}
					}
					multipart.addBodyPart(attachPart);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				System.out.println("Here1");
			}*/

			//System.out.println("Here2");
			//Finally Add all in multipart and send
			message.setContent(multipart);
			Transport.send(message);

			//System.out.println("Emailed Successful...");
			BILogger.writeInfoToLog("EmailSender","Emailed Successful...");



		} catch (MessagingException e) {
			//System.out.println("Emailed UnSuccessful...");
			BILogger.writeErrorToLog("EmailSender","Emailed UnSuccessful...");
			BILogger.writeErrorToLog("EmailSender",e);
			
		}

	}
	public static void main(String args[]) throws InterruptedException{

		/*PropertiesStore ps=new PropertiesStore();
		ps.loadSystemProperties("C:\\temp\\BIAutomation\\systemconfig.properties");
		ps.loadUserProperties("C:\\temp\\BIAutomation\\hiuiuserconfig.properties");
		EmailSender.sendMail(
				ps.getSystemPropertyValue("emailfrom"),
				ps.getUserPropertyValue("EmailTo"), 
				ps.getUserPropertyValue("EmailCc"),
				ps.getUserPropertyValue("EmailSubject"),
				"MessageBody",
				"");
		*/
		
		
	}
}


