package userinyerface.tests.utils;

import framework.utils.TextGenerator;
import framework.utils.PropertiesReader;

public class RandomTextCreator {

    private TextGenerator textGenerator = new TextGenerator.TextGeneratorBuilder()
            .useDigits(true)
            .useLower(true)
            .useUpper(true)
            .build();
    private String password = textGenerator.generate(PropertiesReader.getIntProperty(PropertiesReader.testProperties, "password.length"));
    private String email = password + textGenerator.generate(PropertiesReader.getIntProperty(PropertiesReader.testProperties, "email.length"));
    private String domain = textGenerator.generate(PropertiesReader.getIntProperty(PropertiesReader.testProperties, "domain.length"));

    public String createRandomPassword() {
        return password;
    }

    public String createRandomEmail() {
        return email;
    }

    public String createRandomDomain() {
        return domain;
    }
}
