package kaspersky.pageobject.forms;

import framework.elements.Button;
import kaspersky.pageobject.utils.ElementWaits;
import kaspersky.pageobject.utils.ProgramSelectId;


public class CarouselItemForm extends BaseForm {
    private final String btnDownloadLocator = "//button[contains(@data-application-id, '%s')]";

    public CarouselItemForm() {
        super("//*[@aria-hidden = 'false']//div[contains(@class,'w-downloadProgramCard')]", "Program form");
    }

    public void programDownloadClick(String os, String programName) {
        Button downloadButton = new Button(getLocator() + String.format(btnDownloadLocator, ProgramSelectId.getProductId(os, programName)), "Download button");
        ElementWaits.explicitWaitForVisibility(downloadButton.getLocator());
        downloadButton.click();
    }
}

