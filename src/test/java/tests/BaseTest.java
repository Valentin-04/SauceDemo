package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.CartPage;
import pages.LoginPageFluent;
import pages.ProductsPage;
import utils.CapabilitiesGenerator;

import java.util.concurrent.TimeUnit;

@Listeners(TestListener.class)
public class BaseTest {

    public WebDriver browser;
    public LoginPageFluent loginPage;
    public ProductsPage productsPage;
    public CartPage cartPage;

    @BeforeMethod
    public void setup() {
        //System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver");
        browser = new ChromeDriver(CapabilitiesGenerator.getChromeOptions());
        loginPage = new LoginPageFluent(browser);
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
