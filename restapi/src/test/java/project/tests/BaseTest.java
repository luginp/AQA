package project.tests;

import aquality.selenium.browser.Browser;
import aquality.selenium.browser.BrowserManager;
import aquality.selenium.logger.Logger;
import aquality.selenium.utils.JsonFile;
import framework.utils.MakeScreenshot;
import framework.utils.PropertiesReader;
import io.qameta.allure.Attachment;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;
import org.testng.internal.TestResult;
import project.models.Run;
import framework.utils.TestRailUtils;

import static framework.utils.PropertiesReader.testProperties;

public class BaseTest {
    public String picturePath = PropertiesReader.readValueFromProperties(testProperties, "picturePath");
    protected TestRailUtils testRailUtils = new TestRailUtils();
    protected final Logger logger = Logger.getInstance();
    protected SoftAssert softAssert = new SoftAssert();
    protected Run run = new Run();
    protected String testResult;

    @BeforeTest
    public void addRunBeforeTest() {
        testRailUtils.makeAddRunRequest(run);
    }

    @BeforeMethod
    public void before() throws WebDriverException {
        logger.info("============ PRECONDITIONS =============");
        getBrowser().goTo(getStartUrl());
        getBrowser().maximize();
    }

    @AfterMethod
    public void afterMethod(ITestContext testContext, ITestResult testResult) {
        makeScreenshot();
        logger.info("=== TEST '%1$s' '%2$s' ===", testContext.getName(), getStatusName(testResult.getStatus()));
        getBrowser().quit();
    }

    @AfterMethod
    private void afterMethodSoftAssert() {
        softAssert.assertAll();
    }

    @AfterMethod
    private void softAssertAllAndMakeScreenshot() {
        softAssert.assertAll();
        MakeScreenshot.makeScreenshot(picturePath);
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] makeScreenshot() {
        return getBrowser().getScreenshot();
    }

    private String getStatusName(int status) {
        switch (status) {
            case TestResult.SUCCESS:
                return "SUCCESS";
            case TestResult.FAILURE:
                return "FAILURE";
            case TestResult.SKIP:
                return "SKIP";
            case TestResult.STARTED:
                return "STARTED";
            case TestResult.SUCCESS_PERCENTAGE_FAILURE:
                return "SUCCESS PERCENTAGE FAILURE";
            default:
                return "UNDEFINED STATUS ID " + status;
        }
    }

    private String getStartUrl() {
        JsonFile configurationFile = new JsonFile("settings.json");
        return String.valueOf(configurationFile.getValue("/startUrl"));
    }

    private Browser getBrowser() {
        return BrowserManager.getBrowser();
    }
}

