package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pages.InventoryPage;
import pages.LoginPage;

import java.time.Duration;

public class CartSteps {

    WebDriver driver;
    LoginPage loginPage;
    InventoryPage inventoryPage;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com");
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
    }

    @Given("user is logged in")
    public void userIsLoggedIn() {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
    }

    @When("user adds first product to cart")
    public void userAddsProductToCart() {
        inventoryPage.addFirstProductToCart();
    }

    @Then("user should be redirected to cart page")
    public void userShouldBeOnCartPage() {
        inventoryPage.goToCart();
        Assert.assertTrue(driver.getCurrentUrl().contains("cart"));
    }

    @Then("cart should contain items")
    public void cartShouldContainItems() {
        inventoryPage.goToCart();
        boolean itemPresent = driver.findElement(By.className("cart_item")).isDisplayed();
        Assert.assertTrue(itemPresent);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}