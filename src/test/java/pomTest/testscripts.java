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
    String username = "testuser@example.com";
    String password = "TestPassword123";
    String repoName = "selenium-automation-demo-repo";
    String repoFullName = "testuser/selenium-automation-demo-repo";

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
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

    @Test(priority = 1, description = "Valid Login Test")
    public void loginPositive() {
        loginPage.clickLoginLink();
        loginPage.enterEmail(username);
        loginPage.enterPassword(password);
        loginPage.clickSignInButton();
        Assert.assertTrue(driver.getTitle().contains("GitHub"), "Login failed or not redirected to GitHub home page.");
    }

    @Test(priority = 2, description = "Create Repository Test", dependsOnMethods = {"loginPositive"})
    public void repoCreation() {
        homePage.clickCreateRepo();
        createRepoPage.enterRepoName(repoName);
        createRepoPage.createRepoBtnClick();
        Assert.assertTrue(driver.getCurrentUrl().contains(repoName), "Repository creation failed or not redirected to repo page.");
    }

    @Test(priority = 3, description = "Delete Repository Test", dependsOnMethods = {"repoCreation"})
    public void deleteRepo() {
        deleteRepoPage.clickSettings();
        deleteRepoPage.clickDelete();
        deleteRepoPage.typeRepoName(repoFullName);
        deleteRepoPage.clickProceedDelete();
        Assert.assertTrue(driver.getCurrentUrl().contains("/repositories"), "Repository deletion failed or not redirected to repositories list.");
    }

    @Test(priority = 4, description = "Sign Out Test", dependsOnMethods = {"deleteRepo"})
    public void signOut() {
        signOutPage.clickProfile();
        signOutPage.clickSignOut();
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Sign out failed or not redirected to login page.");
    }

    @Test(priority = 5, description = "Invalid Login Test")
    public void loginNegative() {
        driver.get(baseUrl);
        loginPage.clickLoginLink();
        loginPage.enterEmail("invaliduser@example.com");
        loginPage.enterPassword("wrongpassword");
        loginPage.clickSignInButton();
        Assert.assertTrue(loginPage.isLoginErrorDisplayed(), "Error message not displayed for invalid login.");
    }

    @Test(priority = 6, description = "Repository Creation Error Test")
    public void repoCreationError() {
        loginPage.clickLoginLink();
        loginPage.enterEmail(username);
        loginPage.enterPassword(password);
        loginPage.clickSignInButton();
        homePage.clickCreateRepo();
        createRepoPage.enterRepoName(""); // Empty repo name
        createRepoPage.createRepoBtnClick();
        Assert.assertTrue(createRepoPage.isRepoCreationErrorDisplayed(), "Error message not displayed for empty repo name.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
