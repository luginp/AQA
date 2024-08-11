package framework.browsers;

import framework.logger.Log;
import org.openqa.selenium.WebDriver;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Browser {
    public static WebDriver getDriver() {
        return BrowserManager.getDriver();
    }

    public static void getPage(String page) {
        Log.info("Getting browser");
        getDriver().get(page);
    }

    public static void windowMaximize() {
        Log.info("Maximizing browser window");
        getDriver().manage().window().maximize();
    }

    public static void tearDown() {
        Log.info("Closing Browser");
        getDriver().quit();
    }

    public static void setWaits() {
        Log.info("Setting implicitly wait");
        getDriver().manage().timeouts().implicitlyWait(10, SECONDS);
        Log.info("Setting page load wait");
        getDriver().manage().timeouts().pageLoadTimeout(10, SECONDS);
    }
}
