package automationpractice;

import Utils.Utils;
import driver.CustomisedDriver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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

    @ParameterizedTest(name = "{index} => Checks product number=''{0}''")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6})
    void assertThatModalIsShownAfterAddToCartIsPressed(int productNumber) throws InterruptedException {
        //arrange
        driver.get("http://automationpractice.com/index.php");
        WebElement item = driver.findElements(By.xpath("//li[contains(@class, 'ajax_block_product')]")).get(productNumber);
        WebElement addToCartButton = driver.findElements(By.xpath("//li[contains(@class, 'ajax_block_product')]//a[@title='Add to cart']")).get(productNumber);
        String expectedModalTitle = "Product successfully added to your shopping cart";
        String shoppingCartModalXPath = "//div[contains(@style, 'display: block;')][1]//h2[contains(text()[2], 'Product successfully added to your shopping cart')]";

        //act
        builder.moveToElement(item).perform();
        addToCartButton.click();
        Utils.waitForElement(driver, shoppingCartModalXPath, 30);
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
        Utils.waitForElement(driver, shoppingCartModalXPath, 30);
        WebElement continueShoppingButton = driver.findElement(By.xpath(continueShoppingButtonXPath));
        continueShoppingButton.click();
        Thread.sleep(1000);

        //assert
        Assertions.assertThrows(NoSuchElementException.class, () -> driver.findElement(By.xpath(shoppingCartModalXPath)));
    }

    @Test
    void assertThatSummaryPageIsShownAfterProceedToCheckoutIsPressed() throws InterruptedException {
        //arrange
        driver.get("http://automationpractice.com/index.php");
        WebElement item = driver.findElements(By.xpath("//*[contains(@title, 'Faded Short Sleeve T-shirts')]")).get(1);
        WebElement addToCartButton = driver.findElements(By.xpath("//*[contains(text(), 'Add to cart')]")).get(0);
        String shoppingCartModalXPath = "//div[contains(@style, 'display: block;')][1]//h2[contains(text()[2], 'Product successfully added to your shopping cart')]";
        String proceedToCheckoutButtonXPath = "//div[contains(@style, 'display: block;')][1]//a[@title='Proceed to checkout']";
        String shoppingCartSummaryXPath = "//h1[contains(text(), 'Shopping-cart summary')]";
        String expectedURL = "http://automationpractice.com/index.php?controller=order";

        //act
        builder.moveToElement(item).perform();
        addToCartButton.click();
        Utils.waitForElement(driver, shoppingCartModalXPath, 30);
        WebElement proceedToCheckoutButton = driver.findElement(By.xpath(proceedToCheckoutButtonXPath));
        proceedToCheckoutButton.click();
        Utils.waitForElement(driver, shoppingCartSummaryXPath, 30);
        String actualURL = driver.getCurrentUrl();

        //assert
        Assertions.assertEquals(expectedURL, actualURL, "URL mismatch.");
    }

    @Test
    void assertThatQuantityOfProductIsIncreasedAfterPlusButtonIsPressed() throws InterruptedException {
        //arrange
        driver.get("http://automationpractice.com/index.php");
        WebElement item = driver.findElements(By.xpath("//*[contains(@title, 'Faded Short Sleeve T-shirts')]")).get(1);
        WebElement addToCartButton = driver.findElements(By.xpath("//*[contains(text(), 'Add to cart')]")).get(0);
        String shoppingCartModalXPath = "//div[contains(@style, 'display: block;')][1]//h2[contains(text()[2], 'Product successfully added to your shopping cart')]";
        String proceedToCheckoutButtonXPath = "//div[contains(@style, 'display: block;')][1]//a[@title='Proceed to checkout']";
        String shoppingCartSummaryXPath = "//h1[contains(text(), 'Shopping-cart summary')]";
        builder.moveToElement(item).perform();
        addToCartButton.click();
        Utils.waitForElement(driver, shoppingCartModalXPath, 30);
        WebElement proceedToCheckoutButton = driver.findElement(By.xpath(proceedToCheckoutButtonXPath));
        proceedToCheckoutButton.click();
        Utils.waitForElement(driver, shoppingCartSummaryXPath, 30);
        WebElement plusButton = driver.findElement(By.xpath("//i[@class = 'icon-plus']"));
        WebElement quantityLabel = driver.findElement(By.xpath("//input[@name='quantity_1_1_0_0_hidden']"));
        String expectedQuantity = "5";
        String actualQuantity;

        //act
        Utils.clickElementNTimes(plusButton, 4, 2000);
        actualQuantity = quantityLabel.getAttribute("value");

        //assert
        Assertions.assertEquals(expectedQuantity, actualQuantity, "Quantity of product is different than " + expectedQuantity);
    }


}
