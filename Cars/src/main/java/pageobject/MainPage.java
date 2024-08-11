package pageobject;

import framework.elements.Button;

public class MainPage extends BasePage {
    private final String mainPageLogoLink = "//a[@id='navLogoLink']";
    private final String researchButton = "//ul[@class='_1U4gk']//a[contains(text(),'Research')]";

    private Button getMainPageLogoLink() {
        return new Button(mainPageLogoLink, "Main page button");
    }

    private Button getResearchButton() {
        return new Button(researchButton, "Research Button");
    }

    public boolean onMainPageCheck() {
        return getMainPageLogoLink().isVisible();
    }

    public void goToResearchPage() {
        getResearchButton().click();
    }
}
