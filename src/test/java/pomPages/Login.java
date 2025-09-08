package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Page Object Model class for GitHub Login functionality.
 * Encapsulates all login-related elements and actions.
 * Supports: Sign-in link, Email field, Password field, Sign-in button.
 * Methods: clickLoginLink(), enterEmail(), enterPassword(), clickSignInButton(), visibility/clickability checks.
 * Follows best practices from framework knowledge base and test cases.
 */
public class Login {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators for GitHub login page elements
    @FindBy(xpath = "//a[contains(text(),'Sign in') or @href='/login']")
    private WebElement signInLink;

    @FindBy(id = "login_field")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(name = "commit")
    private WebElement signInButton;

    @FindBy(xpath = "//div[contains(@class,'flash-error')]" )
    private WebElement errorMessage;

    public Login(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /**
     * Clicks the 'Sign in' link on the GitHub homepage.
     */
    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(signInLink));
        signInLink.click();
    }

    /**
     * Enters the email/username into the login field.
     * @param email GitHub username or email
     */
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.clear();
        emailField.sendKeys(email);
    }

    /**
     * Enters the password into the password field.
     * @param password GitHub password
     */
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    /**
     * Clicks the 'Sign in' button to submit credentials.
     */
    public void clickSignInButton() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        signInButton.click();
    }

    /**
     * Checks if the error message is displayed (for invalid/empty credentials).
     * @return true if error message is visible, false otherwise
     */
    public boolean isErrorMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Gets the error message text (if present).
     * @return error message string, or null if not present
     */
    public String getErrorMessageText() {
        if (isErrorMessageDisplayed()) {
            return errorMessage.getText();
        }
        return null;
    }

    /**
     * Waits for the email field to be visible (used for page load verification).
     */
    public void waitForEmailField() {
        wait.until(ExpectedConditions.visibilityOf(emailField));
    }

    /**
     * Waits for the password field to be visible (used for page load verification).
     */
    public void waitForPasswordField() {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
    }
}
