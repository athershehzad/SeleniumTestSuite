package com.compendium.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


  public class TestUtil {
	
	static Logger log = Logger.getLogger(TestUtil.class.getName());
	private static final String TEMPLATE_PATH = "src/test/resources/template.html";
	private static final String RESULT_PLACE_HOLDER = "<!-- INSERT_RESULTS -->";

	public static String getStartTime() {
		Date date = new Date(System.currentTimeMillis());
		return TestConstant.DATE_FORMATE.format(date);

	}

	public static String getEndTime() {
		Date date = new Date(System.currentTimeMillis());
		return TestConstant.DATE_FORMATE.format(date);
	}
	
	public static WebElement findElement(String by, WebDriver driver,
			String byText) {
		WebElement element = null;
		if (by.equalsIgnoreCase("id")) {
			element =  driver.findElement(By.id(byText));
		} else if (by.equalsIgnoreCase("name")) {
			element =  driver.findElement(By.name(byText));
		} else if (by.equalsIgnoreCase("xpath")) {
			element =  driver.findElement(By.xpath(byText));
		} else if (by.equalsIgnoreCase("class")) {
			element =  driver.findElement(By.className(byText));
		} else if (by.equalsIgnoreCase("link")) {
			element =  driver.findElement(By.linkText(byText));
		} else if (by.equalsIgnoreCase("cssSelector")) {
			element =  driver.findElement(By.cssSelector(byText));
		}
		return element;
	}

	public static void loadTestSuiteProperties(ResultInfo info) throws IOException {
		
		log.info("loadConfigProperties started");
		
		FileReader reader = new FileReader("src/test/resources/Properties/config.properties");
		Properties properties = new Properties();
		properties.load(reader);
		info.setBrowser(properties.getProperty("browser"));
		log.info("loadConfigProperties ended");
		
	} 
	
     public static void writeResults(ResultInfo resultInfo) {
		
		try {
			String reportIn = "";
			File file = new File(TEMPLATE_PATH);
			reportIn = FileUtils.readFileToString(file);
			String screenShotTemplete = "";
			File newFile = new File("src/main/resources/template" + resultInfo.getCurrentDate() + ".html");
			if(resultInfo.isScreenShot()) {
				screenShotTemplete = "<a href=reports/screenshots/"+ resultInfo.getTestName()+".png>screenshot</a> /" ;
			}
			if(!newFile.exists()) {
				if(!resultInfo.isScreenShot()) {
					reportIn = reportIn.replaceFirst(RESULT_PLACE_HOLDER,"<html><tbody><table width='100%'><tr>"
							+ "<td width='15%' align='center'>" + resultInfo.getTestName()  +"</td>"
							+ "<td width='10%' align='center'>" + resultInfo.getStartTime() +"</td>"
							+ "<td width='10%' align='center'>" + resultInfo.getEndTime()   +"</td>"
							+ "<td width='5%' align='center'>"  + resultInfo.getStatus()    +"</td>"
							+ "<td width='15%' align='center'>" + resultInfo.getReason()    +"</td>"
							+ "<td width='5%' align='center'>"  + resultInfo.getBrowser()   +"</td>"
							+ "<td width='10%' align='center'>" + screenShotTemplete        +"</td>"
						    + "</tr></tbody></table></body>	</html>" + RESULT_PLACE_HOLDER);
				}
				FileUtils.writeStringToFile(newFile, reportIn);
			} else {
				List<String> lines = FileUtils.readLines(new File(TEMPLATE_PATH), Charset.defaultCharset()); 
	            for (String line : lines) {
	            	if(line.equals("<!-- INSERT_RESULTS -->")) {
	            		reportIn = line;
	            		if(!resultInfo.isScreenShot()) {
	            			reportIn = reportIn.replaceFirst(RESULT_PLACE_HOLDER,"<html><tbody><table width='100%'><tr>"
	            					+ "<td width='15%' align='center'>" + resultInfo.getTestName()  +"</td>"
	    							+ "<td width='10%' align='center'>" + resultInfo.getStartTime() +"</td>"
	    							+ "<td width='10%' align='center'>" + resultInfo.getEndTime()   +"</td>"
	    							+ "<td width='5%' align='center'>"  +  resultInfo.getStatus()   +"</td>"
	    							+ "<td width='15%' align='center'>" + resultInfo.getReason()    +"</td>"
	    							+ "<td width='5%' align='center'>"  + resultInfo.getBrowser()   +"</td>"
	    							+ "<td width='10%' align='center'>" + screenShotTemplete        +"</td>"
	    						    + "</tr></tbody></table></body>	</html>" + RESULT_PLACE_HOLDER);
	    				} 
	            	} 
	            }
	            FileUtils.writeStringToFile(newFile, reportIn, true);
			}
		} catch (Exception e) {
			
		}
	}

    public static String getCurrentDate() {
    	return new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    }
}
