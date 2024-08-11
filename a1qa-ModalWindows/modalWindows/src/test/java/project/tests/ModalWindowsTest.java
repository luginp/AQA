package project.tests;

import framework.utils.ModalWindowsUtils;
import framework.utils.PropertiesReader;
import framework.utils.TextGenerator;
import org.testng.annotations.Test;
import project.pageobject.MainPage;

import static org.testng.Assert.assertEquals;

public class ModalWindowsTest extends BaseTest {
    private final String JS_ALERT_TEXT = PropertiesReader.getStringProperty(PropertiesReader.testProperties, "jsAlertText");
    private final String JS_CONFIRM_TEXT = PropertiesReader.getStringProperty(PropertiesReader.testProperties, "jsConfirmText");
    private final String JS_PROMPT_TEXT = PropertiesReader.getStringProperty(PropertiesReader.testProperties, "jsPromptText");
    private final String RESULT_ALERT_TEXT = PropertiesReader.getStringProperty(PropertiesReader.testProperties, "resultAlert");
    private final String RESULT_CONFIRM_TEXT = PropertiesReader.getStringProperty(PropertiesReader.testProperties, "resultConfirm");
    private final String RESULT_PROMPT_TEXT = PropertiesReader.getStringProperty(PropertiesReader.testProperties, "resultPrompt");

    @Test
    public void modalWindowsTest() {
        MainPage mainPage = new MainPage();

        mainPage.jsAlertClick();

        logger.info("Checking if the right text shown");
        assertEquals(ModalWindowsUtils.getText(), JS_ALERT_TEXT, "Wrong js alert text");

        mainPage.acceptAlert();

        logger.info("Checking if the right text shown");
        assertEquals(mainPage.getResultText(), RESULT_ALERT_TEXT, "Wrong result alert text");

        mainPage.jsConfirmClick();

        logger.info("Checking if the right text shown");
        assertEquals(ModalWindowsUtils.getText(), JS_CONFIRM_TEXT, "Wrong js confirm text");

        mainPage.acceptAlert();

        logger.info("Checking if the right text shown");
        assertEquals(mainPage.getResultText(), RESULT_CONFIRM_TEXT, "Wrong result confirm text");

        mainPage.jsPromptClick();

        logger.info("Checking if the right text shown");
        assertEquals(ModalWindowsUtils.getText(), JS_PROMPT_TEXT, "Wrong js prompt text");

        String randomText = TextGenerator.textGenerator();

        mainPage.sendKeys(randomText);

        mainPage.acceptAlert();

        logger.info("Checking if the right text shown");
        assertEquals(mainPage.getResultText(), String.format(RESULT_PROMPT_TEXT, randomText), "Wrong result prompt text");

    }
}
