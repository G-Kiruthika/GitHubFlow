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
    String username = "your-username"; // Replace with test data
    String password = "your-password"; // Replace with test data
    String repoName = "test-repo-automation"; // Replace with test data
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
        loginPage.clickLoginLink();
        loginPage.enterEmail(username);
        loginPage.enterPassword(password);
        loginPage.clickSignInButton();
        Assert.assertTrue(driver.getTitle().contains("GitHub"), "Login failed or not on GitHub home page.");
    }

    @Test(priority = 2, dependsOnMethods = {"loginPositive"})
    public void RepoCreation() {
        homePage.clickCreateRepo();
        createRepoPage.enterRepoName(repoName);
        createRepoPage.createRepoBtnClick();
        Assert.assertTrue(driver.getCurrentUrl().contains(repoFullName), "Repository creation failed or not navigated to repo page.");
    }

    @Test(priority = 3, dependsOnMethods = {"RepoCreation"})
    public void DeleteRepo() {
        driver.get(baseUrl + repoFullName);
        deleteRepoPage.clickSettings();
        deleteRepoPage.clickDelete();
        deleteRepoPage.typeRepoName(repoFullName);
        deleteRepoPage.clickProceedDelete();
        Assert.assertTrue(driver.getCurrentUrl().equals(baseUrl + username + "?tab=repositories"), "Repository deletion failed or not redirected to repositories list.");
    }

    @Test(priority = 4, dependsOnMethods = {"DeleteRepo"})
    public void SignOut() {
        signOutPage.clickProfile();
        signOutPage.clickSignOut();
        Assert.assertTrue(driver.getTitle().contains("Sign in"), "Sign out failed or not on sign-in page.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
