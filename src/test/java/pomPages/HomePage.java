package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Page Object for GitHub Home Page after login
 * Covers: Create repository button
 * Test Cases: Repo creation
 */
public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//a[@href='/new' and contains(text(),'New')] | //span[contains(text(),'New repository')]/parent::a")
    WebElement createRepoBtn;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Clicks the 'New repository' button
    public void clickCreateRepo() {
        wait.until(ExpectedConditions.elementToBeClickable(createRepoBtn));
        createRepoBtn.click();
    }
}
