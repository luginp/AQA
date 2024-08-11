package project.pageobject;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import framework.utils.DoubleClick;
import framework.utils.FrameUtils;
import org.openqa.selenium.By;

public class MainPage extends Form {
    private String textBoxLocator = "//body[@id='tinymce']//p";
    private By frameXpath = By.xpath("//iframe[@id='mce_0_ifr']");
    private ITextBox strongTextBox = AqualityServices.getElementFactory().getTextBox(By.xpath(textBoxLocator+ "//strong"), "Strong");
    private ITextBox textAreaBox = AqualityServices.getElementFactory().getTextBox(By.xpath(textBoxLocator), "Text area");
    private IButton boldStyleButton = AqualityServices.getElementFactory().getButton(By.xpath("//div[@id='mceu_3']//button[@role='presentation']"), "Bold style");
    public MainPage() {
        super(By.xpath("//div[@class='example']//h3"), "Main page");
    }

    public void enterText(String randomText) {
        FrameUtils.switchFrame(frameXpath);
        textAreaBox.clearAndType(randomText);
    }
    public String getText(){
       return textAreaBox.getText();
    }
    public void selectTheText(){
        DoubleClick.doubleClick(By.xpath(textBoxLocator));
    }
    public void makeTextBold(){
        boldStyleButton.click();
    }
    public String textIsStrong(){
        FrameUtils.switchFrame(frameXpath);
        return strongTextBox.getText();
    }
}
