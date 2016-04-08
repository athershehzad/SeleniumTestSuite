package com.training.framwork;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.compendium.util.ResultInfo;
import com.compendium.util.TestUtil;

public class BaseTest {
	

	protected ResultInfo resultInfo = ResultInfo.getInstance();
	protected WebDriver driver = null;
	
	@BeforeMethod
	//@Parameters({"testName"})
	public void onStart() {
		// String testName
		if("firefox".equals(resultInfo.getBrowser())) {
            driver = new FirefoxDriver();
        } else if("chrome".equals(resultInfo.getBrowser())) {
        	driver = new ChromeDriver();
        }
		resultInfo.setTestName("");
		resultInfo.setStartTime(TestUtil.getStartTime());
		driver.manage().window().maximize();
	}
	
	@AfterMethod
     public void onEnd() {
		driver.close();
		resultInfo.setEndTime(TestUtil.getEndTime());
		TestUtil.writeResults(resultInfo);
	}
 }

