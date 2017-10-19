package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static common.Utils.sleepRandom;

public class LoginArea {

    private WebDriver driver;

    public LoginArea(WebDriver driver) {
        this.driver = driver;
    }

    private static final By loginEmailInput = By.id("userName");
    private static final By loginPassword = By.id("password");
    private static final By loginAreaEnterBtn = By.id("submitLogonForm");

    private void writeEmail(String email) {
        WebElement emailInputElement = driver.findElement(loginEmailInput);
        emailInputElement.click();
        emailInputElement.sendKeys(email);
    }

    private void writePassword(String password) {
        WebElement passwordInputElement = driver.findElement(loginPassword);
        passwordInputElement.click();
        passwordInputElement.sendKeys(password);
    }

    private void clickLoginBtn() {
        WebElement loginButtonElement = driver.findElement(loginAreaEnterBtn);
        loginButtonElement.click();
    }

    public void logIn(String username, String password) {
        writeEmail(username);
        sleepRandom();
        writePassword(password);
        sleepRandom();
        clickLoginBtn();
        sleepRandom();
    }
}
