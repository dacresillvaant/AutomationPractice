package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class MainContainer {

    public MainContainer(WebDriver driver) {
        PageFactory.initElements(driver, this);
        builder = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private Actions builder;
    private WebDriverWait wait;

    @FindBy(css = "img[alt='My Store']")
    private WebElement logo;

    @FindBy(css = "input[id='search_query_top']")
    private WebElement search;

    @FindBy(css = "button[name='submit_search']")
    private WebElement searchLens;

    public MainContainer waitForItClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return this;
    }

    public MainContainer sendSearchInput(String text) {
        search.sendKeys(text);
        return this;
    }

    public MainContainer executeSearch() {
        searchLens.click();
        return this;
    }
}
