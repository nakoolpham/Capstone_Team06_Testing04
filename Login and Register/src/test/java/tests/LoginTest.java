package tests;

import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void testValidLogin() {
        driver.get("https://demo5.cybersoft.edu.vn/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginValidUser("validuser@example.com", "Password123!");
    }

    @Test
    public void testInvalidLogin() {
        driver.get("https://demo5.cybersoft.edu.vn/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginValidUser("invalid@example.com", "wrongpass");
    }
}
