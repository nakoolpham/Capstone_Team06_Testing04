package utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtil {
    private static final Logger log4jLogger = LogManager.getLogger(LoggerUtil.class);
    private static ExtentTest extentTest;

    // Gắn ExtentTest hiện tại (set trong BeforeMethod của TestNG)
    public static void setExtentTest(ExtentTest test) {
        extentTest = test;
    }

    public static void info(String message) {
        log4jLogger.info(message);
        if (extentTest != null) extentTest.log(Status.INFO, message);
    }

    public static void pass(String message) {
        log4jLogger.info("[PASS] " + message);
        if (extentTest != null) extentTest.log(Status.PASS, message);
    }

    public static void fail(String message) {
        log4jLogger.error("[FAIL] " + message);
        if (extentTest != null) extentTest.log(Status.FAIL, message);
    }

    public static void warning(String message) {
        log4jLogger.warn(message);
        if (extentTest != null) extentTest.log(Status.WARNING, message);
    }
}
