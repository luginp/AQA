package battleship;

import framework.logger.Log;
import framework.utils.ReadValuesFromConfig;
import org.testng.annotations.Test;
import battleship.pageobject.forms.MainPage;

import static org.testng.Assert.*;


public class GameWonTest extends BaseTest {
    private final String youWon = ReadValuesFromConfig.readValues("youWon", ReadValuesFromConfig.testPropertiesFileName);
    private final int numberOfTimesClick = Integer.parseInt(ReadValuesFromConfig.readValues("numberOfTimesClick", ReadValuesFromConfig.testPropertiesFileName));

    @Test
    public void GameWonTest() {

        MainPage mainPage = new MainPage();

        Log.info("Checking if main Page opened");
        assertTrue(mainPage.getBtlRival().onMainPageNotificationCheck());

        Log.step("Random enemy select");
        mainPage.randomEnemyButtonClick();

        Log.info("Checking if random enemy selected");
        assertTrue(mainPage.randomEnemyButtonIsVisible());

        Log.step("Random ships arrangement");
        mainPage.randomArrangementMultipleTimesClick(numberOfTimesClick);

        Log.step("Started the game");
        mainPage.startGame();

        Log.step("Waited for Enemy to connect");
        mainPage.waitForGameToStart();

        Log.info("Checking if game started");
        assertTrue(mainPage.gameStarted());

        Log.step("Playing battleships");
        mainPage.getBtlRival().playingGameWithTheMostWinRateMethod();

        Log.info("Checking if we won");
        assertEquals(mainPage.getBtlRival().getNotificationText(), youWon);
    }
}
