package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class ProductsPage extends BasePage {
    public static final String ADD_TO_CART = "//*[text()='%s']/ancestor::*[contains(@class,'inventory_item')]//button";
    public static final By NAME_CATALOG = By.cssSelector(".product_label");
    public static final By MENU_BUTTON = By.cssSelector(".bm-burger-button");
    public static final By LOGOUT_BUTTON = By.id("logout_sidebar_link");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void buyProduct(String productName) {
        driver.findElement(By.xpath(String.format(ADD_TO_CART, productName))).click();
    }

    public void openCart() {
        driver.findElement(By.id("shopping_cart_container")).click();
    }

    public String getProductsCatalog() {
        return driver.findElement(NAME_CATALOG).getText();
    }

    public void openMenu() {
        driver.findElement(MENU_BUTTON).click();
    }

    public void logOut() {
        driver.findElement(LOGOUT_BUTTON).click();
    }

//    public void isPageOpened1() {
//        try {
//            driver.findElement(NAME_CATALOG);
//        } catch (NoSuchElementException ex) {
//            Assert.fail("Страница продуктов не загрузилась");
//        }
//    }

    public void isProductPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(NAME_CATALOG));
        } catch (TimeoutException ex){
            Assert.fail("Страница продуктов не была загружена");
        }
    }
}
