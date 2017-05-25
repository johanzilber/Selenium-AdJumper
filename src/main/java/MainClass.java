import common.BrowserType;
import common.Utils;
import common.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import ui.LoginArea;
import ui.PersonalAreaPage;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by eyal on 18/12/2016.
 */
public class MainClass {

    private static WebDriver createWebDriver() {
        WebDriver driver;
        WebDriverFactory webDriverFactory = new WebDriverFactory();
        final String currentDir = System.getProperty("user.dir");
//        final File windowsChromeDriver = new File("c:" + File.separator + "_Dev" + File.separator + "chromedriver.exe");
        final File linuxChromeDriver = new File(File.separator + "home" + File.separator + "evegenyz" + File.separator + "yad2" + File.separator + "chromedriver");
        driver = webDriverFactory.createWebDriver(BrowserType.CHROME, linuxChromeDriver, null, null);
        driver.get("http://www.yad2.co.il/");
        return driver;
    }

    public static void main(String[] args) {

        WebDriver driver = null;
        try {
            System.out.println((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(new Date()) + " STARTED");
            System.out.println((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(new Date()) + " Going to sleep");
            Utils.sleepRandom(0, 60*60);
            System.out.println((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(new Date()) + " Wakening from sleep");

            driver = createWebDriver();
            LoginArea loginArea = new LoginArea(driver);
            PersonalAreaPage personalAreaPage = new PersonalAreaPage(driver);

            Utils.sleepRandom(5, 10);
            loginArea.logIn("", "");
            personalAreaPage.clickYad2Btn();
            personalAreaPage.closeWarningMessage();
            personalAreaPage.jumpAllAds();

        } catch (Exception e) {
            System.out.println((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(new Date()) + " ERROR in MainClass");
            System.out.println();
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            System.out.println(errors.toString());
        } finally {
            if (driver != null) {
                driver.close();
                driver.quit();
            }
            System.out.println((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(new Date()) + " FINISHED");
            System.out.println();
            System.out.println();
        }

    }
}

