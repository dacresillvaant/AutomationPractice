package tests;

import org.testng.annotations.Test;
import pages.ItemsGrid;
import pages.MainContainer;
import pages.SearchResults;
import selenium.TestBase;

public class SearchTests extends TestBase {

    @Test
    public void shouldSearchForDress() {
        driver.get("http://automationpractice.com/index.php");
        MainContainer mainContainer = new MainContainer(driver);
        ItemsGrid itemsGrid = new ItemsGrid(driver);
        SearchResults searchResults = new SearchResults(driver);

        mainContainer.sendSearchInput("dress")
                .executeSearch()
                .waitForItClickable(itemsGrid.getGridItems().get(0));
        searchResults.setSortType("name:asc");
        System.out.println(itemsGrid.getGridItems().get(0).getText());
    }
}
