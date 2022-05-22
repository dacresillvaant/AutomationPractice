package automationpractice;

import driver.CustomisedDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class MainPage {

    ChromeDriver driver = new CustomisedDriver().setUp();

    @AfterEach
    public void closeAndQuit() {
        driver.close();
        driver.quit();
    }

    @Test
    public void assertThatWebURLisCorrect() {
        driver.get("http://automationpractice.com/index.php");
        String expectedUrl = "http://automationpractice.com/index.php";
        String actualUrl = driver.getCurrentUrl();
        Assertions.assertEquals(expectedUrl, actualUrl, "Actual URL is incorrect.");
    }
}
