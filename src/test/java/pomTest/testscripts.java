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

/**
 * TestNG Test Script for GitHub Automation
 * Covers login, repo creation, deletion, and sign out
 */
public class testscripts {
    WebDriver driver;
    Login loginPage;
    HomePage homePage;
    CreateRepoPage createRepoPage;
    DeleteRepoPage deleteRepoPage;
    SignOut signOutPage;

    // Test data (should be parameterized or loaded from test cases)
    String baseUrl = "https://github.com";
    String username = "testuser@example.com";
    String password = "TestPassword123";
    String repoName = "selenium-automation-demo-repo";

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
        // Step 1: Click Sign in
        loginPage.clickLoginLink();
        // Step 2: Enter username
        loginPage.enterEmail(username);
        // Step 3: Enter password
        loginPage.enterPassword(password);
        // Step 4: Click Sign in
        loginPage.clickSignInButton();
        // Assertion: Home page loaded (Create repo button visible)
        Assert.assertTrue(driver.getCurrentUrl().contains("github.com"), "Login failed or not redirected to home page.");
    }

    @Test(priority = 2, dependsOnMethods = {"loginPositive"})
    public void repoCreation() {
        // Step 1: Click Create repository
        homePage.clickCreateRepo();
        // Step 2: Enter repository name
        createRepoPage.enterRepoName(repoName);
        // Step 3: Click Create repository
        createRepoPage.createRepoBtnClick();
        // Assertion: Repository page loaded
        Assert.assertTrue(driver.getCurrentUrl().contains(repoName), "Repository creation failed or not redirected to repo page.");
    }

    @Test(priority = 3, dependsOnMethods = {"repoCreation"})
    public void deleteRepo() {
        // Step 1: Go to Settings
        deleteRepoPage.clickSettings();
        // Step 2: Click Delete this repository
        deleteRepoPage.clickDelete();
        // Step 3: Type repo name to confirm
        deleteRepoPage.typeRepoName(username.split("@")[0] + "/" + repoName);
        // Step 4: Click I understand the consequences, delete this repository
        deleteRepoPage.clickProceedDelete();
        // Assertion: Redirected to repositories list or confirmation message
        Assert.assertTrue(driver.getCurrentUrl().contains("/repositories") || driver.getPageSource().contains("was successfully deleted"), "Repository deletion failed.");
    }

    @Test(priority = 4, dependsOnMethods = {"deleteRepo"})
    public void signOut() {
        // Step 1: Click profile icon
        signOutPage.clickProfile();
        // Step 2: Click Sign out
        signOutPage.clickSignOut();
        // Assertion: Redirected to login page
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Sign out failed or not redirected to login page.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
