package userinyerface.pageobject.forms;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class HiddenHelpMenuForm extends Form {
    public HiddenHelpMenuForm() {
        super(By.xpath("//div[contains(@class,'is-hidden')]"), "Hidden help menu");
    }

}
