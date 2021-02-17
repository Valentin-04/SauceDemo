package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageFactory extends BasePage {
    @FindBy(id = "user-name")
    WebElement loginInput;
    @FindBy(id = "password")
    WebElement passwordInput;
    @FindBy(id = "login-button")
    WebElement loginButton;
    @FindBy(css = "[data-test=error]")
    WebElement errorMessage;
    @FindBy(css = ".login_password")
    WebElement dataOnTheLoginPage;
    @FindBy(id = "about_sidebar_link")
    WebElement aboutPageButton;
    @FindBy(css = ".module-ticker .supertitle")
    WebElement aboutPageData;

    public LoginPageFactory(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://www.saucedemo.com/");
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password) {
        loginInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public String getDataFromTheLoginPage() {
        return dataOnTheLoginPage.getText();
    }

    public void clickAboutPageButton() {
        aboutPageButton.click();
    }

    public String getAboutPageConfirm() {
        return aboutPageData.getText();
    }
}
