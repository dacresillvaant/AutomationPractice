package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ItemsGrid {

    public ItemsGrid(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div[class='product-container']")
    private List<WebElement> gridItems;

    public ItemsGrid printAmountOfItems() {
        System.out.println("Amount of items is: " + gridItems.size());
        return this;
    }

    public ItemsGrid clickNthGridItem(int itemNumber) {
        gridItems.get(itemNumber).click();
        return this;
    }
}
