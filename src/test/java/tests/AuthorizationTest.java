package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AuthorizationTest extends BaseTest {

    public void authPathForAllUsers(String username) {
        loginPage.open();
        loginPage.login(username, "secret_sauce");
        productsPage.isProductPageOpened();
        if (username.contains("locked")) {
            Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Sorry, this user has been locked out.", "The data is not correct");
        } else {
            Assert.assertEquals(productsPage.getProductsCatalog(), "Products", "User doesn't authorization");
        }
    }

    @Test
    public void checkAuthStandardUser() {
        String standardUser = "standard_user";
        authPathForAllUsers(standardUser);
    }

    @Test
    public void checkAuthLockedUser() {
        String lockedUser = "locked_out_user";
        authPathForAllUsers(lockedUser);
    }

    @Test
    public void checkAuthProblemUser() {
        String problemUser = "problem_user";
        authPathForAllUsers(problemUser);
    }

    @Test
    public void checkAuthPerformanceUser() {
        String performanceUser = "performance_glitch_user";
        authPathForAllUsers(performanceUser);
    }

    @Test
    public void checkAuthRandomUser() {
        loginPage.open();
        loginPage.login("123", "secret_sauce");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username and password do not " +
                "match any user in this service", "Wrong test data");
    }

    @Test
    public void checkLogOut() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.isProductPageOpened();
        productsPage.openMenu();
        productsPage.logOut();
        assertEquals(loginPage.getDataFromTheLoginPage(), "Password for all users:\n" +
                "secret_sauce", "Data doesn't natch");
    }
}
