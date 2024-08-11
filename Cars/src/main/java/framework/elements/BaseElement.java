package framework.elements;

import framework.browsers.Browser;
import framework.logger.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class BaseElement {
    private String name;
    private String locator;

    private WebElement element;
    private List<WebElement> elements;


    public BaseElement(String locator, String name) {
        this.locator = locator;
        this.name = name;
        element = Browser.getDriver().findElement(By.xpath(locator));
        elements = Browser.getDriver().findElements(By.xpath(locator));
    }

    public static void clickOnRightElement(List<WebElement> list, String name) {
        for (WebElement el : list)
            if (el.getText().equals(name))
                el.click();
    }

    public List<WebElement> getElements() {
        Log.infoToLogger("Created List of Elements " + name);
        return elements;

    }


    public String getName() {
        return name;
    }

    public WebElement hover() {
        Log.infoToLogger("Hovered on element " + name);
        return element;

    }

    public boolean isVisible() {
        Log.infoToLogger("Checked if element " + name + " is visible");
        return element.isDisplayed();
    }

    public String getText() {
        Log.infoToLogger("Got text of element " + name);
        return element.getText();
    }

    public void click() {
        Log.infoToLogger("Clicked on element " + getName());
        element.click();
    }

    public String getAttribute(String attribute) {
        Log.infoToLogger("Got text of" + attribute + "of an elementl" + getName());
        return element.getAttribute(attribute);
    }


}
