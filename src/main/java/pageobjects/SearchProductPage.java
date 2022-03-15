package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchProductPage {
	
	private WebDriver driver;
	public SearchProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	@FindBy(xpath="//input[@type='search']")
	public WebElement searchTextBox;
	
	@FindBy(xpath="//span[@class='_1sRm' and text()='Brand']")
	public WebElement filterOnBrand;
	
	@FindBy(xpath="//span[@class='labelName']")
	public List<WebElement> searchCriteria;
	
	@FindBy(css="i.iconArrowRight")
	public WebElement dropDownArrow;
	
	@FindBy(xpath = "//div[@class='_3vL1' and text()='Low Price']")
	public WebElement sortByPrice;
	
	@FindBy(css = "div.pCOS span._1kMS")
	public List<WebElement> priceDetails;
}
