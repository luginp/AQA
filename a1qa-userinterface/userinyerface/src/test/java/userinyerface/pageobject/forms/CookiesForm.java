package userinyerface.pageobject.forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class CookiesForm extends Form {
    public CookiesForm() {
        super(By.xpath("//div[@class='cookies']"), "Cookies");
    }

    public void cookiesAccept() {
        AqualityServices.getElementFactory().getButton(By.xpath("//button[contains(@class,'button--transparent')]"), "Yes").click();
    }
}
