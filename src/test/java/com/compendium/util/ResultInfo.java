package  com.compendium.util;

import java.io.IOException;

public class ResultInfo {
	
	private String status = "";
	private String startTime = "";
	private String endTime = "";
	private String testName = "";
	private String reason = "";
	private String browser = "";
	private String url = "";
	private boolean screenShot = false;
	private String currentDate = "";
	
	private ResultInfo(){
		
	}
	
	private static ResultInfo resultInfo = null;
	
	public static ResultInfo getInstance(){
        if(resultInfo == null){
        	resultInfo = new ResultInfo();
        	resultInfo.setCurrentDate(TestUtil.getCurrentDate());
        	try {
				TestUtil.loadTestSuiteProperties(resultInfo);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        return resultInfo; 
    }

	
	public String getCurrentDate() {
		return currentDate;
	}


	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}


	public boolean isScreenShot() {
		return screenShot;
	}


	public void setScreenShot(boolean screenShot) {
		this.screenShot = screenShot;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static ResultInfo getResultInfo() {
		return resultInfo;
	}

	public static void setResultInfo(ResultInfo resultInfo) {
		ResultInfo.resultInfo = resultInfo;
	}
}
