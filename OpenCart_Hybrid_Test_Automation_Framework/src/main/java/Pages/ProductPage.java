package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    private WebDriver driver;

    private By searchBox = By.name("search");
    private By searchBtn = By.cssSelector("button.btn-default.btn-lg");
    private By addToCartBtn = By.xpath("//button[contains(@onclick, 'cart.add')]");
    private By currencyBtn = By.xpath("//span[text()='Currency']");
    private By euroBtn = By.name("EUR");
    private By compareBtn = By.xpath("//button[contains(@onclick, 'compare.add')]");
    private By compareLink = By.id("compare-total");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchProduct(String itemName) {
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(itemName);
        driver.findElement(searchBtn).click();
    }

    // This method was missing before - FIXED NOW
    public void searchAndAddItems(String[] items) {
        for (String item : items) {
            searchProduct(item);
            driver.findElement(addToCartBtn).click();
        }
    }

    public void switchCurrencyToEuro() {
        driver.findElement(currencyBtn).click();
        driver.findElement(euroBtn).click();
    }

    public void addToCompare() {
        driver.findElement(compareBtn).click();
    }

    public String getCompareText() {
        return driver.findElement(compareLink).getText();
    }
}