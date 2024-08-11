package kaspersky.tests;

import framework.logger.Log;
import framework.utils.ReadValuesFromConfig;
import kaspersky.pageobject.pages.DownloadsPage;
import kaspersky.pageobject.pages.DashboardPage;
import kaspersky.pageobject.pages.MainPage;
import kaspersky.pageobject.utils.CheckingMails;
import kaspersky.pageobject.utils.ElementWaits;
import kaspersky.tests.utils.StepCounter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import kaspersky.pageobject.forms.*;


import static org.testng.Assert.assertTrue;

public class KasperskyTest extends BaseTest {
    private String emailLogin = ReadValuesFromConfig.readValues("email", ReadValuesFromConfig.testPropertiesFile);
    private String emailPassword = ReadValuesFromConfig.readValues("password", ReadValuesFromConfig.testPropertiesFile);

    @Parameters({"productMaker", "login", "password", "os", "product"})
    @Test
    public void kasperskyTest(String productMaker, String login, String password, String os, String product) {
        MainPage main = new MainPage();
        Log.step(stepCounter.nextStep(), "Checking if main page is opened");
        assertTrue(main.formIsVisible());

        Log.step(stepCounter.nextStep(), "Logging in");
        main.logIn(login, password);
        DashboardPage dashboardPage = new DashboardPage();

        Log.step(stepCounter.nextStep(), "Checking if dashboard page is opened");
        assertTrue(dashboardPage.formIsVisible());

        Log.step(stepCounter.nextStep(), "Went to downloads page");
        dashboardPage.downloadsClick();
        DownloadsPage downloadsPage = new DownloadsPage();

        Log.step(stepCounter.nextStep(), "Checking if downloads page is opened");
        assertTrue(dashboardPage.formIsVisible());

        Log.step(stepCounter.nextStep(), "Clicking on");
        downloadsPage.clickOs(os);
        CarouselItemForm carouselItemForm = new CarouselItemForm();

        Log.step(stepCounter.nextStep(), "Downloading selected program");
        carouselItemForm.programDownloadClick(os, product);

        Log.step(stepCounter.nextStep(), "Sending message on mail");
        downloadsPage.sendMessageOnMail();

        Log.step(stepCounter.nextStep(), "Checking if send message menu is visible");
        assertTrue(downloadsPage.sendMessageMenuIsVisible());

        Log.step(stepCounter.nextStep(), "Checking if email is correct");
        assertTrue(downloadsPage.elementHasOurEmail(login));

        Log.step(stepCounter.nextStep(), "Confirming and executing message send");
        downloadsPage.confirmSendMessage();

        Log.step(stepCounter.nextStep(), "Checking if message was send");
        ElementWaits.waitForMail(os, product, emailLogin, emailPassword, productMaker);
        assertTrue(CheckingMails.emailHasRightLink(os, product));
    }
}
