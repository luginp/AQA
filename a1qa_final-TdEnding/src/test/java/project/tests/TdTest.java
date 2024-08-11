package project.tests;

import aquality.selenium.browser.AqualityServices;
import framework.utils.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import project.pageobject.AddProjectPage;
import project.pageobject.MainPage;
import project.pageobject.ProjectPage;
import project.pageobject.forms.*;
import project.steps.ApiSteps;
import project.utils.XmlParser;

import java.util.Collections;
import java.util.List;

import static framework.utils.PropertiesReader.TEST_PROPERTIES;
import static org.testng.Assert.*;

public class TdTest extends BaseTest {
    private String projectSavedText = PropertiesReader.getProperty(TEST_PROPERTIES, "projectSavedText");
    private String tokenKey = PropertiesReader.getProperty(TEST_PROPERTIES, "tokenKey");
    private int randomTextLength = Integer.parseInt(PropertiesReader.getProperty(TEST_PROPERTIES, "randomTextLength"));
    private String picturePath = PropertiesReader.getProperty(TEST_PROPERTIES, "picturePath");
    private String picturePath1 = PropertiesReader.getProperty(TEST_PROPERTIES, "picturePath1");

    @DataProvider
    public Object[][] testData() {
        return new Object[][]{
                {
                        RandomTextGenerator.randomStringGenerator(randomTextLength),
                        RandomTextGenerator.randomStringGenerator(randomTextLength),
                        PropertiesReader.getProperty(TEST_PROPERTIES, "status"),
                        RandomTextGenerator.randomStringGenerator(randomTextLength),
                        PropertiesReader.getProperty(TEST_PROPERTIES, "startTime"),
                        PropertiesReader.getProperty(TEST_PROPERTIES, "endTime"),
                        RandomTextGenerator.randomStringGenerator(randomTextLength),
                        RandomTextGenerator.randomStringGenerator(randomTextLength),
                },
        };
    }

    @Test(dataProvider = "testData")
    public void TdTest(String projectName, String testName, String status, String testMethod, String startTime, String endTime, String environment, String browser) {
        AqualityServices.getLogger().info("Sending cookie");
        String tokenValue = ApiSteps.getToken(variant);
        assertFalse(tokenValue.isEmpty(), "Token value is empty");
        token.setTokenValue(tokenValue);

        MainPage mainPage = new MainPage();
        AqualityServices.getLogger().info("Checking if main page opened");
        assertTrue(mainPage.isDisplayed(), "Not main page is opened");

        AqualityServices.getLogger().info("Sending cookie");
        CookiesUtils.sendingCookie(tokenKey, token.getTokenValue());

        AqualityServices.getLogger().info("Refreshing page");
        mainPage.refreshPage();

        FooterForm footerForm = new FooterForm();
        AqualityServices.getLogger().info("Checking if right variant page is opened");
        assertTrue(footerForm.getFooterVariantText().contains(variant), "Wrong variant page opened");

        ProjectsForm projectsForm = new ProjectsForm();
        AqualityServices.getLogger().info("Getting nexage id");
        String nexageId = projectsForm.getProjectId("Nexage");

        AqualityServices.getLogger().info("Navigating to nexage page");
        projectsForm.navigateToProjectPage("Nexage");

        ProjectPage projectPage = new ProjectPage();
        AqualityServices.getLogger().info("Checking if nexage page is displayed");
        assertTrue(projectPage.isDisplayed(), "Not nexage page opened");

        AqualityServices.getLogger().info("Checking if api response is in xml format");
        String xmlText = ApiSteps.getTestsInXml(nexageId);
        assertTrue(xmlText.contains("<"), "Not xml format");

        AqualityServices.getLogger().info("Checking if tests from page same as from api request");
        List<String> tests = projectsForm.getTestsDate();
        assertTrue(XmlParser.xmlParse(xmlText).equals(projectsForm.getCatalog()));


        AqualityServices.getLogger().info("Checking if tests sorted desc");
        tests.sort(Collections.reverseOrder());
        assertEquals(projectsForm.getTestsDate(), tests, "Test not sorted by date desc");

        AqualityServices.getLogger().info("Adding new project");
        projectPage.navigateToMainPage();
        mainPage.setParentWindowHandle();
        mainPage.addProject();
        mainPage.switchToNextPage();

        AqualityServices.getLogger().info("Checking if switched to next tab");
        AddProjectPage addProjectPage = new AddProjectPage();
        assertTrue(addProjectPage.isDisplayed(), "Switched to next tab");

        AqualityServices.getLogger().info("Submitting project name");
        addProjectPage.selectProjectName(projectName);
        addProjectPage.submitProjectName();

        AqualityServices.getLogger().info("Checking if project successfully created");
        assertEquals(addProjectPage.getSuccessText(), String.format(projectSavedText, projectName));

        AqualityServices.getLogger().info("Checking if tab is closed");
        addProjectPage.switchBackToParentPage();
        assertTrue(mainPage.isDisplayed(), "Tab not closed");

        AqualityServices.getLogger().info("refreshing page");
        mainPage.refreshPage();

        AqualityServices.getLogger().info("Checking if project is visible");
        assertEquals(projectsForm.getProjectName(projectName), projectName);

        AqualityServices.getLogger().info("Navigating to new project page");
        projectsForm.navigateToProjectPage(projectName);

        AqualityServices.getLogger().info("Checking if new project page is opened");
        ProjectPage projectPage1 = new ProjectPage();
        assertTrue(projectPage1.isDisplayed(), "Not new project page opened");

        AqualityServices.getLogger().info("Adding nad saving new test");
        projectPage1.addTestButtonClick();
        AddTestForm addTestForm = new AddTestForm();

        addTestForm.addTestData(testName, status, testMethod, startTime, endTime, environment, browser);
        ScreenshotUtils.makeScreenshot(picturePath);
        addTestForm.uploadFile(picturePath);
        addTestForm.saveTest();

        AqualityServices.getLogger().info("Checking if test is saved");
        assertEquals(addTestForm.testSavedText(), String.format("Test %s saved", testName), "Test not saved");

        AqualityServices.getLogger().info("Clicking outside to close the popup");
        ActionsUtils.clickOutside();

        AqualityServices.getLogger().info("Checking if test is visible without refreshing the page");
        assertEquals(projectsForm.testIsVisible(testName), testName, "Test is not visible");

        AqualityServices.getLogger().info("Opening created test");
        projectsForm.openNewTestPage(testName);

        AqualityServices.getLogger().info("Checking if all test data is correct");
        CommonInfoForm commonInfo = new CommonInfoForm();
        assertEquals(commonInfo.getProjectName(), projectName, "Wrong project");
        assertEquals(commonInfo.getTestName(), testName, "Wrong test name");
        assertEquals(commonInfo.getTestStatus().toUpperCase(), status, "Wrong test status");
        assertEquals(commonInfo.getTestStartTime(), String.format("Start time: %s", startTime), "Wrong start time");
        assertEquals(commonInfo.getTestEndTime(), String.format("End time: %s", endTime), "Wrong end time");
        assertEquals(commonInfo.getTestEnvironment(), environment, "Wrong environment");
        assertEquals(commonInfo.getTestBrowser(), browser, "Wrong browser");
        AttachmentForm attachmentForm = new AttachmentForm();
        attachmentForm.takePictureFromPage();
       assertTrue(ScreenshotUtils.compareImage(picturePath, picturePath1), "Wrong image displayed");
    }
}
