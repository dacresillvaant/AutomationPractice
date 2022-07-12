package tests;

import org.testng.annotations.Test;
import pages.Cart;
import pages.ItemsGrid;
import selenium.TestBase;

public class TestClass extends TestBase {

    @Test
    public void shouldOpenDesiredWebsite() {
        driver.get("http://automationpractice.com/index.php");
        ItemsGrid itemsGrid = new ItemsGrid(driver);
        Cart cart = new Cart(driver);

        itemsGrid.hoverOverNthItem(5)
                .addToCartNthItem(5)
                .waitForIt(itemsGrid.getProceedToCheckout())
                .proceedToCheckout();

        cart.waitForItClickable(cart.getProceedToCheckout())
                .proceedToCheckout()
                .waitForItClickable(cart.getEmail())
                .setEmail()
                .setEncryptedPassword()
                .signIn()
                .waitForItClickable(cart.getProceedToCheckoutAtAddress())
                .proceedToCheckoutFromAddress()
                .waitForItClickable(cart.getProceedToCheckoutAtShipping())
                .tickTermsOfService()
                .proceedToCheckoutFromShipping()
                .waitForItClickable(cart.getByBankWire())
                .payByBankWire()
                .waitForItClickable(cart.getConfirmOrder())
                .confirmOrder();
    }

}
