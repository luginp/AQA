package pageobject;

import framework.elements.Button;
import framework.elements.Link;
import framework.elements.Text;
import pageobject.Utills.RandomElementFromList;
import org.openqa.selenium.WebElement;
import pageobject.Models.Car;

import java.util.List;

public class ResearchPage extends BasePage {
    private final String compareLink = "//a[@data-linkname='compare-cars']";
    private final String carMakeSearchValues = "//form[@class='_24sct']//select[@name='makeId']//option";
    private final String carModelSearchValues = "//form[@class='_24sct']//select[@name='modelId']//option";
    private final String carYearSearchValues = "//form[@class='_24sct']//select[@name='year']//option";
    private final String input = "//form[@class='_24sct']//input[@type='submit']";
    private final String researchSection = "//section[@class='_3WTga _25LBb _3Gv2y']//p";


    private Button getCarMakeSearchValues() {
        return new Button(carMakeSearchValues, "carMakeSearch");
    }


    private Button getCarModelSearchValues() {
        return new Button(carModelSearchValues, "carMakeSearch");
    }

    private Button getCarYearSearchValues() {
        return new Button(carYearSearchValues, "carMakeSearch");
    }

    private Button getInput() {
        return new Button(input, "carMakeSearch");
    }

    private Text getResearchSection() {
        return new Text(researchSection, "Research section");
    }

    private Link getCompareLink() {
        return new Link(compareLink, "Side-by-side Comparisons");
    }

    public void randomMakeClick(Car car) {
        RandomElementFromList.randomElClick(getCarMakeSearchValues().getElements());
        car.setMake(RandomElementFromList.getRandomELText());
    }

    public void randomModelClick(Car car) {
        RandomElementFromList.randomElClick(getCarModelSearchValues().getElements());
        car.setModel(RandomElementFromList.getRandomELText());
    }

    public void randomYearClick(Car car) {
        RandomElementFromList.randomElClick(getCarYearSearchValues().getElements());
        car.setYear(RandomElementFromList.getRandomELText());
    }

    public void randomCarSearch(Car car) {
        getInput().click();
    }


    public String getResearchPageName() {
        return getResearchSection().getText();
    }

    public void toSideBySideComparisons() {
        getCompareLink().click();
    }

}
