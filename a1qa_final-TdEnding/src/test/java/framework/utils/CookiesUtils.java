package framework.utils;

import aquality.selenium.browser.AqualityServices;
import org.openqa.selenium.Cookie;

public class CookiesUtils {
    public static void sendingCookie(String key, String value) {
        AqualityServices.getBrowser().getDriver().manage().addCookie(new Cookie(key, value));
    }
}
