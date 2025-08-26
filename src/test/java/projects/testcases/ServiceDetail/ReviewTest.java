package projects.testcases.ServiceDetail;

/*
 * @Author: Phạm Nguyễn Ngọc Thảo
 * @Version: 1.0
 * @Function: Test Reviews
 * */

import common.BaseTest;
import constants.Constants;
import constants.DataInput;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import listeners.ExtentReportListener;
import listeners.SimpleListener;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import projects.userPages.ReviewPage;
import utils.LoggerUtil;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Epic(Constants.EPIC_SEARCH_AND_HIRESERVICE)
@Story(Constants.USERSTORY_206)
@Feature(Constants.FEATURE_VERIFY_DESCRIPTIONSERVICEPAGE)
@Listeners({SimpleListener.class, ExtentReportListener.class})
public class ReviewTest extends BaseTest {
    ReviewPage page = new ReviewPage();
    String specialChars;
    String payloads;
    public void insertReview(String inputReview) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement textarea = wait.until(ExpectedConditions.visibilityOfElementLocated(page.textareaReview));
        textarea.sendKeys(inputReview);
        Thread.sleep(1000);
        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(page.commentButton));
        new Actions(driver).moveToElement(submit).perform();
    }
    @DataProvider(name = "xssPayloads")
    public Iterator<Object[]> xssPayloads() {
        List<String> payloads = Arrays.asList(
                "<script>alert('xss')</script>"
//                "\"><script>alert(1)</script>",
//                "<img src=x onerror=alert(1)>",
//                "<svg/onload=alert(1)>",
//                "<ScRiPt>alert`1`</ScRiPt>",
//                "<body onload=alert(1)>",
//                "';alert(1);//",
//                "<iframe src=javascript:alert(1)>",
//                "<a href=javascript:alert(1)>click</a>",
//                "${{<%[%\"'`}}><script>alert(1)</script>"
        );
        return payloads.stream().map(p -> new Object[]{p}).iterator();
    }

    @DataProvider(name = "specialChars")
    public Iterator<Object[]> specialChars() {
        List<String> chars = Arrays.asList(
                "!@#$%^&*()"
//                "<script>alert(1)</script>",
//                "' OR '1'='1",
//                "\"; DROP TABLE users; --",
//                "<img src=x onerror=alert(1)>",
//                "`~[]{}\\|;:'\",.<>/?"
        );
        return chars.stream().map(c -> new Object[]{c}).iterator();
    }


    @Test(description = "Kiểm tra Tổng quan đánh giá")
    public void verifyTC250_CompareNumberReviews() throws InterruptedException {
        driver.get(Constants.LINK_DESCRIPTIONSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement reviewCountBefore = wait.until(ExpectedConditions.visibilityOfElementLocated(page.sumReview));
        Thread.sleep(500);
        insertReview("Automation Test");
        Thread.sleep(500);
        WebElement reviewCountAfter = wait.until(ExpectedConditions.visibilityOfElementLocated(page.sumReview));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", reviewCountBefore);
        Thread.sleep(500);
        setLastInteractedElement(reviewCountAfter);
        if (reviewCountBefore.getText().equals(reviewCountAfter.getText())) {
            Assert.fail("[TC250]" + Constants.TEST_FAILED + "Tổng số lượng reviews hiển thị không đúng");
            LoggerUtil.fail("[TC250] " + Constants.TEST_FAILED + "Tổng số lượng reviews hiển thị không đúng");
        }
    }

    @Test(description = "Kiểm tra tìm kiếm và lọc đánh giá")
    public void verifyTC252_CheckSortByReview() throws InterruptedException {
        driver.get(Constants.LINK_DESCRIPTIONSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement sortDropdown = driver.findElement(page.sortByReview);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", sortDropdown);
        Thread.sleep(500);
        setLastInteractedElement(sortDropdown);
        Select select = new Select(sortDropdown);
        select.selectByVisibleText("Most Recent");
        wait.until(ExpectedConditions.visibilityOfElementLocated(page.mostRecentOption));
        List<WebElement> recentReviews = driver.findElements(page.reviewFrist);
        List<String> recentTexts = new ArrayList<>();
        for (WebElement el : recentReviews) {
            recentTexts.add(el.getText().trim());
        }
        select.selectByVisibleText("Most Relevant");
        wait.until(ExpectedConditions.visibilityOfElementLocated(page.mostRelevantOption));

        List<WebElement> relevantReviews = driver.findElements(page.reviewFrist);
        List<String> relevantTexts = new ArrayList<>();
        for (WebElement el : relevantReviews) {
            relevantTexts.add(el.getText().trim());
        }
        Assert.assertNotEquals(recentTexts, relevantTexts, "Sort By không thay đổi kết quả hiển thị!");
        Assert.fail("[TC252] " + Constants.TEST_FAILED + "Sort By không thay đổi kết quả hiển thị!");
        LoggerUtil.fail("[TC252]" + Constants.TEST_FAILED + "Sort By không thay đổi kết quả hiển thị!");
    }

    @Test(description = "Lỗi hiển thị ảnh mục review")
    public void verifyTC253_CheckAvatarUserReview() throws InterruptedException {
        driver.get(Constants.LINK_DESCRIPTIONSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement avatar = wait.until(ExpectedConditions.visibilityOfElementLocated(page.avatarUserReviewFrist));
        Thread.sleep(500);
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
            Assert.fail("[TC253] " + Constants.TEST_FAILED + " Ảnh avatar bị lỗi hoặc không load được!");
            LoggerUtil.fail("[TC253] " + Constants.TEST_FAILED + " Ảnh avatar bị lỗi hoặc không load được!");
        } else {
            LoggerUtil.pass("[TC253] " + Constants.TEST_PASSED + " Ảnh avatar load thành công!");
        }
    }


    @Test(description = "Lỗi hiển thị Quốc gia mục review")
    public void verifyTC253_WrongCountryDisplayed() throws InterruptedException {
        driver.get(Constants.LINK_DESCRIPTIONSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement avatarPattern = wait.until(ExpectedConditions.visibilityOfElementLocated(page.countryFlag));
        Thread.sleep(500);
        insertReview("Automation Test");
        Thread.sleep(500);
        WebElement newAvatar = wait.until(ExpectedConditions.visibilityOfElementLocated(page.countryFlag));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", newAvatar);
        Thread.sleep(500);
        setLastInteractedElement(newAvatar);
        if (avatarPattern.getText().equals(newAvatar.getText())) {
            Assert.fail("[TC253]" + Constants.TEST_FAILED + "Lỗi hiển thị sai quốc gia");
            LoggerUtil.fail("[TC253] " + Constants.TEST_FAILED + "Lỗi hiển thị sai quốc gia");
        }
    }

    @Test(description = "Kiểm tra hiển thị khung đánh giá và bình luận")
    public void verifyTC255_CheckDisplayRatingAndCommentBox(){
        driver.get(Constants.LINK_DESCRIPTIONSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement rating = wait.until(ExpectedConditions.visibilityOfElementLocated(page.displayRating));
        WebElement textarea = wait.until(ExpectedConditions.visibilityOfElementLocated(page.textareaReview));
        WebElement submit = wait.until(ExpectedConditions.visibilityOfElementLocated(page.commentButton));
        if(rating.isDisplayed() && textarea.isDisplayed() && submit.isDisplayed()) {
            LoggerUtil.pass("[TC255]" + Constants.TEST_PASSED + "Hiển thị đầy đủ khung đánh giá và bình luận");
        } else {
            Assert.fail("[TC255]" + Constants.TEST_FAILED + "Lỗi hiển thị khung đánh giá và bình luận");
            LoggerUtil.fail("[TC255] " + Constants.TEST_FAILED + "Lỗi hiển thị khung đánh giá và bình luận");
        }
    }

    @Test(description = "Kiểm tra nhập comment hợp lệ")
    public void verifyTC257_CheckValidCommentInput() throws InterruptedException {
        driver.get(Constants.LINK_DESCRIPTIONSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        insertReview("Automation Test");
        WebElement textarea = wait.until(ExpectedConditions.visibilityOfElementLocated(page.textareaReview));
        if(textarea.getAttribute("value").equals("Automation Test")){
            LoggerUtil.pass("[TC257]" + Constants.TEST_PASSED + "Hiển thị đúng commnent hợp lệ");
        } else {
            Assert.fail("[TC257]" + Constants.TEST_FAILED + "Lỗi hiển thị bình luận");
            LoggerUtil.fail("[TC257] " + Constants.TEST_FAILED + "Lỗi hiển thị bình luận");
        }
    }

    @Test(description = "Kiểm tra gửi đánh giá đầy đủ")
    public void verifyTC258_CheckFullReviewSubmission() throws InterruptedException {
        driver.get(Constants.LINK_DESCRIPTIONSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        insertReview("Automation Test");
        WebElement lastComment = driver.findElement(page.lastCommentReview);
        System.out.println("Review cuối cùng: " + lastComment.getText());
        LoggerUtil.pass("[TC258]" + Constants.TEST_PASSED + "Gửi đánh giá thành công");
    }

    @Test(description = "Kiểm tra gửi đánh giá thiếu comment")
    public void verifyTC260_CheckSubmitReviewMissing() throws InterruptedException {
        driver.get(Constants.LINK_DESCRIPTIONSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement textarea = wait.until(ExpectedConditions.visibilityOfElementLocated(page.textareaReview));
        textarea.clear();
        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(page.commentButton));
        new Actions(driver).moveToElement(submit).perform();
        String validationMessage = textarea.getAttribute("validationMessage");
        Assert.assertEquals(validationMessage, "Please fill out this field.",
                "Validation message không đúng hoặc không hiển thị!");
        LoggerUtil.pass("[TC260]" + Constants.TEST_PASSED + "Please fill out this field.");
    }


    @Test(description = "Kiểm tra gửi comment quá dài")
    public void verifyTC262_CheckLongCommnent() throws InterruptedException {
        driver.get(Constants.LINK_DESCRIPTIONSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        insertReview(DataInput.INPUT_TEXT_2000_WORDS);
        Thread.sleep(500);
        WebElement ta = wait.until(ExpectedConditions.visibilityOfElementLocated(page.textareaReview));
        Thread.sleep(500);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", ta);
        Thread.sleep(500);
        setLastInteractedElement(ta);
        Thread.sleep(500);

        WebElement lastItem = wait.until(ExpectedConditions.visibilityOfElementLocated(page.lastCommentReview));
        if(lastItem.getText().equalsIgnoreCase(ta.getText())) {
            LoggerUtil.pass("[TC262] " + Constants.TEST_PASSED + "Gửi comment thành công");
        } else {
            Assert.fail("[TC262]" + Constants.TEST_FAILED + "Gửi thành công nhưng không chặn giới hạn độ dài");
            LoggerUtil.fail("[TC262] " + Constants.TEST_FAILED + "Gửi thành công nhưng không chặn giới hạn độ dài");
        }
    }

    //@Test(description = "Kiểm tra chặn script/XSS")
    @Test(dataProvider = "xssPayloads")
    public void verifyTC263_TestXSSPreventedTextarea(String payloads) throws InterruptedException {
        driver.get(Constants.LINK_DESCRIPTIONSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        insertReview(payloads);
        Thread.sleep(500);
        WebElement ta = wait.until(ExpectedConditions.visibilityOfElementLocated(page.textareaReview));
        Thread.sleep(500);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", ta);
        Thread.sleep(500);
        setLastInteractedElement(ta);
        Thread.sleep(500);

        WebElement lastItem = wait.until(ExpectedConditions.visibilityOfElementLocated(page.lastCommentReview));
        if(lastItem.getText().equalsIgnoreCase(payloads)) {
            LoggerUtil.pass("[TC263] " + Constants.TEST_PASSED + "Gửi comment thành công");
        } else {
            Assert.fail("[TC263]" + Constants.TEST_FAILED + "Gửi thành công nhưng không chặn script/XSS");
            LoggerUtil.fail("[TC263] " + Constants.TEST_FAILED + "Gửi thành công nhưng không chặn script/XSS");
        }
    }

    @Test(dataProvider = "specialChars")
    public void verifyTC264_SubmittedSpecialCharacters(String specialChars) throws InterruptedException {
        driver.get(Constants.LINK_DESCRIPTIONSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        insertReview(specialChars);
        Thread.sleep(500);
        WebElement ta = wait.until(ExpectedConditions.visibilityOfElementLocated(page.textareaReview));
        Thread.sleep(500);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", ta);
        Thread.sleep(500);
        setLastInteractedElement(ta);
        Thread.sleep(500);

        WebElement lastItem = wait.until(ExpectedConditions.visibilityOfElementLocated(page.lastCommentReview));
        if(lastItem.getText().equalsIgnoreCase(specialChars)) {
            LoggerUtil.pass("[TC264] " + Constants.TEST_PASSED + "Gửi comment thành công và có chặn ký tự đặc biệt");
        } else {
            Assert.fail("[TC264]" + Constants.TEST_FAILED + "Gửi thành công nhưng không chặn ký tự đặc biệt");
            LoggerUtil.fail("[TC264] " + Constants.TEST_FAILED + "Gửi thành công nhưng không chặn ký tự đặc biệt");
        }
    }

    @Test(description = "Kiểm tra gửi thành công và xóa form")
    public void verifyTC265_CheckSuccessSub_DelForm() throws InterruptedException {
        driver.get(Constants.LINK_DESCRIPTIONSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        insertReview("Automation Test");
        WebElement textarea = wait.until(ExpectedConditions.visibilityOfElementLocated(page.textareaReview));
        setLastInteractedElement(textarea);
        if(textarea.getAttribute("value").equals("Automation Test")){
            Assert.fail("[TC265]" + Constants.TEST_FAILED + "Gửi thành công nhưng không xóa form");
            LoggerUtil.fail("[TC265] " + Constants.TEST_FAILED + "Gửi thành công nhưng không xóa form");
        }
    }

    @Test(description = "Kiểm tra chức năng xóa comment")
    public void verifyTC271_CheckCommentDeletion() throws InterruptedException {
        driver.get(Constants.LINK_DESCRIPTIONSERVICE_PAGE);
        WebElement btnDeleteReview = driver.findElement(page.buttonDelReview);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", btnDeleteReview);
        Thread.sleep(500);
        setLastInteractedElement(btnDeleteReview);
        boolean isEnabled = btnDeleteReview.isEnabled();
        Assert.assertFalse(isEnabled,"[TC271]" + Constants.TEST_FAILED + "Lỗi xóa bình luận");
        LoggerUtil.fail("[TC271]" + Constants.TEST_FAILED + "Lỗi xóa bình luận");
    }

    @Test(description = "Kiểm tra chức năng Helpful comment")
    public void verifyTC271_CheckCommentHelpful() throws InterruptedException {
        driver.get(Constants.LINK_DESCRIPTIONSERVICE_PAGE);
        WebElement button = driver.findElement(page.buttonHelpFulReview);
        Thread.sleep(500);
        setLastInteractedElement(button);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", button);
        Thread.sleep(500);
        boolean isEnabled = button.isEnabled();
        Assert.assertFalse(isEnabled,"[TC271]" + Constants.TEST_FAILED + "Không click được icon Helpful");
        LoggerUtil.fail("[TC271]" + Constants.TEST_FAILED + "Không click được icon Helpful");
    }

    @Test(description = "Kiểm tra chức năng Like comment")
    public void verifyTC271_CheckCommentLike() throws InterruptedException {
        driver.get(Constants.LINK_DESCRIPTIONSERVICE_PAGE);
        WebElement button = driver.findElement(page.buttonLikeReview);
        Thread.sleep(500);
        setLastInteractedElement(button);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", button);
        Thread.sleep(500);
        boolean isEnabled = button.isEnabled();
        Assert.assertFalse(isEnabled,"[TC271]" + Constants.TEST_FAILED + "Không click được icon Like");
        LoggerUtil.fail("[TC271]" + Constants.TEST_FAILED + "Không click được icon Like");
    }

    @Test(description = "Kiểm tra chức năng unLike comment")
    public void verifyTC271_CheckCommentUnLike() throws InterruptedException {
        driver.get(Constants.LINK_DESCRIPTIONSERVICE_PAGE);
        WebElement button = driver.findElement(page.buttonUnLikeReview);
        Thread.sleep(500);
        setLastInteractedElement(button);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", button);
        Thread.sleep(500);
        boolean isEnabled = button.isEnabled();
        Assert.assertFalse(isEnabled,"[TC271]" + Constants.TEST_FAILED + "Không click được icon UnLike");
        LoggerUtil.fail("[TC271]" + Constants.TEST_FAILED + "Không click được icon UnLike");
    }

    public void runTest() throws InterruptedException {
        verifyTC250_CompareNumberReviews();
        verifyTC252_CheckSortByReview();
        verifyTC253_CheckAvatarUserReview();
        verifyTC253_WrongCountryDisplayed();
        verifyTC255_CheckDisplayRatingAndCommentBox();
        verifyTC257_CheckValidCommentInput();
        verifyTC258_CheckFullReviewSubmission();
        verifyTC260_CheckSubmitReviewMissing();
        verifyTC262_CheckLongCommnent();
        verifyTC263_TestXSSPreventedTextarea(payloads);
        verifyTC264_SubmittedSpecialCharacters(specialChars);
        verifyTC265_CheckSuccessSub_DelForm();
        verifyTC271_CheckCommentDeletion();
        verifyTC271_CheckCommentHelpful();
        verifyTC271_CheckCommentLike();
        verifyTC271_CheckCommentUnLike();

    }
}
