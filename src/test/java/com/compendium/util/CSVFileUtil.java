package  com.compendium.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.csvreader.CsvReader;
import com.training.info.Test1DataInfo;
import com.training.info.Test2DataInfo;


public class CSVFileUtil {

	static Logger log = Logger.getLogger(CSVFileUtil.class.getName());
	
	public static List<Object[]> readDataFilesForTest1()
			throws IOException {
		log.info("readAdvanceSearchDataFilesForTest started");
		List<Object[]> list = null;
		CsvReader csvReader =  null;
		try {		
			csvReader = new CsvReader("src/test/resources/DataFiles/Test2.csv");
			csvReader.readHeaders();
			list = new ArrayList<Object[]>();
			Test1DataInfo info = null;
			while (csvReader.readRecord()) {
				info = new Test1DataInfo();
				info.setCategory(csvReader.get("Category"));
				info.setLanguage(csvReader.get("Language"));
				info.setUrl(csvReader.get("URL"));
				printValues(info);
				list.add(new Object[] { info });
			}
			csvReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("readAdvanceSearchDataFilesForTest ended");
		return list;
	}
	
	public static List<Object[]> readDataFilesForTest2()
			throws IOException {
		log.info("readAdvanceSearchDataFilesForTest started");
		List<Object[]> list = null;
		CsvReader csvReader =  null;
		try {	
			csvReader = new CsvReader("src/test/resources/DataFiles/Test1.csv");
			csvReader.readHeaders();
			list = new ArrayList<Object[]>();
			Test2DataInfo info = null;
			while (csvReader.readRecord()) {
				info = new Test2DataInfo();
				info.setTrainingOptions(csvReader.get("Training Options"));
				info.setUrl(csvReader.get("URL"));
				info.setState(csvReader.get("State"));
				info.setMassage(csvReader.get("Massage"));
				info.setCourse(csvReader.get("Course"));
				printValues(info);
				list.add(new Object[] { info });
			}
			csvReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("readAdvanceSearchDataFilesForTest ended");
		return list;
	}

	
	private static  void printValues(Object obj) {
		log.info("printValues started");
        try {
        	for (Field field : obj.getClass().getDeclaredFields()) {
        	    field.setAccessible(true);
        	    String name = field.getName();
        	    Object value = field.get(obj);
        	    //System.out.printf("Field name: %s, Field value: %s%n", name, value);
        	    log.info("Field name: "+name+" Field value: "+value);
        	 
        	}
        } catch(IllegalArgumentException illegalArgumentException) {
        	log.info(illegalArgumentException.getMessage());
        } catch(IllegalAccessException illegalAccessException) {
        	log.info(illegalAccessException.getMessage());
        }
        log.info("printValues ended");
    }
 }
	
