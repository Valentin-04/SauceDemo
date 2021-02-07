package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class EndToEndCheckCartTest extends BaseTest {

    @Test
    public void checkProductsInTheCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.buyProduct("Sauce Labs Backpack");
        productsPage.buyProduct("Sauce Labs Bike Light");
        productsPage.buyProduct("Sauce Labs Bolt T-Shirt");
        productsPage.openCart();
        assertEquals(cartPage.getNumberOfProducts(), 3, "The number of products does not match");
        assertEquals(cartPage.getNameOfProduct(1), "Sauce Labs Bike Light", "Name does not match");
    }
}
