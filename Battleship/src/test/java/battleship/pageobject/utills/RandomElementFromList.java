package battleship.pageobject.utills;

import org.openqa.selenium.WebElement;

import java.util.List;


public class RandomElementFromList {
    private static String name;

    public static void randomElClick(List<WebElement> list) {
        int carMakeRandom = (int) (Math.random() * (list.size() - 1)) + 1;
        list.get(carMakeRandom).click();
        name = list.get(carMakeRandom).getText();
    }

    public static String getRandomELText() {
        return name;
    }
}
