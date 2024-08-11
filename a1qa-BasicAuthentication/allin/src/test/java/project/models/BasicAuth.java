package project.models;

public class BasicAuth {
    private boolean authenticated;
    private String user;

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
    public boolean getAuthenticated() {
        return authenticated;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }
}
