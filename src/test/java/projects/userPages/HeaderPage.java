package projects.userPages;

import org.openqa.selenium.By;

/*
 * @Author: Phạm Nguyễn Ngọc Thảo
 * @Version: 1.0
 * @Function: Page Header
 * */

public class HeaderPage {
    public static By logo = By.xpath("//a[@class='logo active']");
    public static By headerSearch = By.xpath("//div[@class='header_search']");
    public static By navigationBar = By.xpath("//ul[@class='ul']");
    public static By liFiverr = By.xpath("//li[@class='li_1 li_fiverr']");
    public static By liExplore = By.xpath("//li[normalize-space()='Explore']");
    public static By liLanguages = By.xpath("//li[normalize-space()='English']");
    public static By liCurency = By.xpath("//li[normalize-space()='US$ USD']");
    public static By liBecomeSeller = By.xpath("//li[contains(text(),'Become a Seller')]");
    public static By liJoin = By.xpath("//a[normalize-space()='Join']");
    public static By liSignin = By.xpath("//a[@href='/profile']");
}
