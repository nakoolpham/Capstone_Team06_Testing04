package listeners;

import common.BaseTest;
import org.openqa.selenium.*;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class SimpleListener implements ITestListener {
    public void onTestFailure(ITestResult result) {
        System.out.println("Screenshot captured for test case: " + result.getName());

        BaseTest testInstance = (BaseTest) result.getInstance();
        WebDriver driver = testInstance.getDriver();
        WebElement lastElement = testInstance.getLastInteractedElement();

        // lấy exception message làm ghi chú
        String note = result.getThrowable() != null
                ? result.getThrowable().getMessage()
                : "Test Failed";

//        try {
//            // gọi highlight (thay vì chỉ chụp thường)
//            ScreenshotHighlighter.highlight(
//                    driver,
//                    lastElement,
//                    //result.getName() + "_" + System.currentTimeMillis(),
//                    result.getName(),
//                    note
//            );
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public void onTestStart(ITestResult result) {/* compiled code */}
    public void onTestSuccess(ITestResult result) { /* compiled code */ }
    public void onTestSkipped(ITestResult result) { /* compiled code */}
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) { /* compiled code */ }
    public void onTestFailedWithTimeout(ITestResult result) { /*compiled code */ }
    public void onStart(org.testng.ITestContext context) { /* compiled code */ }
    public void onFinish(org.testng.ITestContext context) { /* compiled code */ }

}
