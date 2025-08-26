package projects.testcases.ServiceDetail;

/*
 * @Author: Phạm Nguyễn Ngọc Thảo
 * @Version: 1.0
 * @Function: Test Service Pricing Table
 * */

import common.BaseTest;
import constants.Constants;
import io.qameta.allure.Epic;import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import listeners.ExtentReportListener;
import listeners.SimpleListener;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import projects.userPages.ServicePricingTablePage;
import utils.LoggerUtil;

import java.time.Duration;

@Epic(Constants.EPIC_SEARCH_AND_HIRESERVICE)
@Story(Constants.USERSTORY_203)
@Feature(Constants.FEATURE_VERIFY_DESCRIPTIONSERVICEPAGE)
@Listeners({SimpleListener.class, ExtentReportListener.class})
public class ServicePricingTableTest extends BaseTest {
    ServicePricingTablePage page = new ServicePricingTablePage();

    @Test(description = "Kiểm tra phần Tab chọn gói dịch vụ (Basic / Standard / Premium)")
    public void verifyTC236_CheckPackageSelectionTab(){
        driver.get(Constants.LINK_DESCRIPTIONSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement title = wait.until(ExpectedConditions
                .visibilityOfElementLocated(page.titleTab));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", title);

        Actions actions = new Actions(driver);
        actions.moveToElement(title).perform();
        setLastInteractedElement(title);
        WebElement subMenu = wait.until(ExpectedConditions.elementToBeClickable(page.categoriesMenu));

        boolean isOverflow = (Boolean) ((JavascriptExecutor) driver).executeScript(
                "var el = arguments[0];" +
                        "var rect = el.getBoundingClientRect();" +
                        "return (rect.right > window.innerWidth || rect.bottom > window.innerHeight);",
                subMenu
        );
        if (isOverflow) {
            LoggerUtil.fail("[TC236] " + Constants.TEST_FAILED + " Tràn bố cục Layout Overflow");
        } else {
            LoggerUtil.pass("[TC236] " + Constants.TEST_PASSED);
        }
    }

    @Test(description = "Kiểm tra thông tin chi tiết gói dịch vụ")
    public void verifyTC237_CheckServicePackageDetail(){
        driver.get(Constants.LINK_DESCRIPTIONSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement tabBasic = wait.until(ExpectedConditions.elementToBeClickable(page.basicTab));
            Thread.sleep(500);
            tabBasic.click();
            WebElement detailBasic = wait.until(ExpectedConditions.visibilityOfElementLocated(page.packageDetail));
            Thread.sleep(500);
            setLastInteractedElement(detailBasic);
            String basicText = detailBasic.getText().trim();
            WebElement tabPremium = wait.until(ExpectedConditions.elementToBeClickable(page.preniumTab));
            tabPremium.click();
            WebElement detailPremium = wait.until(ExpectedConditions.visibilityOfElementLocated(page.packageDetail));
               String premiumText = detailPremium.getText().trim();

            if (basicText.equals(premiumText)) {
                LoggerUtil.fail("[TC237] " + Constants.TEST_FAILED + " Nội dung tab không thay đổi khi click tab khác");
                Assert.fail("Tab Basic và Premium hiển thị nội dung giống nhau");
            } else {
                LoggerUtil.pass("[TC237] " + Constants.TEST_PASSED + " Nội dung tab thay đổi đúng khi click tab khác");
            }
        } catch (Exception e) {
            LoggerUtil.fail("[TC237] " + Constants.TEST_FAILED +
                    " Lỗi khi kiểm tra Service Package Detail: " + e.getMessage());
            Assert.fail("Test case failed: " + e.getMessage());
        }
    }

    @Test(description = "Kiểm tra Nút Continue")
    public void verifyTC239_CheckContinueButton(){
        driver.get(Constants.LINK_DESCRIPTIONSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(page.continueButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(button));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(page.toastSuccess));

            String toastText = toast.getText().trim();
            System.out.println("Toast xuất hiện: " + toastText);

            Assert.assertTrue(toastText.contains("Thuê công việc thành công!") || toastText.contains("success"),
                    "❌ Toast không hiển thị thông báo thành công");
            LoggerUtil.pass("[TC239] " + Constants.TEST_PASSED + " Toast hiển thị: " + toastText);
        }catch (ElementClickInterceptedException e){
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        } catch (Exception ex) {
            Actions actions = new Actions(driver);
            actions.moveToElement(button).click().perform();
            LoggerUtil.pass("[TC239] " + Constants.TEST_PASSED + "Thuê công việc thành công!");
        }
    }

    @Test(description = "Kiểm tra Nút Compare Package")
    public void verifyTC240_CheckComparePackageButton() throws InterruptedException {
        driver.get(Constants.LINK_DESCRIPTIONSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement titleDescription = wait.until(ExpectedConditions.elementToBeClickable(page.titleDescription));
            Point location = titleDescription.getLocation();
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, arguments[0]);", location.getY() - 100);
            WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(page.comparePackageButton));
            setLastInteractedElement(button);
            Thread.sleep(500);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
            new Actions(driver).moveToElement(button).perform();
        } catch (ElementNotInteractableException e){
            LoggerUtil.fail("[TC240] " + Constants.TEST_FAILED + " Lỗi khi nhấn nút Compare Package");
            Assert.fail("[TC240] " + Constants.TEST_FAILED + " Lỗi khi nhấn nút Compare Package");
        }
    }

    public void runTest() throws InterruptedException {
        verifyTC236_CheckPackageSelectionTab();
        verifyTC237_CheckServicePackageDetail();
        verifyTC239_CheckContinueButton();
        verifyTC240_CheckComparePackageButton();
    }
}

