package automationpractice;

import Utils.Utils;
import driver.CustomisedDriver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

class ShoppingCart {

    static ChromeDriver driver = new CustomisedDriver().setUp();
    private Actions builder;

    @BeforeEach
    void setUpActionsBuilder() {
        builder = new Actions(driver);
    }

    @Test
    void assertThatModalIsShownAfterAddToCartIsPressed() throws InterruptedException {
        //arrange
        driver.get("http://automationpractice.com/index.php");
        WebElement item = driver.findElements(By.xpath("//*[contains(@title, 'Faded Short Sleeve T-shirts')]")).get(1);
        WebElement addToCartButton = driver.findElements(By.xpath("//*[contains(text(), 'Add to cart')]")).get(0);
        String expectedModalTitle = "Product successfully added to your shopping cart";
        String shoppingCartModalXPath = "//div[contains(@style, 'display: block;')][1]//h2[contains(text()[2], 'Product successfully added to your shopping cart')]";

        //act
        builder.moveToElement(item).perform();
        addToCartButton.click();
        Utils.waitForElement(driver, shoppingCartModalXPath);
        String actualModalTitle = driver.findElement(By.xpath(shoppingCartModalXPath)).getText();

        //assert
        Assertions.assertEquals(expectedModalTitle, actualModalTitle, "Adding product to shopping cart failed.");
    }

    @Test
    void assertThatModalShownAfterAddToCartIsPressedDisappearsAfterContinueShoppingIsPressed() throws InterruptedException {
        //arrange
        driver.get("http://automationpractice.com/index.php");
        WebElement item = driver.findElements(By.xpath("//*[contains(@title, 'Faded Short Sleeve T-shirts')]")).get(1);
        WebElement addToCartButton = driver.findElements(By.xpath("//*[contains(text(), 'Add to cart')]")).get(0);
        String shoppingCartModalXPath = "//div[contains(@style, 'display: block;')][1]//h2[contains(text()[2], 'Product successfully added to your shopping cart')]";
        String continueShoppingButtonXPath = "//div[contains(@style, 'display: block;')][1]//span[@title='Continue shopping']";

        //act
        builder.moveToElement(item).perform();
        addToCartButton.click();
        Utils.waitForElement(driver, shoppingCartModalXPath);
        WebElement continueShoppingButton = driver.findElement(By.xpath(continueShoppingButtonXPath));
        continueShoppingButton.click();
        Thread.sleep(1000);

        //assert
        Assertions.assertThrows(NoSuchElementException.class, () -> driver.findElement(By.xpath(shoppingCartModalXPath)));
    }
}
