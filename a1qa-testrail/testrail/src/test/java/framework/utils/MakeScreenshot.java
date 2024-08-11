package framework.utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;



public class MakeScreenshot {
    private static Logger logger = AqualityServices.getLogger();

    public static void makeScreenshot(String name) {
        try {
            File scrFile = ((TakesScreenshot) AqualityServices.getBrowser().getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(name));
        } catch (IOException e) {
            logger.fatal("Exception was caught", e);
        }
    }
}
