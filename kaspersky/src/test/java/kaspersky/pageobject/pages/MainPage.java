package kaspersky.pageobject.pages;


import framework.elements.Button;
import framework.elements.TextBox;
import kaspersky.pageobject.forms.BaseForm;
import kaspersky.pageobject.utils.ElementWaits;

public class MainPage extends BaseForm {
    private String emailInputLocator = "//input[@type='email']";
    private String passwordInputLocator = "//input[contains(@class,'control_eshift_1')]";
    private Button signInButton = new Button("//button[contains(@class,'btn-primary')]", "Sign in button");
    private Button logInButton = new Button("//button[@type='submit']", "Log in button");


    public MainPage() {
        super("//div[contains(@class, 'icon-kpc-logo')]", MainPage.class.getName());
    }

    public void logIn(String email, String password) {
        signInButton.click();
        ElementWaits.explicitWaitForClickable(emailInputLocator);
        new TextBox(emailInputLocator, "Email input").sendKeys(email);
        new TextBox(passwordInputLocator, "Password input").sendKeys(password);
        logInButton.click();
    }
}
