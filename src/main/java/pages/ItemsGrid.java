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
import java.util.List;

@Getter
public class ItemsGrid {

    public ItemsGrid(WebDriver driver) {
        PageFactory.initElements(driver, this);
        builder = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private Actions builder;
    private WebDriverWait wait;

    @FindBy(css = "div[class='product-container']")
    private List<WebElement> gridItems;

    @FindBy(css = "a[title='Add to cart']")
    private List<WebElement> addToCart;

    @FindBy(css = "a[title='Proceed to checkout']")
    private WebElement proceedToCheckout;

    public ItemsGrid printAmountOfItems() {
        System.out.println("Amount of items is: " + gridItems.size());
        return this;
    }

    public ItemsGrid clickNthItem(int itemNumber) {
        gridItems.get(itemNumber).click();
        return this;
    }

    public ItemsGrid hoverOverNthItem(int itemNumber) {
        builder.moveToElement(gridItems.get(itemNumber)).perform();
        return this;
    }

    public ItemsGrid addToCartNthItem(int itemNumber) {
        addToCart.get(itemNumber).click();
        return this;
    }

    public ItemsGrid proceedToCheckout() {
        proceedToCheckout.click();
        return this;
    }

    public ItemsGrid waitForIt(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return this;
    }

}
