package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    private WebDriver driver;
    public CheckoutPage(WebDriver driver) { this.driver = driver; }
    public void goToCheckout() { driver.findElement(By.linkText("Checkout")).click(); }
}