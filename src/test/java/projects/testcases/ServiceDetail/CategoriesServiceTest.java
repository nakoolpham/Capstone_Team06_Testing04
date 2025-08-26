package projects.testcases.ServiceDetail;

/*
 * @Author: Phạm Nguyễn Ngọc Thảo
 * @Version: 1.0
 * @Function: Test Categories Service
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
import projects.userPages.ServiceCardPage;
import utils.LoggerUtil;

import java.time.Duration;
@Epic(Constants.EPIC_SEARCH_AND_HIRESERVICE)
@Story(Constants.USERSTORY_201)
@Feature(Constants.FEATURE_VERIFY_CATEGORIESSERVICEPAGE)
@Listeners({SimpleListener.class, ExtentReportListener.class})
public class CategoriesServiceTest extends BaseTest {
    ServiceCardPage page = new ServiceCardPage();
    @Test(description = "Kiểm tra layout danh mục")
    public void verifyTC208_LayoutCategoriesMenu(){
        driver.get(Constants.LINK_TRENDINGSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement parentMenu = wait.until(ExpectedConditions.visibilityOf(driver.findElement(page.titleTesting)));
        Actions actions = new Actions(driver);
        actions.moveToElement(parentMenu).perform();
        WebElement subMenu = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(page.categoriesmenuWebAppDesign)));
        subMenu.click();

        String expectedText = "1 services available";
        String actualText = driver.findElement(page.preTitle).getText();
        Assert.assertEquals(actualText,expectedText,
                "[TC208] " + Constants.TEST_FAILED + "Tràn bố cục Layout Overflow");
        LoggerUtil.fail("[TC208] " + Constants.TEST_FAILED + "Tràn bố cục Layout Overflow");
    }

    @Test(description = "Kiểm tra chức năng Dropdown Filter (categories)")
    public void verifyTC209_DropdownFilterNavigation() throws InterruptedException {
        driver.get(Constants.EXPECTED_TAG_URL_CATEGORIES_2);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(page.dropdownCategory));
        dropdown.click();
        Thread.sleep(500);
        setLastInteractedElement(dropdown);
        WebElement dropdownItem = wait.until(ExpectedConditions.visibilityOfElementLocated(page.dropdownCategoryItem));
        Thread.sleep(500);
        dropdownItem.click();

        String expectedUrl = "https://demo5.cybersoft.edu.vn/categories/2#WebPrograming";
        String actualUrl = driver.getCurrentUrl();
        try {
            Assert.assertEquals(actualUrl, expectedUrl,
                    "[TC209] " + Constants.TEST_FAILED + " - URL không đúng sau khi click dropdown");
            LoggerUtil.pass("[TC209] Passed - URL đúng sau khi click dropdown");
        } catch (AssertionError e) {
            LoggerUtil.fail("[TC209] " + Constants.TEST_FAILED + " - Expected: " + expectedUrl + " | Actual: " + actualUrl);
            throw e;
        }
    }

    @Test(description = "Kiểm tra chức năng Toggle Switch (categories)")
    public void verifyTC210_ToggleSwitchNavigation() throws InterruptedException {
        driver.get(Constants.EXPECTED_TAG_URL_CATEGORIES_2);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement toggleswitch = wait.until(ExpectedConditions.elementToBeClickable(page.toggleSwitchProService));
        Thread.sleep(500);
        setLastInteractedElement(toggleswitch);
        toggleswitch.click();

        String expectedText = "1 services available";
        String actualText = driver.findElement(page.preTitle_2).getText();
        Assert.assertEquals(actualText,expectedText,
                "[TC210] " + Constants.TEST_FAILED + "Toggle Switch không hoạt động");
        LoggerUtil.fail("[TC210] " + Constants.TEST_FAILED + "Toggle Switch không hoạt động");
    }

    @Test(description = "Kiểm tra chức năng Sort by (categories)")
    public void verifyTC211_SortByNavigation() throws InterruptedException {
        driver.get(Constants.EXPECTED_TAG_URL_CATEGORIES_2);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(page.sortBy));
        Thread.sleep(500);
        setLastInteractedElement(element);
        element.click();
        WebElement sortByItem = wait.until(ExpectedConditions.visibilityOfElementLocated(page.sortByBestSelling));
        sortByItem.click();
        Thread.sleep(500);

        String expectedText = "1 services available";
        String actualText = driver.findElement(page.preTitle_2).getText();
        Assert.assertEquals(actualText,expectedText,
                "[TC211] " + Constants.TEST_FAILED + "Sort by không hoạt động");
        LoggerUtil.fail("[TC211] " + Constants.TEST_FAILED + "Sort by không hoạt động");
    }

    @Test(description = "Kiểm tra hiển thị hình ảnh Seller trên Service Card (categories)")
    public void verifyTC218_ImageSellerServiceCard() {
        driver.get(Constants.EXPECTED_TAG_URL_CATEGORIES_2);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement img = wait.until(ExpectedConditions.visibilityOfElementLocated(page.imageSeller));
        setLastInteractedElement(img);

        Boolean isImageLoaded = (Boolean) ((JavascriptExecutor)driver)
                .executeScript("return arguments[0].complete && arguments[0].naturalWidth > 0;", img);
        if (!isImageLoaded) {
            Assert.fail("[TC218]" + Constants.TEST_FAILED + "Ảnh bị lỗi hoặc không load được!");
            LoggerUtil.fail("[TC218] " + Constants.TEST_FAILED + "Ảnh bị lỗi hoặc không load được!");
        } else {
            LoggerUtil.pass("[TC218] " + Constants.TEST_PASSED + "Ảnh load thành công!");
        }
    }

    @Test(description = "Kiểm tra hiển thị tên Seller trên Service Card (categories)")
    public void verifyTC219_NameSellerServiceCard() throws InterruptedException {
        driver.get(Constants.EXPECTED_TAG_URL_CATEGORIES_2);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement name = wait.until(ExpectedConditions.elementToBeClickable(page.nameSeller));
        setLastInteractedElement(name);
        new Actions(driver).moveToElement(name).perform();
        Thread.sleep(500);

        String expectedUrl = "https://demo5.cybersoft.edu.vn/sellerinfo";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl,
                "[TC219] " + Constants.TEST_FAILED + "Không click hoặc hover tên seller");
        LoggerUtil.fail("[TC219] " + Constants.TEST_FAILED + "Không click hoặc hover tên seller");
    }

    @Test(description = "Kiểm tra hiển thị thông tin trên Footer Service Card")
    public void verifyTC220_AddToFavorite_StartingAdd() throws InterruptedException {
        driver.get(Constants.EXPECTED_TAG_URL_CATEGORIES_2);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement heartIcon = wait.until(ExpectedConditions.elementToBeClickable(page.addToFavorite));
        setLastInteractedElement(heartIcon);
        new Actions(driver).moveToElement(heartIcon).perform();
        Thread.sleep(500);
        WebElement startingat = wait.until(ExpectedConditions.elementToBeClickable(page.startingAt));
        new Actions(driver).moveToElement(startingat).perform();
        Thread.sleep(500);

        String favClass = heartIcon.getAttribute("class");
        Assert.assertTrue(favClass.contains("active") || favClass.contains("selected"),
                "[TC220] Add to Favorite không thành công (icon không đổi trạng thái)");
    }

    public void runTest() throws InterruptedException {
        verifyTC208_LayoutCategoriesMenu();
        verifyTC209_DropdownFilterNavigation();
        verifyTC210_ToggleSwitchNavigation();
        verifyTC211_SortByNavigation();
        verifyTC218_ImageSellerServiceCard();
        verifyTC219_NameSellerServiceCard();
        verifyTC220_AddToFavorite_StartingAdd();
    }
}
