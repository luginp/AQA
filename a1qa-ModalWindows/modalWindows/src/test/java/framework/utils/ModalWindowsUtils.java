package framework.utils;

import aquality.selenium.browser.AqualityServices;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Iterator;
import java.util.Set;

public class ModalWindowsUtils {
    private static RemoteWebDriver driver = AqualityServices.getBrowser().getDriver();

    public static void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public static void sendKeys(String keys) {
        driver.switchTo().alert().sendKeys(keys);
    }

    public static String getText() {
        return driver.switchTo().alert().getText();
    }
}
