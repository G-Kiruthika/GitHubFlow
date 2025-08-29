package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;
    By signInLink = By.linkText("Sign in");
    By signUpButton = By.linkText("Sign up");
    By logo = By.cssSelector("a.header-logo");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLogoDisplayed() {
        return driver.findElement(logo).isDisplayed();
    }

    public boolean isSignInDisplayed() {
        return driver.findElement(signInLink).isDisplayed();
    }

    public boolean isSignUpDisplayed() {
        return driver.findElement(signUpButton).isDisplayed();
    }

    public void clickSignIn() {
        driver.findElement(signInLink).click();
    }
}
