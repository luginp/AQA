package kaspersky.pageobject.enums;

public enum ProgramName {
    TOTAL_SECURITY("Total Security"),
    SAFE_KIDS("Safe Kids"),
    INTERNET_SECURITY("Internet Security"),
    ANTI_VIRUS("Anti-Virus"),
    PASSWORD_MANAGER("Password Manager");
    private String type;

    ProgramName(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
