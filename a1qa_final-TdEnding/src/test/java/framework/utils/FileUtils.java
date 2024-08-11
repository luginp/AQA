package framework.utils;


import aquality.selenium.browser.AqualityServices;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {

    public static void writeInFile(String str, String filePath) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(str);
            writer.close();
        } catch (IOException e) {
            AqualityServices.getLogger().fatal("Exception was caught", e);
        }
    }
}
