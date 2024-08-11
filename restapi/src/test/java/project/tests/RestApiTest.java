package project.tests;

import framework.utils.*;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import project.enums.BookCost;
import project.enums.TestRes;
import project.models.Catalog;
import project.models.ApiResponse;
import project.models.Result;
import project.steps.ApiSteps;
import project.utils.BookSelectUtils;

import static framework.utils.PropertiesReader.testProperties;
import static org.testng.Assert.assertEquals;

public class RestApiTest extends BaseTest {
    private String commentary = PropertiesReader.readValueFromProperties(testProperties, "commentary");


    @Test
    @Parameters({"url", "statusCode"})
    public void restApiTest(String url, int statusCode) {
        ApiSteps apiSteps = new ApiSteps();
        logger.info("Sending get request");
        ApiResponse apiResponse = APIUtils.makeGetApiRequest(url);
        Catalog catalog = XmlUtils.xmlStringToObject(apiResponse.getResponse());

        logger.info("Checking if status code same as ours");
        assertEquals(apiResponse.getStatusCode(), statusCode);

        logger.info("checking if order of books is right");
        apiSteps.assertAscOrderArrayBooks(catalog);

        logger.info("Checking if books aren't the same");
        softAssert.assertNotEquals(BookSelectUtils.getMaxOrMinCostBookFromList(catalog, BookCost.MAX).getTitle(),
                BookSelectUtils.getMaxOrMinCostBookFromList(catalog, BookCost.MIN).getTitle(), "Title not equals");
        softAssert.assertNotEquals(BookSelectUtils.getMaxOrMinCostBookFromList(catalog, BookCost.MAX).getDescription(),
                BookSelectUtils.getMaxOrMinCostBookFromList(catalog, BookCost.MIN).getDescription(), "Description not equals");
    }

    @AfterMethod
    public void afterMethodGetStatusOfTest(ITestResult result) {
        int resultStatus = result.getStatus();
        try {
            if (resultStatus == ITestResult.SUCCESS) {
                testResult = TestRes.SUCCESS.getCode();
            } else if (resultStatus == ITestResult.FAILURE) {

                testResult = TestRes.FAILURE.getCode();
            }
        } catch (Exception e) {
            logger.fatal("Exception was caught ", e);
        }

    }

    @AfterTest
    private void afterTestAddCommentaryWithPicture() {
        Result result = new Result();
        testRailUtils.makeAddResultForCaseRequest(result, run, testResult, commentary);
        testRailUtils.makeAddAttachmentToResultRequest(result, picturePath);
    }
}
