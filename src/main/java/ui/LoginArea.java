package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static common.Utils.sleepRandom;

public class LoginArea {

    private WebDriver driver;

    public LoginArea(WebDriver driver) {
        this.driver = driver;
    }

    private static final By loginArea = By.xpath(".//div[4]/form/div");
    private static final By loginEmailInput = By.xpath(".//*[@id=\"userName\"]");
    private static final By loginPassword = By.xpath(".//*[@id=\"password\"]");
    private static final By loginAreaEnterBtn = By.xpath(".//*[@id=\"submitLogonForm\"]");

    private void selectUserEmailArea() {
        driver.findElement(loginEmailInput).click();
    }

    private void selectUserPasswordArea() {
        driver.findElement(loginPassword).click();
    }

    private void writeEmailInField(String email) {
        driver.findElement(loginEmailInput).sendKeys(email);
    }

    private void writePassword(String password) {
        driver.findElement(loginPassword).sendKeys(password);
    }

    private void clickLoginOkBtn() {
        driver.findElement(loginAreaEnterBtn).click();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public static By getLoginArea() {
        return loginArea;
    }

    public static By getLoginEmailInput() {
        return loginEmailInput;
    }

    public static By getLoginPassword() {
        return loginPassword;
    }

    public void logIn(String username, String password) {
        selectUserEmailArea();
        writeEmailInField(username);
        sleepRandom();
        selectUserPasswordArea();
        writePassword(password);
        sleepRandom();
        clickLoginOkBtn();
        sleepRandom();
    }
}
