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

import BASE_CLASSES.AllMethods;



public class KWDMain {

	
public static void main(String[] args) {
	ReadWrite rw=new ReadWrite();
	AllMethods am=new AllMethods();
	ArrayList<Data> al=new ArrayList<Data>();
	rw.readExcel(al);
	String k;
	for(Data dd:al)
	{
		k=dd.keyword;
		switch(k)
		{
		case "launchbrowser":
			am.launchbrowser(dd.testdata);
			break;
		case "click":
			am.click(dd.xpath);
			break;
		case "entertext":
			am.entertext(dd.xpath, dd.testdata);
		    break;
		case "checkrb":
			am.checkrb(dd.xpath);
		    break;
		case "verify":
		    dd.test_result=	am.verify(dd.xpath);
		    rw.writeExcel(dd.tc_id,dd.test_result,dd.keyword);
			break;
		case "clicklink":
			am.clicklink(dd.xpath);
			break;
		case "verifysearch":
			dd.test_result=am.verifysearch();
			 rw.writeExcel(dd.tc_id,dd.test_result,dd.keyword);
			break;
		case "waitexp":
			 am.waitexp(dd.xpath);
			 break;
		case "logsupd":
			am.logsupd(dd.testdata);
			break;
		case "close":
			am.close();
			break;
		default:
			System.out.println("Invalid operation");
		}
	}
}
}
