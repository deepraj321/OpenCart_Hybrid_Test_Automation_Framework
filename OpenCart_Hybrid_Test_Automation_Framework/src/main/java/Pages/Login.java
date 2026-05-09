package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {
    private WebDriver driver;
    private By emailField = By.id("input-email");
    private By passwordField = By.id("input-password");
    private By loginBtn = By.xpath("//input[@value='Login']");

    public Login(WebDriver driver) { this.driver = driver; }

    public void doLogin(String email, String pwd) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(pwd);
        driver.findElement(loginBtn).click();
    }
}