package BASE_CLASSES;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AllMethods {
 WebDriver dr;
	Logger log;
	WebDriverWait wt;
	public void waitexp(String path)
	{
		wt=new WebDriverWait(dr,10);
	    wt.until(ExpectedConditions.presenceOfElementLocated(By.xpath(path)));
	}
	public void logsupd(String msg)
	{
		log=Logger.getLogger("devpinoyLogger");
		  log.info(msg);
	}
public void launchbrowser(String url)
{
	System.setProperty("webdriver.chrome.driver", "chromedriver_v75.exe");
	dr=new ChromeDriver();
	dr.get(url);
	
}
public void click(String path)
{
	dr.findElement(By.xpath(path)).click();	
}
public void entertext(String path,String data)
{

	dr.findElement(By.xpath(path)).sendKeys(data);
	}
public void checkrb(String path)
{

	dr.findElement(By.xpath(path)).click();	
}
public String verify(String path)
{
	String ar=dr.findElement(By.xpath(path)).getText() ;
	  return ar;
}
public String verifysearch()
{
    String ar=dr.getCurrentUrl();
	  return ar;
}
public void clicklink(String path)
{
	dr.findElement(By.xpath(path)).click();
}
public void close()
{
	dr.close();
}
}
