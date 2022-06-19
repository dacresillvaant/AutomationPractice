package automationpractice;

import driver.CustomisedDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class MainPageTest {

    static ChromeDriver driver = new CustomisedDriver().setUp();

    @AfterEach
    public void closeAndQuit() {
//        driver.close();
//        driver.quit();
    }

    @Test
    void assertThatWebURLisCorrect() {
        driver.get("http://automationpractice.com/index.php");
        String expectedUrl = "http://automationpractice.com/index.php";
        String actualUrl = driver.getCurrentUrl();
        Assertions.assertEquals(expectedUrl, actualUrl, "Actual URL is incorrect.");
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t", "@", "trololo", "emailmalpa.pl"})
    void assertThatValidationIsShownForIncorrectEmailAddress(String input) {
        //arrange
        driver.get("http://automationpractice.com/index.php");
        String newsletterLabelXPath = "//input[@id='newsletter-input']";
        String newsletterButtonXPath = "//button[@name='submitNewsletter']";
        String validationLabelXPath = "//p[@class = 'alert alert-danger']";
        String expectedValidationMessage = "Newsletter : Invalid email address.";
        String actualValidationMessage;
        WebElement newsletterLabel = driver.findElement(By.xpath(newsletterLabelXPath));
        WebElement newsletterButton = driver.findElement(By.xpath(newsletterButtonXPath));
        WebElement validationLabel;

        //act
        newsletterLabel.sendKeys(input);
        newsletterButton.click();
        validationLabel = driver.findElement(By.xpath(validationLabelXPath));
        actualValidationMessage = validationLabel.getText();

        //assert
        Assertions.assertEquals(expectedValidationMessage, actualValidationMessage);
    }

    @ParameterizedTest
    @ValueSource(strings = {"test@test.pl", "john.doe@gmail.com"})
    void assertThatValidationIsShownForAlreadyRegisteredEmail(String input) {
        //arange
        driver.get("http://automationpractice.com/index.php");
        String newsletterLabelXPath = "//input[@id='newsletter-input']";
        String newsletterButtonXPath = "//button[@name='submitNewsletter']";
        String validationLabelXPath = "//p[@class = 'alert alert-danger']";
        String expectedValidationMessage = "Newsletter : This email address is already registered.";
        String actualValidationMessage;
        WebElement newsletterLabel = driver.findElement(By.xpath(newsletterLabelXPath));
        WebElement newsletterButton = driver.findElement(By.xpath(newsletterButtonXPath));
        WebElement validationLabel;

        //act
        newsletterLabel.sendKeys(input);
        newsletterButton.click();
        validationLabel = driver.findElement(By.xpath(validationLabelXPath));
        actualValidationMessage = validationLabel.getText();

        //assert
        Assertions.assertEquals(expectedValidationMessage, actualValidationMessage);
    }
}
