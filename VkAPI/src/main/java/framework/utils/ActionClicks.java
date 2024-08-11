package framework.utils;

import framework.browsers.Browser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionClicks {
    public static void clickInCenter(WebElement el) {
        Actions action = new Actions(Browser.getDriver());
        action.moveToElement(el, 0, 0).click().build();
    }
}
