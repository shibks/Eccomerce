package Utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
	
	static Properties prop;
	
	public static void loadProperties() {
		if(prop==null) {
			prop=new Properties();
			try(FileInputStream fis=new FileInputStream("C:\\Automation\\Selenium\\java\\src\\test\\resources\\config.properties")){
				prop.load(fis);
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("----------CONFIG FILE NOT LOADED----------");
			}
		}
	}

	
	public static String getProperty(String key) {
		if(prop==null) {
			loadProperties();
		}
		
		return prop.getProperty(key);
	}
}
