package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CreateRepoPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "repository_name")
    WebElement repoNameInput;

    @FindBy(id = "repository_description")
    WebElement repoDescInput;

    @FindBy(id = "repository_visibility_public")
    WebElement publicVisibilityRadio;

    @FindBy(xpath = "//button[contains(text(),'Create repository')]")
    WebElement createRepoBtn;

    @FindBy(xpath = "//div[contains(@class,'flash-error')] | //div[contains(@class,'error')]")
    WebElement errorMessage;

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

    public void enterRepoDescription(String desc) {
        wait.until(ExpectedConditions.visibilityOf(repoDescInput));
        repoDescInput.clear();
        repoDescInput.sendKeys(desc);
    }

    public void selectPublicVisibility() {
        wait.until(ExpectedConditions.elementToBeClickable(publicVisibilityRadio));
        if (!publicVisibilityRadio.isSelected()) {
            publicVisibilityRadio.click();
        }
    }

    public void clickCreateRepoBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(createRepoBtn));
        createRepoBtn.click();
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
