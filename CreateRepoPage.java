package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateRepoPage {
    WebDriver driver;
    By newRepoButton = By.linkText("New");
    By repoNameField = By.id("repository_name");
    By createButton = By.cssSelector("button.first-in-line");

    public CreateRepoPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickNewRepo() {
        driver.findElement(newRepoButton).click();
    }

    public void enterRepoName(String repoName) {
        driver.findElement(repoNameField).sendKeys(repoName);
    }

    public void clickCreate() {
        driver.findElement(createButton).click();
    }
}
