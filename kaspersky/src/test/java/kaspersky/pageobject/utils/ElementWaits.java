package kaspersky.pageobject.utils;

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
    public static void fluentWait(String locator) {
        Log.info("Setting fluent wait for an element");
        Wait<WebDriver> gWait = new FluentWait<>(Browser.getDriver()).withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(600)).ignoring(NoSuchElementException.class);
        gWait.until(webDriver -> Browser.getDriver().findElement(By.xpath(locator)));
    }

    public static void explicitWaitForPresence(String id) {
        Log.info("Explicit wait for an element presence");
        WebElement dynamicElement = (new WebDriverWait(Browser.getDriver(), 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(id)));
    }



    public static void explicitWaitForVisibility(String id) {
        Log.info("Waiting for an element to be visible");
        WebElement dynamicElement = (new WebDriverWait(Browser.getDriver(), 5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(id)));
    }

    public static void explicitWaitForClickable(String el) {
        Log.info("Waiting for an element to be clickable");
        WebElement dynamicElement = (new WebDriverWait(Browser.getDriver(), 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(el)));
    }

    public static void explicitWaitForInvisibility(String id) {
        Log.info("Waiting for an element to be invisible");
        Boolean dynamicElement = (new WebDriverWait(Browser.getDriver(), 90))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(id)));
    }
    public static void waitForMail(String os, String product,String email, String password, String productMaker) {
        WebDriverWait wait = (new WebDriverWait(Browser.getDriver(),60));
        wait.until(webDriver -> CheckingMails.readMessage(email,password, productMaker, os, product));
    }

}
