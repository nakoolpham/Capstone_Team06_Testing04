package projects.userPages;

import org.openqa.selenium.By;

/*
 * @Author: Phạm Nguyễn Ngọc Thảo
 * @Version: 1.0
 * @Function: Page Footer
 * */

public class FooterPage  {
    public static By iconAccessibility = By.xpath("//button[@class='button']");
    public static By accessibilityPanel = By.xpath("modal");
    public static By footerTop = By.xpath("//div[@class='footer_top']");
    public static By careers = By.xpath("//a[normalize-space()='Careers']");
    public static By socialFooter = By.xpath("//ul[@class='social']");
    public static By languagesFooter = By.xpath("//button[@class='selection']");
    public static By currencyFooter = By.xpath("//div[@class='settings_locale']");
    public static By rightFooter = By.xpath("//section[@class='settings']");
}
