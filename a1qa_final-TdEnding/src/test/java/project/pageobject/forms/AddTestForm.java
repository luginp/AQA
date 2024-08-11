package project.pageobject.forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.io.File;

public class AddTestForm extends Form {
    private IButton testStatusButton = AqualityServices.getElementFactory().getButton(By.xpath("//select[contains(@id,'testStatus')]"), "Test status");
    private ITextBox testNameTextBox = AqualityServices.getElementFactory().getTextBox(By.xpath("//input[contains(@id,'testName')]"), "Test name");
    private ITextBox testMethodTextBox = AqualityServices.getElementFactory().getTextBox(By.xpath("//input[contains(@id,'testMethod')]"), "Test method");
    private ITextBox testStartTimeTextBox = AqualityServices.getElementFactory().getTextBox(By.xpath("//input[contains(@id,'startTime')]"), "Test start time");
    private ITextBox testEndTimeTextBox = AqualityServices.getElementFactory().getTextBox(By.xpath("//input[contains(@id,'endTime')]"), "Test end time");
    private ITextBox testEnvironmentTextBox = AqualityServices.getElementFactory().getTextBox(By.xpath("//input[contains(@id,'environment')]"), "Test environment");
    private ITextBox testBrowserTextBox = AqualityServices.getElementFactory().getTextBox(By.xpath("//input[contains(@id,'browser')]"), "Test browser");
    private IButton fileUploadButton = AqualityServices.getElementFactory().getButton(By.xpath("//input[contains(@id, 'attachment')]"), "File upload");
    private IButton saveTestButton = AqualityServices.getElementFactory().getButton(By.xpath("//button[contains(@class,'btn btn-primary')]"), "Save test");
    private ILabel testSavedLabel = AqualityServices.getElementFactory().getLabel(By.xpath("//div[contains(@class,'alert-success')]"), "Test saved");
    private ILabel awayLabel = AqualityServices.getElementFactory().getLabel(By.xpath("//body"),"Away");

    public AddTestForm() {
        super(By.xpath("//div[@class='modal-body']"), "Add test form");
    }

    public void addTestData(String testName, String status, String testMethod, String startTime, String endTime, String environment, String browser) {
        testNameTextBox.clearAndType(testName);
        testStatusButton.click();
        for (IElement element : testStatusButton.findChildElements(By.xpath("//option"), ElementType.BUTTON)) {
            if (element.getText().equals(status)) {
                element.click();
            }
        }
        testMethodTextBox.clearAndType(testMethod);
        testStartTimeTextBox.clearAndType(startTime);
        testEndTimeTextBox.clearAndType(endTime);
        testEnvironmentTextBox.clearAndType(environment);
        testBrowserTextBox.clearAndType(browser);
    }

    public void uploadFile(String filePath) {
        File file = new File(filePath);
        fileUploadButton.sendKeys(file.getAbsolutePath());
    }

    public void saveTest() {
        saveTestButton.click();
    }

    public String testSavedText() {
        return testSavedLabel.getText();
    }
}
