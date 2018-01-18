package com.example.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.exam1.CreateSheetExcel;
import com.example.exam1.OutputFile;
import com.example.exam1.SendEmail;
import com.example.service.TestService;

@RestController
@EnableScheduling
public class DBController {
	@Autowired
    private TestService ts;
	
	/**
 	 * 邮件发送七格格 拉夏数据(每周一9:30)
 	 * @return
 	 */
	@Scheduled(cron = "20 30 9 ? * MON")
	public void db1(){
		System.err.println("每周一上午发送的七格格拉夏数据。。。");
		OutputFile out1=new OutputFile();
		OutputFile out2=new OutputFile();
		String[] headers = { "淘宝订单号","快递公司","快递单号","买家ID"}; 
		XSSFWorkbook workbook1=out1.downLoad(ts.checkDeliveryWeekLaxia(),headers);
		XSSFWorkbook workbook2=out2.downLoad(ts.checkDeliveryWeekQigege(),headers);
		String path1="E:\\邮件\\拉夏\\La"+simpleData()+".xlsx";
		String path2="E:\\邮件\\七格格\\Qigege"+simpleData()+".xlsx";
		FileOutputStream fileOut1 = null;
		FileOutputStream fileOut2 = null;
		//写出
        try {
			fileOut1 = new FileOutputStream(path1);
			fileOut2 = new FileOutputStream(path2);
			workbook1.write(fileOut1);
			workbook2.write(fileOut2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        //发邮件
        SendEmail send =new SendEmail();
 		List<File> attachment = new ArrayList<File>();
		File f1=new File(path1);
		File f2=new File(path2);
		attachment.add(f1);
		attachment.add(f2);
 		send.doSendHtmlEmail("已取消需排查订单", "这是"+DBController.StrHtml(7)+"到"+DBController.StrHtml(1)+"已取消订单，请排查快递是否发出", "jiangshaorong@7gege.com,yuer@7gege.com,yangxin@7gege.com,kaye@7gege.com,bill@7gege.com","","",attachment);
	}
 	
 	/**
 	 * 邮件发送聚石塔销量数据(每天9:00)
 	 */
	@Scheduled(cron = "20 00 09 * * ?")
	public void db2(){
		System.err.println("销量数据....");
		OutputFile out1=new OutputFile();
		String[] headers = { "outer_sku_id","xiaoliang7","xiaoliang14","xiaoliang30","xiaoliang"};  
		XSSFWorkbook workbook=out1.downLoad(ts.jstData(),headers);
		String path="E:\\邮件\\jst销量\\data"+simpleData()+".xlsx";
		FileOutputStream fileOut = null;
		//写出
        try {
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        SendEmail send =new SendEmail();
 		List<File> attachment = new ArrayList<File>();
			File f=new File(path);
			attachment.add(f);
 		send.doSendHtmlEmail ("销量数据", "销量数据7天、14天以及30天的数据", "cinderella@7gege.com,kaye@7gege.com,maggie@7gege.com,steven@7gege.com,yangxin@7gege.com","","",attachment);
      
	}
 	
	/**
	 * 邮件发送七格格数据(每个月9号9:05)
	 */
	@Scheduled(cron = "00 05 09 3 * ?")
	public void db3(){
		//采购入库  销退  采退  其它出库  其它入库
		String[] headers1 = { "biid","inco","qty","chna","a_whid","b_whid","crdt"};
		//调拨入库
		String[] headers2 = { "biid","inco","qty","chna","pfwh","powh","soco","crdt"};
		//销售出库
		String[] headers3 = { "soco","biid","inco","qty","chna","a_whid","b_whid","crdt"};
		//调拨出库
		String[] headers4 = { "biid","inco","qty","chna","pfwh","powh","crdt"};
		//盘点
		String[] headers5 = { "biid","inco","tqty","chna","a_whid","b_whid","crdt"};
		CreateSheetExcel sheet=new CreateSheetExcel();
	
		XSSFWorkbook workbook1=sheet.downLoad(ts.purchaseMonthQigege(), headers1, 0,"采购入库");
		 workbook1=sheet.downLoad(ts.transfeInMonthQigege(),headers2,1,"调拨入库");
		 workbook1=sheet.downLoad(ts.pinBackMonthQigege(),headers1,2,"销退");
		 workbook1=sheet.downLoad(ts.sellMonthQigege(),headers3,3,"销售出库");
		 workbook1=sheet.downLoad(ts.transfeOutMonthQigege(),headers4,4,"调拨出库");
		 workbook1=sheet.downLoad(ts.feedBackMonthQigege(),headers1,5,"采退");
		 workbook1=sheet.downLoad(ts.checkMonthQigege(),headers5,6,"盘点");
		 workbook1=sheet.downLoad(ts.otherOutMonthQigege(),headers1,7,"其他出库");
		 workbook1=sheet.downLoad(ts.OtherInMonthQigege(),headers1,8,"其他入库");
		String path="E:\\邮件\\每个月的数据\\data"+simpleData()+".xlsx";
		FileOutputStream fileOut = null;
		//写出
        try {
			fileOut = new FileOutputStream(path);
			workbook1.write(fileOut);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(fileOut!=null){
				try {
					fileOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			SendEmail send =new SendEmail();
	 		List<File> attachment = new ArrayList<File>();
				File f=new File(path);
				attachment.add(f);   
	 		send.doSendHtmlEmail ("01W01-1-243-1 库位 的收发存数据", "附件中为上个月 01W01-1-243-1 库位 的收发存数据，请查收！", "yuer@7gege.com,kaye@7gege.com,1164337934@qq.com","","",attachment);
	      
		}
 
	}
	
 	//当天时间
 	public String simpleData(){
 		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
 		return sdf.format(new Date());
 	}
 	
 	//时间间隔
 	public static String StrHtml(int past){
 		Calendar cs=Calendar.getInstance();
 		cs.set(cs.DAY_OF_YEAR,cs.get(cs.DAY_OF_YEAR)-past);
 		Date d=cs.getTime();
 		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
 		return f.format(d);
 	}
 	
 	//测试
	@Scheduled(cron = "20 47 9 ? * SAT")
	public void test(){
		System.err.println("当前时间"+new Date());
	}
	
	@RequestMapping("/")
 	@ResponseBody
	public String hello(){
 		return "hello";
	}
}
