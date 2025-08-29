package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login {
    WebDriver driver;
    By usernameField = By.id("login_field");
    By passwordField = By.id("password");
    By signInButton = By.name("commit");

    public Login(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickSignIn() {
        driver.findElement(signInButton).click();
    }

    public void loginToGithub(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickSignIn();
    }
}
