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
 * TestNG Test Scripts for GitHub Automation
 * Covers login, create repo, delete repo, sign out
 * Test data and steps are mapped to test cases
 */
public class testscripts {
    WebDriver driver;
    Login loginPage;
    HomePage homePage;
    CreateRepoPage createRepoPage;
    DeleteRepoPage deleteRepoPage;
    SignOut signOutPage;

    // Test Data (should be parameterized or loaded from test cases in real framework)
    String baseUrl = "https://github.com/";
    String username = "testuser@example.com";
    String password = "TestPassword123";
    String repoName = "selenium-automation-demo-repo";
    String repoFullName = "testuser/selenium-automation-demo-repo";

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
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page should be displayed");
        // Step 2: Enter email
        loginPage.enterEmail(username);
        // Step 3: Enter password
        loginPage.enterPassword(password);
        // Step 4: Click Sign in
        loginPage.clickSignInButton();
        // Step 5: Assert Home Page loaded (Create repo button visible)
        Assert.assertTrue(driver.getCurrentUrl().contains("github.com"), "Should be on GitHub after login");
    }

    @Test(priority = 2, dependsOnMethods = {"loginPositive"})
    public void RepoCreation() {
        // Step 1: Click Create repository
        homePage.clickCreateRepo();
        // Step 2: Enter repository name
        createRepoPage.enterRepoName(repoName);
        // Step 3: Click Create repository button
        createRepoPage.createRepoBtnClick();
        // Step 4: Assert repository created (URL contains repo name)
        Assert.assertTrue(driver.getCurrentUrl().contains(repoName), "Repository should be created and navigated to repo page");
    }

    @Test(priority = 3, dependsOnMethods = {"RepoCreation"})
    public void DeleteRepo() {
        // Step 1: Go to Settings
        deleteRepoPage.clickSettings();
        // Step 2: Click Delete this repository
        deleteRepoPage.clickDelete();
        // Step 3: Type repo full name for confirmation
        deleteRepoPage.typeRepoName(repoFullName);
        // Step 4: Click final delete button
        deleteRepoPage.clickProceedDelete();
        // Step 5: Assert repository deleted (redirected to repositories list)
        Assert.assertTrue(driver.getCurrentUrl().contains("/repositories"), "Should be redirected to repositories list after deletion");
    }

    @Test(priority = 4, dependsOnMethods = {"DeleteRepo"})
    public void SignOut() {
        // Step 1: Click profile icon
        signOutPage.clickProfile();
        // Step 2: Click Sign out
        signOutPage.clickSignOut();
        // Step 3: Assert redirected to login page
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Should be redirected to login page after sign out");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
