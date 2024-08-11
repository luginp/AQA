import framework.browsers.Browser;
import framework.utils.ReadValuesFromConfig;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public abstract class BaseTest {
    private static String mainPage = ReadValuesFromConfig.readValues("mainpageurl", ReadValuesFromConfig.globalPropertiesFileName);

    @BeforeTest
    public static void bfTest() {
        Browser.windowMaximize();
        Browser.getPage(mainPage);
        Browser.setWaits();
    }

    @AfterTest
    public static void teatDown() {
        Browser.tearDown();
    }
}
