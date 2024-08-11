package project.tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.logging.Logger;
import framework.utils.MakeScreenshot;
import framework.utils.PropertiesReader;
import framework.utils.TestRailUtils;
import org.testng.ITestResult;
import org.testng.annotations.*;
import project.enums.TestRes;
import project.models.Result;
import project.models.Run;

import java.io.FileNotFoundException;
import java.io.IOException;

import static framework.utils.PropertiesReader.testProperties;


public abstract class BaseTest {
    protected Browser browser = AqualityServices.getBrowser();
    private String commentary = PropertiesReader.getProperty(testProperties, "commentary");
    public String picturePath = PropertiesReader.getProperty(testProperties, "picturePath");
    private String url = PropertiesReader.getProperty(testProperties, "url");
    protected Logger logger = AqualityServices.getLogger();
    protected String testResult;
    protected TestRailUtils testRailUtils = new TestRailUtils();
    protected Run run = new Run();

    @BeforeTest
    public void addRunBeforeTest() {
        testRailUtils.makeAddRunRequest(run);
    }

    @BeforeMethod
    public void beforeTest() {
        browser.maximize();
        browser.goTo(url);
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
            logger.fatal("Exception was caught ", e);
        }
        MakeScreenshot.makeScreenshot(picturePath);
    }

    @AfterClass
    public void afterClass() {
        Result result = new Result();
        testRailUtils.makeAddResultForCaseRequest(result, run, testResult, commentary);
        testRailUtils.makeAddAttachmentToResultRequest(result, picturePath);
        browser.quit();
    }
}
