package project.tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.logging.Logger;
import framework.utils.PropertiesReader;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;


public abstract class BaseTest {
    private Browser browser = AqualityServices.getBrowser();
    protected Logger logger = AqualityServices.getLogger();
    protected String url = PropertiesReader.getStringProperty(PropertiesReader.testProperties, "url");

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
