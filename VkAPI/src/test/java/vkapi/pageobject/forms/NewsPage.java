package vkapi.pageobject.forms;

import framework.elements.Button;
import framework.utils.ReadValuesFromConfig;

public class NewsPage extends BaseForm {

    public NewsPage() {
        super("//div[@class='narrow_column_wrap']//a[contains(@class,'item_news ui_rmenu_item_sel')]", NewsPage.class.getName());
    }

    public SideMenuForm getSideMenuForm() {
        return new SideMenuForm("Side menu form");
    }
}
