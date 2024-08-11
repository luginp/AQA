package kaspersky.pageobject.utils;

import framework.utils.ReadValuesFromConfig;

import java.security.SecureRandom;

public class RandomTextMaker {
    private static final String alphabet = ReadValuesFromConfig.readValues("alphabet", ReadValuesFromConfig.testPropertiesFile);

    public static String getRandomWord() {
        StringBuilder sb = new StringBuilder(Math.max(10, 15));
        for (int i = 0; i < 10; i++) {
            int len = alphabet.length();
            SecureRandom RND = new SecureRandom();
            int random = RND.nextInt(len);
            char c = alphabet.charAt(random);
            sb.append(c);
        }
        return sb.toString();
    }
}
