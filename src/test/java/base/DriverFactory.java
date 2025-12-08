package base;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Utilities.ConfigReader;


public class DriverFactory {
	private static ThreadLocal<WebDriver> driver=new ThreadLocal<>();
	
	public static WebDriver initDriver() {
		ConfigReader.loadProperties();
		String browser = System.getProperty("browser");
		System.out.println(">>>------------ Received browser from Jenkins: " + System.getProperty("browser"));
		System.out.println(">>>-------------- Browser after fallback logic: " + browser);

		if (browser == null || browser.isEmpty()) {
		  
		    browser = ConfigReader.getProperty("browser");
		}

		browser = browser.toLowerCase();
		switch(browser) {
		case "chrome":
			
			ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--start-maximized");
            options.addArguments("--incognito");  // prevents Chrome from remembering card inputs

            // Disable browser password manager popups
            options.addArguments("--disable-features=PasswordManagerEnabled");
            options.addArguments("--disable-features=PasswordLeakDialog");
            options.addArguments("--disable-features=CredentialManagementAPI");

            // Disable Chrome Autofill (very important for payment automation)
            options.addArguments("--disable-features=AutofillServerCommunication");
            options.addArguments("--disable-features=AutofillCreditCardUpload");
            options.addArguments("--disable-features=AutofillAddressProfileSavePrompt");
            options.addArguments("--disable-features=AutofillCreditCardSavePrompt");

            // Disable Chrome Payment Save Prompt (THE FIX FOR YOUR POPUP)
            options.addArguments("--disable-features=EnableAutofillCreditCardSavePrompt");
            options.addArguments("--disable-features=PaymentMethodSavePrompt");

            // Add experimental preferences
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("autofill.profile_enabled", false);
            prefs.put("autofill.credit_card_enabled", false);
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            prefs.put("payment_method_save_prompt_enabled", false);

            options.setExperimentalOption("prefs", prefs);


			
			driver.set(new ChromeDriver(options));
			break;
			
		case "firefox" :
			
			driver.set(new FirefoxDriver());
			break;
			
		case "edge":
		
			System.setProperty("webdriver.edge.driver", "C:\\Automation\\Selenium\\Drivers\\msedgedriver.exe");
			driver.set(new EdgeDriver());
			break;
			
			default:
                throw new IllegalArgumentException("❌ Invalid browser specified in config.properties: " + browser);
		}
		
	return driver.get();
		
	}
	
	public static WebDriver getDriver() {
		return driver.get();
	}
	
	public static void quitDriver(){
		if(driver.get() !=null) {
			getDriver().quit();
			driver.remove();
		}
		
	}

}
