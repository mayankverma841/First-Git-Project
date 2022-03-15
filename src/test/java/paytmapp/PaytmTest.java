package paytmapp;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utilities.Base;
import Utilities.InstanciatingPageObjects;
import junit.framework.Assert;

public class PaytmTest extends Base {
	private static Logger log = LogManager.getLogger(PaytmTest.class.getName());
	public WebDriver driver;
	
	@BeforeTest 
	public void startingTest() {
		log.info("***************Starting the Execution of Test Cases*******************");
	}
	
	@BeforeClass
	public void intializeDriver() {
		driver=init();
		driver.manage().deleteAllCookies();
		Base.pageObject=InstanciatingPageObjects.prepare(driver);
	}
	
	@BeforeMethod
	public void initUrl(final Method method) {
		log.info("Stating the Test : "+method.getName());
		intializeUrl(driver);
		log.info("URL is opened successfully");
	}
	
	@Test
	public void prepaidPostPaidTab() {
		pageObject.getTab().getPrepaidTab().click();
		log.info("Clicked on Prepaid/Postpait Tab");
		Assert.assertTrue(isElementPresent(driver, pageObject.getTab().prepaidRadioButton));
		Assert.assertTrue(isElementPresent(driver, pageObject.getTab().postpaidRadioButton));
		Assert.assertTrue(isElementPresent(driver, pageObject.getTab().mobileNumberText));
		Assert.assertTrue(isElementPresent(driver, pageObject.getTab().operatorText));
		Assert.assertTrue(isElementPresent(driver, pageObject.getTab().amountText));
		Assert.assertTrue(isElementPresent(driver, pageObject.getTab().proceedToRechargeButton));
	}
	
	@Test
	public void validateTextBoxAndDropdownInPrepaidPostPaidTab(){
		
		pageObject.getTab().getPrepaidTab().click();
		log.info("Clicked on Prepaid/Postpait Tab");
		log.info("Validating the label of Text Box");
		Assert.assertEquals(pageObject.getTab().getMobileNumberText()
				.findElement(By.xpath("./following::label[1]")).getText(), "Mobile Number");
		Assert.assertEquals(pageObject.getTab().getOperatorText()
				.findElement(By.xpath("./following::label[1]")).getText(), "Operator");
		Assert.assertEquals(pageObject.getTab().getAmountText()
				.findElement(By.xpath("./following::label[1]")).getText(), "Amount");
		log.info("Validating the Operators");
		pageObject.getTab().getOperatorText().click();
		List<String> test = Arrays.asList("Airtel","MTNL","BSNL","Jio","Vi");
		pageObject.getTab().getOperatorsList().stream()
							.forEach(a -> Assert.assertTrue(test.contains(a)));
	}
	
	@AfterClass
	public void closeDriver() {
		driver.close();
	}
}
