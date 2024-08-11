package project.pageobject;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import framework.utils.ModalWindowsUtils;
import org.openqa.selenium.By;

public class MainPage extends Form {
    private IButton jsAlertButton = AqualityServices.getElementFactory().getButton(By.xpath("//button[contains(@onclick,'jsAlert')]"), "Js alert button");
    private IButton jsConfirmButton = AqualityServices.getElementFactory().getButton(By.xpath("//button[contains(@onclick, 'jsConfirm')]"), "Js confirm button");
    private IButton jsPromptButton = AqualityServices.getElementFactory().getButton(By.xpath("//button[contains(@onclick, 'jsPrompt')]"), "Js prompt button");
    private ILabel resultLabel = AqualityServices.getElementFactory().getLabel(By.xpath("//p[@id='result']"),"Result");

    public MainPage() {
        super(By.xpath("//div[@class='example']"), "Main page");
    }

    public void jsAlertClick() {
        jsAlertButton.click();
    }

    public void jsConfirmClick() {
        jsConfirmButton.click();
    }

    public void jsPromptClick() {
        jsPromptButton.click();
    }
    public String getResultText(){
        return resultLabel.getText();
    }
    public void acceptAlert(){
        ModalWindowsUtils.acceptAlert();
    }
    public void sendKeys(String keys){
        ModalWindowsUtils.sendKeys(keys);
    }

}
