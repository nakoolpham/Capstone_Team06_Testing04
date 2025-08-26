package projects.testcases.ServiceDetail;

/*
 * @Author: Phạm Nguyễn Ngọc Thảo
 * @Version: 1.0
 * @Function: Test FAQ
 * */


import common.BaseTest;
import constants.Constants;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import listeners.ExtentReportListener;
import listeners.SimpleListener;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import projects.userPages.FAQPage;
import utils.LoggerUtil;

import java.time.Duration;
import java.util.List;

@Epic(Constants.EPIC_SEARCH_AND_HIRESERVICE)
@Story(Constants.USERSTORY_205)
@Feature(Constants.FEATURE_VERIFY_DESCRIPTIONSERVICEPAGE)
@Listeners({SimpleListener.class, ExtentReportListener.class})
public class FAQTest extends BaseTest {
    FAQPage page = new FAQPage();
    @Test(description = "Kiểm tra hiển thị nội dung FAQ")
    public void verifyTC245_CheckTitleFAQ(){
        driver.get(Constants.LINK_DESCRIPTIONSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement group = wait.until(ExpectedConditions.visibilityOfElementLocated(page.groupFAQ));
            setLastInteractedElement(group);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", group);
            WebElement title1 = wait.until(ExpectedConditions.visibilityOfElementLocated(page.titleFAQ_1));
            WebElement title2 = wait.until(ExpectedConditions.visibilityOfElementLocated(page.titleFAQ_2));
            String text1 = title1.getText().trim();
            String text2 = title2.getText().trim();
            if (text1.equalsIgnoreCase(text2)) {
                Assert.fail("[TC245] " + Constants.TEST_FAILED + " Tiêu đề FAQ bị lặp lại: " + text1);
                LoggerUtil.fail("[TC245] " + Constants.TEST_FAILED + " Tiêu đề FAQ bị lặp lại: " + text1);
            } else {
                LoggerUtil.pass("[TC245] " + Constants.TEST_PASSED + " Các tiêu đề FAQ khác nhau: '"
                        + text1 + "' và '" + text2 + "'");
            }
        } catch (TimeoutException te) {
            Assert.fail("[TC245] " + Constants.TEST_FAILED + " Không tìm thấy FAQ section hoặc tiêu đề FAQ.");
            LoggerUtil.fail("[TC245] " + Constants.TEST_FAILED + " Không tìm thấy FAQ section hoặc tiêu đề FAQ.");
        } catch (Exception ex) {
            Assert.fail("[TC245] " + Constants.TEST_FAILED + " Lỗi không mong muốn: " + ex.getMessage());
            LoggerUtil.fail("[TC245] " + Constants.TEST_FAILED + " Lỗi không mong muốn: " + ex.getMessage());
        }
    }

    @Test(description = "Kiểm tra tương tác Accordion (mở/đóng câu trả lời)")
    public void verifyTC246_CheckAccordingFAQ(){
        driver.get(Constants.LINK_DESCRIPTIONSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement li1 = wait.until(ExpectedConditions.visibilityOfElementLocated(page.titleFAQ_1));
        WebElement li2 = wait.until(ExpectedConditions.visibilityOfElementLocated(page.titleFAQ_2));
        List<WebElement> faqItems = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(page.listFAQ));

        for (int i = 0; i < faqItems.size(); i++) {
            WebElement current = faqItems.get(i);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", current);
            current.click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int openedCount = 0;
            for (WebElement item : faqItems) {
                // ⚠️ tùy HTML thực tế, bạn cần thay selector "is-open" bằng class/attr thực sự của accordion mở
                String classes = item.getAttribute("class");
                if (classes.contains("is-open")) {
                    openedCount++;
                }
            }
            if (openedCount != 1) {
                Assert.fail("[TC246] " + Constants.TEST_FAILED + " Accordion FAQ cho phép mở " + openedCount + " tab cùng lúc!");
                LoggerUtil.fail("[TC246] " + Constants.TEST_FAILED + " Accordion FAQ cho phép mở " + openedCount + " tab cùng lúc!");
            } else {
                LoggerUtil.pass("[TC246] " + Constants.TEST_PASSED + " Accordion chỉ mở 1 tab tại một thời điểm (tab " + (i+1) + ")");
            }
        }
    }

    @Test(description = "Kiểm tra nội dung của từng mục FAQ (nội dung thực tế)")
    public void verifyTC247_CheckDescriptionDetailFAQ(){
        driver.get(Constants.LINK_DESCRIPTIONSERVICE_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement group = wait.until(ExpectedConditions.visibilityOfElementLocated(page.groupFAQ));
        setLastInteractedElement(group);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", group);

        WebElement q1 = wait.until(ExpectedConditions.elementToBeClickable(page.desFAQ_1));
        WebElement q2 = wait.until(ExpectedConditions.elementToBeClickable(page.desFAQ_2));

        String t1 = q1.getText().trim();
        String t2 = q2.getText().trim();
        if (t1.equalsIgnoreCase(t2)) {
            Assert.fail("[TC247] " + Constants.TEST_FAILED + " Tiêu đề FAQ bị lặp lại: " + t1);
            LoggerUtil.fail("[TC247] " + Constants.TEST_FAILED + " Tiêu đề FAQ bị lặp lại: " + t1);
            return;
        } else {
            LoggerUtil.pass("[TC247] " + Constants.TEST_PASSED + " Tiêu đề khác nhau: '" + t1 + "' và '" + t2 + "'");
        }

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", q1);
        try {
            q1.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", q1);
        }
        try {
            wait.until(ExpectedConditions.attributeToBe(q1, "aria-expanded", "true"));
        } catch (Exception ignore) {}
        By answerP = page.desFAQ_1;
        WebElement answer = wait.until(ExpectedConditions.visibilityOfElementLocated(answerP));

        String answerText = answer.getText().trim();
        Assert.assertFalse(answerText.isEmpty(), "[TC247] " + Constants.TEST_FAILED + " Nội dung FAQ trống!");
        LoggerUtil.pass("[TC247] " + Constants.TEST_PASSED + " Nội dung FAQ hiển thị: " + answerText);
    }

    public void runTest(){
        verifyTC245_CheckTitleFAQ();
        verifyTC246_CheckAccordingFAQ();
        verifyTC246_CheckAccordingFAQ();
    }
}
