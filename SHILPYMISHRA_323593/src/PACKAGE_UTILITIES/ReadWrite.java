package PACKAGE_UTILITIES;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadWrite {
	Data d;
	public void updateSheet1(String tc_id,String status)
	{
		try {
			File path=new File("Pocdata.xlsx");
			 FileInputStream f=new FileInputStream(path);
		     XSSFWorkbook wb=new XSSFWorkbook(f);
			XSSFSheet sheet1=wb.getSheet("TC_SELECTION_SHEET");
			for(int i=0;i<=sheet1.getLastRowNum();i++)
			{
				XSSFRow r=sheet1.getRow(i);
				String id=r.getCell(0).getStringCellValue();
				if(id.equals(tc_id))
                 {
				  XSSFCell c4=r.createCell(3);
				  FileOutputStream  fos=new FileOutputStream(path);
				  c4.setCellValue(status);
				  wb.write(fos);
				  break;
                 }
			}
		}
		catch(Exception ee)
		{
			
		}
	}
	public int findid(String tc_id)
	{
		 int sot = 0;
		try {
			File path=new File("Pocdata.xlsx");
			 FileInputStream f=new FileInputStream(path);
		     XSSFWorkbook wb=new XSSFWorkbook(f);
			XSSFSheet sheet2=wb.getSheet("KEYWORD");
			
			for(int i=1;i<=(sheet2.getLastRowNum()-sheet2.getFirstRowNum());i++)
			{
		
	          
				XSSFRow rr=sheet2.getRow(i);
			   if(rr.getCell(0).getStringCellValue().equalsIgnoreCase(tc_id))
			   {
			   sot=i;
			   break;
			   }
			
		}
		    
		}catch(Exception ee)
		{
			
		}
		return sot;
	}
	public void store(String tc_id,int nos,ArrayList<Data> al)
	{
	try {
		File path=new File("Pocdata.xlsx");
		 FileInputStream f=new FileInputStream(path);
	     XSSFWorkbook wb=new XSSFWorkbook(f);
		XSSFSheet sheet2=wb.getSheet("KEYWORD");
	     XSSFRow rr;
	     String cc1,cc2,cc3,cc4;
	     int sot=findid(tc_id);
		 for(int j=sot;j<=(nos+sot-1);j++)
		 {
			     d=new Data();
			     d.tc_id=tc_id;
			     rr=sheet2.getRow(j);
				 cc1=rr.getCell(2).getStringCellValue();
				 d.keyword=cc1;
				 cc2=rr.getCell(3).getStringCellValue();
				 d.xpath=cc2;
				 cc3=rr.getCell(4).getStringCellValue();
				 d.testdata=cc3;
				 cc4=rr.getCell(5).getStringCellValue();
				 d.expected_res=cc4;
				 al.add(d);
		 }
	}
	catch(Exception ee)
	{
		
	}
	}
	public void readExcel(ArrayList<Data> al)
	{
		
		try {
			
			
			 File path=new File("Pocdata.xlsx");
			 FileInputStream f=new FileInputStream(path);
		     XSSFWorkbook wb=new XSSFWorkbook(f);
		     XSSFSheet sheet1=wb.getSheet("TC_SELECTION_SHEET");
		     int no_of_rows=sheet1.getLastRowNum()-sheet1.getFirstRowNum();
		     for(int i=1;i<=no_of_rows;i++)
		     {
		    	 XSSFRow r=sheet1.getRow(i);
			   XSSFCell c2=r.getCell(1);
			 
			 if(c2.getStringCellValue().equalsIgnoreCase("Y"))
			 {
				// System.out.println("In r"+i);
				 XSSFCell c1=r.getCell(0);
				 XSSFCell c3=r.getCell(2);
				 String tc_id=c1.getStringCellValue();
				 int no_of_steps=(int)c3.getNumericCellValue();
				 store(tc_id,no_of_steps,al);
			 }
		     }
		}//try
		catch(Exception ee){
		ee.getMessage();
		}
		
		}
public void writeExcel(String tc_id,String test_result,String keyword)
{
		  try {
			  File path=new File("Pocdata.xlsx");
			  FileInputStream f=new  FileInputStream(path);
		  
		  XSSFWorkbook wb=new XSSFWorkbook(f);
		  XSSFSheet sheet=wb.getSheet("KEYWORD");
		  int sot=findid(tc_id);
		  for(int i=sot;i<sheet.getLastRowNum();i++)
		  {
			  XSSFRow r=sheet.getRow(i);
			  String c3=r.getCell(2).getStringCellValue();
			  if(c3.equalsIgnoreCase(keyword))
			  {
				  XSSFCell c7=r.createCell(6);
				  FileOutputStream  fos=new FileOutputStream(path);
				  c7.setCellValue(test_result);
				  wb.write(fos);
	              String c6=r.getCell(5).getStringCellValue();
	              if(c6.equals(test_result))
	            	  updateSheet1(tc_id,"PASS");
	             else
	                  updateSheet1(tc_id,"FAIL");
	              break;
			  }
		  }
		  
		  } catch(IOException ee) { ee.getMessage(); }
		 
}
}
