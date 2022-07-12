package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Base64;

@Getter
public class Cart {

    public Cart(WebDriver driver) {
        PageFactory.initElements(driver, this);
        builder = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private Actions builder;
    private WebDriverWait wait;

    @FindBy(css = "a[title='Proceed to checkout'][style='']")
    private WebElement proceedToCheckout;

    @FindBy(css = "button[name='processAddress']")
    private WebElement proceedToCheckoutAtAddress;

    @FindBy(css = "button[name='processCarrier']")
    private WebElement proceedToCheckoutAtShipping;

    @FindBy(css = "input[name='email'][value='']")
    private WebElement email;

    @FindBy(css = "input[id='passwd']")
    private WebElement password;

    @FindBy(css = "button[id='SubmitLogin']")
    private WebElement signIn;

    @FindBy(css = "input[id='cgv']")
    private WebElement termsCheckBox;

    @FindBy(css = "a[class='bankwire']")
    private WebElement byBankWire;

    @FindBy(xpath = "//span[contains(text(),'I confirm my order')]")
    private WebElement confirmOrder;


    public Cart proceedToCheckout() {
        proceedToCheckout.click();
        return this;
    }

    public Cart proceedToCheckoutFromAddress() {
        proceedToCheckoutAtAddress.click();
        return this;
    }

    public Cart proceedToCheckoutFromShipping() {
        proceedToCheckoutAtShipping.click();
        return this;
    }

    public Cart waitForItClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return this;
    }

    public Cart setEmail() {
        email.sendKeys("java.scrapper1337@gmail.com");
        return this;
    }

    public Cart setEncryptedPassword() {
        byte[] encodedPassword = {86, 86, 116, 108, 78, 87, 77, 49, 74, 48, 69, 61};
        byte[] decoded = Base64.getDecoder().decode(encodedPassword);
        password.sendKeys(new String(decoded));
        return this;
    }

    public Cart tickTermsOfService() {
        termsCheckBox.click();
        return this;
    }

    public Cart payByBankWire() {
        byBankWire.click();
        return this;
    }

    public Cart confirmOrder() {
        confirmOrder.click();
        return this;
    }


    public Cart signIn() {
        signIn.click();
        return this;
    }
}
