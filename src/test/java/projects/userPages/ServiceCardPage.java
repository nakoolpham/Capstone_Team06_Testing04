package projects.userPages;

import org.openqa.selenium.By;

/*
 * @Author: Phạm Nguyễn Ngọc Thảo
 * @Version: 1.0
 * @Function: Page Service Card
 * */

public class ServiceCardPage {
    public static By titleTesting = By.xpath("//p[normalize-space()='Testing']");
    public static By categoriesmenuWebAppDesign = By.xpath("//a[@class='categoriesmenu_li_jobdetail_detail_job container'][normalize-space()='Web & App Design']");
    public static By preTitle = By.xpath("//span[normalize-space()='0 services available']");
    public static By preTitle_2 = By.xpath("//span[normalize-space()='2 services available']");
    public static By dropdownCategory = By.xpath("//button[normalize-space()='Category']");
    public static By dropdownCategoryItem = By.xpath("//ul[@class='dropdown-menu show']//a[@class='dropdown-item'][normalize-space()='Web Programing']");
    public static By toggleSwitchProService = By.xpath("(//span[@class='slider round'])[1]");
    public static By sortBy = By.xpath("//div[@class='sort-by']");
    public static By sortByBestSelling = By.xpath("//option[@value='bestselling']");
    public static By imageSeller = By.xpath("(//img[@alt='avatar'])[1]");
    public static By nameSeller = By.xpath("(//div[@class='seller-info d-flex align-items-center'])//div[@class='name']");
    public static By addToFavorite = By.xpath("(//div[@class='heart-icon'])[1]");
    public static By startingAt = By.xpath("(//div[@class='price d-flex'])[1]");
}
