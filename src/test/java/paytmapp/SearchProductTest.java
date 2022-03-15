package paytmapp;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utilities.Base;
import Utilities.InstanciatingPageObjects;
import junit.framework.Assert;

public class SearchProductTest extends Base {
	
	private static Logger log = LogManager.getLogger(SearchProductTest.class.getName());
	public WebDriver driver;
	
	
	@BeforeClass
	public void intializeDriver() {
		driver=init();
		Base.pageObject=InstanciatingPageObjects.prepare(driver);
	}
	
	@BeforeMethod
	public void initUrl(final Method method) {
		log.info("Stating the Test : "+method.getName());
		intializeUrl(driver);
		log.info("URL is opened successfully");
	}
	
	@Test(dataProvider="ProvidingData")
	public void validateSortingInProductDeatails(String name, String brand) throws InterruptedException {
		
		Actions action = new Actions(driver);
		action.moveToElement(pageObject.productPage().searchTextBox).click()
						.sendKeys(name).sendKeys(Keys.ENTER).build().perform();
		log.info("Searching for the product in the app");
		action.moveToElement(pageObject.productPage().filterOnBrand).build().perform();
		pageObject.productPage().searchCriteria.stream()
				.filter(w -> w.getText().equals(brand))
				.findFirst().orElseThrow().findElement(By.xpath("./preceding::span[@class='check'][1]")).click();
		log.info("Getting the list of products with desired brand");
		pageObject.productPage().dropDownArrow.click();
		pageObject.productPage().sortByPrice.click();
		log.info("Sorting the products as per price");
		Thread.sleep(5000);
		List<Integer> priceList = pageObject.productPage().priceDetails.stream()
												.map(w -> w.getText().split(" ")[1])
												.map(s -> Integer.valueOf(s))
												.collect(Collectors.toList());
		List<Integer> sortedPriceList = priceList.stream().sorted()
												.collect(Collectors.toList());
		Assert.assertTrue(priceList.equals(sortedPriceList));
	}
	
	@DataProvider(name="ProvidingData")
	public Object[][] getData() {
		return new Object[][] {
			{"Mobile Phone","Lenovo"},
			{"Washing Machine","Onida"}
		};
	}	
	
	@AfterClass
	public void closeDriver() {
		driver.close();
	}
}
