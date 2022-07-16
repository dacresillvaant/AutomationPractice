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

    @FindBy(css = "button[id='SubmitCreate']")
    private WebElement createAccount;

    @FindBy(css = "input[id='cgv']")
    private WebElement termsCheckBox;

    @FindBy(css = "a[class='bankwire']")
    private WebElement byBankWire;

    @FindBy(xpath = "//span[contains(text(),'I confirm my order')]")
    private WebElement confirmOrder;

    @FindBy(css = "p[class='cheque-indent']")
    private WebElement orderConfirmationDetails;

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

    public Cart waitForTextVisible(WebElement element, String text) {
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
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

}
