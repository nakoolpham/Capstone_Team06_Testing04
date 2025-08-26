
package projects.testcases.ServiceDetail;

/*
    @Author: Phạm Nguyễn Ngọc Thảo
    @Version:1.0
    @Script Test: Test Trending Service
*/


import common.BaseTest;
import constants.Constants;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import listeners.ExtentReportListener;
import listeners.SimpleListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import projects.userPages.TrendingServicesPage;
import utils.LoggerUtil;

import java.time.Duration;

@Epic(Constants.EPIC_SEARCH_AND_HIRESERVICE)
@Story(Constants.USERSTORY_201)
@Feature(Constants.FEATURE_VERIFY_TRENDINGSERVICEPAGE)
@Listeners({SimpleListener.class, ExtentReportListener.class})
public class TrendingServicesTest extends BaseTest {
    TrendingServicesPage page = new TrendingServicesPage();
    static Logger logger = LogManager.getLogger("Testing Trending Service Page (Service Detail)");

    @Test(description = "Kiểm tra hoạt động của nút How Fiverr Works (title)")
    public void verifyTC202_ButtonHowFiverrWorks() {
        driver.get(Constants.LINK_TRENDINGSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(page.btnHowFiverrWorks));
        setLastInteractedElement(element);
        element.click();

        if (!driver.findElements(page.modalHowFiverrWorks).isEmpty()) {
            WebElement elModal = wait.until(ExpectedConditions.visibilityOfElementLocated(page.modalHowFiverrWorks));
            Assert.assertTrue(elModal.isDisplayed());
        } else {
            Assert.fail("[TC202] " + Constants.TEST_FAILED + " Modal không hiển thị sau khi click button!");
            LoggerUtil.fail("[TC202] " + Constants.TEST_FAILED + " Modal không hiển thị sau khi click button!");
        }
    }

    @Test(description = "Kiểm tra danh mục menu (title)")
    public void verifyTC204_MenuTitle() {
        driver.get(Constants.LINK_TRENDINGSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(page.titleDigitalMarketing));
        element.click();

        String expectedUrl = "https://demo5.cybersoft.edu.vn/title/2";
        String actualUrl = driver.getCurrentUrl();
        if (actualUrl.equals(expectedUrl)) {
            LoggerUtil.pass("[TC204] " + Constants.TEST_PASSED + "Hiển thị đúng link title");
        } else {
            LoggerUtil.fail("[TC204] " + Constants.TEST_FAILED + "Không hiển thị đúng link title");
        }
    }

    @Test(description = "Kiểm tra phần hiển thị Tags trong mục Related job titile (title)")
    public void verifyTC205_TagNavigation() {
        driver.get(Constants.LINK_TRENDINGSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(page.tagMinimalistLogoDesign));
        setLastInteractedElement(element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        new Actions(driver).moveToElement(element).click().perform();

        String expectedUrl = "https://demo5.cybersoft.edu.vn/title/2";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl,
                "[TC205] " + Constants.TEST_FAILED + "Tags không hoạt động");
        LoggerUtil.fail("[TC205] " + Constants.TEST_FAILED + "Tags không hoạt động");
    }

    @Test(description = "Kiểm tra danh mục menu (categories)")
    public void verifyTC213_MenuCategories() {
        driver.get(Constants.LINK_TRENDINGSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement eleTitle = wait.until(ExpectedConditions.elementToBeClickable(page.titleDigitalMarketing));
        new Actions(driver).moveToElement(eleTitle).perform();

        WebElement eleCategorie = wait.until(ExpectedConditions.elementToBeClickable(page.itemCategoriesSocialMediaMarketing));
        eleCategorie.click();

        String expectedURL = "https://demo5.cybersoft.edu.vn/categories/8";
        String actualURL = driver.getCurrentUrl();
        if (actualURL.equals(expectedURL)) {
            LoggerUtil.pass("[TC213] " + Constants.TEST_PASSED + "Chuyển đúng trang");
        } else {
            LoggerUtil.fail("[TC213] " + Constants.TEST_FAILED + "Chuyển không đúng trang");
        }
    }

    public void runTest() {
        verifyTC202_ButtonHowFiverrWorks();
        verifyTC204_MenuTitle();
        verifyTC205_TagNavigation();
        verifyTC213_MenuCategories();
    }
}
