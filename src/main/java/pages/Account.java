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
import java.util.List;
import java.util.Objects;

@Getter
public class Account {

    public Account(WebDriver driver) {
        PageFactory.initElements(driver, this);
        builder = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private Actions builder;
    private WebDriverWait wait;

    @FindBy(css = "input[name='email'][value='']")
    private WebElement email;

    @FindBy(css = "input[name='email_create']")
    private WebElement emailNewAccount;

    @FindBy(css = "input[id='passwd']")
    private WebElement password;

    @FindBy(css = "button[id='SubmitLogin']")
    private WebElement signIn;

    @FindBy(css = "button[id='SubmitCreate']")
    private WebElement createAccount;

    @FindBy(css = "button[name='submitAccount']")
    private WebElement register;

    @FindBy(css = "input[name='id_gender']")
    private List<WebElement> sex;

    public Account setEmail() {
        email.sendKeys("java.scrapper1337@gmail.com");
        return this;
    }

    public Account setNewAccountEmail(int prefixLength) {
        String domain = "@test.com";
        String prefix = Utils.generateRandomString(prefixLength);
        String newAccountEmail = prefix + domain;
        System.out.println("Email to be used for creating account: " + newAccountEmail);
        emailNewAccount.sendKeys(newAccountEmail);
        return this;
    }

    public Account setEncryptedPassword() {
        byte[] encodedPassword = {86, 86, 116, 108, 78, 87, 77, 49, 74, 48, 69, 61};
        byte[] decoded = Base64.getDecoder().decode(encodedPassword);
        password.sendKeys(new String(decoded));
        return this;
    }

    public Account signIn() {
        signIn.click();
        return this;
    }

    public Account createNewAccount() {
        createAccount.click();
        return this;
    }

    public Account waitForItClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return this;
    }

    public Account setSex(String chosenSex) {
        if (Objects.equals(chosenSex, "male")) {
            sex.get(0).click();
        } else {
            sex.get(1).click();
        }
        return this;
    }
}
