package project.enums;

public enum TestRes {
    FAILURE("5"),
    SUCCESS("1");
    private String code;

    TestRes(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
