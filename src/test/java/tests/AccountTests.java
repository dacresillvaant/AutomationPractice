package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Cart;
import pages.Menu;
import selenium.TestBase;

public class AccountTests extends TestBase {

    @Test
    public void shouldSignIn() {
        driver.get("http://automationpractice.com/");
        Menu menu = new Menu(driver);
        Cart cart = new Cart(driver);

        menu.signIn();
        cart.waitForItClickable(cart.getSignIn())
                .setEmail()
                .setEncryptedPassword()
                .signIn();
        Assert.assertEquals(menu.getMyAccount().getText(), "Matthew Tester");
    }
}
