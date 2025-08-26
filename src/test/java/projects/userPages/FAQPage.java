package projects.userPages;

import org.openqa.selenium.By;

/*
 * @Author: Phạm Nguyễn Ngọc Thảo
 * @Version: 1.0
 * @Function: Page FAQ
 * */

public class FAQPage {
    public static By groupFAQ = By.xpath("//div[@class='FAQ mt-5']");
    public static By titleFAQ_1 = By.cssSelector("div[class='FAQ mt-5'] li:nth-child(1)");
    public static By titleFAQ_2 = By.cssSelector("div[class='FAQ mt-5'] li:nth-child(2)");
    public static By listFAQ = By.cssSelector(" //div[@class='FAQ mt-5']//ul");
    public static By desFAQ_1 = By.xpath("//div[@class='FAQ mt-5']//li[1]//p[1]");
    public static By desFAQ_2 = By.xpath("//div[@class='FAQ mt-5']//li[1]//p[1]");
}
