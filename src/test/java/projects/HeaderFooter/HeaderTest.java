package projects.HeaderFooter;

/*
 * @Author: Phạm Nguyễn Ngọc Thảo
 * @Version: 1.0
 * @Function: Test Header
 * */

import common.BaseTest;
import constants.Constants;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import listeners.ExtentReportListener;
import listeners.SimpleListener;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import projects.userPages.HeaderPage;
import utils.LoggerUtil;

import java.time.Duration;

@Epic(Constants.EPIC_SEARCH_AND_HIRESERVICE)
@Story(Constants.USERSTORY_501)
@Feature(Constants.FEATURE_VERIFY_UI_HEADER)
@Listeners({SimpleListener.class, ExtentReportListener.class})
public class HeaderTest extends BaseTest {
    HeaderPage page = new HeaderPage();

    @Test(description = "Click vào logo góc trái")
    public void verifyTC501_ClickLogo(){
        driver.get(Constants.LINK_DESCRIPTIONSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement logo = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(page.logo)));
        logo.click();

        String expectedURL = Constants.LINK_WEBSITE;
        String actualURL = getDriver().getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL,
                    "[TC501] " + Constants.TEST_FAILED + " - URL không đúng sau khi click logo");
        LoggerUtil.pass("[TC501] " + Constants.TEST_PASSED + " - URL đúng sau khi click logo");
    }

    @Test(description = "Kiểm tra hiển thị thanh tìm kiếm")
    public void verifyTC502_CheckSearchBarDisplay(){
        driver.get(Constants.LINK_DESCRIPTIONSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement search = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(page.headerSearch)));
        if(search.isDisplayed()) {
            LoggerUtil.pass("[TC502] " + Constants.TEST_PASSED + "Có hiển thị thanh tìm kiếm");
        }
    }

    @Test(description = "Kiểm tra hiển thị thanh menu điều hướng chính")
    public void verifyTC503_CheckNavigationBar(){
        driver.get(Constants.LINK_DESCRIPTIONSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement iNavigation = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(page.navigationBar)));
        setLastInteractedElement(iNavigation);
        WebElement iFiverr = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(page.liFiverr)));
        WebElement iExplore = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(page.liExplore)));
        WebElement iLanguages = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(page.liLanguages)));
        WebElement iCurrency = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(page.liCurency)));
        WebElement iBecomeSeller = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(page.liBecomeSeller)));
        if(!iFiverr.isEnabled() || !iExplore.isEnabled() || !iLanguages.isEnabled()
            || !iCurrency.isEnabled() || !iBecomeSeller.isEnabled()){
            Assert.fail("[TC503]" + Constants.TEST_FAILED + "Không click được danh mục truy cập nhanh (Fiverr Business, Thay đổi ngôn ngữ English, Become a Seller... )");
            LoggerUtil.fail("[TC503] " + Constants.TEST_FAILED + "Không click được danh mục truy cập nhanh (Fiverr Business, Thay đổi ngôn ngữ English, Become a Seller... )");
        }
    }

    @Test(description = " Kiểm tra hiển thị nút Đăng nhập/Đăng ký tài khoản")
    public void verifyTC504_CheckDisplayLoginRegister(){
        driver.get(Constants.LINK_WEBSITE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement iJoin = wait.until(ExpectedConditions.visibilityOfElementLocated(page.liJoin));
        WebElement iSignIn = wait.until(ExpectedConditions.visibilityOfElementLocated(page.liSignin));
        if(iSignIn.isDisplayed() || iJoin.isDisplayed()){
            LoggerUtil.pass("[TC504] " + Constants.TEST_PASSED + " Có hiển thị nút Đăng nhập/Đăng ký tài khoản");
        } else {
            LoggerUtil.fail("[TC504] " + Constants.TEST_FAILED + " Không hiển thị nút Đăng nhập/Đăng ký tài khoản");
        }
    }

    public void runTest(){
        verifyTC501_ClickLogo();
        verifyTC502_CheckSearchBarDisplay();
        verifyTC503_CheckNavigationBar();
        verifyTC504_CheckDisplayLoginRegister();
    }
}
