package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AuthorizationTest extends BaseTest {

    @DataProvider(name = "Input for login")
    public Object[][] inputForLogin() {
        return new Object[][]{
                {"standard_user", "123", "Epic sadface: Username and password do not match any user in this service"}
//                {"standard_user", "secret_sauce", "Products"}
        };
    }


    public void authPathForAllUsers(String username) {
        loginPage.open();
        loginPage.login(username, "secret_sauce");

        if (username.contains("locked")) {
            Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Sorry, this user has been locked out.", "The data is not correct");
        } else {
            productsPage.isProductPageOpened();
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

    @Test(retryAnalyzer = Retry.class)
    public void checkAuthPerformanceUser() {
        String performanceUser = "performance_glitch_user";
        authPathForAllUsers(performanceUser);
    }

    @Test(dataProvider = "Input for login")
    public void checkAuthRandomUser(String user, String pswrd, String error) {
        loginPage.open();
        loginPage.login(user, pswrd);
        assertEquals(loginPage.getErrorMessage(), error, "Wrong test data");
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
