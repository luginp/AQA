package pageobject.Utills;

import framework.browsers.Browser;
import framework.logger.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class ElementWaits {
    public static void fluentWait(final String id) throws NoSuchElementException {
        Log.infoToLogger("Setting fluent wait for an element");
        Wait<WebDriver> gWait = new FluentWait<WebDriver>(Browser.getDriver()).withTimeout(Duration.ofSeconds(100))
                .pollingEvery(Duration.ofMillis(600)).ignoring(NoSuchElementException.class);
        gWait.until(webDriver -> webDriver.findElement(By.xpath(id)));

    }

    public static void explicitWaitForPresence(String id) {
        Log.infoToLogger("Setting explicit wait for an element");
        WebElement dynamicElement = (new WebDriverWait(Browser.getDriver(), 30))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(id)));
    }
}
