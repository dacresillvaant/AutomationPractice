package tests;

import org.testng.annotations.Test;
import selenium.TestBase;

public class TestClass extends TestBase {

    @Test
    public void shouldOpenDesiredWebsite() {
        driver.get("http://automationpractice.com/index.php");
    }
}
