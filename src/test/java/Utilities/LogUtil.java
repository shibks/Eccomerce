package Utilities;

import com.aventstack.extentreports.ExtentTest;

public class LogUtil {

    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    // Set test from listener
    public static void setTest(ExtentTest extentTest) {
        test.set(extentTest);
    }

    // INFO LOG
    public static void info(String message) {
        test.get().info("ℹ️ " + message);
    }

    // WARNING LOG
    public static void warn(String message) {
        test.get().warning("⚠️ " + message);
    }

    // ERROR LOG
    public static void error(String message) {
        test.get().fail("❌ " + message);
    }

    // PASS LOG
    public static void pass(String message) {
        test.get().pass("🟢 " + message);
    }

    // FAIL LOG
    public static void fail(String message) {
        test.get().fail("🔴 " + message);
    }
}
