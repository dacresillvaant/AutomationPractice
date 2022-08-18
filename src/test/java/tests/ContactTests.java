package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Contact;
import pages.Menu;
import selenium.TestBase;

public class ContactTests extends TestBase {

    @Test
    public void shouldSendContactRequest() {
        Contact contact = new Contact(driver);
        Menu menu = new Menu(driver);

        driver.get("http://automationpractice.com/index.php");
        menu.contactUs();
        contact.waitForItClickable(contact.getSend())
                .setSubject("Customer service")
                .setEmail("test@test.com")
                .setOrder("123456")
                .setMessage("Test message via automated test.")
                .sendMessage();

        Assert.assertEquals(contact.getSuccessfulLabel().getText(), "Your message has been successfully sent to our team.");
    }
}
