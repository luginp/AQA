package pageobject;

import framework.elements.BaseElement;
import pageobject.Models.Car;
import pageobject.Utills.ElementWaits;
import framework.elements.Button;
import framework.elements.Text;


public class CompareSideBySidePage extends BasePage {
    private final String carMakeValue = "//div[@class='dropdown dropdown-make']//select//option";
    private final String carModelValue = "//div[@class='dropdown dropdown-model']//select//option";
    private final String carYearValue = "//div[@class='dropdown dropdown-year last']//select//option";
    private final String startComparing = "//button[@class='done-button']";
    private final String fullCarName = "//span[@class='info-data col-two-cars']//h4";
    private final String carEngine = "//cars-compare-compare-info[@header='Engine']//span[1]";
    private final String carTransmission = "//cars-compare-compare-info[@header='Transmission']//span[1]";


    private Button getCarMakeValue() {
        return new Button(carMakeValue, "Make button");
    }


    private Button getCarModelValue() {
        return new Button(carModelValue, "Make button");
    }


    private Button getCarYearValue() {
        return new Button(carYearValue, "Year button");
    }


    private Button getCarStartComparing() {
        return new Button(startComparing, "Start comparing button");
    }


    private Text getFullCarName() {
        return new Text(fullCarName, "Full car name");
    }


    private Text getCarEngine() {
        return new Text(carEngine, "Car engine");
    }

    private Text getCarTransmission() {
        return new Text(carTransmission, "Car transmission");
    }


    public void enterFirstCarValues(Car car) {
        ElementWaits.fluentWait(carMakeValue);
        BaseElement.clickOnRightElement(getCarMakeValue().getElements(), car.getMake());
        BaseElement.clickOnRightElement(getCarModelValue().getElements(), car.getModel());
        BaseElement.clickOnRightElement(getCarYearValue().getElements(), car.getYear());
        getCarStartComparing().click();

    }


    public String getCarOneName() {
        return getFullCarName().getText();
    }


    public String getCarOneEngineText() {
        ElementWaits.fluentWait(carEngine);
        return getCarEngine().getText();
    }

    public String getCarOneTransmissionText() {

        return getCarTransmission().getText();
    }
}
