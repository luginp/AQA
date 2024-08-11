package framework.browsers;

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

import static java.util.concurrent.TimeUnit.SECONDS;

public class Browser {


    public static WebDriver getDriver() {
        return BrowserManager.getDriver();
    }

    public static void getPage(String page) {
        Log.infoToLogger("Getting browser");
        getDriver().get(page);
    }

    public static void windowMaximize() {
        Log.infoToLogger("Maximizing browser window");
        getDriver().manage().window().maximize();
    }

    public static void tearDown() {
        Log.infoToLogger("Closing Browser");
        getDriver().quit();
    }

    public static void setWaits() {
        Log.infoToLogger("Setting implicitly wait");
        getDriver().manage().timeouts().implicitlyWait(15, SECONDS);
        Log.infoToLogger("Setting page load wait");
        getDriver().manage().timeouts().pageLoadTimeout(15, SECONDS);
    }


}
