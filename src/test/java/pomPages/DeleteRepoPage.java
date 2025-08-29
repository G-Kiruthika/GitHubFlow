package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Page Object for GitHub Delete Repository Page
 * Covers: Settings link, Delete buttons, Confirmation input
 * Test Cases: Repo deletion
 */
public class DeleteRepoPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//nav//a[contains(@href, '/settings')]" )
    WebElement settingsLink;

    @FindBy(xpath = "//summary[contains(text(),'Delete this repository')]" )
    WebElement deleteBtn;

    @FindBy(xpath = "//form[contains(@action, '/settings/delete')]/p/input[@name='verify']" )
    WebElement confirmRepoNameInput;

    @FindBy(xpath = "//form[contains(@action, '/settings/delete')]//button[contains(text(),'I understand the consequences')]" )
    WebElement proceedDeleteBtn;

    public DeleteRepoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Navigates to repository settings
    public void clickSettings() {
        wait.until(ExpectedConditions.elementToBeClickable(settingsLink));
        settingsLink.click();
    }

    // Clicks the 'Delete this repository' button
    public void clickDelete() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteBtn));
        deleteBtn.click();
    }

    // Types the repository name for confirmation
    public void typeRepoName(String repoFullName) {
        wait.until(ExpectedConditions.visibilityOf(confirmRepoNameInput));
        confirmRepoNameInput.clear();
        confirmRepoNameInput.sendKeys(repoFullName);
    }

    // Clicks the final delete confirmation button
    public void clickProceedDelete() {
        wait.until(ExpectedConditions.elementToBeClickable(proceedDeleteBtn));
        proceedDeleteBtn.click();
    }
}
