package project.pageobject;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import framework.utils.WindowHandle;
import org.openqa.selenium.By;

public class AddProjectPage extends Form {
    private ILabel successLabel = AqualityServices.getElementFactory().getLabel(By.xpath("//div[contains(@class,'alert-success')]"), "Success");
    private IButton submitButton = AqualityServices.getElementFactory().getButton(By.xpath("//button[contains(@type, 'submit')]"),"Submit");
    private ITextBox projectNameTextBox = AqualityServices.getElementFactory().getTextBox(By.xpath("//input[contains(@id, 'projectName')]"), "Project name");

    public AddProjectPage(){
        super(By.xpath("//form[contains(@id, 'addProjectForm')]"), "Add project page");
    }
    public void selectProjectName(String name){
        projectNameTextBox.clearAndType(name);
    }
    public void submitProjectName(){
        submitButton.click();
    }
    public void switchBackToParentPage(){
        WindowHandle.switchBackToParent();
    }
    public String getSuccessText(){
        return successLabel.getText();
    }
}
