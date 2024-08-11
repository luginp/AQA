package framework.utils;

import aquality.selenium.browser.AqualityServices;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    private static String propertiesPath = "src/test/resources/%s";
    public static final String API_PROPERTIES = String.format(propertiesPath, "api.properties");
    public static final String TEST_RAIL_PROPERTIES = String.format(propertiesPath, "testRail.properties");
    public static final String USER_PROPERTIES = String.format(propertiesPath, "user.properties");
    public static final String TEST_PROPERTIES = String.format(propertiesPath, "test.properties");
    private static final Properties property = new Properties();

    public static String getProperty(String filePath, String propertyName) {
        String propertyValue = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            property.load(fileInputStream);
            propertyValue = property.getProperty(propertyName);
        } catch (IOException e) {
            AqualityServices.getLogger().fatal("File do not exists", e);
        }
        return propertyValue;
    }
}
