package Listener;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Utilities.LogUtil;
import Utilities.cleanOldFile;
import org.openqa.selenium.*;
import org.testng.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportListener implements ITestListener {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    private static final String REPORT_FOLDER = System.getProperty("user.dir") + "/Reports/";
    private static final String SCREENSHOT_FOLDER = System.getProperty("user.dir") + "/Screenshots/";

    public synchronized static ExtentReports getExtentInstance() {

        if (extent == null) {

            cleanOldFile.cleanOldFiles(REPORT_FOLDER);
            cleanOldFile.cleanOldFiles(SCREENSHOT_FOLDER);

            new File(REPORT_FOLDER).mkdirs();
            new File(SCREENSHOT_FOLDER).mkdirs();

            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String reportPath = REPORT_FOLDER + "Enterprise_Automation_Report_" + timestamp + ".html";

            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setTheme(Theme.DARK);
            spark.config().setReportName("Enterprise UI Automation – Execution Summary");
            spark.config().setDocumentTitle("Automation Report – Enterprise Edition");
            spark.config().setTimelineEnabled(true);     // Enables timeline (Enterprise style)
            spark.config().setEncoding("UTF-8");
            spark.config().setTimeStampFormat("dd MMM yyyy HH:mm:ss");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            // ============ REAL INDUSTRY SYSTEM INFO ============
            extent.setSystemInfo("Executed By", "Shibin");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Build Version", "v1.4.3");
            extent.setSystemInfo("Browser", "Chrome 118");
            extent.setSystemInfo("Driver", "ChromeDriver 118");
            extent.setSystemInfo("Executor", "Jenkins CI/CD");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("Machine", System.getProperty("user.name"));
        }

        return extent;
    }

    // =================================================================
    //            SCREENSHOT CAPTURE (ENHANCED)
    // =================================================================
    private String captureScreenshot(WebDriver driver, String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String path = SCREENSHOT_FOLDER + testName + "_" + timestamp + ".png";

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(src.toPath(), new File(path).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }

    // =================================================================
    //                       TEST START
    // =================================================================
    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest test = getExtentInstance()
                .createTest(result.getMethod().getMethodName(),
                        result.getMethod().getDescription());

        // ====== Assign Tags – Real Industry Usage ======
        test.assignAuthor("Shibin");
        test.assignCategory("Regression");
        test.assignCategory("Module: " + result.getTestClass().getName());
        test.assignDevice("Chrome 118");

        testThread.set(test);

        test.log(Status.INFO, "🟦 Test Started – " + result.getMethod().getMethodName());
        LogUtil.setTest(test);
        LogUtil.info("---------Test Started → " + result.getMethod().getMethodName()+"------------");
    }

    // =================================================================
    //                       TEST SUCCESS
    // =================================================================
    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = testThread.get();
        test.log(Status.PASS, "🟢 Test Passed Successfully");
        LogUtil.setTest(test);
        LogUtil.info("---------Test Passed Success → " + result.getMethod().getMethodName()+"------------");
    }

    // =================================================================
    //                       TEST FAILURE
    // =================================================================
    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTest test = testThread.get();

        test.log(Status.FAIL, "🔴 Test Failed");
        test.log(Status.FAIL, result.getThrowable());

        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("WebDriver");

        if (driver != null) {
            String screenshotPath = captureScreenshot(driver, result.getMethod().getMethodName());

            try {
                test.addScreenCaptureFromPath(screenshotPath, 
                        "Screenshot on Failure: " + result.getMethod().getMethodName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        LogUtil.setTest(test);
        LogUtil.error("-----------Test Failed → " + result.getThrowable().getMessage()+"--------------");
    }
    

    // =================================================================
    //                       TEST SKIPPED
    // =================================================================
    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = testThread.get();
        test.log(Status.SKIP, "🟡 Test Skipped");
        test.log(Status.SKIP, result.getThrowable());
        
        LogUtil.setTest(test);
        LogUtil.warn("--------Test Skipped "+result.getMethod().getMethodName()+"---------");
    }

    // =================================================================
    //                   SUITE START
    // =================================================================
    @Override
    public void onStart(ITestContext context) {
        getExtentInstance();
    }

    // =================================================================
    //                   SUITE FINISH
    // =================================================================
    @Override
    public void onFinish(ITestContext context) {
        getExtentInstance().flush();
    }
}
