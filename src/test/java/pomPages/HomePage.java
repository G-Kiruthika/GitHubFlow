package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//summary[@aria-label='View profile and more']")
    WebElement profileIcon;

    @FindBy(xpath = "//a[@href='/new' and contains(text(),'New') or contains(text(),'Create repository')]")
    WebElement createRepoBtn;

    @FindBy(xpath = "//img[@alt='GitHub']")
    WebElement githubLogo;

    @FindBy(xpath = "//a[contains(text(),'Sign in')]")
    WebElement signInBtn;

    @FindBy(xpath = "//a[contains(text(),'Sign up')]")
    WebElement signUpBtn;

    @FindBy(xpath = "//input[@name='q' and @type='text']")
    WebElement searchBar;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean isLogoVisible() {
        return wait.until(ExpectedConditions.visibilityOf(githubLogo)).isDisplayed();
    }

    public boolean isSignInButtonVisible() {
        return wait.until(ExpectedConditions.visibilityOf(signInBtn)).isDisplayed();
    }

    public boolean isSignUpButtonVisible() {
        return wait.until(ExpectedConditions.visibilityOf(signUpBtn)).isDisplayed();
    }

    public boolean isSearchBarVisible() {
        return wait.until(ExpectedConditions.visibilityOf(searchBar)).isDisplayed();
    }

    public void clickCreateRepo() {
        wait.until(ExpectedConditions.elementToBeClickable(createRepoBtn));
        createRepoBtn.click();
    }

    public boolean isProfileIconVisible() {
        return wait.until(ExpectedConditions.visibilityOf(profileIcon)).isDisplayed();
    }
}
