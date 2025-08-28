package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Page Object for Repository Creation
 * Covers: Repository name input, create repository button
 */
public class CreateRepoPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "repository_name")
    WebElement repoNameInput;

    @FindBy(xpath = "//button[contains(text(),'Create repository')]")
    WebElement createRepoBtn;

    @FindBy(xpath = "//div[contains(@class,'flash-error')]")
    WebElement errorMsg;

    public CreateRepoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void enterRepoName(String name) {
        wait.until(ExpectedConditions.visibilityOf(repoNameInput));
        repoNameInput.clear();
        repoNameInput.sendKeys(name);
    }

    public void createRepoBtnClick() {
        wait.until(ExpectedConditions.elementToBeClickable(createRepoBtn));
        createRepoBtn.click();
    }

    public boolean isErrorDisplayed() {
        try {
            return errorMsg.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        return errorMsg.getText();
    }
}
