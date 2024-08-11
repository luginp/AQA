package userinyerface.pageobject.forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.forms.Form;
import framework.utils.PropertiesReader;
import org.openqa.selenium.By;
import framework.utils.RandomNumberGenerator;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

public class SecondCardForm extends Form {
    private ArrayList<IElement> elementList = new ArrayList<IElement>();
    private String picturePath = PropertiesReader.getStringProperty(PropertiesReader.testProperties, "picture");

    public SecondCardForm() {
        super(By.xpath("//div[@class='avatar-and-interests__form']"), "Second card");
    }

    public void clearAllInterests() {
        AqualityServices.getElementFactory().getCheckBox(By.xpath("//label[contains(@for,'unselectall')]//span"), "Unselect all").click();
    }

    public void clickOnThreeRandomInterests() {
        elementList.addAll(AqualityServices.getElementFactory()
                .getLabel(By.xpath("//div[@class='avatar-and-interests__interests-list']"), "Interests list")
                .findChildElements(By.xpath(".//div[contains(@class,'list__item')]"), ElementType.CHECKBOX));
        elementList.removeIf(element -> element.getText().contains("Unselect all") || element.getText().contains("Select all"));
        Integer[] arr = RandomNumberGenerator.randomNumbers(3, elementList.size() - 1);
        elementList.get(arr[0]).findChildElement(By.xpath("//label//span"), ElementType.CHECKBOX).click();
        elementList.get(arr[1]).findChildElement(By.xpath("//label//span"), ElementType.CHECKBOX).click();
        elementList.get(arr[2]).findChildElement(By.xpath("//label//span"), ElementType.CHECKBOX).click();
    }



    public void uploadPictureClick(){
        AqualityServices.getElementFactory().getLink(By.xpath("//p[contains(@class, 'avatar')]//a[contains(@class,'upload')]"), "Upload").click();

    }

    public void nextCardClick() {
        AqualityServices.getElementFactory().getButton(By.xpath("//button[contains(@class,'button--white button--fluid')]"), "Next card").click();
    }
}
