package pageobject;

import framework.elements.BaseElement;
import pageobject.Models.Car;
import pageobject.Utills.ElementWaits;
import framework.elements.Button;
import framework.elements.Text;

public class AddingSecondPageToComparePage extends BasePage {
    private final String addAnotherOne = "//a[@id='add-from-your-favorite-cars-link']";
    private final String secondCarMakeValue = "//div[@class='dropdown']//select[@id='make-dropdown']//option";
    private final String secondCarModelValue = "//div[@class='dropdown']//select[@id='model-dropdown']//option";
    private final String secondCarYearValue = "//div[@class='dropdown']//select[@id='year-dropdown']//option";
    private final String doneButton = "//button[@class='modal-button']";
    private final String fullSecondCarName = "//span[@class='info-data col-three-cars']//h4";
    private final String carEngine = "//cars-compare-compare-info[@header='Engine']//span[2]";
    private final String carTransmission = "//cars-compare-compare-info[@header='Transmission']//span[2]";


    private Button getSecondCarMakeValue() {
        return new Button(secondCarMakeValue, "Make button");
    }


    private Button getSecondCarModelValue() {
        return new Button(secondCarModelValue, "Model button");
    }


    private Button getSecondCarYearValue() {
        return new Button(secondCarYearValue, "Year button");
    }

    private Button getAddAnotherOne() {
        return new Button(addAnotherOne, "Another one button");
    }

    private Text getCarEngine() {
        return new Text(carEngine, "Car engine");
    }

    private Text getCarTransmission() {
        return new Text(carTransmission, "Car transmission");
    }

    private Button getDoneButton() {
        return new Button(doneButton, "Another one button");
    }

    private Text getSecondFullCarName() {
        return new Text(fullSecondCarName, "Second full car name");
    }

    public void enterSecondCarValues(Car car) {
        getAddAnotherOne().click();
        BaseElement.clickOnRightElement(getSecondCarMakeValue().getElements(), car.getMake());
        BaseElement.clickOnRightElement(getSecondCarModelValue().getElements(), car.getModel());
        BaseElement.clickOnRightElement(getSecondCarYearValue().getElements(), car.getYear());
        getDoneButton().click();
    }


    public String cetCarTwoName() {
        return getSecondFullCarName().getElements().get(1).getText();
    }


    public String getCarTwoEngineText() {
        ElementWaits.fluentWait(carEngine);
        return getCarEngine().getText();
    }

    public String getCarTwoTransmissionText() {

        return getCarTransmission().getText();
    }
}
