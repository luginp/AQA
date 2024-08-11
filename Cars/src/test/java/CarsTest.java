import pageobject.*;
import framework.utils.ReadValuesFromConfig;
import framework.utils.TextMatch;
import org.testng.annotations.Test;
import pageobject.Models.Car;

import static org.testng.Assert.*;

public class CarsTest extends BaseTest {
    private final static String localTestFilename = "forTest.xml";
    private static String research = ReadValuesFromConfig.readValues("research", localTestFilename);
    private static String trimsText = ReadValuesFromConfig.readValues("trims", localTestFilename);


    private void getCar(Car carName, MainPage main, ResearchPage re, RandomCarPage car, CompareTrimsPage trims) {
        for (int i = 0; i < 3; i++) {
            main.goToResearchPage();
            assertTrue(TextMatch.checkIfTextMatches(research, re.getResearchPageName()), "Not on the research page");
            re.randomMakeClick(carName);
            re.randomModelClick(carName);
            re.randomYearClick(carName);
            re.randomCarSearch(carName);
            assertEquals(car.getFullCarNameOnThisPage(), carName.getFullCarName(), "Wrong car opened");
            if (car.compareTrimsLinkIsVisible()) {
                car.compareTrimsLinkClick();
                assertTrue(trims.getTrimsPageText().contains(trimsText), "Not on the Trims Page");
                trims.saveCarParams(carName);
                assertFalse(carName.getEngine().isEmpty() || carName.getTransmission().isEmpty(), "Car parts are not the same");
                break;
            } else {
                car.toMainPage();
                assertTrue(main.onMainPageCheck(), "Not main page opened");
            }
        }
    }


    @Test
    public void carsTest() {
        MainPage main = new MainPage();
        assertTrue(main.onMainPageCheck(), "Not main page opened");

        ResearchPage research = new ResearchPage();
        RandomCarPage carPage = new RandomCarPage();
        CompareTrimsPage trims = new CompareTrimsPage();
        Car car1 = new Car();
        getCar(car1, main, research, carPage, trims);

        trims.toMainPage();
        assertTrue(main.onMainPageCheck(), "Not main page opened");
        Car car2 = new Car();
        getCar(car2, main, research, carPage, trims);

        trims.goToResearchPage();
        assertTrue(TextMatch.checkIfTextMatches(CarsTest.research, research.getResearchPageName()), "Not on the research page");

        research.toSideBySideComparisons();
        CompareSideBySidePage side = new CompareSideBySidePage();
        side.enterFirstCarValues(car1);
        assertEquals(side.getCarOneName(), car1.getFullCarName(), "Wrong Car Name");

        AddingSecondPageToComparePage addSecond = new AddingSecondPageToComparePage();
        addSecond.enterSecondCarValues(car2);
        assertEquals(addSecond.cetCarTwoName(), car2.getFullCarName(), "Wrong Car Name");

        Car car1LastPage = new Car(side.getCarOneEngineText(), side.getCarOneTransmissionText());
        Car car2LastPage = new Car(addSecond.getCarTwoEngineText(), addSecond.getCarTwoTransmissionText());
        assertTrue(car1LastPage.equals(car1) && car2LastPage.equals(car2), "Engine and Transmission are not the same");

    }
}
