package projects.testcases.ServiceDetail;

/*
 * @Author: Phạm Nguyễn Ngọc Thảo
 * @Version: 1.0
 * @Function: Test Seller Info
 * */

import common.BaseTest;
import constants.Constants;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import listeners.ExtentReportListener;
import listeners.SimpleListener;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import projects.userPages.SellerInfoPage;
import utils.LoggerUtil;

import java.time.Duration;

@Epic(Constants.EPIC_SEARCH_AND_HIRESERVICE)
@Story(Constants.USERSTORY_204)
@Feature(Constants.FEATURE_VERIFY_DESCRIPTIONSERVICEPAGE)
@Listeners({SimpleListener.class, ExtentReportListener.class})
public class SellerInfoTest extends BaseTest {
    SellerInfoPage page = new SellerInfoPage();

    @Test(description = "Kiểm tra phần hiển thị Thông tin hồ sơ cá nhân")
    public void verifyTC241_CheckAvatarSeller(){
        driver.get(Constants.LINK_DESCRIPTIONSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement avatar = wait.until(ExpectedConditions.visibilityOfElementLocated(page.avartarSeller));
        setLastInteractedElement(avatar);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", avatar);

        Object result = ((JavascriptExecutor) driver).executeScript(
                "if(arguments[0] && arguments[0].tagName.toLowerCase() === 'img') {" +
                        "   return arguments[0].complete && " +
                        "   typeof arguments[0].naturalWidth != 'undefined' && " +
                        "   arguments[0].naturalWidth > 0;" +
                        "} else { return false; }", avatar);

        boolean isImageLoaded = result != null && (Boolean) result;
        if (!isImageLoaded) {
            Assert.fail("[TC241] " + Constants.TEST_FAILED + " Ảnh avatar bị lỗi hoặc không load được!");
            LoggerUtil.fail("[TC241] " + Constants.TEST_FAILED + " Ảnh avatar bị lỗi hoặc không load được!");
        } else {
            LoggerUtil.pass("[TC241] " + Constants.TEST_PASSED + " Ảnh avatar load thành công!");
        }
    }

    @Test(description = "Kiểm tra Nút Contact me")
    public void verifyTC244_CheckButtonContactMe() {
        driver.get(Constants.LINK_DESCRIPTIONSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(page.contactMeButton));
        setLastInteractedElement(button);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
        String currentUrl = driver.getCurrentUrl();
        new Actions(driver).moveToElement(button).perform();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String newUrl = driver.getCurrentUrl();
        if (newUrl.equals(currentUrl)) {
            LoggerUtil.fail("[TC244] " + Constants.TEST_FAILED + " Button Contact Me KHÔNG hoạt động (không chuyển trang)");
            Assert.fail("[TC244] " + Constants.TEST_FAILED + " Button Contact Me KHÔNG hoạt động (không chuyển trang)");
        } else {
            Assert.fail("[TC244] " + Constants.TEST_PASSED + " Button Contact Me HOẠT ĐỘNG (URL thay đổi)");
            LoggerUtil.fail("[TC244] " + Constants.TEST_PASSED + " Button Contact Me HOẠT ĐỘNG (URL thay đổi)");
        }
    }

    public void runTest(){
        verifyTC241_CheckAvatarSeller();
        verifyTC244_CheckButtonContactMe();
    }

}
