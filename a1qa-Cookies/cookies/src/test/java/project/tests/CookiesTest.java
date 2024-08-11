package project.tests;

import org.testng.annotations.Test;
import project.steps.CookiesSteps;

public class CookiesTest extends BaseTest{
    @Test
    public void cookiesTest(){
        CookiesSteps cookiesSteps = new CookiesSteps();
        logger.info("Sending cookies");
        cookiesSteps.sendingCookies();

        logger.info("Deleting cookie with key example_key_1 ");
        cookiesSteps.deletingCookie();

        logger.info("Changing value of the cookie with key example_key_3");
        cookiesSteps.changingValue();

        logger.info("Deleting rest of the cookies");
        cookiesSteps.deletingRestOfTheCookies();
    }
}
