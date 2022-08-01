package tests;

import org.testng.annotations.Test;
import pages.ItemsGrid;
import pages.MainContainer;
import selenium.TestBase;

public class SearchTests extends TestBase {

    @Test
    public void shouldSearchForDress() {
        driver.get("http://automationpractice.com/index.php");
        MainContainer mainContainer = new MainContainer(driver);
        ItemsGrid itemsGrid = new ItemsGrid(driver);

        mainContainer.sendSearchInput("dress")
                .executeSearch();
    }
}
