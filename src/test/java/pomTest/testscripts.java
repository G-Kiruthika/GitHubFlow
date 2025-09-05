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
    String baseUrl = "https://github.com/";
    String username = "testuser@example.com";
    String password = "TestPassword123";
    String repoName = "selenium-automation-repo";

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
        loginPage.clickLoginLink();
        loginPage.enterEmail(username);
        loginPage.enterPassword(password);
        loginPage.clickSignInButton();
        Assert.assertTrue(driver.getTitle().contains("GitHub"), "Login failed or not redirected to GitHub home page.");
    }

    @Test(priority = 2, dependsOnMethods = {"loginPositive"})
    public void RepoCreation() {
        homePage.clickCreateRepo();
        createRepoPage.enterRepoName(repoName);
        createRepoPage.createRepoBtnClick();
        Assert.assertTrue(driver.getCurrentUrl().contains(repoName), "Repository creation failed or not redirected to repo page.");
    }

    @Test(priority = 3, dependsOnMethods = {"RepoCreation"})
    public void DeleteRepo() {
        deleteRepoPage.clickSettings();
        deleteRepoPage.clickDelete();
        deleteRepoPage.typeRepoName(username.split("@")[0] + "/" + repoName);
        deleteRepoPage.clickProceedDelete();
        Assert.assertTrue(driver.getCurrentUrl().contains("/repositories"), "Repository deletion failed or not redirected to repositories list.");
    }

    @Test(priority = 4, dependsOnMethods = {"DeleteRepo"})
    public void SignOut() {
        signOutPage.clickProfile();
        signOutPage.clickSignOut();
        Assert.assertTrue(loginPage.isSignInButtonVisible(), "Sign out failed or not redirected to login page.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
