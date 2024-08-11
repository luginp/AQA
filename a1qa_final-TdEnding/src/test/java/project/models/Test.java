package project.models;

import java.util.Objects;

public class Test {
    private String method;
    private String name;
    private String startTime;
    private String endTime;
    private String status;

    public Test() {

    }

    public Test(String method, String name, String startTime, String endTime, String status) {
        this.method = method;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getMethod() {
        return method;
    }

    public String getName() {
        return name;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getStatus() {
        return status;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Test{" +
                "method='" + method + '\'' +
                ", name='" + name + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Test)) return false;
        Test test = (Test) o;
        return method.equals(test.method) &&
                name.equals(test.name) &&
                startTime.equals(test.startTime) &&
                endTime.equals(test.endTime) &&
                status.equals(test.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, name, startTime, endTime, status);
    }
}
