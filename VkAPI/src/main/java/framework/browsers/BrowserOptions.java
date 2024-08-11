package framework.browsers;

import framework.utils.ReadValuesFromConfig;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;

public class BrowserOptions {

    private static String downloadsDirectory = ReadValuesFromConfig.readValues("downloadDyr", ReadValuesFromConfig.globalPropertiesFileName);
    private static String language = ReadValuesFromConfig.readValues("language", ReadValuesFromConfig.globalPropertiesFileName);

    static ChromeOptions getChromeOptions() {
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", System.getProperty("user.dir") + downloadsDirectory);
        chromePrefs.put("safebrowsing.enabled", "true");
        chromePrefs.put("intl.accept_languages", language);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        return options;
    }

    static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("intl.accept_languages", language);
        options.setCapability("browser.download.folderList", 2);
        options.setCapability("browser.download.dir", System.getProperty("user.dir") + downloadsDirectory);
        options.setCapability("browser.helperApps.neverAsk.saveToDisk", "application/x-debian-package");
        options.setCapability("browser.download.panel.shown", false);
        options.setCapability("pdfjs.disabled", true);
        return options;
    }
}
