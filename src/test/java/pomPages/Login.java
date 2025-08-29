package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Page Object for GitHub Login Page
 * Covers: Sign-in link, email, password, sign-in button
 * Test Cases: Login, landing page verification
 */
public class Login {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//a[text()='Sign in']")
    WebElement signInLink;

    @FindBy(id = "login_field")
    WebElement emailField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(name = "commit")
    WebElement signInButton;

    public Login(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Clicks the 'Sign in' link on the landing page
    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(signInLink));
        signInLink.click();
    }

    // Enters the email/username
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.clear();
        emailField.sendKeys(email);
    }

    // Enters the password
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    // Clicks the 'Sign in' button
    public void clickSignInButton() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        signInButton.click();
    }

    // Verifies landing page elements
    public boolean isLandingPageLoaded() {
        return signInLink.isDisplayed();
    }
}
