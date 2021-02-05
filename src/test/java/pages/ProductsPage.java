package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {
    public static final String ADD_TO_CART = "//*[text()='%s']/ancestor::*[contains(@class,'inventory_item')]//button";

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void buyProduct(String productName) {
        driver.findElement(By.xpath(String.format(ADD_TO_CART, productName))).click();
    }

    public void openCart() {
        driver.findElement(By.id("shopping_cart_container")).click();
    }
}
