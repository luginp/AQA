package project.steps;

import com.google.gson.Gson;
import framework.utils.ApiUtils;
import framework.utils.PropertiesReader;
import project.enums.TestRailRequest;
import project.models.ApiResponse;
import project.models.Result;
import project.models.Run;

import java.util.TreeMap;

public class TestRailSteps {
    private String apiPropPath = PropertiesReader.TEST_RAIL_PROPERTIES;
    private String suiteId = PropertiesReader.getProperty(apiPropPath, "suiteId");
    private String projectId = PropertiesReader.getProperty(apiPropPath, "projectId");
    private String caseId = PropertiesReader.getProperty(apiPropPath, "caseId");
    private String user = PropertiesReader.getProperty(apiPropPath, "user");
    private String password = PropertiesReader.getProperty(apiPropPath, "password");

    public void makeAddRunRequest(String url, Run run) {
        TreeMap<String, String> param = new TreeMap<>();
        param.put("suite_id", suiteId);
        ApiResponse apiResponse = ApiUtils.sendGetRequestWithBasicAuth(url, user, password, String.format(TestRailRequest.ADD_RUN.getRequest(), projectId), param);
        run.setId(new Gson().fromJson(apiResponse.getResponse(), Run.class).getId());
    }

    public void makeAddResultForCaseRequest(String url, Result result, Run run, String statusId, String commentary) {
        TreeMap<String, String> param = new TreeMap<>();
        param.put("status_id", statusId);
        param.put("comment", commentary);
        ApiResponse apiResponse = ApiUtils.sendGetRequestWithBasicAuth(url, user, password, String.format(TestRailRequest.ADD_RESULT_FOR_CASE.getRequest(), run.getId(), caseId), param);
        result.setId(new Gson().fromJson(apiResponse.getResponse(), Result.class).getId());
    }

    public void makeAddAttachmentToResultRequest(String url, Result result, String filePath) {
        ApiUtils.sendFilePostRequestWithBasicAuth(url, user, password, String.format(TestRailRequest.ADD_ATTACHMENT_TO_RESULT.getRequest(), result.getId()), filePath);

    }
}
