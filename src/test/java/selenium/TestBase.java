package selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

public class TestBase {

    public WebDriver driver;
    public SoftAssert softAssert;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
//        WebDriverManager.operadriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
//        options.addArguments("--headless");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        softAssert = new SoftAssert();
    }

    //    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
