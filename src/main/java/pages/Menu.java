package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class Menu {

    public Menu(WebDriver driver) {
        PageFactory.initElements(driver, this);
        builder = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private Actions builder;
    private WebDriverWait wait;

    @FindBy(css = "a[title='Contact Us']")
    private WebElement contactUs;

    @FindBy(css = "a[title='View my customer account']")
    private WebElement myAccount;

    @FindBy(css = "a[title='Log in to your customer account']")
    private WebElement signIn;

    @FindBy(css = "a[title='Log me out']")
    private WebElement signOut;

    public Menu signIn() {
        signIn.click();
        return this;
    }

    public Menu signOut() {
        signOut.click();
        return this;
    }

    public Menu accountDetails() {
        myAccount.click();
        return this;
    }

    public Menu contactUs() {
        contactUs.click();
        return this;
    }
}
