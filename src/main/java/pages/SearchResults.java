package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class SearchResults {

    public SearchResults(WebDriver driver) {
        PageFactory.initElements(driver, this);
        builder = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private Actions builder;
    private WebDriverWait wait;

    @FindBy(css = "span[class='heading-counter']")
    private WebElement searchCounter;

    @FindBy(css = "select[id='selectProductSort']")
    private WebElement sortSelect;

    public SearchResults setSortType(String value) {
        new Select(sortSelect).selectByValue(value);
        return this;
    }

    public SearchResults waitForItClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return this;
    }
}
