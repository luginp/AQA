package framework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TextMatch {
    public static Boolean checkIfTextMatches(String lowestName, String fullName) {
        Pattern pattern = Pattern.compile(lowestName.toLowerCase());
        return pattern.matcher(fullName.toLowerCase()).find();
    }
}
