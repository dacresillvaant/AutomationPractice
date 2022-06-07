package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Utils {

    public static boolean isElementPresentByXPath(WebDriver driver, String xPath) {
        boolean isPresent = false;
        try {
            driver.findElement(By.xpath(xPath));
            System.out.println("Located element defined by provided XPath -> " + xPath);
            isPresent = true;
        } catch (Exception e) {
            System.out.println("Could not find element defined by provided XPath-> " + xPath);
        }
        return isPresent;
    }

    public static void waitForElement(WebDriver driver, String xPath, int retryAmount) throws InterruptedException {
        boolean isLoaded = false;
        int tryIndex = 1;
        while (isLoaded == false) {
            System.out.println("Trying to locate element for the " + tryIndex + " time.");
            tryIndex++;
            isLoaded = isElementPresentByXPath(driver, xPath);
            Thread.sleep(1000);
            if (tryIndex == retryAmount) {
                break;
            }
        }
    }

    public static void clickElementNTimes(WebElement element, int n, int sleepIntervalMilliseconds) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            element.click();
            Thread.sleep(sleepIntervalMilliseconds);
        }
    }
}
