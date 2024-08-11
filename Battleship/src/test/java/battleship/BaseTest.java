package battleship;

import framework.browsers.Browser;
import framework.logger.Log;
import framework.utils.ReadValuesFromConfig;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public abstract class BaseTest {
    private String mainPage = ReadValuesFromConfig.readValues("mainpageurl", ReadValuesFromConfig.globalPropertiesFileName);

    @BeforeTest
    public void bfTest() {
        Browser.setWaits();
        Browser.windowMaximize();
        Log.step("Went on main Page");
        Browser.getPage(mainPage);
    }

    @AfterTest
    public void tearDown() {
        Browser.tearDown();
    }
}
