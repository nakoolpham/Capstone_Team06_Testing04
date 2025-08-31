package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private WebDriver driver;

    private By nameField = By.xpath("//input[@placeholder='Your Name']");
    private By emailField = By.xpath("//input[@placeholder='Your Email']");
    private By passwordField = By.xpath("//input[@placeholder='Your Password']");
    private By confirmPasswordField = By.xpath("//input[@placeholder='Repeat your password']");
    private By phoneField = By.xpath("//input[@placeholder='Your Phone']");
    private By birthdayField = By.xpath("//input[@placeholder='mm/dd/yyyy']");
    private By maleRadio = By.xpath("//input[@value='Male']");
    private By femaleRadio = By.xpath("//input[@value='Female']");
    private By termsCheckbox = By.xpath("//input[@type='checkbox']");
    private By submitButton = By.xpath("//button[contains(text(),'Submit')]");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void setConfirmPassword(String password) {
        driver.findElement(confirmPasswordField).sendKeys(password);
    }

    public void setPhone(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }

    public void setBirthday(String birthday) {
        driver.findElement(birthdayField).sendKeys(birthday);
    }

    public void selectMale() {
        driver.findElement(maleRadio).click();
    }

    public void selectFemale() {
        driver.findElement(femaleRadio).click();
    }

    public void acceptTerms() {
        driver.findElement(termsCheckbox).click();
    }

    public void clickSubmit() {
        driver.findElement(submitButton).click();
    }

    public void registerValidUser(String name, String email, String password, String phone, String birthday) {
        setName(name);
        setEmail(email);
        setPassword(password);
        setConfirmPassword(password);
        setPhone(phone);
        setBirthday(birthday);
        selectMale();
        acceptTerms();
        clickSubmit();
    }
}
