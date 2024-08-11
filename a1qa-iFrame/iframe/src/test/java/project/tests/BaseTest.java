package project.tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.logging.Logger;
import framework.utils.PropertiesReader;
import org.testng.annotations.*;


public abstract class BaseTest {
    protected Browser browser = AqualityServices.getBrowser();
    private String url = PropertiesReader.getProperty(PropertiesReader.testProperties, "url");
    protected Logger logger = AqualityServices.getLogger();


    @BeforeMethod
    public void beforeTest() {
        browser.maximize();
        browser.goTo(url);
        browser.waitForPageToLoad();
    }

    @AfterClass
    public void afterTest() {
        browser.quit();
    }
}
