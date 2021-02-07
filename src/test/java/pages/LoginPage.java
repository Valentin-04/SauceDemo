package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    public static final By LOGIN_INPUT = By.id("user-name");
    public static final By PASSWORD_INPUT = By.id("password");
    public static final By LOGIN_BUTTON = By.id("login-button");
    public static final By ERROR_MESSAGE = By.cssSelector("[data-test=error]");
    public static final By DATA_ON_THE_LOGIN_PAGE = By.cssSelector(".login_password");
    public static final By ABOUT_PAGE_BUTTON = By.id("about_sidebar_link");
    public static final By ABOUT_PAGE_DATA = By.cssSelector(".module-ticker .supertitle");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://www.saucedemo.com/");
    }

    public void login(String username, String password) {
        driver.findElement(LOGIN_INPUT).sendKeys(username);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }

    public String getDataFromTheLoginPage() {
         return driver.findElement(DATA_ON_THE_LOGIN_PAGE).getText();
    }

    public void clickAboutPageButton() {
        driver.findElement(ABOUT_PAGE_BUTTON).click();
    }

    public String getAboutPageConfirm() {
        return driver.findElement(ABOUT_PAGE_DATA).getText();
    }
}
