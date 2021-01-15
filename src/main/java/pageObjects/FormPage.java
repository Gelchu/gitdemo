package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage {

	public FormPage(AppiumDriver driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement nameField;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioFemale")
	private WebElement radio;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/spinnerCountry")
	private WebElement countrySelect;
	

	
	public WebElement getNameField() {
		System.out.println("trying to get the Name Field");
		return nameField;
	}
	
	public WebElement getCountrySelect() {
		System.out.println("Getting the Country Select");
		return countrySelect;
	}
	
	
	
}
