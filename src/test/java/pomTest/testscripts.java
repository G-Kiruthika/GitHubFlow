package pomTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pomPages.Login;
import pomPages.HomePage;
import pomPages.CreateRepoPage;
import pomPages.DeleteRepoPage;
import pomPages.SignOut;
import java.time.Duration;

public class testscripts {
    WebDriver driver;
    Login loginPage;
    HomePage homePage;
    CreateRepoPage createRepoPage;
    DeleteRepoPage deleteRepoPage;
    SignOut signOutPage;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        loginPage = new Login(driver);
        homePage = new HomePage(driver);
        createRepoPage = new CreateRepoPage(driver);
        deleteRepoPage = new DeleteRepoPage(driver);
        signOutPage = new SignOut(driver);
    }

    @Test(priority = 1)
    public void verifyLandingPageElements() {
        driver.get("https://github.com");
        Assert.assertTrue(homePage.isLogoVisible(), "GitHub logo is not visible");
        Assert.assertTrue(homePage.isSignInButtonVisible(), "Sign in button is not visible");
        Assert.assertTrue(homePage.isSignUpButtonVisible(), "Sign up button is not visible");
        Assert.assertTrue(homePage.isSearchBarVisible(), "Search bar is not visible");
    }

    @Test(priority = 2)
    public void loginWithValidCredentials() {
        driver.get("https://github.com");
        loginPage.clickLoginLink();
        loginPage.enterEmail("valid@email.com");
        loginPage.enterPassword("validPassword");
        loginPage.clickSignInButton();
        Assert.assertTrue(homePage.isProfileIconVisible(), "Profile icon is not visible. Login might have failed.");
    }

    @Test(priority = 3)
    public void loginWithInvalidEmail() {
        driver.get("https://github.com");
        loginPage.clickLoginLink();
        loginPage.enterEmail("invalid@email");
        loginPage.enterPassword("validPassword");
        loginPage.clickSignInButton();
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message not displayed for invalid email.");
        Assert.assertTrue(loginPage.isAtLoginPage(), "User is not on login page after invalid email attempt.");
    }

    @Test(priority = 4)
    public void loginWithInvalidPassword() {
        driver.get("https://github.com");
        loginPage.clickLoginLink();
        loginPage.enterEmail("valid@email.com");
        loginPage.enterPassword("invalidPassword");
        loginPage.clickSignInButton();
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message not displayed for invalid password.");
        Assert.assertTrue(loginPage.isAtLoginPage(), "User is not on login page after invalid password attempt.");
    }

    @Test(priority = 5)
    public void loginWithEmptyEmail() {
        driver.get("https://github.com");
        loginPage.clickLoginLink();
        loginPage.enterEmail("");
        loginPage.enterPassword("validPassword");
        loginPage.clickSignInButton();
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message not displayed for empty email.");
        Assert.assertTrue(loginPage.isAtLoginPage(), "User is not on login page after empty email attempt.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
