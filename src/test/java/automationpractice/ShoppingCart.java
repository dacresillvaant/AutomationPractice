package automationpractice;

import driver.CustomisedDriver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
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
        builder.moveToElement(item).perform();
        WebElement addToCartButton = driver.findElements(By.xpath("//*[contains(text(), 'Add to cart')]")).get(0);
        String expectedModalTitle = "Product successfully added to your shopping cart";

        //act
        addToCartButton.click();
        Thread.sleep(5000);
        String actualModalTitle = driver.findElement(By.xpath("//div[@id='layer_cart']//h2[contains(text()[2], 'Product successfully added to your shopping cart')]")).getText();

        //assert
        Assertions.assertEquals(expectedModalTitle, actualModalTitle, "Adding product to shopping cart failed.");

    }
}
