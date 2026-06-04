package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {


    WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    By usernameField = By.id("user-name");
    By passwordField = By.id("password");
    By loginButton = By.id("login-button");
    By errorMessage = By.cssSelector("h3[data-test='error']");

    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);

    }
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }
    public void clickLogin() {
        driver.findElement(loginButton).click();
    }
    public boolean isErrorDisplayed() {
        return driver.findElement(errorMessage).isDisplayed();
    }
}
