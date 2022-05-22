package automationpractice;

import driver.CustomisedDriver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class Search {

    ChromeDriver driver = new CustomisedDriver().setUp();

    @Test
    void assertThatSearchReturnsNoneResultsGivenTrololo() {
        driver.get("http://automationpractice.com/index.php");
        driver.findElement(By.id("search_query_top")).sendKeys("Trololo");
        driver.findElement(By.xpath("//*[contains(@name, 'submit_search')]")).click();
        String expectedResults = "0 results have been found.";
        String actualResults = driver.findElement(By.xpath("//*[contains(text(), 'results have been found')]")).getText();
        Assertions.assertEquals(expectedResults, actualResults, "Amount of results is different than 0.");
    }
}
