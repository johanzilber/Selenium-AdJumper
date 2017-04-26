import common.BrowserType;
import common.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import ui.LoginArea;
import ui.PersonalAreaPage;

import java.io.File;

/**
 * Created by eyal on 18/12/2016.
 */
public class MainClass {

    private static WebDriver createWebDriver() {
        WebDriver driver;
        WebDriverFactory webDriverFactory = new WebDriverFactory();
        driver = webDriverFactory.createWebDriver(BrowserType.CHROME, new File("c:" + File.separator + "_Dev" + File.separator + "chromedriver.exe"), null, null);
        driver.get("http://www.yad2.co.il/");
        return driver;
    }

    public static void main(String[] args) {

        WebDriver driver = createWebDriver();
        LoginArea loginArea = new LoginArea(driver);
        PersonalAreaPage personalAreaPage = new PersonalAreaPage(driver);

        loginArea.logIn("", "");
        personalAreaPage.clickYad2Btn();
        personalAreaPage.jumpAllAds();

        driver.close();
        driver.quit();
    }
}

