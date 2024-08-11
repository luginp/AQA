package framework.elements;

import framework.browsers.Browser;
import framework.logger.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class BaseElement {
    private String name;
    private String locator;

    public BaseElement(String locator, String name) {
        this.locator = locator;
        this.name = name;
    }

    public static void clickOnRightElement(List<WebElement> list, String name) {
        for (WebElement el : list)
            if (el.getText().equals(name))
                el.click();
    }
    public List<WebElement> getElementsByCss(String cssLocator){
       return Browser.getDriver().findElement(By.xpath(locator)).findElements(By.cssSelector(cssLocator));
    }

    public List<WebElement> getElements() {
        
        return Browser.getDriver().findElements(By.xpath(locator));
    }

    public String getName() {
        return name;
    }

    public boolean isVisible() {
        Log.info("Checked if element " + name + " is visible");
        return Browser.getDriver().findElement(By.xpath(locator)).isDisplayed();
    }

    public String getText() {
        Log.info("Got text of element " + name);
        return Browser.getDriver().findElement(By.xpath(locator)).getText();
    }

    public void click() {
        Log.info("Clicked on element " + getName());
        Browser.getDriver().findElement(By.xpath(locator)).click();
    }

    public String getAttribute(String attribute) {
        Log.info("Got text of" + attribute + "of an element" + getName());
        return Browser.getDriver().findElement(By.xpath(locator)).getAttribute(attribute);
    }

    public boolean elementIsVisibleWithoutException() {
        return Browser.getDriver().findElements(By.xpath(locator)).size() > 0;
    }

    public List<WebElement> getElementsInsideCurrentElement() {
        Log.info("Created List of Elements " + name);
        return Browser.getDriver().findElements(By.xpath(locator));
    }
}
