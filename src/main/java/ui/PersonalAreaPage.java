package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import static common.Utils.sleepRandom;

public class PersonalAreaPage {
    private WebDriver driver;

    public PersonalAreaPage(WebDriver driver) {
        this.driver = driver;
    }

    private static final By YAD_2_BTN = By.xpath("//*[@class=\"thumbnailNavBar\"]/div[3]");
    private static final By REHEV_BTN = By.xpath("//*[@class=\"thumbnailNavBar\"]/div[1]");
    private static final By PERSONAL_AREA_MESSAGE = By.xpath("//*[@id=\"autoCashContainer\"]/div[4]/div");
    private static final By REHEV_AREA_MESSAGE = By.xpath("//*[@id=\"autoCashContainer\"]/div[1]");
    private static final By PERSONAL_AD = By.xpath("//*[@class=\"item item-color-1\"]");
    private static final By HAKPATZA_BTN = By.id("bounceRatingOrderBtn");

    public void clickYad2Btn() {
        sleepRandom();
        driver.findElement(YAD_2_BTN).click();
    }

    public void clickRehevBtn() {
        sleepRandom();
        driver.findElement(REHEV_BTN).click();
    }

    public void closeYad2WarningMessage() {
        sleepRandom();
        try {
            driver.findElement(PERSONAL_AREA_MESSAGE).click();
        } catch (Exception e) {
            System.out.println((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(new Date()) + " Close yad2 warning message failed");
        }
    }

    public void closeRehevWarningMessage() {
        sleepRandom();
        try {
            driver.findElement(REHEV_AREA_MESSAGE).click();
        } catch (Exception e) {
            System.out.println((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(new Date()) + " Close rehev warning message failed");
        }
        sleepRandom();
    }

    public void jumpAllAds() {
        int rowsCounter = 3;
        for (WebElement currElement : driver.findElements(PERSONAL_AD)) {
            rowsCounter = takpizAd(rowsCounter, currElement);
        }
    }

    private int takpizAd(int rowsCounter, WebElement currElement) {
        String currAdText = currElement.getText().replace("\r\n", " ").replace("\n", " ");
        sleepRandom(2, 4);
        currElement.click();
        WebElement frame = driver.findElement(By.xpath("//*[@id=\"feed\"]/tbody/tr[" + rowsCounter + "]/td/iframe"));
        driver.switchTo().frame(frame);
        doTakpiz(currAdText);
        driver.switchTo().parentFrame();
        sleepRandom(1, 2);
        currElement.click();

        return rowsCounter + 2;
    }

    private boolean isHakpatzaEnabled(String adText) {

        boolean result = true;

        try {
            if (driver.findElement(HAKPATZA_BTN).getCssValue("background").toString().contains("204")) {
                System.out.println((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(new Date()) + " DISABLED: Hakpatza button disabled for " + adText);
                result = false;
            }
        } catch (Exception e) {
            System.out.println((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(new Date()) + " NOT FOUND: Hakpatza button not found for " + adText);
            result = false;
        }

        return result;
    }

    private void doTakpiz(String adText) {
        try {
            if (isHakpatzaEnabled(adText)) {
                sleepRandom(2, 5);
                driver.findElement(HAKPATZA_BTN).click();
                System.out.println((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(new Date()) + " HUKPATZ: " + adText);
                sleepRandom(3, 5);
            }
        } catch (Exception e) {
            System.out.println((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(new Date()) + " NOT HUKPATZ: " + adText);
            System.out.println();
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            System.out.println(errors.toString());
        }
    }
}
