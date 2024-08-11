package vkapi.pageobject.forms;

import framework.elements.Button;
import vkapi.pageobject.enums.SideMenuLabels;

public class SideMenuForm extends BaseForm {
    SideMenuForm(String name) {
        super("//div[@id='side_bar_inner']", name);
    }

    public void sideMenuItemClick(SideMenuLabels sideMenuLabels) {
        new Button(String.format("//span[@class='left_fixer']//span[contains(text(),'%s')]", sideMenuLabels.getLabel()), "My page button").click();
    }
}
