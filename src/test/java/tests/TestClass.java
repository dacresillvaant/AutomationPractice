package tests;

import org.testng.annotations.Test;
import pages.ItemsGrid;
import selenium.TestBase;

public class TestClass extends TestBase {

    @Test
    public void shouldOpenDesiredWebsite() {
        driver.get("http://automationpractice.com/index.php");
        ItemsGrid itemsGrid = new ItemsGrid(driver);
//        itemsGrid.printAmountOfItems().clickNthItem(5);
        itemsGrid.hoverOverNthItem(5).addToCartNthItem(5);
    }

}
