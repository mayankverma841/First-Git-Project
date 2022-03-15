package Utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base {
	
	private WebDriver driver;
	public static InstanciatingPageObjects pageObject;
	
	public WebDriver init() {
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\resources\\chromedriver.exe");
//		driver= new ThreadLocal<WebDriver>() {
//			@Override
//			protected WebDriver initialValue() {
//				return new ChromeDriver();
//			}
//		};
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
		return driver;
	}
	
	public void intializeUrl(WebDriver driver) {
		driver.get("https://paytm.com/");
		driver.manage().window().maximize();
	}
	
	public boolean isElementPresent(WebDriver driver, By by) {
		if (driver.findElements(by).size()!=0)
			return true;
		else
			return false;		
	}
	
	public String getScreenShotPath(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source =ts.getScreenshotAs(OutputType.FILE);
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
		String current = LocalDateTime.now().format(pattern);
		String destinationFile = System.getProperty("user.dir")+"\\reports\\"+testCaseName+"_"+current+".png";
		FileUtils.copyFile(source,new File(destinationFile));
		return destinationFile;


	}
}
