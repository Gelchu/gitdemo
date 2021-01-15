package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Dependencies {
	public Dependencies(AppiumDriver driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="android:id/checkbox")
	public WebElement CheckBox;
	
	@AndroidFindBy(xpath="(//android.widget.RelativeLayout)[2]")
	public WebElement WifiSettings;

	@AndroidFindBy(className="android.widget.EditText")
	public WebElement EditText;
	
	
	@AndroidFindBy(className="android.widget.Button")
	public List<WebElement> Buttons;
}
