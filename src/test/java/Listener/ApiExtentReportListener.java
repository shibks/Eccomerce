package Listener;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.restassured.response.Response;
import org.testng.*;

import Utilities.cleanOldFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ApiExtentReportListener implements ITestListener {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    private static final String REPORT_FOLDER = System.getProperty("user.dir") + "/ApiReports/";

    // ---------------------- CREATE EXTENT INSTANCE ----------------------
    public synchronized static ExtentReports getExtentInstance() {

        if (extent == null) {

            cleanOldFile.cleanOldFiles(REPORT_FOLDER);
            new File(REPORT_FOLDER).mkdirs();

            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String reportPath = REPORT_FOLDER + "API_Test_Report_" + timestamp + ".html";

            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setTheme(Theme.DARK);
            spark.config().setDocumentTitle("API Automation Execution Report");
            spark.config().setReportName("REST API Test Summary");
            spark.config().setTimeStampFormat("dd MMM yyyy HH:mm:ss");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Executed By", "Shibin");
            extent.setSystemInfo("Framework", "Rest Assured + TestNG");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Base URI", "https://automationexercise.com");
            extent.setSystemInfo("Executor", "Jenkins CI/CD");
        }

        return extent;
    }



    // ---------------------- TEST START ----------------------
    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest test = getExtentInstance()
                .createTest(result.getMethod().getMethodName())
                .assignCategory("API Test")
                .assignAuthor("Shibin");

        test.log(Status.INFO, "🟦-----Test Started-------: " + result.getMethod().getMethodName());

        testThread.set(test);
    }



    // ---------------------- TEST PASS ----------------------
    @Override
    public void onTestSuccess(ITestResult result) {
        testThread.get().log(Status.PASS, "🟢------Test Passed Successfully------");
    }



    // ---------------------- TEST FAILURE ----------------------
    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTest test = testThread.get();

        test.log(Status.FAIL, "🔴-----Test Failed------");
        test.log(Status.FAIL, result.getThrowable());

        // LOG API RESPONSE ON FAILURE (if available)
        Object apiRes = result.getTestContext().getAttribute("API_RESPONSE");

        if (apiRes instanceof Response) {
            Response res = (Response) apiRes;

            test.log(Status.INFO, "📌 Response on Failure:");
            test.log(Status.INFO, "<pre>" + res.asPrettyString() + "</pre>");
        }
    }



    // ---------------------- TEST SKIPPED ----------------------
    @Override
    public void onTestSkipped(ITestResult result) {
        testThread.get().log(Status.SKIP, "🟡 Test Skipped");
    }


    // ---------------------- ON FINISH ----------------------
    @Override
    public void onFinish(ITestContext context) {
        getExtentInstance().flush();
    }
}
