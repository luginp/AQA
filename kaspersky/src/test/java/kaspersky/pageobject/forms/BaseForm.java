package kaspersky.pageobject.forms;

import framework.browsers.Browser;
import framework.logger.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebElement;
import kaspersky.pageobject.utils.ElementWaits;

import java.util.List;

public abstract class BaseForm {
    private String locator;
    private String name;

    public BaseForm(String locator, String name) {
        this.name = name;
        this.locator = locator;
    }

    public boolean formIsVisible() {
        Log.info("Form" + name + "is visible");
        return Browser.getDriver().findElement(By.xpath(locator)).isDisplayed();
    }
    public WebElement getForm(){
       return Browser.getDriver().findElement(By.xpath(locator));
    }

    public boolean elementOnFormIsVisible(String locator) {
        Log.info("Element on " + name + " is visible");
        return Browser.getDriver().findElement(By.xpath(this.locator)).findElement(By.xpath(locator)).isDisplayed();
    }

    public void formVisibleWait() {
        Log.info("Waiting for " + name + " to be visible");
        ElementWaits.explicitWaitForVisibility(locator);
    }


    public void scrollToElementOnForm(String elementOnForm) {
        Log.info("Scrolling to element on " + name);
        JavascriptExecutor js = (JavascriptExecutor) Browser.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", Browser.getDriver().findElement(By.xpath(locator)).findElement(By.xpath(elementOnForm)));
    }

    public List<WebElement> getElementsOnFormByCss(String elementsLocator) {
        return Browser.getDriver().findElement(By.xpath(locator)).findElements(By.cssSelector(elementsLocator));
    }

    public String getLocator() {
        return locator;
    }

    public boolean formIsVisibleWithoutException() {
        return Browser.getDriver().findElements(By.xpath(locator)).size() > 0;
    }

    public void scrollDown() {
        Log.info("Scroll down" + name);
        JavascriptExecutor js = (JavascriptExecutor) Browser.getDriver();
        js.executeScript("scroll(0, document.body.scrollHeight);");
    }

    public void scrollToElement(WebElement element) {
        Log.info("Scroll to element on" + name);
        JavascriptExecutor js = (JavascriptExecutor) Browser.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public String getCurrentUrl() {
        Log.info("Get current URL of" + name);
        return Browser.getDriver().getCurrentUrl();
    }


}

