package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.Date;

import static common.Utils.sleepRandom;

/**
 * Created by eyal on 15/12/2016.
 */
public class PersonalAreaPage {
    //*[@id="SearchButton"]                    PAGE LOCATOR

    WebDriver driver;

    public PersonalAreaPage(WebDriver driver){
        this.driver = driver;
    }

    private static final By yad2Btn = By.xpath("//*[@class=\"thumbnailNavBar\"]/div[3]");
    private static final By personalAd = By.xpath("//*[@class=\"item item-color-1\"]");
    private static final By HakpatzaBtnNotEnabled = By.xpath(".//html/body/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr[1]/td[3]/img");
    private static final By HakpatzaBtn = By.xpath(".//html/body/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr[1]/td[3]/a");

    public void clickYad2Btn(){
        sleepRandom();
//        driver.findElement(yad2Btn).click();
    }

    public void jumpAllAds(){
        int counter = 3;
        for (WebElement currElement : driver.findElements(personalAd)) {
            String currAdText = currElement.getText();
            sleepRandom(2,6);
            currElement.click();
            sleepRandom(2,6);
            WebElement frame = driver.findElement(By.xpath("//*[@id=\"SearchButton\"]/table[4]/tbody/tr[1]/td[2]/table/tbody/tr[" + counter + "]/td/table/tbody/tr/td[2]/iframe"));
            counter+=2;
            driver.switchTo().frame(frame);
            sleepRandom(2,6);

            if (!isHakpatzaDisabled()){
                clickHAKPATZA();
                sleepRandom(6, 12);

                // todo: write to log
                if (isHakpatzaDisabled()){
                    System.out.println((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(new Date()) + " Ok: Hakpatz" + currAdText);
                }
                else {
                    System.out.println((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(new Date()) + " Error: Hukpatza button enabled for " + currAdText);
                }
            } else {
                System.out.println((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(new Date()) + " Idle: Hakpatza button disabled for " + currAdText);
            }

            driver.switchTo().parentFrame();
            currElement.click();
        }
    }

    public boolean isHakpatzaDisabled(){

        boolean result = false;

        try {
            if (driver.findElement(HakpatzaBtnNotEnabled).isDisplayed()) {
                result = true;
            }
        } catch (Exception e) {
           // e.printStackTrace();
        }

        return result;
    }

    public void clickHAKPATZA(){
        try {
            sleepRandom();
            driver.findElement(HakpatzaBtn).click();
        }
        catch (Exception e){
            try {
                driver.findElement(HakpatzaBtnNotEnabled).isDisplayed();
            }
            catch (Exception f){
                System.out.println((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(new Date()) + " The Add was not hukpetza might be related to the time frame every 4 hours");
            }
        }
    }
}
