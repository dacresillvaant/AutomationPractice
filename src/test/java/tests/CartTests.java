package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Cart;
import pages.ItemsGrid;
import selenium.TestBase;

public class CartTests extends TestBase {

    @Test
    public void shouldAddProductToCartAndFinalizeOrder() {
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
                .confirmOrder()
                .waitForTextVisible(cart.getOrderConfirmationDetails(), "Your order on My Store is complete.");

        Assert.assertEquals(cart.getOrderConfirmationDetails().getText(), "Your order on My Store is complete.");
    }

}
