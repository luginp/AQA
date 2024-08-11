package framework.utils;

import aquality.selenium.browser.AqualityServices;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;

public class ScreenshotUtils {
    public static void makeScreenshot(String name) {
        try {
            File scrFile = ((TakesScreenshot) AqualityServices.getBrowser().getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(name));
        } catch (IOException e) {
            AqualityServices.getLogger().fatal("Exception was caught", e);
        }
    }

    public static void gettingPictureFromPage(By by) {
        try {
            WebElement Image = AqualityServices.getBrowser().getDriver().findElement(by);
            //Rihgt click on Image using contextClick() method.
            Actions action = new Actions(AqualityServices.getBrowser().getDriver());
            action.contextClick(Image).build().perform();

            //To perform press Ctrl + v keyboard button action.
            action.sendKeys(Keys.CONTROL, "v").build().perform();

            Robot robot = new Robot();

            // To press D key.
            robot.keyPress(KeyEvent.VK_D);
            // To press : key.
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_SEMICOLON);
            robot.keyRelease(KeyEvent.VK_SHIFT);
            // To press \ key.
            robot.keyPress(KeyEvent.VK_BACK_SLASH);
            // To press "test" key one by one.
            robot.keyPress(KeyEvent.VK_T);
            robot.keyPress(KeyEvent.VK_E);
            robot.keyPress(KeyEvent.VK_S);
            robot.keyPress(KeyEvent.VK_T);
            // To press Save button.
            robot.keyPress(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            AqualityServices.getLogger().fatal("Exception was caught", e);
        }
    }

    public static boolean compareImage(String picturePath, String picturePath1) {
        try {
            // take buffer data from botm image files //
            BufferedImage biA = ImageIO.read(new File(picturePath));
            DataBuffer dbA = biA.getData().getDataBuffer();
            int sizeA = dbA.getSize();
            BufferedImage biB = ImageIO.read(new File(picturePath1));
            DataBuffer dbB = biB.getData().getDataBuffer();
            int sizeB = dbB.getSize();
            // compare data-buffer objects //
            if (sizeA == sizeB) {
                for (int i = 0; i < sizeA; i++) {
                    if (dbA.getElem(i) != dbB.getElem(i)) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            AqualityServices.getLogger().fatal("Failed to compare image files", e);
            return false;
        }
    }
}
