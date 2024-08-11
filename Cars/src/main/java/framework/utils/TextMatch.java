package framework.utils;


import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.regex.Pattern;


public class TextMatch {
    public static Boolean checkIfTextMatches(String lowestName, String fullName) {
        Pattern pattern = Pattern.compile(lowestName.toLowerCase());
        return pattern.matcher(fullName.toLowerCase()).find();
    }

}
