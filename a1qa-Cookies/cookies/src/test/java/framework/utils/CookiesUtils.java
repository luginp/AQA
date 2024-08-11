package framework.utils;

import aquality.selenium.browser.AqualityServices;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.util.Set;

public class CookiesUtils {
    private static RemoteWebDriver driver = AqualityServices.getBrowser().getDriver();

    public static void sendingCookie(String key, String value) {
        driver.manage().addCookie(new Cookie(key, value));
    }

    public static void deletingCookie(String key) {
        driver.manage().deleteCookieNamed(key);
    }

    public static void changingValue(String key, String value) {
        Cookie cookie = driver.manage().getCookieNamed(key);
        driver.manage().deleteCookie(cookie);
        driver.manage().addCookie(
                new Cookie.Builder(cookie.getName(), value)
                        .domain(cookie.getDomain())
                        .expiresOn(cookie.getExpiry())
                        .path(cookie.getPath())
                        .isSecure(cookie.isSecure())
                        .build()
        );
    }

    public static boolean checkingIfCookiesExists(String key, String value) {
        Set<Cookie> cookies = driver.manage().getCookies();
        return cookies.contains(new Cookie(key, value));
    }
    public static String gettingCookiesValue(String key){
        Cookie cookie1 = driver.manage().getCookieNamed(key);
        return cookie1.getValue();
    }
}
