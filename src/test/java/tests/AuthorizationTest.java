package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AuthorizationTest extends BaseTest{

    public void authPathForAllUsers(String username) {
        loginPage.open();
        loginPage.login(username, "secret_sauce");
        if (username.contains("locked")) {
            Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Sorry, this user has been locked out.", "The data is not correct");
        } else {
            Assert.assertEquals(loginPage.getProductsCatalog(), "Products", "User doesn't authorization");
        }
    }

    @Test
    public void standardUserAuthorizationTest() {
        String standardUser = "standard_user";
        authPathForAllUsers(standardUser);
    }

    @Test
    public void lockedUserAuthorizationTest() {
        String lockedUser = "locked_out_user";
        authPathForAllUsers(lockedUser);
    }

    @Test
    public void problemUserAuthorizationTest() {
        String problemUser = "problem_user";
        authPathForAllUsers(problemUser);
    }

    @Test
    public void performanceUserAuthorizationTest() {
        String performanceUser = "performance_glitch_user";
        authPathForAllUsers(performanceUser);
    }
}
