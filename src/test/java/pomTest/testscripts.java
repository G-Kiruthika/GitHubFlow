package pomTest;

import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pomPages.Login;
import pomPages.HomePage;
import pomPages.CreateRepoPage;
import pomPages.DeleteRepoPage;
import pomPages.SignOut;

/**
 * TestNG Test Script for GitHub Core Functionalities
 * Implements: Login, Create Repo, Delete Repo, Logout
 * Each test case is mapped to a method as per framework KB
 */
public class testscripts {
    WebDriver driver;
    Login loginPage;
    HomePage homePage;
    CreateRepoPage createRepoPage;
    DeleteRepoPage deleteRepoPage;
    SignOut signOutPage;

    // Test Data (replace with actual test data from test cases)
    String baseUrl = "https://github.com";
    String username = "your_email@example.com";
    String password = "your_password";
    String repoName = "test-repo-automation";

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
        // Login to GitHub
        loginPage.clickLoginLink();
        loginPage.enterEmail(username);
        loginPage.enterPassword(password);
        loginPage.clickSignInButton();
        // Assert home page loaded
        Assert.assertTrue(driver.getTitle().contains("GitHub"), "Login failed or Home page not loaded.");
    }

    @Test(priority = 2)
    public void RepoCreation() {
        // Create new repository
        homePage.clickCreateRepo();
        createRepoPage.enterRepoName(repoName);
        createRepoPage.createRepoBtnClick();
        // Assert repo created
        Assert.assertTrue(driver.getCurrentUrl().contains(repoName), "Repository creation failed.");
    }

    @Test(priority = 3)
    public void DeleteRepo() {
        // Delete repository
        deleteRepoPage.clickSettings();
        deleteRepoPage.clickDelete();
        deleteRepoPage.typeRepoName(username + "/" + repoName);
        deleteRepoPage.clickProceedDelete();
        // Assert repo deleted (redirected to repo list or confirmation message)
        Assert.assertTrue(driver.getPageSource().contains("Repository deleted"), "Repository deletion failed.");
    }

    @Test(priority = 4)
    public void SignOut() {
        // Sign out from GitHub
        signOutPage.clickProfile();
        signOutPage.clickSignOut();
        // Assert sign out (login page visible)
        Assert.assertTrue(driver.getTitle().contains("Sign in"), "Sign out failed.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
