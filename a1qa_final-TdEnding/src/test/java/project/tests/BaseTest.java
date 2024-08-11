package project.tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import framework.utils.PropertiesReader;
import framework.utils.ScreenshotUtils;
import org.testng.ITestResult;
import org.testng.annotations.*;
import project.enums.TestRes;
import project.models.Result;
import project.steps.TestRailSteps;
import project.models.Run;
import project.models.Token;

import static framework.utils.PropertiesReader.*;


public abstract class BaseTest {
    private String testRailUrl = PropertiesReader.getProperty(TEST_RAIL_PROPERTIES, "url");
    protected String variant = PropertiesReader.getProperty(TEST_PROPERTIES, "variant");
    private String user = PropertiesReader.getProperty(USER_PROPERTIES, "user");
    private String password = PropertiesReader.getProperty(USER_PROPERTIES, "password");
    private Browser browser = AqualityServices.getBrowser();
    private String commentary = PropertiesReader.getProperty(TEST_RAIL_PROPERTIES, "commentary");
    private String picturePath = PropertiesReader.getProperty(TEST_RAIL_PROPERTIES, "picturePath");
    private String url = "http://%s:%s@"+ PropertiesReader.getProperty(TEST_PROPERTIES, "url");
    private String testResult;
    private TestRailSteps testRailSteps = new TestRailSteps();
    private Run run = new Run();
    protected Token token = new Token();

    @BeforeTest
    public void beforeTest() {
        testRailSteps.makeAddRunRequest(testRailUrl, run);
    }

    @BeforeMethod
    public void beforeMethod() {
        browser.maximize();
        browser.goTo(String.format(url, user, password));
        browser.waitForPageToLoad();
    }

    @AfterMethod
    public void afterMethodGetStatusOfTest(ITestResult result) {
        int resultStatus = result.getStatus();
        try {
            if (resultStatus == ITestResult.SUCCESS) {
                testResult = TestRes.SUCCESS.getCode();
            } else if (resultStatus == ITestResult.FAILURE) {

                testResult = TestRes.FAILURE.getCode();
            }
        } catch (Exception e) {
            AqualityServices.getLogger().fatal("Exception was caught ", e);
        }
        ScreenshotUtils.makeScreenshot(picturePath);
    }

    @AfterClass
    public void afterClass() {
        Result result = new Result();
        testRailSteps.makeAddResultForCaseRequest(testRailUrl, result, run, testResult, commentary);
        testRailSteps.makeAddAttachmentToResultRequest(testRailUrl, result, picturePath);
    }

    @AfterTest
    public void afterTest() {
        browser.quit();
    }
}
