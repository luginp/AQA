package framework.utils;

import aquality.selenium.browser.AqualityServices;

public class PageUtils {
    public static void refreshPage() {
        AqualityServices.getBrowser().getDriver().navigate().refresh();
    }
}
