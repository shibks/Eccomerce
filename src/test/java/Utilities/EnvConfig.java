package Utilities;

public class EnvConfig {
	
	public static String getURL() {
		String URL=System.getProperty("env");
		switch(URL.toUpperCase()) {
		case "DEV" :
			return "https://automationexercise.com/";
		case "QA":
			default:
			return "https://automationexercise.com/";
		}
	}

}
