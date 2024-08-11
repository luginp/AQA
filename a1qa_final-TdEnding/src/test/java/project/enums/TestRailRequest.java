package project.enums;

public enum TestRailRequest {
    ADD_RUN("add_run/%s"),
    ADD_RESULT_FOR_CASE("add_result_for_case/%s/%s"),
    ADD_ATTACHMENT_TO_RESULT("add_attachment_to_result/%s");
    private String request;

    TestRailRequest(String request) {
        this.request = request;
    }

    public String getRequest() {
        return request;
    }
}
