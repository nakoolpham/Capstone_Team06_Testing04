package projects.userPages;

import org.openqa.selenium.By;

/*
 * @Author: Phạm Nguyễn Ngọc Thảo
 * @Version: 1.0
 * @Function: Page Description Service
 * */

public class DescriptionServicePage {
    public static By titleService = By.xpath("//h1[@class='job-title']");
    public static By menuCategories = By.xpath("(//nav[@class='categoriesmenu_row'])[1]");
    public static By imgSeller = By.xpath("//img[@alt='avatar']");
    public static By nameSeller = By.xpath("//div[@class='seller-name']");
    public static By avatarSeller = By.xpath("//div[@class='seller-avatar']");
    public static By starRatingSeller = By.xpath("(//div[@class='star d-flex'])[1]");
    public static By sumReviewRatingSeller = By.xpath("//div[@class='rating'][normalize-space()='(1155)']");
    public static By sumReviewRatingSeller2 = By.xpath("//h2[normalize-space()='1155 Reviews']");
    public static By inputReview = By.xpath("//input[@placeholder='Search reviews']");
    public static By buttonSearchReview = By.xpath("//form[@class='search-form d-flex ']//button");
    public static By resultReviewSearchReview = By.xpath("//div[@class='review-comment']//li[1]");
}
