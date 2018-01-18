package com.example.exam1;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CreateSheetExcel {
	 // 声明一个工作薄
		XSSFWorkbook  workbook = new XSSFWorkbook ();
		//
		public XSSFWorkbook downLoad(List<?> dataset,String[] headers,int sheetIndex,String sheetName){
	        // 生成一个表格
			XSSFSheet sheet = workbook.createSheet();
			//设置sheet
			workbook.setSheetName(sheetIndex,sheetName);
	        // 设置表格默认列宽度为20个字节
	        sheet.setDefaultColumnWidth((short) 20);
	        XSSFRow row = sheet.createRow(0);
	        //遍历集合数据，产生数据行
	        for (short i = 0; i < headers.length; i++) {
	            XSSFCell cell = row.createCell(i);
	            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
	            cell.setCellValue(text);
	        }
	        Iterator<?> it = dataset.iterator();
	        int index = 0;
	        while (it.hasNext()) {
	            index++;
	            row =  sheet.createRow(index);
	            Object t =  it.next();
	            //利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
	            Field[] fields = t.getClass().getDeclaredFields();
	            for (short i = 0; i < fields.length; i++) {
	                XSSFCell cell =  row.createCell(i);
	                Field field = fields[i];
	                String fieldName = field.getName();
	                String getMethodName = "get"
	                        + fieldName.substring(0, 1).toUpperCase()
	                        + fieldName.substring(1);
	               
	                try {
	                    Class tCls = t.getClass();
	                    Method getMethod = tCls.getMethod(getMethodName,new Class[]{});
	                    Object value = getMethod.invoke(t, new Object[]{});
	                    String textValue =  value.toString();
	                          
	                    XSSFRichTextString richString = new XSSFRichTextString(textValue);
		                    XSSFFont  font3 = (XSSFFont) workbook.createFont();
		                    font3.setColor(HSSFColor.BLACK.index);//定义Excel数据颜色
		                    richString.applyFont(font3);
		                    cell.setCellValue(richString);
		                  
	                } catch (SecurityException e) {
	                    e.printStackTrace();
	                } catch (NoSuchMethodException e) {
	                    e.printStackTrace();
	                } catch (IllegalArgumentException e) {
	                    e.printStackTrace();
	                } catch (IllegalAccessException e) {
	                    e.printStackTrace();
	                } catch (InvocationTargetException e) {
	                    e.printStackTrace();
	                } 
			        
	            }
	        }
	        return workbook;   
		}
		//文件名
		public String fileName(){
			Date d=new Date();
	        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
	        String fn= f.format(d);
	      //文件名称防止文件名含有中文乱码
	        String filedisplay =fn+".xlsx";
	        
			try {
				filedisplay = new String( filedisplay.getBytes("gb2312"), "ISO8859-1" );
				
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return filedisplay;
		}
}
