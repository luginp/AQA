package battleship.pageobject.forms;

import battleship.pageobject.utills.ElementWaits;
import framework.elements.Button;
import framework.elements.Label;
import framework.elements.TextBox;


public class MainPage extends BaseForm {
    private final String randomEnemy = "//div[@class='battlefield-start-choose_rival']//a[contains(@class, 'choose_rival')]";
    private final String randomEnemySelected = "//div[@class='battlefield-start-choose_rival']//li[contains(@class, 'active')]";
    private final String start = "//div[@class='battlefield-start']//div[@class='battlefield-start-button']";
    private final String randomArrangement = "//div[@class='battlefield-gap']//span[@class='placeships-variant-link']";
    private final String chatWithEnemy = "//div[@class='chat']//input[contains(@class,'input')]";
    private final String rivalBattlefield = "//div[@class='battlefield battlefield__rival']";

    private Button randomEnemyButton = new Button(randomEnemy, "Random Enemy Button");
    private Button startButton = new Button(start, "Start Button");
    private Label randomEnemySelectedLabel = new Label(randomEnemySelected, "Random Enemy Selected label");


    public MainPage() {
        super("//div[@class='chat']//input[@class='input input__textarea chat-teletype']", MainPage.class.getName());
    }

    public BattleshipRivalForm getBtlRival() {
        return new BattleshipRivalForm(rivalBattlefield, "Rival Battlefield form");
    }

    public void randomEnemyButtonClick() {
        randomEnemyButton.click();
    }

    public void startGame() {
        startButton.click();
    }

    public void randomArrangementClick() {
        Button randomArrangementButton = new Button(randomArrangement, "Random Arrangement Button");
        randomArrangementButton.click();
    }

    public void waitForGameToStart() {
        ElementWaits.explicitWaitForVisibility(chatWithEnemy);
    }

    public boolean gameStarted() {
        TextBox chatWithEnemyTextBox = new TextBox(chatWithEnemy, "Chat with enemy");
        return chatWithEnemyTextBox.isVisible();
    }

    public boolean randomEnemyButtonIsVisible() {
        return randomEnemySelectedLabel.isVisible();
    }

    public void randomArrangementMultipleTimesClick(int diapasonForRandomTimes) {
        int random = (int) (1 + Math.random() * diapasonForRandomTimes -1);
        for (int i = 0; i < random; i++)
            randomArrangementClick();
    }
}
