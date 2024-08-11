package framework.utils;

import aquality.selenium.browser.AqualityServices;
import org.openqa.selenium.interactions.Actions;

public class ActionsUtils {
    public static void clickOutside() {
        Actions action = new Actions(AqualityServices.getBrowser().getDriver());
        action.moveByOffset(0, 0).click().build().perform();
    }
}
