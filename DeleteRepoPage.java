package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DeleteRepoPage {
    WebDriver driver;
    By settingsTab = By.xpath("//nav//span[contains(text(),'Settings')]");
    By deleteButton = By.xpath("//summary[contains(text(),'Delete this repository')]");
    By confirmField = By.xpath("//form[contains(@action,'delete')]/p/input");
    By confirmDeleteButton = By.xpath("//form[contains(@action,'delete')]/button");

    public DeleteRepoPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToSettings() {
        driver.findElement(settingsTab).click();
    }

    public void clickDeleteRepo() {
        driver.findElement(deleteButton).click();
    }

    public void confirmDelete(String repoFullName) {
        driver.findElement(confirmField).sendKeys(repoFullName);
        driver.findElement(confirmDeleteButton).click();
    }
}
