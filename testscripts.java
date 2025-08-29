package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;

public class testscripts {
    WebDriver driver;
    HomePage homePage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://github.com");
        homePage = new HomePage(driver);
    }

    @Test
    public void verifyLandingPageElements() {
        Assert.assertTrue(homePage.isHeaderDisplayed(), "Global header is not displayed");
        Assert.assertTrue(homePage.isSignInLinkDisplayed(), "Sign In link is not displayed");
        Assert.assertTrue(homePage.isSignUpLinkDisplayed(), "Sign Up link is not displayed");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
