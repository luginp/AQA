package project.pageobject.forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class CommonInfoForm extends Form {
    private ILabel projectNameTextLabel = AqualityServices.getElementFactory().getLabel(By.xpath("//div[contains(@class, 'list-group')][1]//p"), "Project name");
    private ILabel testNameTextLabel = AqualityServices.getElementFactory().getLabel(By.xpath("//div[contains(@class, 'list-group')][2]//p"), "Test name");
    private ILabel testMethodNameTextLabel = AqualityServices.getElementFactory().getLabel(By.xpath("//div[contains(@class, 'list-group')][3]//p"), "Test method name");
    private ILabel testStatusTextLabel = AqualityServices.getElementFactory().getLabel(By.xpath("//div[contains(@class, 'list-group')][4]//p"), "Test status");
    private ILabel testStartTimeTextLabel = AqualityServices.getElementFactory().getLabel(By.xpath("//div[contains(@class, 'list-group')][5]//p[1]"), "Test start time");
    private ILabel testEndTimeTextLabel = AqualityServices.getElementFactory().getLabel(By.xpath("//div[contains(@class, 'list-group')][5]//p[2]"), "Test end time");
    private ILabel testEnvironmentTextLabel = AqualityServices.getElementFactory().getLabel(By.xpath("//div[contains(@class, 'list-group')][6]//p"), "Test environment");
    private ILabel testBrowserTextLabel = AqualityServices.getElementFactory().getLabel(By.xpath("//div[contains(@class, 'list-group')][7]//p"), "Test browser");
    public CommonInfoForm(){
        super(By.xpath("//div[contains(@class, 'col-md-4')]"),"Common info form");
    }
    public String getProjectName(){
        return projectNameTextLabel.getText();
    }
    public String getTestName(){
        return testNameTextLabel.getText();
    }
    public String getTestMethod(){
        return testMethodNameTextLabel.getText();
    }
    public String getTestStatus(){
        return testStatusTextLabel.getText();
    }
    public String getTestStartTime(){
        return testStartTimeTextLabel.getText();
    }
    public String getTestEndTime(){
        return testEndTimeTextLabel.getText();
    }
    public String getTestEnvironment(){
        return testEnvironmentTextLabel.getText();
    }
    public String getTestBrowser(){
        return testBrowserTextLabel.getText();
    }
}
