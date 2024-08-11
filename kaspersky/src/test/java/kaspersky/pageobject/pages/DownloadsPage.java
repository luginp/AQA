package kaspersky.pageobject.pages;

import framework.elements.Button;
import framework.elements.Label;
import framework.elements.TextBox;
import framework.logger.Log;
import kaspersky.pageobject.forms.BaseForm;
import kaspersky.pageobject.utils.ElementWaits;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class DownloadsPage extends BaseForm {
    private String captchaLocator = "//div[contains(@style,'visible;')]//iframe[contains(@title,'recaptcha')]";
    private String btnConfirmLocator = "//button[contains(@class, 'js-send-installer-button')]";
    private String lblSendMessageLocator = "//div[contains(@class,'lightGrayLined')]";
    private String txtbEmailLocator = "//input[@class='u-inputField__control']";
    private String btnSendMessageLocator = "//button[contains(@class,'js-send-mail-for-me')]";
    private Button osButton = new Button("//div[@class='u-osTile__wrapper']", "OS button");

    public DownloadsPage() {
        super("//a[contains(@class,'is-active')][title='Загрузки']", DownloadsPage.class.getName());
    }

    public void clickOs(String os) {
        try {
            for (WebElement el : osButton.getElements()) {
                if (el.getText().equals(os)) {
                    if (os.equals("Windows")) {
                        break;
                    }
                    el.click();
                    break;
                }
            }
        } catch (NoSuchElementException e) {
            Log.exceptionCatch("Wrong OS name", e);
        }
    }

    public void sendMessageOnMail() {
        new Button(btnSendMessageLocator, "Main send button").click();
    }

    public void confirmSendMessage() {
        try {
            ElementWaits.fluentWait(captchaLocator);
        } catch (NoSuchElementException ignore) {
        }
        ElementWaits.explicitWaitForInvisibility(captchaLocator);
        new Button(btnConfirmLocator, "Send confirm button").click();
    }

    public boolean sendMessageMenuIsVisible() {
        return new Label(lblSendMessageLocator, "Send message menu label").elementIsVisibleWithoutException();
    }

    public boolean elementHasOurEmail(String email) {
        return new TextBox(txtbEmailLocator, "Email text box").getAttribute("value").contains(email.toLowerCase());
    }
}
