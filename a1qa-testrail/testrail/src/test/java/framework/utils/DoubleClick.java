package framework.utils;

import aquality.selenium.browser.AqualityServices;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DoubleClick {
    private static RemoteWebDriver driver = AqualityServices.getBrowser().getDriver();

    public static void doubleClick(By by)  {
        Actions actions = new Actions(driver);
        actions.doubleClick(driver.findElement(by)).perform();
    }
}
