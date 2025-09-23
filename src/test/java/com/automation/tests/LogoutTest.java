package com.automation.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LogoutTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://example.com/dashboard");
    }

    @Test
    public void testLogout() {
        WebElement profileMenu = driver.findElement(By.id("profileMenu"));
        profileMenu.click();
        WebElement logoutButton = driver.findElement(By.id("logoutBtn"));
        logoutButton.click();
        WebElement loginPage = driver.findElement(By.id("loginPage"));
        Assert.assertTrue(loginPage.isDisplayed());
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
