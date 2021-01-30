import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class AuthorizationTest {
    public WebDriver browser = new ChromeDriver();
    public String result;

    public void authPathForAllUsers(String username) {
        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver");
        browser.manage().window().maximize();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        browser.get("https://www.saucedemo.com/");

        browser.findElement(By.id("user-name")).sendKeys(username);
        browser.findElement(By.id("password")).sendKeys("secret_sauce");
        browser.findElement(By.id("login-button")).click();
//        browser.findElement(By.xpath("//div[@class='bm-burger-button']//button[text()='Open Menu']")).click();

    }

    @Test
    public void standardUserAuthorizationTest() {
        String standardUser = "standard_user";
        authPathForAllUsers(standardUser);
        result = browser.findElement(By.xpath("//div[@class='product_label']")).getText();
        assertEquals(result, "Products", "User doesn't authorization");
        browser.quit();
    }

    @Test
    public void lockedUserAuthorizationTest() {
        String lockedUser = "locked_out_user";
        authPathForAllUsers(lockedUser);
        result = browser.findElement(By.xpath("//h3[@data-test='error'")).getText();
        System.out.println(result);
        assertEquals(result, " ");
    }

//    @Test
//    public void standardUserAuthorizationTest() {
//        String standardUser = "standard_user";
//        authPathForAllUsers(standardUser);
//    }
//
//    @Test
//    public void standardUserAuthorizationTest() {
//        String standardUser = "standard_user";
//        authPathForAllUsers(standardUser);
//    }
}
