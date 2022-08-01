package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Account;
import pages.Cart;
import pages.Menu;
import selenium.TestBase;

public class AccountTests extends TestBase {

    @Test
    public void shouldSignIn() {
        driver.get("http://automationpractice.com/");
        Menu menu = new Menu(driver);
        Cart cart = new Cart(driver);
        Account account = new Account(driver);

        menu.signIn();
        account.waitForItClickable(account.getSignIn())
                .setEmail()
                .setEncryptedPassword()
                .signIn();
        Assert.assertEquals(menu.getMyAccount().getText(), "Matthew Tester");
    }

    @Test
    public void shouldSignOut() {
        driver.get("http://automationpractice.com/");
        Menu menu = new Menu(driver);
        Account account = new Account(driver);

        menu.signIn();
        account.waitForItClickable(account.getSignIn())
                .setEmail()
                .setEncryptedPassword()
                .signIn()
                .waitForItClickable(menu.getSignOut());
        menu.signOut();
        Assert.assertTrue(menu.getSignIn().isDisplayed());
    }

    @Test
    public void shouldCreateNewAccount() {
        driver.get("http://automationpractice.com/");
        Menu menu = new Menu(driver);
        Account account = new Account(driver);

        menu.signIn();
        account.waitForItClickable(account.getCreateAccount())
                .setNewAccountEmail(8)
                .createNewAccount()
                .waitForItClickable(account.getRegister())
                .setSex("male")
                .setFirstName("inputFN")
                .setLastName("inputLN")
                .setPassword("password123+PASS")
                .setDayOfBirth("5")
                .setMonthOfBirth("6")
                .setYearOfBirth("2022")
                .setAddress("Test address")
                .setCity("New York")
                .setState("32")
                .setPostCode("10001")
                .setMobilePhone("500500500")
                .setAddressAlias("Home")
                .register()
                .waitForItClickable(menu.getSignOut());
        Assert.assertTrue(menu.getSignOut().isDisplayed());
    }

}
