package projects.userPages;

import org.openqa.selenium.By;

/*
 * @Author: Phạm Nguyễn Ngọc Thảo
 * @Version: 1.0
 * @Function: Page Review
 * */

public class ReviewPage {
    public static By sumReview = By.xpath("//h2[normalize-space()='1155 Reviews']");
    public static By textareaReview = By.xpath("//textarea[@name='noiDung']");
    public static By commentButton = By.cssSelector(".comment-submit");
    public static By sortByReview = By.xpath("//div[@class='sort d-flex align-items-center']//select");
    public static By mostRecentOption = By.xpath("//option[@value='recent']");
    public static By mostRelevantOption = By.xpath("//option[@value='relevant']");
    public static By reviewFrist = By.xpath(" //p[contains(text(),'Lorem ipsum dolor sit amet consectetur adipisicing')]");
    public static By avatarUserReviewFrist = By.xpath("(//div[@class='reviewer-avatar col-2'])[25]");
    public static By countryFlag = By.xpath("//div[@class='reviewer-comment col-10']//img[@class='country-flag']");
    public static By displayRating = By.xpath("//div[@class='d-flex align-items-center gap-1']");
    public static By lastCommentReview = By.xpath("(//li[@class='row py-4'])[last()]//div[@class='comment']");
    public static By buttonDelReview = By.xpath("(//div[@class='reviewer-comment-del col-1'])");
    public static By buttonLikeReview = By.xpath("//div[@class='yes d-flex align-items-center gap-1']");
    public static By buttonUnLikeReview = By.xpath("//div[@class='no d-flex align-items-center gap-1']");
    public static By buttonHelpFulReview = By.xpath("(//div[@class='helpful-title'][normalize-space()='Helpful?'])");
}
