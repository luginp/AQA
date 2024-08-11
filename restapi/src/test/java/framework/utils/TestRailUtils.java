package framework.utils;

import com.google.gson.Gson;
import project.enums.TestRailRequest;
import project.models.ApiResponse;
import project.models.Result;
import project.models.Run;

import java.util.TreeMap;

public class TestRailUtils {
    private String apiPropPath = PropertiesReader.apiProperties;
    private String suiteId = PropertiesReader.readValueFromProperties(apiPropPath, "suiteId");
    private String projectId = PropertiesReader.readValueFromProperties(apiPropPath, "projectId");
    private String caseId = PropertiesReader.readValueFromProperties(apiPropPath, "caseId");

    public void makeAddRunRequest(Run run) {
        TreeMap<String, String> param = new TreeMap<>();
        param.put("suite_id", suiteId);
        ApiResponse apiResponse = TestRailApi.sendRequest(String.format(TestRailRequest.ADD_RUN.getRequest(), projectId), param);
        run.setId(new Gson().fromJson(apiResponse.getResponse(), Run.class).getId());
    }

    public void makeAddResultForCaseRequest(Result result, Run run, String statusId, String commentary) {
        TreeMap<String, String> param = new TreeMap<>();
        param.put("status_id", statusId);
        param.put("comment", commentary);
        ApiResponse apiResponse = TestRailApi.sendRequest(String.format(TestRailRequest.ADD_RESULT_FOR_CASE.getRequest(), run.getId(), caseId), param);
        result.setId(new Gson().fromJson(apiResponse.getResponse(), Result.class).getId());
    }

    public void makeAddAttachmentToResultRequest(Result result, String filePath) {
        System.out.println(result.getId());
        TestRailApi.uniRestApi(String.format(TestRailRequest.ADD_ATTACHMENT_TO_RESULT.getRequest(), result.getId()), filePath);
    }
}
