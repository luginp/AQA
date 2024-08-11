package framework.utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FrameUtils {
    private static RemoteWebDriver driver = AqualityServices.getBrowser().getDriver();

    public static void switchFrame(By by) {
        driver.switchTo().frame(driver.findElement(by));
    }
    public static void quitFrame(){
        driver.switchTo().defaultContent();
    }
}
