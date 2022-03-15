package Utilities;

import org.openqa.selenium.WebDriver;

import pageobjects.PrepaidPostpaidTab;
import pageobjects.SearchProductPage;

public class InstanciatingPageObjects {

	private WebDriver driver;
	
	private InstanciatingPageObjects(WebDriver driver) {
		
		this.driver = driver;
	}
	
	public static InstanciatingPageObjects prepare (WebDriver driver) {
		return new InstanciatingPageObjects(driver);
	}
	
	public PrepaidPostpaidTab getTab() {
		return new PrepaidPostpaidTab(driver);
	}
	
	public SearchProductPage productPage() {
		return new SearchProductPage(driver);
	}

}
