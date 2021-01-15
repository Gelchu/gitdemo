package resources;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import practice.AppiumFramework.Base;

public class Listeners implements ITestListener {


	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);
		
		//Screenshot
		String testCaseName = result.getName();
		try {
			Base.getScreenshot(testCaseName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
