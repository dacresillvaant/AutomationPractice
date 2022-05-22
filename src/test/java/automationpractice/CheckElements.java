package automationpractice;

import driver.CustomisedDriver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class CheckElements {

    ChromeDriver driver = new CustomisedDriver().setUp();

    @Test
    public void firstTest() {
        driver.get("http://www.google.pl");
    }
}
