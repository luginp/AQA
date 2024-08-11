package framework.utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    public static String testProperties = "./test.properties";
    private static Properties property = new Properties();
    private static Logger logger = AqualityServices.getLogger();

    public static String getProperty(String filePath, String propertyName) {
        String propertyValue = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            property.load(fileInputStream);
            propertyValue = property.getProperty(propertyName);
        } catch (IOException e) {
            logger.fatal("File do not exists", e);
        }
        return propertyValue;
    }
}
