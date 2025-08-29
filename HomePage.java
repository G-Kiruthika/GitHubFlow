package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[aria-label='Global']")
    WebElement globalHeader;

    @FindBy(css = "a[href='/login']")
    WebElement signInLink;

    @FindBy(css = "a[href='/join?source=header-home']")
    WebElement signUpLink;

    public boolean isHeaderDisplayed() {
        return globalHeader.isDisplayed();
    }

    public boolean isSignInLinkDisplayed() {
        return signInLink.isDisplayed();
    }

    public boolean isSignUpLinkDisplayed() {
        return signUpLink.isDisplayed();
    }
}
