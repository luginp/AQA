package framework.utils;

import framework.browsers.Browser;
import framework.logger.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.io.File;
import java.nio.file.Paths;
import java.time.Duration;

public class WorkWithFile {

    public static void waitForFileDownload(String expectedFileName) {
        Log.info("Setting wait for file" + expectedFileName);
        FluentWait<WebDriver> wait = new FluentWait<>(Browser.getDriver())
                .withTimeout(Duration.ofSeconds(100))
                .pollingEvery(Duration.ofMillis(600));
        File fileToCheck = Paths.get(System.getProperty("user.dir") + "/downloads")
                .resolve(expectedFileName)
                .toFile();
        wait.until((WebDriver wd) -> fileToCheck.exists());
    }
}
