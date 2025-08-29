package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {
    WebDriver driver;

    public Login(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Example login elements (not used in this test case, but included for POM completeness)
    @FindBy(name = "login")
    WebElement usernameField;

    @FindBy(name = "password")
    WebElement passwordField;

    @FindBy(name = "commit")
    WebElement signInButton;

    public void loginToGitHub(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        signInButton.click();
    }
}
