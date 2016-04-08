package com.training.test;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.compendium.util.CSVFileUtil;
import com.training.framwork.BaseTest;
import com.training.info.Test2DataInfo;

public class CreateTest2 extends BaseTest {

	static Logger log = Logger.getLogger(CreateTest2.class.getName());
	
	@DataProvider(name = "test")
	public static Iterator<Object[]> data() throws IOException {
		List<Object[]> dataList = CSVFileUtil.readDataFilesForTest2();
		return dataList.iterator();
	}
	
	@Test(dataProvider = "test")
	public void testCreateTest2(Test2DataInfo test2DataInfo) {
		driver.get(test2DataInfo.getUrl());
			log.info("testCreateTest2 Started");
			
	}
}
