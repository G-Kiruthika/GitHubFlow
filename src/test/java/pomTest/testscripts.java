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

/**
 * TestNG Test Script for GitHub Automation
 * Covers: Login, Create Repo, Delete Repo, Sign Out, Error Handling
 */
public class testscripts {
    WebDriver driver;
    Login loginPage;
    HomePage homePage;
    CreateRepoPage createRepoPage;
    DeleteRepoPage deleteRepoPage;
    SignOut signOutPage;

    // Test data (replace with actual test data from JSON)
    String baseUrl = "https://github.com";
    String validEmail = "testuser@example.com";
    String validPassword = "TestPassword123";
    String invalidEmail = "invalid@example.com";
    String invalidPassword = "wrongpass";
    String repoName = "selenium-test-repo";

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
        loginPage = new Login(driver);
        homePage = new HomePage(driver);
        createRepoPage = new CreateRepoPage(driver);
        deleteRepoPage = new DeleteRepoPage(driver);
        signOutPage = new SignOut(driver);
    }

    @Test(priority = 1)
    public void loginPositive() {
        loginPage.clickLoginLink();
        loginPage.enterEmail(validEmail);
        loginPage.enterPassword(validPassword);
        loginPage.clickSignInButton();
        // Assert home page loaded
        Assert.assertTrue(driver.getTitle().contains("GitHub"), "Login failed or Home page not loaded.");
    }

    @Test(priority = 2)
    public void loginNegative() {
        driver.get(baseUrl);
        loginPage.clickLoginLink();
        loginPage.enterEmail(invalidEmail);
        loginPage.enterPassword(invalidPassword);
        loginPage.clickSignInButton();
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message not displayed for invalid login.");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Incorrect username or password."), "Unexpected error message.");
    }

    @Test(priority = 3)
    public void repoCreation() {
        homePage.clickCreateRepo();
        createRepoPage.enterRepoName(repoName);
        createRepoPage.createRepoBtnClick();
        // Assert repo created (URL contains repo name)
        Assert.assertTrue(driver.getCurrentUrl().contains(repoName), "Repository creation failed.");
    }

    @Test(priority = 4)
    public void repoCreationNegative() {
        homePage.clickCreateRepo();
        createRepoPage.enterRepoName(""); // Empty repo name
        createRepoPage.createRepoBtnClick();
        Assert.assertTrue(createRepoPage.isErrorDisplayed(), "Error message not displayed for empty repo name.");
    }

    @Test(priority = 5)
    public void deleteRepo() {
        driver.get(baseUrl + "/" + validEmail.split("@")[0] + "/" + repoName);
        deleteRepoPage.clickSettings();
        deleteRepoPage.clickDelete();
        deleteRepoPage.typeRepoName(validEmail.split("@")[0] + "/" + repoName);
        deleteRepoPage.clickProceedDelete();
        // Assert repo deleted (redirected to repo list)
        Assert.assertTrue(driver.getCurrentUrl().contains("/repositories"), "Repository deletion failed.");
    }

    @Test(priority = 6)
    public void signOut() {
        signOutPage.clickProfile();
        signOutPage.clickSignOut();
        // Assert sign out (login link visible)
        Assert.assertTrue(driver.getPageSource().contains("Sign in"), "Sign out failed.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
