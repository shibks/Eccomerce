
package Listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Utilities.cleanOldFile;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ExtentReportListener implements ITestListener {

	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();
	private static Map<Long, ExtentTest> testMap = new HashMap<>();
	
	private static final String REPORT_FOLDER = System.getProperty("user.dir") + "/Reports";
    private static final String SCREENSHOT_FOLDER = System.getProperty("user.dir") + "/screenshots";

	// Initialize report only once
	public synchronized static ExtentReports getExtentInstance() {
		if (extent == null) {
			
			cleanOldFile.cleanOldFiles(REPORT_FOLDER);
            cleanOldFile.cleanOldFiles(SCREENSHOT_FOLDER);
			String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
			//String reportFolderPath = System.getProperty("user.dir") + "/Reports";
            String reportPath = REPORT_FOLDER + "/ExtentReport_" + timestamp + ".html";

            // ✅ Create Reports folder if it doesn't exist
            File reportFolder = new File(REPORT_FOLDER);
            if (!reportFolder.exists()) {
                reportFolder.mkdir();
                System.out.println("Created Reports folder: " + REPORT_FOLDER);
            }

			ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
			spark.config().setTheme(Theme.STANDARD);
			spark.config().setDocumentTitle("Automation Execution Report");
			spark.config().setReportName("UI Automation Test Results");

			extent = new ExtentReports();
			extent.attachReporter(spark);
			extent.setSystemInfo("Tester", "Shibin");
			extent.setSystemInfo("Browser", "Chrome");
			extent.setSystemInfo("OS", System.getProperty("os.name"));
			extent.setSystemInfo("Environment", "QA");
		}
		return extent;
	}

	// Capture Screenshot
	private String captureScreenshot(WebDriver driver, String testName) {
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String destPath = SCREENSHOT_FOLDER + testName + "_" + timestamp + ".png";
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			Files.copy(src.toPath(), new File(destPath).toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destPath;
	}

	// Test Start
	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest test = getExtentInstance().createTest(result.getMethod().getMethodName());
		testThread.set(test);
		testMap.put(Thread.currentThread().getId(), test);
		test.log(Status.INFO, "Test Started:----------------------------" + result.getMethod().getDescription());
	}

	// Test Pass
	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentTest test = testThread.get();
		test.log(Status.PASS, "✅ Test Passed:--------------------------- " + result.getMethod().getMethodName());
	}

	// Test Failure
	@Override
	public void onTestFailure(ITestResult result) {
		ExtentTest test = testThread.get();
		test.log(Status.FAIL, "❌ Test Failed:---------------------------- " + result.getThrowable());

		WebDriver driver = (WebDriver) result.getTestContext().getAttribute("WebDriver");
		if (driver != null) {
			String screenshotPath = captureScreenshot(driver, result.getMethod().getMethodName());
			try {
				test.addScreenCaptureFromPath(screenshotPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// Test Skipped
	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentTest test = testThread.get();
		test.log(Status.SKIP, "⚠️ Test Skipped:--------------------------- " + result.getMethod().getMethodName());
	}

	// Suite Start
	@Override
	public void onStart(ITestContext context) {
		getExtentInstance();
	}

	// Suite Finish
	@Override
	public void onFinish(ITestContext context) {
		getExtentInstance().flush();
	}
}
