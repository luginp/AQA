package userinyerface.tests.utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import framework.utils.PropertiesReader;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

public class UploadPicture {
    private static Logger logger = AqualityServices.getLogger();
    private static String picturePath = PropertiesReader.getStringProperty(PropertiesReader.testProperties, "picture");

    private static void setClipboardData(String string) {
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(stringSelection, null);
    }

    public static void uploadPicture() {
        File file = new File(picturePath);
        setClipboardData(file.getAbsolutePath());
        try{
        Robot robot = new Robot();
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.delay(300);
        robot.keyPress(KeyEvent.VK_V);
        robot.delay(300);
        robot.keyRelease(KeyEvent.VK_V);
        robot.delay(300);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(300);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(300);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(300);
    } catch (AWTException e) {
            logger.fatal("AWTException caught", e);
        }
    }

}
