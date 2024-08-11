package project.tests;

import framework.utils.FrameUtils;
import framework.utils.PropertiesReader;
import framework.utils.TextGenerator;
import org.testng.annotations.Test;
import project.pageobject.MainPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class IFrameTest extends BaseTest {
    private String randomTextLength = PropertiesReader.getProperty(PropertiesReader.testProperties, "randomTextLength");

    @Test
    public void iFrameTest() {
        MainPage mainPage = new MainPage();
        assertTrue(mainPage.isDisplayed());
        String randomText = TextGenerator.textGenerator(Integer.parseInt(randomTextLength));
        mainPage.enterText(randomText);
        assertEquals(mainPage.getText(), randomText);
        mainPage.selectTheText();
        FrameUtils.quitFrame();
        mainPage.makeTextBold();
        assertEquals(mainPage.textIsStrong(), randomText);
    }

}
