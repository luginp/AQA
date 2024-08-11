package framework.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class PropertiesReader {
    private static String propertiesPAth = "src/test/resources/%s";
    public static String apiProperties = String.format(propertiesPAth, "api.properties");
    public static String userProperties = String.format(propertiesPAth, "user.properties");
    public static String testProperties = String.format(propertiesPAth, "test.properties");

    public static String readValueFromProperties(String filePath, String propertyName) {
        FileInputStream fis;
        Properties property = new Properties();
        String neededProperty = null;
        try {
            fis = new FileInputStream(filePath);
            property.load(fis);
            neededProperty = property.getProperty(propertyName);
        } catch (IOException e) {

        }
        return neededProperty;
    }
}


