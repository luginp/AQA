package pageobject;

import framework.elements.Button;
import framework.elements.Link;
import framework.elements.Text;
import framework.utils.TextMatch;
import pageobject.Models.Car;

public class CompareTrimsPage extends BasePage {
    private final String trimsHeading = "//h1[@class='cui-heading-1']";
    private final String engineOnPage = "//div[@class='cell cell-bg grow-2']";
    private final String transOnPage = "//div[@class='cell grow-2']";
    private final String mainPageLink = "//a[@data-linkname='header-home']";
    private final String researchButton = "//ul[@class='global-nav__parent']//a[contains(text(),'Research')]";


    private Text getTrimsHeading() {
        return new Text(trimsHeading, "trims heading");
    }

    private Text getEngineOnPage() {
        return new Text(engineOnPage, "Engine text");
    }

    private Text getTransOnPage() {
        return new Text(transOnPage, "Trans Text");
    }

    private Link getMainPageLink() {
        return new Link(mainPageLink, "main page link");
    }

    private Button getResearchButton() {
        return new Button(researchButton, "Research Button");
    }


    public String getTrimsPageText() {
        return getTrimsHeading().getText();
    }


    public void saveCarParams(Car car) {
        car.setEngine(getEngineOnPage().getText());
        car.setTransmission(getTransOnPage().getText());

    }


    public void toMainPage() {
        getMainPageLink().click();
    }

    public void goToResearchPage() {
        getResearchButton().click();
    }

}
