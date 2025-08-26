package helpers;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ScreenshotHighlighter;

import java.io.File;

public class ReportHelper {
    public static void attachScreenshotToReport(WebDriver driver, WebElement element,
                                                String testName, String note, ExtentTest test) {
        try {
            // Gọi hàm highlight để chụp screenshot
            String screenshotPath = ScreenshotHighlighter.highlight(driver, element, testName, note);

            // Đường dẫn tương đối cho ExtentReport
            String relativePath = "../screenshots/" + new File(screenshotPath).getName();

            // Attach vào report
            test.fail(note, MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());

        } catch (Exception e) {
            test.warning("Không attach được screenshot: " + e.getMessage());
        }
    }
}
