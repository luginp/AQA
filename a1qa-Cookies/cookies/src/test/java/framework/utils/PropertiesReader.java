package framework.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    public static String testProperties = "./test.properties";
    private static Properties property = new Properties();

    public static int getIntProperty(String filePath, String propertyName) {
        int propertyValue = 0;
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            property.load(fileInputStream);
            propertyValue = Integer.parseInt(property.getProperty(propertyName));
        } catch (IOException e) {
            System.out.println("File do not exists");
        }
        return propertyValue;
    }

    public static String getStringProperty(String filePath, String propertyName) {
        String propertyValue = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            property.load(fileInputStream);
            propertyValue = property.getProperty(propertyName);
        } catch (IOException e) {
            System.out.println("File do not exists");
        }
        return propertyValue;
    }
}
