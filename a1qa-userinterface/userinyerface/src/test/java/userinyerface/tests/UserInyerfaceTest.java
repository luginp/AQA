package userinyerface.tests;

import aquality.selenium.browser.AqualityServices;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import userinyerface.pageobject.MainPage;
import userinyerface.pageobject.forms.*;
import userinyerface.tests.utils.RandomTextCreator;
import userinyerface.tests.utils.UploadPicture;

import java.awt.*;

import static org.testng.Assert.*;

public class UserInyerfaceTest extends BaseTest {
    @Test
    public void UserInyerFaceTest() throws AWTException {
        MainPage mainPage = new MainPage();
        assertTrue(mainPage.isDisplayed());
        mainPage.nextPageClick();
        FirstCardForm firstCardForm = new FirstCardForm();
        assertTrue(firstCardForm.isDisplayed());
        RandomTextCreator randomTextCreator = new RandomTextCreator();
        firstCardForm.inputRandomPassword(randomTextCreator.createRandomPassword());
        firstCardForm.inputRandomEmail(randomTextCreator.createRandomEmail());
        firstCardForm.inputRandomDomain(randomTextCreator.createRandomDomain());
        firstCardForm.clickRandomDropdown();
        SecondCardForm secondCardForm = new SecondCardForm();
        assertTrue(secondCardForm.isDisplayed());
        secondCardForm.clearAllInterests();
        secondCardForm.clickOnThreeRandomInterests();
        secondCardForm.uploadPictureClick();
        UploadPicture.uploadPicture();
        secondCardForm.nextCardClick();
        ThirdCardForm thirdCardForm = new ThirdCardForm();
        assertTrue(thirdCardForm.isDisplayed());
    }

    @Test
    public void SecondUserInyerfaceTest() {
        AqualityServices.getElementFactory().getLink(By.xpath("//a[contains(@class,'start')]"), "Next page")
        MainPage mainPage = new MainPage();
        assertTrue(mainPage.isDisplayed());
        mainPage.nextPageClick();
        VisibleHelpMenuForm visibleHelpMenuForm = new VisibleHelpMenuForm();
        HiddenHelpMenuForm hiddenHelpMenuForm = new HiddenHelpMenuForm();
        visibleHelpMenuForm.hideHelpMenu();
        assertTrue(hiddenHelpMenuForm.isDisplayed());
    }

    @Test
    public void ThirdInyerFaceTest() {
        MainPage mainPage = new MainPage();
        assertTrue(mainPage.isDisplayed());
        mainPage.nextPageClick();
        CookiesForm cookiesForm = new CookiesForm();
        cookiesForm.cookiesAccept();
        assertFalse(cookiesForm.isDisplayed());
    }

    @Test
    public void ForthInyerFaceTest() {
        MainPage mainPage = new MainPage();
        assertTrue(mainPage.isDisplayed());
        mainPage.nextPageClick();
        FirstCardForm firstCardForm = new FirstCardForm();
        assertTrue(firstCardForm.timerStartPositionCheck());
    }
}
