package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {
    public static final By FIRST_NAME = By.id("first-name");
    public static final By LAST_NAME = By.id("last-name");
    public static final By POSTAL_CODE = By.id("postal-code");
    public static final By CHECKOUT_BUTTON = By.cssSelector(".checkout_button");
    public static final By CONTINUE_BUTTON = By.cssSelector(".cart_button");
    public static final By FINISH_BUTTON = By.xpath("//*[text()='FINISH']");
    public static final By ORDER_COMPLETE = By.cssSelector(".complete-header");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getNumberOfProducts() {
        List<WebElement> numberOfProductsInTheCart = driver.findElements(By.cssSelector(".cart_item"));
        return numberOfProductsInTheCart.size();
    }

    public String getNameOfProduct(int index) {
        List<WebElement> listOfProductNamesInTheCart = driver.findElements(By.cssSelector(".inventory_item_name"));
        return listOfProductNamesInTheCart.get(index).getText();
    }

    public void fillCheckoutForm(String firstName, String lastName, String postalCode) {
        driver.findElement(FIRST_NAME).sendKeys(firstName);
        driver.findElement(LAST_NAME).sendKeys(lastName);
        driver.findElement(POSTAL_CODE).sendKeys(postalCode);
        driver.findElement(CONTINUE_BUTTON).click();
    }

    public void clickCheckoutButton() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }

    public void clickFinishButton() {
        driver.findElement(FINISH_BUTTON).click();
    }

    public String getOrderConfirm() {
        return driver.findElement(ORDER_COMPLETE).getText();
    }

    public double getSummaryOfProducts() {
        double result = 0;
        List<WebElement> sumOfItems = driver.findElements(By.cssSelector(".inventory_item_price"));
        for (int i = 0; i < sumOfItems.size(); i++) {
            String value = sumOfItems.get(i).getText();
            result = result + Double.parseDouble(value.substring(1, value.length()));
        }
        return result;
    }
}
