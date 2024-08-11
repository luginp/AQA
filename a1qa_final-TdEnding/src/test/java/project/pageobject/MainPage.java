package project.pageobject;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import framework.utils.WindowHandle;
import org.openqa.selenium.By;

public class MainPage extends Form {
    private IButton addButton = AqualityServices.getElementFactory().getButton(By.xpath("//a[@href='addProject']"), "Add");

    public MainPage() {
        super(By.xpath("//div[contains(@class, 'panel ')]"), "Main page");
    }


    public void setParentWindowHandle() {
        WindowHandle.setParentWindowHandle();
    }

    public void switchToNextPage() {
        WindowHandle.switchToSecondPage();
    }

    public void refreshPage() {
        AqualityServices.getBrowser().getDriver().navigate().refresh();
    }

    public void addProject() {
        addButton.click();
    }
}
