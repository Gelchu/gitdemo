package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

//All the objects belonging to 1 page will be defined in one java class
public class HomePage {
	
	public HomePage(AppiumDriver driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Preference']")
	public WebElement Preferences;
	
	
}
