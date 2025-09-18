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
    String baseUrl = "https://github.com";
    String username = "<your-username>";
    String password = "<your-password>";
    String repoName = "test-repo-automation";

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
        // Step 1: Launch browser and go to GitHub
        Assert.assertTrue(driver.getTitle().contains("GitHub"), "GitHub landing page did not load.");
        // Step 2: Click Sign In
        loginPage.clickLoginLink();
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page not displayed.");
        // Step 3: Enter credentials and login
        loginPage.enterEmail(username);
        loginPage.enterPassword(password);
        loginPage.clickSignInButton();
        // Step 4: Verify home page loaded
        Assert.assertTrue(homePage.isHomePageLoaded(), "Home page not loaded after login.");
    }

    @Test(priority = 2, dependsOnMethods = {"loginPositive"})
    public void RepoCreation() {
        // Step 1: Click Create Repository
        homePage.clickCreateRepo();
        Assert.assertTrue(createRepoPage.isCreateRepoPageLoaded(), "Create Repo page not loaded.");
        // Step 2: Enter repository name
        createRepoPage.enterRepoName(repoName);
        // Step 3: Click Create Repository button
        createRepoPage.createRepoBtnClick();
        // Step 4: Validate repository creation (URL or success message)
        Assert.assertTrue(driver.getCurrentUrl().contains(repoName), "Repository was not created successfully.");
    }

    @Test(priority = 3, dependsOnMethods = {"RepoCreation"})
    public void DeleteRepo() {
        // Step 1: Go to Settings
        deleteRepoPage.clickSettings();
        // Step 2: Click Delete this repository
        deleteRepoPage.clickDelete();
        // Step 3: Confirm repository name
        deleteRepoPage.typeRepoName(username + "/" + repoName);
        // Step 4: Proceed with deletion
        deleteRepoPage.clickProceedDelete();
        // Step 5: Validate deletion (redirected to repositories list)
        Assert.assertTrue(driver.getCurrentUrl().contains("/repositories"), "Repository was not deleted successfully.");
    }

    @Test(priority = 4, dependsOnMethods = {"DeleteRepo"})
    public void SignOut() {
        // Step 1: Click profile icon
        signOutPage.clickProfile();
        // Step 2: Click Sign Out
        signOutPage.clickSignOut();
        // Step 3: Validate sign out (redirected to login or landing page)
        Assert.assertTrue(driver.getTitle().contains("GitHub"), "Sign out failed or landing page not loaded.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
