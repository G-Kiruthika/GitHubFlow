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
 * Handles repository deletion workflow
 */
public class DeleteRepoPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//nav//a[contains(@href,'/settings')]" )
    WebElement settingsLink;

    @FindBy(xpath = "//summary[contains(text(),'Delete this repository')]")
    WebElement deleteBtn;

    @FindBy(xpath = "//form[contains(@action,'/settings/delete')]/p/input")
    WebElement confirmRepoNameInput;

    @FindBy(xpath = "//form[contains(@action,'/settings/delete')]//button[contains(text(),'I understand the consequences') or contains(text(),'Delete this repository')]")
    WebElement proceedDeleteBtn;

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

    public void typeRepoName(String repoFullName) {
        wait.until(ExpectedConditions.visibilityOf(confirmRepoNameInput));
        confirmRepoNameInput.clear();
        confirmRepoNameInput.sendKeys(repoFullName);
    }

    public void clickProceedDelete() {
        wait.until(ExpectedConditions.elementToBeClickable(proceedDeleteBtn));
        proceedDeleteBtn.click();
    }
}
