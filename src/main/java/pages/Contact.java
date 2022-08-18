package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class Contact {

    public Contact(WebDriver driver) {
        PageFactory.initElements(driver, this);
        builder = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private Actions builder;
    private WebDriverWait wait;

    @FindBy(css = "button[id='submitMessage']")
    private WebElement send;

    @FindBy(css = "select[id = 'id_contact']")
    private WebElement subjectSelect;

    @FindBy(css = "input[id='email']")
    private WebElement emailInput;

    @FindBy(css = "input[id='id_order']")
    private WebElement orderInput;

    @FindBy(css = "textarea[id='message']")
    private WebElement messageArea;

    @FindBy(css = "p[class='alert alert-success']")
    private WebElement successfulLabel;

    public Contact waitForItClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return this;
    }

    public Contact setSubject(String value) {
        new Select(subjectSelect).selectByVisibleText(value);
        return this;
    }

    public Contact setEmail(String value) {
        emailInput.sendKeys(value);
        return this;
    }

    public Contact setOrder(String value) {
        orderInput.sendKeys(value);
        return this;
    }

    public Contact setMessage(String value) {
        messageArea.sendKeys(value);
        return this;
    }

    public Contact sendMessage() {
        send.click();
        return this;
    }

}
