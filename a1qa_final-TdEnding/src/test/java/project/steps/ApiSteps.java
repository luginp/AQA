package project.steps;

import framework.utils.ApiUtils;
import framework.utils.PropertiesReader;
import project.models.ApiResponse;

import static framework.utils.PropertiesReader.API_PROPERTIES;

public class ApiSteps {
    private static String url = PropertiesReader.getProperty(API_PROPERTIES, "url");
    private static String getToken = "/token/get?variant=%s";
    private static String getTestsInXml = "/test/get/xml?projectId=%s";

    public static String getToken(String variant) {
        ApiResponse apiResponse = ApiUtils.makePostApiRequest(url, String.format(getToken, variant));
        return apiResponse.getResponse();
    }

    public static String getTestsInXml(String projectId) {
        ApiResponse apiResponse = ApiUtils.makePostApiRequest(url, String.format(getTestsInXml, projectId));
        return apiResponse.getResponse();
    }
}
