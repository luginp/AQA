package pageobject;

import framework.browsers.Browser;
import framework.logger.Log;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public abstract class BasePage {
    public BasePage() {

    }

    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) Browser.getDriver();
        js.executeScript("scroll(0, document.body.scrollHeight);");
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) Browser.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public String getCurrentUrl() {
        Log.infoToLogger("Getting current form url");
        return Browser.getDriver().getCurrentUrl();
    }
}
