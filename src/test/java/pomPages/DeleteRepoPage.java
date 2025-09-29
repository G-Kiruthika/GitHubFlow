package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DeleteRepoPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//nav//a[contains(@href,'/settings')]")
    WebElement settingsLink;

    @FindBy(xpath = "//summary[contains(text(),'Delete this repository')]")
    WebElement deleteBtn;

    @FindBy(xpath = "//input[@name='verify']")
    WebElement confirmRepoNameInput;

    @FindBy(xpath = "//button[contains(text(),'I understand the consequences, delete this repository')]")
    WebElement proceedDeleteBtn;

    @FindBy(xpath = "//div[contains(@class,'flash-error')] | //div[contains(@class,'error')]")
    WebElement errorMessage;

    @FindBy(xpath = "//button[contains(text(),'Cancel')]")
    WebElement cancelBtn;

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
        wait.until(ExpectedConditions.elementToBeClickable(proceedDeleteBtn));
        proceedDeleteBtn.click();
    }

    public void clickCancel() {
        wait.until(ExpectedConditions.elementToBeClickable(cancelBtn));
        cancelBtn.click();
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessageText() {
        try {
            return errorMessage.getText();
        } catch (Exception e) {
            return "";
        }
    }
}
