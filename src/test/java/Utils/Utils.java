package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Utils {

    public static boolean isElementPresentByXPath(WebDriver driver, String xPath) {
        boolean isPresent = false;
        try {
            WebElement element = driver.findElement(By.xpath(xPath));
            isPresent = true;
        } catch (Exception e) {
            System.out.println("Could not find element defined by provided XPath-> " + xPath);
        }
        return isPresent;
    }
}
