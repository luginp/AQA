package kaspersky.pageobject.pages;

import framework.elements.Button;
import kaspersky.pageobject.forms.BaseForm;

public class DashboardPage extends BaseForm {
    private String btnDownloadLocator = "//a[contains(@class,'w-menu__item')]//img[contains(@class,'downloads')]";

    public DashboardPage() {
        super("//span[contains(@class, 'user-email')]", DashboardPage.class.getName());
    }

    public void downloadsClick() {
        new Button(btnDownloadLocator, "Downloads button").click();
    }
}
