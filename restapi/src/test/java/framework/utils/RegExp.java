package framework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExp {
    public static int getNumbers(String stringPattern, String text) {
        Pattern pattern = Pattern.compile(stringPattern);
        Matcher matcher = pattern.matcher(text);
        int statusCode = 0;
        if (matcher.find()) {
            statusCode = Integer.parseInt(matcher.group(1));
        }
        return statusCode;
    }
}
