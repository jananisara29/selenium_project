package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {

    WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }
    By productTitle = By.className("inventory_item_name");
    By addToCartButton = By.xpath("//button[text()='Add to cart']");
    By cartIcon = By.cssSelector(".shopping_cart_link");

    public String getFirstProductName() {
        return driver.findElement(productTitle).getText();
    }

    public void addFirstProductToCart() {
        driver.findElement(addToCartButton).click();
    }

    public void goToCart() {
        driver.findElement(cartIcon).click();
    }
}
