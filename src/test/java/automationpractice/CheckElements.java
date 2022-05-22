package automationpractice;

import driver.CustomisedDriver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class CheckElements {

    ChromeDriver driver = new CustomisedDriver().setUp();

    @Test
    public void assertThatWebURLisCorrect() {
        driver.get("http://automationpractice.com/index.php");
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "http://automationpractice.com/index.php";
        Assertions.assertEquals(expectedUrl, actualUrl, "Actual URL is incorrect.");
    }
}
