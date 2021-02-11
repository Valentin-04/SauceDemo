package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;

public class BaseTest {

    public WebDriver browser;
    public LoginPage loginPage;
    public ProductsPage productsPage;
    public CartPage cartPage;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver");
        browser = new ChromeDriver();
        loginPage = new LoginPage(browser);
        productsPage = new ProductsPage(browser);
        cartPage = new CartPage(browser);
        browser.manage().window().maximize();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (browser != null) {
            browser.quit();
        }
    }
}
