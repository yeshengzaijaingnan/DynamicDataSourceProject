package com.example.conf;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailAuthenticator extends Authenticator{
	private final static String username = "xxxx@163.com";//发件人邮箱账号  
    private final static String password = "xxxxx";//发件人邮箱密码  
  
    protected PasswordAuthentication getPasswordAuthentication() {  
        return new PasswordAuthentication(username, password);  
  
    }  
}
