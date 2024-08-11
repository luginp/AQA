package project.pageobject.forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class FooterForm extends Form {
    private ILabel footerLabel = AqualityServices.getElementFactory().getLabel(By.xpath("//p[contains(@class, 'footer')]//span"), "Footer");
    public FooterForm(){
        super(By.xpath("//p[contains(@class,'footer')]"), "Footer form");
    }
    public String getFooterVariantText() {
        return footerLabel.getText();
    }
}
