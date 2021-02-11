package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class RandomScenarioTest extends BaseTest {

    @Test
    public void checkTransitionOnThePageAbout() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.isProductPageOpened();
        productsPage.openMenu();
        loginPage.clickAboutPageButton();
        assertEquals(loginPage.getAboutPageConfirm(), "TESTS COMPLETED", "Finish page doesn't match");
    }

    @Test
    public void checkSuccessCheckout() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.isProductPageOpened();
        productsPage.buyProduct("Sauce Labs Backpack");
        productsPage.buyProduct("Sauce Labs Bike Light");
        productsPage.buyProduct("Sauce Labs Bolt T-Shirt");
        productsPage.openCart();
        cartPage.isCartPageOpened();
        cartPage.clickCheckoutButton();
        cartPage.fillCheckoutForm("JSON", "Statham", "Shirbrook");
        assertEquals(cartPage.getNumberOfProducts(), 3, "The number of products does not match");
        cartPage.clickFinishButton();
        assertEquals(cartPage.getOrderConfirm(), "THANK YOU FOR YOUR ORDER", "Order not completed");
    }

    @Test
    public void checkEmptyCheckoutFormValidation() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.isProductPageOpened();
        productsPage.buyProduct("Sauce Labs Backpack");
        productsPage.openCart();
        cartPage.isCartPageOpened();
        cartPage.clickCheckoutButton();
        cartPage.fillCheckoutForm("", "", "");
        assertEquals(loginPage.getErrorMessage(), "Error: First Name is required",
                "Empty form validation check failed");
    }

    @Test
    public void checkLastNameValidationCheckoutForm() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.isProductPageOpened();
        productsPage.buyProduct("Sauce Labs Backpack");
        productsPage.openCart();
        productsPage.isProductPageOpened();
        cartPage.clickCheckoutButton();
        cartPage.fillCheckoutForm("qwerty", "", "qwerty");
        assertEquals(loginPage.getErrorMessage(), "Error: Last Name is required",
                "Message doesn't match");
    }

    @Test
    public void checkPostalCodeValidationCheckoutForm() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.isProductPageOpened();
        productsPage.buyProduct("Sauce Labs Backpack");
        productsPage.openCart();
        cartPage.isCartPageOpened();
        cartPage.clickCheckoutButton();
        cartPage.fillCheckoutForm("qwerty", "qwerty", "");
        assertEquals(loginPage.getErrorMessage(), "Error: Postal Code is required",
                "Message doesn't match");
    }

    @Test
    public void checkItemTotalSumInCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.isProductPageOpened();
        productsPage.buyProduct("Sauce Labs Backpack");
        productsPage.buyProduct("Sauce Labs Bike Light");
        productsPage.buyProduct("Sauce Labs Bolt T-Shirt");
        productsPage.openCart();
        cartPage.isCartPageOpened();
        cartPage.clickCheckoutButton();
        cartPage.fillCheckoutForm("JSON", "Statham", "Shirbrook");
        assertEquals(cartPage.getSummaryOfProducts(), 55.97, "Total summary of products doesn't match");
    }
}
