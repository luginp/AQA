package project.pageobject;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class ProjectPage extends Form {
    private ILink mainPageLink = AqualityServices.getElementFactory().getLink(By.xpath("//a[contains(text(),'Home')]"), "Main page");
    private IButton addTestButton = AqualityServices.getElementFactory().getButton(By.xpath("//button[contains(@data-target,'addTest')]"), "Add test");

    public ProjectPage() {
        super(By.xpath("//span[contains(@class, 'sr-only')]"), "Project page");
    }

    public void addTestButtonClick() {
        addTestButton.click();
    }

    public void navigateToMainPage() {
        mainPageLink.click();
    }
}
