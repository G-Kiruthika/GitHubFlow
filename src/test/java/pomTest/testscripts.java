package pomTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
pomPages.Login;
pomPages.HomePage;
pomPages.CreateRepoPage;
pomPages.DeleteRepoPage;
pomPages.SignOut;
import java.time.Duration;

/**
 * TestNG Test Script for GitHub Automation
 * Covers: Login, Create Repo, Delete Repo, Sign Out
 * Traceability: HAP-492 TS-001 TC-001, ...
 */
public class testscripts {
    WebDriver driver;
    Login loginPage;
    HomePage homePage;
    CreateRepoPage createRepoPage;
    DeleteRepoPage deleteRepoPage;
    SignOut signOutPage;
    String baseUrl = "https://github.com";
    String username = "<your-username>"; // Replace with valid username
    String password = "<your-password>"; // Replace with valid password
    String repoName = "test-repo-automation";
    String repoFullName = username + "/" + repoName;

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
        // Step 1-3: Verify landing page and login
        Assert.assertTrue(loginPage.isLandingPageLoaded(), "Landing page not loaded or Sign in link missing");
        loginPage.clickLoginLink();
        loginPage.enterEmail(username);
        loginPage.enterPassword(password);
        loginPage.clickSignInButton();
        // Optionally, assert home page loaded
        Assert.assertTrue(driver.getTitle().contains("GitHub"), "Login failed or Home page not loaded");
    }

    @Test(priority = 2, dependsOnMethods = {"loginPositive"})
    public void RepoCreation() {
        // Create new repository
        homePage.clickCreateRepo();
        createRepoPage.enterRepoName(repoName);
        createRepoPage.createRepoBtnClick();
        // Assert repository created (URL or success message)
        Assert.assertTrue(driver.getCurrentUrl().contains(repoName), "Repository creation failed");
    }

    @Test(priority = 3, dependsOnMethods = {"RepoCreation"})
    public void DeleteRepo() {
        // Delete the created repository
        deleteRepoPage.clickSettings();
        deleteRepoPage.clickDelete();
        deleteRepoPage.typeRepoName(repoFullName);
        deleteRepoPage.clickProceedDelete();
        // Assert repository deleted (redirect or message)
        Assert.assertTrue(driver.getCurrentUrl().contains("/repositories"), "Repository deletion failed");
    }

    @Test(priority = 4, dependsOnMethods = {"DeleteRepo"})
    public void SignOut() {
        // Sign out from GitHub
        signOutPage.clickProfile();
        signOutPage.clickSignOut();
        // Assert sign out (Sign in link visible)
        Assert.assertTrue(loginPage.isLandingPageLoaded(), "Sign out failed");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
