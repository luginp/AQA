package framework.elements;

import framework.browsers.Browser;
import org.openqa.selenium.By;

public class TextBox extends BaseElement {
    public TextBox(String locator, String name) {
        super(locator, name);
    }
    public void sendKeys(String keys){
        Browser.getDriver().findElement(By.xpath(getLocator())).sendKeys(keys);
    }
}
