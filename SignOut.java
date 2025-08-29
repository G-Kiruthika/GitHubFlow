package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignOut {
    WebDriver driver;
    By profileDropdown = By.xpath("//summary[@aria-label='View profile and more']");
    By signOutButton = By.xpath("//button[contains(text(),'Sign out')]");

    public SignOut(WebDriver driver) {
        this.driver = driver;
    }

    public void openProfileDropdown() {
        driver.findElement(profileDropdown).click();
    }

    public void clickSignOut() {
        driver.findElement(signOutButton).click();
    }
}
