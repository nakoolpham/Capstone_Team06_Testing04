package tests;

import org.testng.annotations.Test;
import pages.RegisterPage;

public class RegisterTest extends BaseTest {

    @Test
    public void testValidRegister() {
        driver.get("https://demo5.cybersoft.edu.vn/register");
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.registerValidUser("John Doe", "johndoe@example.com", "Password123!", "0987654321", "01/01/1995");
    }

    @Test
    public void testInvalidRegister() {
        driver.get("https://demo5.cybersoft.edu.vn/register");
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.registerValidUser("", "wrongemail", "123", "abcd", "99/99/9999");
    }
}
