package projects.HeaderFooter;

/*
 * @Author: Phạm Nguyễn Ngọc Thảo
 * @Version: 1.0
 * @Function: Test Footer
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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import projects.userPages.FooterPage;
import utils.LoggerUtil;

import java.time.Duration;


@Epic(Constants.EPIC_SEARCH_AND_HIRESERVICE)
@Story(Constants.USERSTORY_501)
@Feature(Constants.FEATURE_VERIFY_UI_FOOTER)
@Listeners({SimpleListener.class, ExtentReportListener.class})
public class FooterTest extends BaseTest {
    FooterPage page = new FooterPage();

//    @BeforeMethod
//    @Override
//    public void setupTest(String browser) {
//        super.setupTest(browser);
//    }

//    public void runTest() {
//        super.setupTest();
//        LoggerUtil.info("Run UI Footer Test");
//        System.out.println("");
//        verifyTC508_ClickAccessibility();
//        verifyTC509_ClickLinkFooterTop();
//        verifyTC510_ClickLanguagesCurrency();
//        verifyTC510_ClickSocialFooter();
//    }

    @Test(description = "Click icon accessibility profiles")
    public void verifyTC508_ClickAccessibility() throws InterruptedException {
        driver.get(Constants.LINK_DESCRIPTIONSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement iAccess = wait.until(ExpectedConditions.elementToBeClickable(page.iconAccessibility));
        Thread.sleep(500);
        setLastInteractedElement(iAccess);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'nearest'});", iAccess);
        Thread.sleep(500);
        new Actions(driver).moveToElement(iAccess).perform();
        WebElement profilePanel = wait.until(ExpectedConditions.visibilityOfElementLocated(page.accessibilityPanel));
        Assert.assertTrue(profilePanel.isDisplayed(),
                    "[TC508] " + Constants.TEST_FAILED + " - Không hiển thị accessibility profiles sau khi click");
        LoggerUtil.pass("[TC508] " + Constants.TEST_PASSED + " - Click accessibility profiles thành công");
    }

    @Test(description = "Click liên kết: Chính sách, Điều khoản, Giới thiệu....")
    public void verifyTC509_ClickLinkFooterTop() throws InterruptedException {
        driver.get(Constants.LINK_DESCRIPTIONSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement footertop = wait.until(ExpectedConditions.elementToBeClickable(page.footerTop));
        WebElement careers = wait.until(ExpectedConditions.elementToBeClickable(page.careers));
        Thread.sleep(500);
        setLastInteractedElement(footertop);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'nearest'});", footertop);
        Thread.sleep(500);
        new Actions(driver).moveToElement(careers).perform();

        String expectedURL = Constants.LINK_WEBSITE + "careers";
        String actualURL = getDriver().getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL,
                "[TC509] " + Constants.TEST_FAILED + " Không hiển thị liên kết sau khi click");
        LoggerUtil.pass("[TC509] " + Constants.TEST_PASSED + " Click liên kết thành công");
    }

    @Test(description = "Click liên kết: Chuyền đổi ngôn ngữ, Chuyển đổi tiền tệ, Icon mạng xã hội")
    public void verifyTC510_ClickSocialFooter() throws InterruptedException {
        driver.get(Constants.LINK_DESCRIPTIONSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement social = wait.until(ExpectedConditions.elementToBeClickable(page.socialFooter));
        Thread.sleep(500);
        setLastInteractedElement(social);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'nearest'});", social);
        Thread.sleep(500);
        new Actions(driver).moveToElement(social).perform();

        String expectedURL = Constants.LINK_WEBSITE + "social";
        String actualURL = getDriver().getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL,
                "[TC510] " + Constants.TEST_FAILED + " Không click được các icon social trên footer");
        LoggerUtil.pass("[TC510] " + Constants.TEST_PASSED + " Click liên kết thành công");
    }

    @Test(description = "Click liên kết: Chuyền đổi ngôn ngữ, Chuyển đổi tiền tệ, Icon mạng xã hội")
    public void verifyTC510_ClickLanguagesCurrency() throws InterruptedException {
        driver.get(Constants.LINK_DESCRIPTIONSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement languages = wait.until(ExpectedConditions.elementToBeClickable(page.languagesFooter));
        WebElement currency = wait.until(ExpectedConditions.elementToBeClickable(page.currencyFooter));
        WebElement right = wait.until(ExpectedConditions.elementToBeClickable(page.rightFooter));

        Thread.sleep(500);
        setLastInteractedElement(right);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'nearest'});", right);
        Thread.sleep(500);
        WebElement profilePanel = wait.until(ExpectedConditions.visibilityOfElementLocated(page.accessibilityPanel));
        Assert.assertTrue(profilePanel.isDisplayed(),"[TC510]" + Constants.TEST_FAILED + " Không click được các icon Language & Currency trên footer" );
    }

    public void runTest() throws InterruptedException {
        verifyTC508_ClickAccessibility();
        verifyTC509_ClickLinkFooterTop();
        verifyTC510_ClickLanguagesCurrency();
        verifyTC510_ClickSocialFooter();
    }
}
