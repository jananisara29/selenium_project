package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pages.LoginPage;

import java.time.Duration;

public class LoginSteps {

    WebDriver driver;
    LoginPage loginPage;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
    }

    @Given("user is on saucedemo login page")
    public void userIsOnLoginPage() {
        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"));
    }
    @When("user enters username {string} and password {string}")
    public void userEntersCredentials(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

    }
    @Then("user should be redirected to inventory page")
    public void userShouldSeeInventoryPage() {
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }

    @Then("user should see error message")
    public void userShouldSeeErrorMessage() {
        Assert.assertTrue(loginPage.isErrorDisplayed());
    }

    @When("user clicks login without entering credentials")
    public void userClicksLoginWithoutCredentials() {
        loginPage.clickLogin();
    }

    @After
    public void teardown() {
        driver.quit();
    }

}
