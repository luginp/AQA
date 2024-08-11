package userinyerface.pageobject;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MainPage extends Form {
    private ILink nextPageLink = AqualityServices.getElementFactory().getLink(By.xpath("//a[contains(@class,'start')]"), "Next page");

    public MainPage() {
        super(By.xpath("//p[contains(@class,'start')]"), "Main page");
    }

    public void nextPageClick() {
        nextPageLink.click();
    }
}
