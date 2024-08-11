package vkapi.pageobject.forms;


import framework.elements.Button;
import framework.elements.Label;
import framework.elements.TextBox;
import framework.utils.ReadValuesFromConfig;

public class MainPage extends BaseForm {

    private Label logInLabel = new Label(getLocator(), "Log in label");
    private TextBox emailBox = new TextBox("//input[@id='index_email']", "Email input");
    private TextBox passwordBox = new TextBox("//input[@id='index_pass']", "Password input");
    private Button logInButton = new Button("//button[@id='index_login_button']", "Log in button");

    public MainPage() {
        super("//div[@id='index_login']", MainPage.class.getName());
    }

    public boolean pageHasUniqueElement() {
        return logInLabel.isVisible();
    }

    public void logIn(String email, String password) {
        emailBox.sendKeys(email);
        passwordBox.sendKeys(password);
        logInButton.click();
    }
}
