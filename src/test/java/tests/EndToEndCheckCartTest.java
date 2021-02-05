package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class EndToEndCheckCartTest extends BaseTest{

    @Test
    public void checkProductsInTheCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.buyProduct("Sauce Labs Backpack");
        productsPage.buyProduct("Sauce Labs Bike Light");
        productsPage.buyProduct("Sauce Labs Bolt T-Shirt");
        productsPage.openCart();

        List<WebElement> numberOfBlocksWithProductsInTheCart = browser.findElements(By.cssSelector(".cart_item_label"));
        int result = numberOfBlocksWithProductsInTheCart.size();
        assertEquals(result, 3, "The number of products does not match");

        List<WebElement> listOfProductNamesInTheCart = browser.findElements(By.cssSelector(".inventory_item_name"));
        String itemName = listOfProductNamesInTheCart.get(1).getText();
        assertEquals(itemName, "Sauce Labs Bike Light", "Name does not match");

        browser.quit();
    }
}
