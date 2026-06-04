package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;

import java.time.Duration;

@Listeners(utils.TestListener.class)
public class LoginTest {
    public WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com");
        loginPage = new LoginPage(driver);

    }

    @Test
    public void validLogin() {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
//        driver.findElement(By.id("user-name")).sendKeys("standard_user");
//        driver.findElement(By.id("password")).sendKeys("secret_sauce");
//        driver.findElement(By.id("login-button")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }
    @Test
    public void invalidLogin() {
        loginPage.enterUsername("standard_usr");
        loginPage.enterPassword("secret_saue");
        loginPage.clickLogin();
        boolean visible = loginPage.isErrorDisplayed();

//        driver.findElement(By.id("user-name")).sendKeys("standard_use");
//        driver.findElement(By.id("password")).sendKeys("secret_sace");
//        driver.findElement(By.id("login-button")).click();
//        boolean visible = driver.findElement(By.cssSelector("h3[data-test='error']")).isDisplayed();
        Assert.assertTrue(visible);
    }

    @Test
    public void emptyLogin() {
         loginPage.clickLogin();
        boolean visible = loginPage.isErrorDisplayed();
        Assert.assertTrue(visible);

//        driver.findElement(By.id("login-button")).click();
//        boolean visible = driver.findElement(By.cssSelector("h3[data-test='error']")).isDisplayed();
//        Assert.assertTrue(visible);
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][] {
                {"standard_user", "secret_sauce", true},
                {"locked_out_user", "secret_sauce", false},
                {"wrong_user", "wrong_pass", false}
        };
    }

    @Test(dataProvider = "loginData")
    public void dataDriverLogin(String username, String password, boolean expectedResult) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        if (expectedResult) {
            Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
        } else {
            Assert.assertTrue(loginPage.isErrorDisplayed());
        }
    }
    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}