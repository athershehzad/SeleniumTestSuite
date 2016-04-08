package com.training.test;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.compendium.util.CSVFileUtil;
import com.training.framwork.BaseTest;
import com.training.info.Test1DataInfo;

public class CreateTest1 extends BaseTest {

	static Logger log = Logger.getLogger(CreateTest1.class.getName());
	
	@DataProvider(name = "test")
	public static Iterator<Object[]> data() throws IOException {
		List<Object[]> dataList = CSVFileUtil.readDataFilesForTest1();
		return dataList.iterator();
	}
	
	@Test(dataProvider = "test")
	public void testCreateTest1(Test1DataInfo test1DataInfo) {
	driver.get(test1DataInfo.getUrl());
			log.info("testCreateTest1 Started");
			
	}
}
