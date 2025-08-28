package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Page Object for Repository Deletion Page
 * Handles navigation to settings and deletion actions
 */
public class DeleteRepoPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//nav//a[contains(@href,'/settings') and contains(text(),'Settings')]")
    WebElement settingsLink;

    @FindBy(xpath = "//summary[contains(text(),'Delete this repository')]")
    WebElement deleteBtn;

    @FindBy(xpath = "//form[contains(@action,'/settings/delete')]/p/input")
    WebElement confirmRepoNameInput;

    @FindBy(xpath = "//form[contains(@action,'/settings/delete')]//button[contains(text(),'I understand the consequences')]")
    WebElement confirmDeleteBtn;

    public DeleteRepoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void clickSettings() {
        wait.until(ExpectedConditions.elementToBeClickable(settingsLink));
        settingsLink.click();
    }

    public void clickDelete() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteBtn));
        deleteBtn.click();
    }

    public void typeRepoName(String repoName) {
        wait.until(ExpectedConditions.visibilityOf(confirmRepoNameInput));
        confirmRepoNameInput.clear();
        confirmRepoNameInput.sendKeys(repoName);
    }

    public void clickProceedDelete() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteBtn));
        confirmDeleteBtn.click();
    }
}
