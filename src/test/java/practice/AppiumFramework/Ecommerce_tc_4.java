package practice.AppiumFramework;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.CheckoutPage;
import pageObjects.FormPage;

public class Ecommerce_tc_4 extends Base {
	//private static AndroidDriver<AndroidElement> driver;
	
	@BeforeTest
	public void killNodes() throws IOException, InterruptedException {
		killAllNodes();
	}
	
	@Test
	public void totalValidation() throws InterruptedException, IOException {

		String country = "Austria";
		String name = "Graeme";
		String gender = "Female";
		
		service = startServer();
		AndroidDriver<AndroidElement> driver = capabilities("GeneralStoreApp");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Utilities u = new Utilities(driver);
		FormPage formpage = new FormPage(driver);
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		
		//Edit the name
		formpage.getNameField().sendKeys(name);
		driver.hideKeyboard();
		
		//Change the gender radio button
		driver.findElement(By.id("com.androidsample.generalstore:id/radio"+gender)).click();
		
		//Change the country
		formpage.getCountrySelect().click();
		u.scrollToText(country);
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+country+"']")).click();
		
		
		//driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys(name);
		//driver.hideKeyboard();
		
//		driver.findElement(By.id("com.androidsample.generalstore:id/radio"+gender)).click();
//		
//		driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
//		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+country+"\"))");
//		driver.findElement(By.xpath("//android.widget.TextView[@text='"+country+"']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
				
		driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(0).click();
		driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(1).click();
		//driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(2).click();
		
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		Thread.sleep(4000);
		
		
		List<WebElement> elements = checkoutPage.productList;
		int count = elements.size();
		double totalvalue = 0;
		
		for(int i = 0; i < count; i++) {
			String amount = elements.get(i).getText();
			double amountvalue = getAmount(amount);
			totalvalue += amountvalue;
		}

		double giventotalvalue = Double.parseDouble(checkoutPage.totalAmount.getText().substring(1));
		
		System.out.println("Sum totals = "+totalvalue);
		System.out.println("Given totals = "+giventotalvalue);
		
		
		Assert.assertEquals(totalvalue, giventotalvalue);
		
		service.stop();
	}
	
	private static double getAmount(String value) {
		return Double.parseDouble(value.substring(1));
	}
}
