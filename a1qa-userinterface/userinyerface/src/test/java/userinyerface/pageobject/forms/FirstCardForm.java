package userinyerface.pageobject.forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.forms.Form;
import framework.utils.RandomNumberGenerator;
import org.openqa.selenium.By;

import java.util.ArrayList;

public class FirstCardForm extends Form {
    public FirstCardForm() {
        super(By.xpath("//div[@class='login-form__container']"), "First card");
    }

    public void clickRandomDropdown() {
        AqualityServices.getElementFactory().getButton(By.xpath("//div[@class='dropdown__opener']"), "Dropdown").click();
        ArrayList<IElement> arrayList = new ArrayList<IElement>(AqualityServices.getElementFactory().getComboBox(By.xpath("//div[@class='dropdown__list']"), "").findChildElements(By.xpath("//div"), ElementType.LABEL));
        arrayList.removeIf(element -> element.getText().contains("other"));
        arrayList.get(RandomNumberGenerator.createRandomNumber(arrayList.size() - 1)).click();
        AqualityServices.getElementFactory().getCheckBox(By.xpath("//span[contains(@class,'icon-check')]"), "Terms and conditions").check();
        AqualityServices.getElementFactory().getButton(By.xpath("//a[contains(@class,'button')][contains(text(),'Next')]"), "Next button").click();
    }

    public void inputRandomPassword(String randomPassword) {
        AqualityServices.getElementFactory().getTextBox(By.xpath("//input[contains(@placeholder,'Password')]"), "Password").clearAndType(randomPassword);
    }

    public void inputRandomEmail(String randomEmail) {
        AqualityServices.getElementFactory().getTextBox(By.xpath("//input[contains(@placeholder,'email')]"), "Email").clearAndType(randomEmail);
    }

    public void inputRandomDomain(String randomDomain){
        AqualityServices.getElementFactory().getTextBox(By.xpath("//input[contains(@placeholder,'Domain')]"), "Domain").clearAndType(randomDomain);
    }

    public Boolean timerStartPositionCheck() {
        return AqualityServices.getElementFactory().getLabel(By.xpath("//div[contains(@class,'timer--white')]"), "Timer").getText().contains("00:00:00");
    }
}
