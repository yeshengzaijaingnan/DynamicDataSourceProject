package com.example.entity;

import java.util.List;

public class Email {
    private final static String FROM = "youjian@7gege.com";//发件人的邮箱  
    private String subject;  //设置主题
    private String text;//设置内容 
    private List<String> files; //附件
    private final static String Email_Host = "smtp.qiye.163.com";//指定邮箱server 
    private List<String> toAdress;//收件人的邮箱地址  (多个)
    private String header;
    private final static String pwd="6FEjAJHAjEvO9";
    
    
	public static String getPwd() {
		return pwd;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public static String getEmailHost() {
		return Email_Host;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<String> getFiles() {
		return files;
	}
	public void setFiles(List<String> files) {
		this.files = files;
	}
	
	public List<String> getToAdress() {
		return toAdress;
	}
	public void setToAdress(List<String> toAdress) {
		this.toAdress = toAdress;
	}
	public static String getFrom() {
		return FROM;
	}
    	
}
