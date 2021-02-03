import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class RandomScenarioTest {
    public final String URL = "https://www.saucedemo.com/";
    public final String USERNAME = "user-name";
    public final String PASSWORD = "password";
    public final String LOGIN_BTN = "login-button";

    @Test
    public void checkRandomUsernameAuthorization() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        WebDriver browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        browser.get(URL);

        browser.findElement(By.id(USERNAME)).sendKeys("12345");
        browser.findElement(By.id(PASSWORD)).sendKeys("secret_sauce");
        browser.findElement(By.id(LOGIN_BTN)).click();

        String result = browser.findElement(By.xpath("//*[@data-test='error']")).getText();
        assertEquals(result, "Epic sadface: Username and password do not match any user in this service");

        browser.quit();
    }

    @Test
    public void checkLogOut() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        WebDriver browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        browser.get(URL);

        browser.findElement(By.id(USERNAME)).sendKeys("standard_user");
        browser.findElement(By.id(PASSWORD)).sendKeys("secret_sauce");
        browser.findElement(By.id(LOGIN_BTN)).click();
        browser.findElement(By.cssSelector(".bm-burger-button")).click();
        browser.findElement(By.id("logout_sidebar_link")).click();

        String result = browser.findElement(By.cssSelector(".login_password")).getText();
        System.out.println(result);
        assertEquals(result, "Password for all users:\n" + "secret_sauce");

        browser.quit();
    }

    /*
    1. Authorization
    2. Open menu and transition on the public SauceLabs page
     */
    @Test
    public void checkTransitionOnThePageAbout() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        WebDriver browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        browser.get(URL);

        browser.findElement(By.id(USERNAME)).sendKeys("standard_user");
        browser.findElement(By.id(PASSWORD)).sendKeys("secret_sauce");
        browser.findElement(By.id(LOGIN_BTN)).click();
        browser.findElement(By.cssSelector(".bm-burger-button")).click();
        browser.findElement(By.id("about_sidebar_link")).click();

        String result = browser.findElement(By.cssSelector(".module-ticker .supertitle")).getText();
        assertEquals(result, "TESTS COMPLETED");

        browser.quit();
    }

    /*
    1. Authorization
    2. Add products to cart
    3. Fill form for checkout
    4. Check number of products in the finish order page
    5. Finish
     */
    @Test
    public void checkCheckout() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        WebDriver browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        browser.get("https://www.saucedemo.com/");

        browser.findElement(By.id(USERNAME)).sendKeys("standard_user");
        browser.findElement(By.id(PASSWORD)).sendKeys("secret_sauce");
        browser.findElement(By.id(LOGIN_BTN)).click();

        browser.findElement(By.xpath("//*[text()='Sauce Labs Backpack']/ancestor::*[contains(@class,'inventory_item')]//button")).click();
        browser.findElement(By.xpath("//*[text()='Sauce Labs Bike Light']/ancestor::*[contains(@class,'inventory_item')]//button")).click();
        browser.findElement(By.xpath("//*[text()='Sauce Labs Bolt T-Shirt']/ancestor::*[contains(@class,'inventory_item')]//button")).click();
        browser.findElement(By.id("shopping_cart_container")).click();
        browser.findElement(By.cssSelector(".checkout_button")).click();

        browser.findElement(By.id("first-name")).sendKeys("JSON");
        browser.findElement(By.id("last-name")).sendKeys("Statham");
        browser.findElement(By.id("postal-code")).sendKeys("Shirbrook");
        browser.findElement(By.cssSelector(".cart_button")).click();

        List<WebElement> numberOfBlocksWithProductsInTheOrder = browser.findElements(By.cssSelector(".cart_item"));
        int resultNumber = numberOfBlocksWithProductsInTheOrder.size();
        assertEquals(resultNumber, 3, "The number of products does not match");
        browser.findElement(By.xpath("//*[text()='FINISH']")).click();

        String resultText = browser.findElement(By.cssSelector(".complete-header")).getText();
        assertEquals(resultText, "THANK YOU FOR YOUR ORDER", "Order not completed");

        browser.quit();
    }

    /*
    1. Authorization
    2. Add product to cart
    3. Send empty form for checkout
     */
    @Test
    public void checkEmptyCheckoutFormValidation() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        WebDriver browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        browser.get("https://www.saucedemo.com/");

        browser.findElement(By.id(USERNAME)).sendKeys("standard_user");
        browser.findElement(By.id(PASSWORD)).sendKeys("secret_sauce");
        browser.findElement(By.id(LOGIN_BTN)).click();

        browser.findElement(By.xpath("//*[text()='Sauce Labs Backpack']/ancestor::*[contains(@class,'inventory_item')]//button")).click();
        browser.findElement(By.id("shopping_cart_container")).click();
        browser.findElement(By.cssSelector(".checkout_button")).click();

        browser.findElement(By.id("first-name")).sendKeys("");
        browser.findElement(By.id("last-name")).sendKeys("");
        browser.findElement(By.id("postal-code")).sendKeys("");
        browser.findElement(By.cssSelector(".cart_button")).click();

        String resultText = browser.findElement(By.xpath("//*[@data-test='error']")).getText();
        assertEquals(resultText, "Error: First Name is required", "Empty form validation check failed");

        browser.quit();
    }
}
