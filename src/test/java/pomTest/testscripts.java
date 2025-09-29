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

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://github.com");
        loginPage = new Login(driver);
        homePage = new HomePage(driver);
        createRepoPage = new CreateRepoPage(driver);
        deleteRepoPage = new DeleteRepoPage(driver);
        signOutPage = new SignOut(driver);
    }

    @Test(priority = 1)
    public void loginPositive() {
        loginPage.clickLoginLink();
        loginPage.enterEmail("valid@email.com");
        loginPage.enterPassword("validPassword");
        loginPage.clickSignInButton();
        Assert.assertFalse(loginPage.isErrorMessageDisplayed(), "Login error message displayed for valid credentials.");
    }

    @Test(priority = 2)
    public void loginNegativeInvalidEmail() {
        loginPage.clickLoginLink();
        loginPage.enterEmail("invalid@email");
        loginPage.enterPassword("validPassword");
        loginPage.clickSignInButton();
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message not displayed for invalid email.");
    }

    @Test(priority = 3)
    public void loginNegativeInvalidPassword() {
        loginPage.clickLoginLink();
        loginPage.enterEmail("valid@email.com");
        loginPage.enterPassword("invalidPassword");
        loginPage.clickSignInButton();
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message not displayed for invalid password.");
    }

    @Test(priority = 4)
    public void loginNegativeEmptyEmail() {
        loginPage.clickLoginLink();
        loginPage.enterEmail("");
        loginPage.enterPassword("validPassword");
        loginPage.clickSignInButton();
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message not displayed for empty email.");
    }

    @Test(priority = 5)
    public void loginNegativeEmptyPassword() {
        loginPage.clickLoginLink();
        loginPage.enterEmail("valid@email.com");
        loginPage.enterPassword("");
        loginPage.clickSignInButton();
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message not displayed for empty password.");
    }

    @Test(priority = 6)
    public void repoCreationPositive() {
        loginPositive();
        homePage.clickCreateRepo();
        createRepoPage.enterRepoName("new-repo");
        createRepoPage.enterRepoDescription("Test repo");
        createRepoPage.selectPublicVisibility();
        createRepoPage.clickCreateRepoBtn();
        Assert.assertFalse(createRepoPage.isErrorMessageDisplayed(), "Error message displayed for valid repo creation.");
    }

    @Test(priority = 7)
    public void repoCreationEmptyName() {
        loginPositive();
        homePage.clickCreateRepo();
        createRepoPage.enterRepoName("");
        createRepoPage.enterRepoDescription("Test repo");
        createRepoPage.selectPublicVisibility();
        createRepoPage.clickCreateRepoBtn();
        Assert.assertTrue(createRepoPage.isErrorMessageDisplayed(), "Error message not displayed for empty repo name.");
    }

    @Test(priority = 8)
    public void repoCreationDuplicateName() {
        loginPositive();
        homePage.clickCreateRepo();
        createRepoPage.enterRepoName("existing-repo");
        createRepoPage.enterRepoDescription("Test repo");
        createRepoPage.selectPublicVisibility();
        createRepoPage.clickCreateRepoBtn();
        Assert.assertTrue(createRepoPage.isErrorMessageDisplayed(), "Error message not displayed for duplicate repo name.");
    }

    @Test(priority = 9)
    public void repoCreationInvalidChars() {
        loginPositive();
        homePage.clickCreateRepo();
        createRepoPage.enterRepoName("repo#name");
        createRepoPage.enterRepoDescription("Test repo");
        createRepoPage.selectPublicVisibility();
        createRepoPage.clickCreateRepoBtn();
        Assert.assertTrue(createRepoPage.isErrorMessageDisplayed(), "Error message not displayed for invalid repo name.");
    }

    @Test(priority = 10)
    public void deleteRepoPositive() {
        loginPositive();
        // Navigate to repo page assumed
        deleteRepoPage.clickSettings();
        deleteRepoPage.clickDelete();
        deleteRepoPage.typeRepoName("delete-me");
        deleteRepoPage.clickProceedDelete();
        Assert.assertFalse(deleteRepoPage.isErrorMessageDisplayed(), "Error message displayed for valid repo deletion.");
    }

    @Test(priority = 11)
    public void deleteRepoIncorrectName() {
        loginPositive();
        deleteRepoPage.clickSettings();
        deleteRepoPage.clickDelete();
        deleteRepoPage.typeRepoName("wrong-name");
        deleteRepoPage.clickProceedDelete();
        Assert.assertTrue(deleteRepoPage.isErrorMessageDisplayed(), "Error message not displayed for incorrect repo name.");
    }

    @Test(priority = 12)
    public void deleteRepoCancel() {
        loginPositive();
        deleteRepoPage.clickSettings();
        deleteRepoPage.clickDelete();
        deleteRepoPage.clickCancel();
        // Verification of repo presence would require additional steps
    }

    @Test(priority = 13)
    public void signOutPositive() {
        loginPositive();
        signOutPage.clickProfile();
        signOutPage.clickSignOut();
        Assert.assertTrue(loginPage.isErrorMessageDisplayed() || driver.getCurrentUrl().contains("login"), "User not redirected to login page after sign out.");
    }

    @Test(priority = 14)
    public void signOutDismissDropdown() {
        loginPositive();
        signOutPage.clickProfile();
        driver.navigate().refresh(); // Simulate dismiss
        Assert.assertFalse(loginPage.isErrorMessageDisplayed(), "User should remain logged in after dismissing dropdown.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
