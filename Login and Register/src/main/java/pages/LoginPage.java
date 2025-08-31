package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    private By emailField = By.xpath("//input[@placeholder='Your Email']");
    private By passwordField = By.xpath("//input[@placeholder='Your Password']");
    private By loginButton = By.xpath("//button[contains(text(),'LOGIN')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public void loginValidUser(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLogin();
    }
}
