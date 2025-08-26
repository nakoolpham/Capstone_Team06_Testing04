package projects.userPages;

import org.openqa.selenium.By;

/*
 * @Author: Phạm Nguyễn Ngọc Thảo
 * @Version: 1.0
 * @Function: Page Service Pricing Table
 * */

public class ServicePricingTablePage {
    public static By titleTab = By.xpath("//div[@class='check-out mobile mt-5']//div[@class='check-out-header']");
    public static By categoriesMenu = By.xpath(" //nav[@class='categoriesmenu_row']");
    public static By basicTab = By.xpath("(//button[@role='tab'][normalize-space()='Basic'])[1]");
    public static By preniumTab = By.xpath("(//button[@role='tab'][normalize-space()='Premium'])[2]");
    public static By packageDetail = By.xpath("(//p[@class='description'][contains(text(),'1 AMAZING Video Ad for your Product')])[1]");
    public static By comparePackageButton = By.xpath("//a[@href='#compare']");
    public static By continueButton = By.cssSelector("div[class='check-out-footer'] button[type='button']");
    public static By titleDescription = By.xpath("(//h2[normalize-space()='About This Gig'])[1]");
    public static By toastSuccess = By.xpath("//div[@class='Toastify']");


}