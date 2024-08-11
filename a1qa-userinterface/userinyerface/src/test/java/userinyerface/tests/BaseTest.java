package userinyerface.tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import framework.utils.PropertiesReader;
import org.testng.annotations.*;


public abstract class BaseTest {
    private Browser browser = AqualityServices.getBrowser();
    private String url = PropertiesReader.getStringProperty(PropertiesReader.testProperties, "url");


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
