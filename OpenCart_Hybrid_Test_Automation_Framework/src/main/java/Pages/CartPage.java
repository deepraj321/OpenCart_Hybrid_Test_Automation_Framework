package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private WebDriver driver;
    public CartPage(WebDriver driver) { this.driver = driver; }
    public String getCartSummary() { return driver.findElement(By.id("cart-total")).getText(); }
}