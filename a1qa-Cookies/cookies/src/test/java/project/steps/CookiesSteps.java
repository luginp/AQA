package project.steps;

import framework.utils.CookiesUtils;
import framework.utils.PropertiesReader;

import static org.testng.Assert.*;

public class CookiesSteps {
    private String key1 = PropertiesReader.getStringProperty(PropertiesReader.testProperties, "key1");
    private String key2 = PropertiesReader.getStringProperty(PropertiesReader.testProperties, "key2");
    private String key3 = PropertiesReader.getStringProperty(PropertiesReader.testProperties, "key3");
    private String value1 = PropertiesReader.getStringProperty(PropertiesReader.testProperties, "value1");
    private String value2 = PropertiesReader.getStringProperty(PropertiesReader.testProperties, "value2");
    private String value3 = PropertiesReader.getStringProperty(PropertiesReader.testProperties, "value3");
    private String value4 = PropertiesReader.getStringProperty(PropertiesReader.testProperties, "value4");

    public void sendingCookies() {
        CookiesUtils.sendingCookie(key1, value1);
        assertTrue(CookiesUtils.checkingIfCookiesExists(key1, value1));
        CookiesUtils.sendingCookie(key2, value2);
        assertTrue(CookiesUtils.checkingIfCookiesExists(key2, value2));
        CookiesUtils.sendingCookie(key3, value3);
        assertTrue(CookiesUtils.checkingIfCookiesExists(key3, value3));
    }

    public void deletingCookie() {
        CookiesUtils.deletingCookie(key1);
        assertFalse(CookiesUtils.checkingIfCookiesExists(key1, value1));
    }

    public void changingValue() {
        CookiesUtils.changingValue(key3, value4);
        assertEquals(CookiesUtils.gettingCookiesValue(key3), value4);
    }

    public void deletingRestOfTheCookies() {
        CookiesUtils.deletingCookie(key2);
        assertFalse(CookiesUtils.checkingIfCookiesExists(key2, value2));
        CookiesUtils.deletingCookie(key3);
        assertFalse(CookiesUtils.checkingIfCookiesExists(key3, value3));
    }
}
