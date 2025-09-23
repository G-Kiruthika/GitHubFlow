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

public class testscripts {
    WebDriver driver;
    Login loginPage;
    HomePage homePage;
    CreateRepoPage createRepoPage;
    DeleteRepoPage deleteRepoPage;
    SignOut signOutPage;

    String baseUrl = "https://github.com";
    String email = "testuser@example.com";
    String password = "TestPassword123";
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
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickSignInButton();
        Assert.assertTrue(driver.getTitle().contains("GitHub"), "Login failed or GitHub home page not loaded.");
    }

    @Test(priority = 2)
    public void repoCreation() {
        homePage.clickCreateRepo();
        createRepoPage.enterRepoName(repoName);
        createRepoPage.createRepoBtnClick();
        Assert.assertTrue(driver.getCurrentUrl().contains(repoName), "Repository creation failed or repo page not loaded.");
    }

    @Test(priority = 3)
    public void deleteRepo() {
        deleteRepoPage.clickSettings();
        deleteRepoPage.clickDelete();
        deleteRepoPage.typeRepoName("testuser/" + repoName);
        deleteRepoPage.clickProceedDelete();
        Assert.assertTrue(driver.getCurrentUrl().equals(baseUrl + "/testuser?tab=repositories"), "Repository deletion failed or not redirected to repositories page.");
    }

    @Test(priority = 4)
    public void signOut() {
        signOutPage.clickProfile();
        signOutPage.clickSignOut();
        Assert.assertTrue(driver.getTitle().contains("Sign in to GitHub"), "Sign out failed or not redirected to login page.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
