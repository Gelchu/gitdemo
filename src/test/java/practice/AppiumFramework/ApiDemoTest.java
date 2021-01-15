package practice.AppiumFramework;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.Dependencies;
import pageObjects.HomePage;
import pageObjects.Preferences;

public class ApiDemoTest extends Base {
	
	@Test(dataProvider="InputData",dataProviderClass=TestData.class)
	public void validateWifi(Object text) throws InterruptedException, IOException {
		
		service = startServer();
		AndroidDriver<AndroidElement> driver = capabilities("ApiDemosApp");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//xpath, id, className, androidUIautomator
		/* xpath Syntax
		 * //tagName[@attribute='value']
		 * e.g:
		 * //android.widget.TextView[@text='Preference']
		 * */
		
		HomePage h = new HomePage(driver);
		Preferences p = new Preferences(driver);
		Dependencies d = new Dependencies(driver);
		//Constructor of the class will be invoked when you create object for the class
		h.Preferences.click();
		p.Dependencies.click();
		d.CheckBox.click();
		d.WifiSettings.click();
//		d.EditText.sendKeys("hello");
		d.EditText.sendKeys(text.toString());
		d.Buttons.get(1).click();
		
		service.stop();
		
	}
}
