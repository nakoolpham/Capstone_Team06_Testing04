package common;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import constants.Constants;
import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.LoggerUtil;

public class BaseTest {
    public WebDriver driver;
    public WebElement lastInteractedElement;
    ExtentReports extent;
    ExtentTest test;
    static Logger logger = LogManager.getLogger("Testing Website demo5.cybersoft.edu.vn Page ");
    //@Parameters(Constants.BROWSER) // lấy tham số từ testNG.xml
    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @BeforeMethod
    public void setupTest(@Optional(Constants.CHROME)String browser){ setup(browser);}

    public void setup(String browser) {
        LoggerUtil.info(Constants.SETTING_UP_WEB_DRIVER);
        DriverFactory driverFactory = new DriverFactory();
        driverFactory.setDriver(browser);
        driver = driverFactory.getDriver();
        driver.get(Constants.LINK_WEBSITE);
        LoggerUtil.info(Constants.ACCESSED_SUCCESSFULLY);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setLastInteractedElement(WebElement element) {
        this.lastInteractedElement = element;
    }
    public WebElement getLastInteractedElement() {
        return this.lastInteractedElement;
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
        // driver.close();
    }
}
