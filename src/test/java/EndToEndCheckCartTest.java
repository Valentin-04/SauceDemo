import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class EndToEndCheckCartTest {

    @Test
    public void checkProductsInTheCart() {
        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver");
        WebDriver browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        browser.get("https://www.saucedemo.com/");

        browser.findElement(By.id("user-name")).sendKeys("standard_user");
        browser.findElement(By.id("password")).sendKeys("secret_sauce");
        browser.findElement(By.id("login-button")).click();

        browser.findElement(By.xpath("//*[text()='Sauce Labs Backpack']/ancestor::*[contains(@class,'inventory_item')]//button")).click();
        browser.findElement(By.xpath("//*[text()='Sauce Labs Bike Light']/ancestor::*[contains(@class,'inventory_item')]//button")).click();
        browser.findElement(By.xpath("//*[text()='Sauce Labs Bolt T-Shirt']/ancestor::*[contains(@class,'inventory_item')]//button")).click();
        browser.findElement(By.id("shopping_cart_container")).click();

        List<WebElement> numberOfBlocksWithProductsInTheCart = browser.findElements(By.cssSelector(".cart_item_label"));
        int result = numberOfBlocksWithProductsInTheCart.size();
        assertEquals(result, 3, "The number of products does not match");

        List<WebElement> listOfProductNamesInTheCart = browser.findElements(By.cssSelector(".inventory_item_name"));
        String itemName = listOfProductNamesInTheCart.get(1).getText();
        assertEquals(itemName, "Sauce Labs Bike Light", "Name does not match");

        browser.quit();
    }
}
