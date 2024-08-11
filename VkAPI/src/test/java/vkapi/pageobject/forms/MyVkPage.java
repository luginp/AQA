package vkapi.pageobject.forms;

import framework.elements.Label;


public class MyVkPage extends BaseForm {
    private SideMenuForm sideMenuFormMainPage = new SideMenuForm("Side menu form on My vk page");

    public MyVkPage() {
        super("//h2[@class='page_name']", MyVkPage.class.getName());
    }

    public boolean pageHasUniqueElement() {
        return new Label(getLocator(), "My vk page label").isVisible();
    }
}
