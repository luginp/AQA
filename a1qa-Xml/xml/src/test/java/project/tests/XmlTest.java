package project.tests;

import framework.utils.ApiUtils;
import org.testng.annotations.Test;
import project.models.ApiResponse;
import project.utils.XmlUtils;

import static org.testng.Assert.*;

public class XmlTest extends BaseTest {
    @Test
    public void xmlTest() {
        ApiResponse apiResponse = ApiUtils.sendGetRequest();
        assertEquals(apiResponse.getStatusCode(), 200, "Error while sending get request");
        assertTrue(apiResponse.getResponse().contains("<?xml"),"Not a xml format");
        XmlUtils xmlUtils = new XmlUtils();
        assertTrue(xmlUtils.checkIfBooksInTheRightOrder(), "Books sorted in the wrong order");
        assertFalse(xmlUtils.checkingIfHighestCostBookNotTheSameAsLowest(),"Highest cost book are the same as lowest cost");
    }
}
