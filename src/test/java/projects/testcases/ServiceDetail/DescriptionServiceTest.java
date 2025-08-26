package projects.testcases.ServiceDetail;

/*
 * @Author: Phạm Nguyễn Ngọc Thảo
 * @Version: 1.0
 * @Function: Test Description Service
 * */

import common.BaseTest;
import constants.Constants;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import listeners.ExtentReportListener;
import listeners.SimpleListener;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import projects.userPages.DescriptionServicePage;
import utils.LoggerUtil;

import java.time.Duration;
import java.util.List;

@Epic(Constants.EPIC_SEARCH_AND_HIRESERVICE)
@Story(Constants.USERSTORY_202)
@Feature(Constants.FEATURE_VERIFY_DESCRIPTIONSERVICEPAGE)
@Listeners({SimpleListener.class, ExtentReportListener.class})
public class DescriptionServiceTest extends BaseTest {
    DescriptionServicePage page = new DescriptionServicePage();
    @Test(description = "Kiểm tra tên hiển thị (Service Title)")
    public void verifyTC227_TitleService() throws InterruptedException {
        driver.get(Constants.LINK_DESCRIPTIONSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(page.titleService));
        setLastInteractedElement(title);
        WebElement header = driver.findElement(page.menuCategories);
        int headerHeight = header.getRect().getHeight();
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true); window.scrollBy(0, -arguments[1]);",
                title, headerHeight);
        new Actions(driver).moveToElement(title).perform();

        Rectangle titleRect = title.getRect();
        Rectangle headerRect = header.getRect();
        if (titleRect.getY() < headerRect.getY() + headerRect.getHeight()) {
            Assert.fail("[TC227] " + Constants.TEST_FAILED + " Title bị menu che (Layout Overflow)");
            LoggerUtil.fail("[TC227] " + Constants.TEST_FAILED + " Title bị menu che (Layout Overflow)");
        }
    }

    @Test(description = "Kiểm tra hình ảnh Seller")
    public void verifyTC228_ImageSeller(){
        driver.get(Constants.LINK_DESCRIPTIONSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement avatar = wait.until(ExpectedConditions.visibilityOfElementLocated(page.avatarSeller));
        setLastInteractedElement(avatar);
        WebElement img = wait.until(ExpectedConditions.visibilityOfElementLocated(page.imgSeller));

        Boolean isImageLoaded = (Boolean) ((JavascriptExecutor)driver)
                .executeScript("return arguments[0].complete && arguments[0].naturalWidth > 0;", img);
        if (!isImageLoaded) {
            Assert.fail("[TC228]" + Constants.TEST_FAILED + "Ảnh bị lỗi hoặc không load được!");
            LoggerUtil.fail("[TC228] " + Constants.TEST_FAILED + "Ảnh bị lỗi hoặc không load được!");
        } else {
            LoggerUtil.pass("[TC228] " + Constants.TEST_PASSED + "Ảnh load thành công!");
        }
    }

    @Test(description = "Kiểm tra hiển thị tên Seller")
    public void verifyTC228_NameSeller(){
        driver.get(Constants.LINK_DESCRIPTIONSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(page.nameSeller));
            setLastInteractedElement(name);

            boolean isVisible = name.isDisplayed()
                    && !"hidden".equals(name.getCssValue("visibility"))
                    && !"none".equals(name.getCssValue("display"));
            if (!isVisible) {
                setLastInteractedElement(name);
                Assert.fail("[TC228] " + Constants.TEST_FAILED + " Không hiển thị tên Seller");
                LoggerUtil.fail("[TC228] " + Constants.TEST_FAILED + " Không hiển thị tên Seller");
            } else {
                LoggerUtil.pass("[TC228] " + Constants.TEST_PASSED + " Có hiển thị tên Seller: " + name.getText());
            }
        } catch (TimeoutException e) {
            Assert.fail("[TC228] " + Constants.TEST_FAILED + " Không tìm thấy element tên Seller");
        }
    }

    @Test(description = "Kiểm tra sao Rating Seller")
    public void verifyTC229_RatingIsReadOnly(){
        driver.get(Constants.LINK_DESCRIPTIONSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Tìm danh sách sao
        List<WebElement> stars = driver.findElements(page.starRatingSeller);
        if (stars.size() > 1) {
            // Trường hợp có nhiều element sao
            WebElement star4 = stars.get(3); // ngôi sao thứ 4
            String before = star4.getAttribute("class");

            try {
                star4.click(); // click thử
            } catch (Exception e) {
                LoggerUtil.pass("[TC229] Rating readonly - không click được (đúng)");
                return;
            }

            String after = star4.getAttribute("class");

            if (!before.equals(after)) {
                Assert.fail("[TC229] " + Constants.TEST_FAILED + " Rating thay đổi sau khi click!");
            } else {
                LoggerUtil.pass("[TC229] " + Constants.TEST_PASSED + " Rating không thay đổi khi click.");
            }

        } else {
            // Trường hợp chỉ có 1 element bao ngoài (sprite hoặc SVG)
            WebElement ratingBox = driver.findElement(page.starRatingSeller);
            String before = ratingBox.getAttribute("innerHTML");

            // Click offset tại vị trí ngôi sao thứ 4
            new Actions(driver)
                    .moveToElement(ratingBox, 60, 5) // offset theo pixel
                    .click()
                    .perform();

            String after = ratingBox.getAttribute("innerHTML");

            if (!before.equals(after)) {
                Assert.fail("[TC229] " + Constants.TEST_FAILED + " Rating thay đổi khi click offset!");
            } else {
                LoggerUtil.pass("[TC229] " + Constants.TEST_PASSED + " Rating readonly, không thay đổi khi click.");
            }
        }
    }

    @Test(description = "Kiểm tra điều hướng khi click tổng số rating")
    public void verifyTC229_ClickSumRating(){
        driver.get(Constants.LINK_DESCRIPTIONSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement reviewTop = wait.until(ExpectedConditions.elementToBeClickable(page.sumReviewRatingSeller));
        setLastInteractedElement(reviewTop);
        WebElement reviewBottom = wait.until(ExpectedConditions.presenceOfElementLocated(page.sumReviewRatingSeller2));

        Double initialY = (Double) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].getBoundingClientRect().top;", reviewBottom);
        reviewTop.click();
        wait.until(driver -> {
            Double afterClickY = (Double) ((JavascriptExecutor) driver)
                    .executeScript("return arguments[0].getBoundingClientRect().top;", reviewBottom);
            return afterClickY < initialY;
        });
        LoggerUtil.pass("[TC229] " + Constants.TEST_PASSED + " Scroll đến reviewBottom thành công");
    }

    @Test(description = "Kiểm tra tìm kiếm review")
    public void verifyTC233_searchReview() throws InterruptedException {
        driver.get(Constants.LINK_DESCRIPTIONSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(page.inputReview));
        input.sendKeys("ThaoPham");
        Thread.sleep(500);
        setLastInteractedElement(input);
        ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center', inline:'nearest'});", input);
        Thread.sleep(500);
        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(page.buttonSearchReview));
        new Actions(driver).moveToElement(submit).perform();
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(page.resultReviewSearchReview));
        if(result.isDisplayed()) {
            LoggerUtil.fail("[TC233] " + Constants.TEST_FAILED + " Không hiển thị kết quả sau khi search");
            Assert.fail("[TC233] " + Constants.TEST_FAILED + " Không hiển thị kết quả sau khi search");
        }
    }

    public void runTest() throws InterruptedException {
        verifyTC227_TitleService();
        verifyTC228_ImageSeller();
        verifyTC228_NameSeller();
        verifyTC229_ClickSumRating();
        verifyTC229_RatingIsReadOnly();
        verifyTC233_searchReview();
    }
}
