package framework.utils;

import aquality.selenium.browser.AqualityServices;

import java.util.Iterator;
import java.util.Set;

public class WindowHandle {
    private static String parentWindowHandle;

    public static void setParentWindowHandle() {
        parentWindowHandle = AqualityServices.getBrowser().getDriver().getWindowHandle();
    }

    public static void switchToSecondPage() {
        Set<String> s = AqualityServices.getBrowser().getDriver().getWindowHandles();
        Iterator<String> I1 = s.iterator();
        while (I1.hasNext()) {
            String child_window = I1.next();
            if (!parentWindowHandle.equals(child_window)) {
                AqualityServices.getBrowser().getDriver().switchTo().window(child_window);
            }
        }
    }

    public static void switchBackToParent() {
        AqualityServices.getBrowser().getDriver().switchTo().window(parentWindowHandle);
    }
}
