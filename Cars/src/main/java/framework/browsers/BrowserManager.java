package framework.browsers;

import framework.utils.ReadValuesFromConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class BrowserManager {
    private final static String browser = ReadValuesFromConfig.readValues("browser", ReadValuesFromConfig.globalPropertiesFileName);
    private static WebDriver driver;

    private BrowserManager() {

    }


    static public WebDriver getDriver() {
        if (driver == null) {
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(BrowserOptions.getChromeOptions());
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(BrowserOptions.getFirefoxOptions());
                    break;
                default:
                    throw new IllegalArgumentException("Wrong browser name");
            }
        }
        return driver;

    }

}