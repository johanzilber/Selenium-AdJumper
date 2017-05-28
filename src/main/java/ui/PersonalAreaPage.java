package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.Date;

import static common.Utils.sleepRandom;

public class PersonalAreaPage {
    private WebDriver driver;

    public PersonalAreaPage(WebDriver driver) {
        this.driver = driver;
    }

    private static final By YAD_2_BTN = By.xpath("//*[@class=\"thumbnailNavBar\"]/div[3]");
    private static final By PERSONAL_AREA_MESSAGE = By.xpath("//*[@id=\"autoCashContainer\"]/div[4]/div");
    private static final By PERSONAL_AD = By.xpath("//*[@class=\"item item-color-1\"]");
    private static final By HAKPATZA_BTN = By.id("bounceRatingOrderBtn");

    public void clickYad2Btn() {
        sleepRandom();
        driver.findElement(YAD_2_BTN).click();
    }

    public void closeWarningMessage() {
        sleepRandom();
        try {
            driver.findElement(PERSONAL_AREA_MESSAGE).click();
        } catch (Exception e) {
            System.out.println("closeWarningMessage failed");
        }
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

        return rowsCounter + 2;
    }

    private boolean isHakpatzaDisabled() {

        boolean result = false;

        try {
            if (driver.findElement(HAKPATZA_BTN).getCssValue("background").toString().contains("204")) {
                result = true;
            }
        } catch (Exception e) {
            System.out.println((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(new Date()) +
                    " Couldn't find hakpaza button");
        }

        return result;
    }

    private void doTakpiz(String adText) {
        try {
            if (!isHakpatzaDisabled()) {

                sleepRandom(2, 5);
                driver.findElement(HAKPATZA_BTN).click();
                sleepRandom(2, 5);

                if (isHakpatzaDisabled()) {
                    System.out.println((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(new Date()) +
                            " Ok: Hakpatz" + adText);
                } else {
                    System.out.println((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(new Date()) +
                            " Error: Hukpatza button enabled for " + adText);
                }
            } else {
                System.out.println((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(new Date()) +
                        " Idle: Hakpatza button disabled for " + adText);
            }

        } catch (Exception e) {
            System.out.println((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(new Date()) +
                    " The Ad was not hukpetza might be related to the time frame every 4 hours");
        }
    }
}
