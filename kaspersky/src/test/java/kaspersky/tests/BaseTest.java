package kaspersky.tests;

import framework.browsers.Browser;
import framework.logger.Log;
import framework.utils.ReadValuesFromConfig;
import kaspersky.tests.utils.StepCounter;
import org.testng.annotations.*;

public abstract class BaseTest {
    private String mainPage = ReadValuesFromConfig.readValues("mainpageurl", ReadValuesFromConfig.globalPropertiesFileName);
    StepCounter stepCounter = new StepCounter();

    @BeforeClass
    public void bfTest() {
        Browser.windowMaximize();
        Browser.setWaits();
        Log.step(stepCounter.nextStep(), "Went on main Page");
        Browser.getPage(mainPage);
    }

    @AfterClass
    public void tearDown() {
        Browser.tearDown();
    }
}
