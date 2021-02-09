package tests;

import org.testng.annotations.Test;

public class ProductsTest extends BaseTest {

    @Test
    public void productsShouldBeIsAvailableInCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.isProductPageOpened();
        productsPage.buyProduct("Sauce Labs Bike Light");
    }
}
