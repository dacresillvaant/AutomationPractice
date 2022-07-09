package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ItemsGrid {

    public ItemsGrid(WebDriver driver) {
        PageFactory.initElements(driver, this);
        builder = new Actions(driver);
    }

    private Actions builder;

    @FindBy(css = "div[class='product-container']")
    private List<WebElement> gridItems;

    @FindBy(css = "a[title='Add to cart']")
    private List<WebElement> addToCart;

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

}
