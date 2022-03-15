package pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utilities.Base;

public class PrepaidPostpaidTab extends Base {
	
	WebDriver driver;
	public PrepaidPostpaidTab(WebDriver driver) {
		this.driver=driver;
	}
	
	public By prepaidTab = By.xpath("//*[@title='Prepaid/Postpaid']");
	public By prepaidRadioButton = By.xpath("//input[@type='radio' and @value='prepaid']");
	public By postpaidRadioButton = By.xpath("//input[@type='radio' and @value='postpaid']");
	public By mobileNumberText = By.name("Mobile Number");
	public By operatorText = By.name("Operator");
	public By operatorNames = By.xpath("//*[@name='Operator']/following::div[@class='_3xI1']//li/span");
	public By amountText = By.name("Amount");
	public By proceedToRechargeButton=By.xpath("//button[@class='_11kC  _15qf _2qE6']");
	
	public WebElement getPrepaidTab() {
		return driver.findElement(prepaidTab);
	}
	
	public WebElement getPrepaidRadioButton() {
		return driver.findElement(prepaidRadioButton);
	}
	
	public WebElement getPostpaidRadioButton() {
		return driver.findElement(postpaidRadioButton);
	}
	
	public WebElement getMobileNumberText() {
		return driver.findElement(mobileNumberText);
	}
	
	public WebElement getOperatorText() {
		return driver.findElement(operatorText);
	}
	
	public List<WebElement> getOperators() {
		return driver.findElements(operatorNames);
	}
	
	public WebElement getAmountText() {
		return driver.findElement(amountText);
	}
	
	public WebElement getRechargeButton() {
		return driver.findElement(proceedToRechargeButton);
	}
	
	public List<String> getOperatorsList() {
		List<String> operators = new ArrayList<String>();
		for (WebElement element : this.getOperators()) {
			operators.add(element.getText().trim());
		}
		return operators;
	}
}
