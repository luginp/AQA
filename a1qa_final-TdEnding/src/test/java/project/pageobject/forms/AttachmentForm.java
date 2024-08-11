package project.pageobject.forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import framework.utils.ScreenshotUtils;
import org.openqa.selenium.By;

public class AttachmentForm extends Form {
    private IButton pictureButton = AqualityServices.getElementFactory().getButton(By.xpath("//img[contains(@class,'thumbnail')]"), "Picture");
    private By pictureButtonXPath = By.xpath("//img[contains(@class,'thumbnail')]");

    public AttachmentForm() {
        super(By.xpath("//div[contains(@class,'panel panel-default')]//table"), "Attachment form");
    }

    public void takePictureFromPage() {
        ScreenshotUtils.gettingPictureFromPage(pictureButtonXPath);
    }
}
