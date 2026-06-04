package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

import java.time.Duration;

public class InventoryTest {

    WebDriver driver;
    LoginPage loginPage;
    InventoryPage inventoryPage;

    @BeforeMethod
    public void setup()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);


    }

    @Test
    public void verifyProductIsDisplayed(){
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        String productName = inventoryPage.getFirstProductName();
        Assert.assertNotNull(productName);
        System.out.println("Product name is "+productName);
    }

    @Test
    public void addProductToCart(){
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        inventoryPage.addFirstProductToCart();
        inventoryPage.goToCart();
        Assert.assertTrue(driver.getCurrentUrl().contains("cart"));
    }
    @AfterMethod
    public void teardown()
    {
        driver.quit();
    }
}
