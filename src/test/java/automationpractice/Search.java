package automationpractice;

import driver.CustomisedDriver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

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

    @Test
    void assertThatLogoRedirectsFromSearchResultsToHomePage() {
        driver.get("http://automationpractice.com/index.php?controller=search&orderby=position&orderway=desc&search_query=Trololo&submit_search=");
        driver.findElement(By.cssSelector(".logo.img-responsive")).click();
        String expectedUrl = "http://automationpractice.com/index.php";
        String actualUrl = driver.getCurrentUrl();
        Assertions.assertEquals(expectedUrl, actualUrl);
    }

    @Test
    void assertThatSearchReturns7ResultsGivenDress() {
        driver.get("http://automationpractice.com/index.php");
        driver.findElement(By.id("search_query_top")).sendKeys("dress");
        driver.findElement(By.xpath("//*[contains(@name, 'submit_search')]")).click();
        String expectedResults = "7 results have been found.";
        String actualResults = driver.findElement(By.xpath("//*[contains(text(), 'results have been found')]")).getText();
        Assertions.assertEquals(expectedResults, actualResults, "Amount of results is different than 7.");
    }

    @Test
    void assertThatFoundSearchResultHaveAddToCartAndMoreButtons() {
        driver.get("http://automationpractice.com/index.php");
        driver.findElement(By.id("search_query_top")).sendKeys("dress");
        driver.findElement(By.xpath("//*[contains(@name, 'submit_search')]")).click();
        List<WebElement> foundElements = driver.findElements(By.xpath("//*[contains(@title, 'Printed Summer Dress')]"));
        WebElement foundElement = driver.findElements(By.xpath("//*[contains(@title, 'Printed Summer Dress')]")).get(1);
        Actions builder = new Actions(driver);
        builder.moveToElement(foundElement).perform();
        WebElement addToCartButton = driver.findElements(By.xpath("//*[contains(text(), 'Add to cart')]")).get(1);
        WebElement moreButton = driver.findElements(By.xpath("//*[contains(text(), 'More')]")).get(1);
        Assertions.assertNotNull(addToCartButton, "Add to cart button not found.");
        Assertions.assertNotNull(moreButton, "More button not found.");
    }
}
