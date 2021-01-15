package practice.AppiumFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Base {
	
	public static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement> driver;
	
	
	public AppiumDriverLocalService startServer() {
		if(!checkIfServerIsRunning(4723)) {
			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
		}
		return service;
	}
	
	public static boolean checkIfServerIsRunning(int port) {
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		}
		catch (IOException e) {
			//If control comes here, then it means the port is in use
			isServerRunning = true;
		}
		finally {
			serverSocket = null;
		}
		
		return isServerRunning;
	}
	
	public static void startEmulator() throws IOException, InterruptedException {
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\startEmulator.bat");
		Thread.sleep(6000);
	}
	
	public static void killAllNodes() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);
	}
	
	
	
	public static AndroidDriver<AndroidElement> capabilities(String appName) throws InterruptedException, IOException {
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\practice\\AppiumFramework\\global.properties");
		Properties prop = new Properties();
		prop.load(fis);		
		
		File appDir = new File("src");
		File app = new File(appDir, (String) prop.get(appName));
		
		DesiredCapabilities cap = new DesiredCapabilities();
		
//		if(device.equals("real")) {
//			cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
//		}
//		else if(device.equals("emulator")){
//			cap.setCapability(MobileCapabilityType.DEVICE_NAME, "GraemeEmulator");
//		}
		
		//String device = (String) prop.get("device");
		
		String device = System.getProperty("deviceName");
		
		if(device.toLowerCase().contains("emulator")) {
			startEmulator();
		}
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Uiautomator2");
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 14);
		cap.setCapability("chromedriverExecutable", "C:/QA_Tools/chromedriver/lib/chromedriver/chromedriver.exe");
		
		
		cap.setCapability("autoGrantPermissions", true);
		cap.setCapability("noReset", "false");
		cap.setCapability("fullReset", "true");
		
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new AndroidDriver<AndroidElement>(url, cap);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		if(appName.equals("ApiDemosApp")) {
			driver.findElement(By.xpath("//android.widget.Button[@text='OK']")).click();
		}

		return driver;
	}
	
	public static void getScreenshot(String testCaseName) throws IOException {
		File scrfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrfile, new File("C:\\Users\\graeme_churchman\\Pictures\\FailedTests\\defectscreen_"+testCaseName+".png"));
	}
}
