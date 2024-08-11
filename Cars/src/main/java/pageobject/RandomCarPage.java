package pageobject;

import framework.browsers.Browser;
import framework.elements.Link;
import framework.elements.Text;
import org.openqa.selenium.By;

public class RandomCarPage extends BasePage {
    private final String mainPageLink = "//a[@data-linkname='header-home']";
    private final String compareTrims = "//a[@data-linkname='trim-compare']";
    private final String randomCarName = "//h1[@class='cui-page-section__title']";

    private Link getCompareTrims() {
        return new Link(compareTrims, "compareTrims");
    }

    private Text getRandomCarName() {
        return new Text(randomCarName, "Rand car name");
    }

    private Link getMainPageLink() {
        return new Link(mainPageLink, "main page link");
    }

    public void compareTrimsLinkClick() {
        getCompareTrims().click();
    }

    public void toMainPage() {
        getMainPageLink().click();
    }

    public String getFullCarNameOnThisPage() {
        return getRandomCarName().getText();
    }

    public boolean compareTrimsLinkIsVisible() {
        return Browser.getDriver().findElements(By.xpath(compareTrims)).size() > 0;
    }


}
