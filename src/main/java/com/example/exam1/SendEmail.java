package com.example.exam1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class SendEmail {
	/** 
     * 发送带附件的邮件 
     * @throws UnsupportedEncodingException 
     */
	 private MimeMessage message;
	 private Session session;  
	 private Transport transport;  
	 private String mailHost = "";  
	 private String sender_username = "";  
	 private String sender_password = "";  
	 private String contentPart_Type = "";
	 private Properties properties = new Properties();  

	 public SendEmail() {  
		 
		try {
			FileInputStream  is = new FileInputStream("F:\\dataSources\\DataSourcesProject\\src\\main\\resources\\application.properties");
	         properties.load(is);  
	         this.mailHost = properties.getProperty("spring.mail.host");  
	         this.sender_username = properties.getProperty("spring.mail.username");  
	         this.sender_password = properties.getProperty("spring.mail.password");  
	         this.contentPart_Type = properties.getProperty("mail.contentPart.type"); 
	         
	         session = Session.getInstance(properties);  
	        // session.setDebug(true); //开启后有调试信息,发布时关闭  
	         message = new MimeMessage(session);  
	         
	         
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	 }
	
	 public void doSendHtmlEmail(String subject, String sendHtml,  
		        String  toUser, String ccUser, String bccUser, List<File> attaches){  
		try {
			// 发件人  
	        InternetAddress from = new InternetAddress(sender_username);  
	        message.setFrom(from);
		
	         // 设置多个收件人地址  
	         if(null != toUser && !toUser.isEmpty()){  
	          @SuppressWarnings("static-access")  
	          InternetAddress[] internetAddressTo = new InternetAddress().parse(toUser);  
	          message.setRecipients(Message.RecipientType.TO, internetAddressTo);  
	         } 
	         
	         // 设置多个抄送地址  
	           if(null != ccUser && !ccUser.isEmpty()){  
	            @SuppressWarnings("static-access")  
	            InternetAddress[] internetAddressCC = new InternetAddress().parse(ccUser);  
	            message.setRecipients(Message.RecipientType.CC, internetAddressCC);  
	           }  
	           // 设置多个密送地址  
	           if(null != bccUser && !bccUser.isEmpty()){  
	            @SuppressWarnings("static-access")  
	            InternetAddress[] internetAddressBCC = new InternetAddress().parse(bccUser);  
	            message.setRecipients(Message.RecipientType.BCC, internetAddressBCC);  
	           }  
	             
	           // 发送日期  
	          // message.setSentDate(new Date());   
	             
	           // 邮件主题  
	           message.setSubject(subject);  
	             
	           // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件  
	           Multipart multipart = new MimeMultipart();  
	             
	           // 添加邮件正文  
	           BodyPart contentPart = new MimeBodyPart();  
	           contentPart.setContent(sendHtml, contentPart_Type);  
	           multipart.addBodyPart(contentPart);  
	           
	           BodyPart attachmentBodyPart = null;  
	           // 添加附件的内容  
	          
	           if (attaches!=null  && attaches.size() != 0) { 
	        	   
	        	   
	        		   for (File file : attaches) {  
	   	                attachmentBodyPart = new MimeBodyPart();  
	   	                DataSource source = new FileDataSource(file);  
	   	                attachmentBodyPart.setDataHandler(new DataHandler(source));  
	   	                //MimeUtility.encodeWord可以避免文件名乱码  
	   	                   attachmentBodyPart.setFileName(MimeUtility.encodeWord(file.getName()));  
	   	                   multipart.addBodyPart(attachmentBodyPart); 
	   	             }  
	        	   
	           }  
	           // 将multipart对象放到message中  
	           message.setContent(multipart);  
	             
	           // 保存邮件  
	           message.saveChanges();  
	             
	           // smtp验证  
	           transport = session.getTransport("smtp");  
	           transport.connect(mailHost, sender_username, sender_password);  
	             
	           // 发送  
	           transport.sendMessage(message, message.getAllRecipients());  
	           System.out.println("发送成功！");  
	           
	           
         } catch (MessagingException  e) {
 			e.printStackTrace();
 		 } catch(UnsupportedEncodingException e){
 			e.printStackTrace();
 		 } finally {  
 	        if (transport != null) {  
                try {  
                    transport.close();  
                } catch (MessagingException e) {  
                    e.printStackTrace();  
                }  
            }  
 		 }   
	 }
}
