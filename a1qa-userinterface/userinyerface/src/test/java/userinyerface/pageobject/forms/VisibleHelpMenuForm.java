package userinyerface.pageobject.forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class VisibleHelpMenuForm extends Form {
    public VisibleHelpMenuForm() {
        super(By.xpath("//div[@class='help-form']"), "Help form");
    }

    public void hideHelpMenu() {
        AqualityServices.getElementFactory().getButton(By.xpath("//button[contains(@class,'send-to-bottom')]"), "Hide help menu").click();
    }
}
