package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.ItemsGrid;
import pages.MainContainer;
import pages.SearchResults;
import selenium.TestBase;

public class SearchTests extends TestBase {

    @Test
    public void shouldSearchForDressAndSortByNameAscending() {
        driver.get("http://automationpractice.com/index.php");
        MainContainer mainContainer = new MainContainer(driver);
        ItemsGrid itemsGrid = new ItemsGrid(driver);
        SearchResults searchResults = new SearchResults(driver);

        mainContainer.sendSearchInput("dress")
                .executeSearch()
                .waitForItClickable(itemsGrid.getGridItems().get(0));
        searchResults.setSortType("name:asc");

        softAssert.assertEquals(itemsGrid.getGridItems().get(0).findElement(By.cssSelector("a[class='product-name']")).getText(), "Blouse");
        softAssert.assertEquals(itemsGrid.getGridItems().get(itemsGrid.getGridItems().size()-1).findElement(By.cssSelector("a[class='product-name']")).getText(), "Printed Summer Dress");
    }
}
