package battleship.pageobject.forms;

import battleship.pageobject.Enums.BattleshipEnums;
import battleship.pageobject.utills.ElementWaits;
import framework.elements.Button;
import framework.elements.Label;
import framework.logger.Log;
import framework.utils.ReadValuesFromConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BattleshipRivalForm extends BaseForm {
    private final int y = Integer.parseInt(ReadValuesFromConfig.readValues("startY", ReadValuesFromConfig.testPropertiesFileName));
    private final int x = Integer.parseInt(ReadValuesFromConfig.readValues("startX", ReadValuesFromConfig.testPropertiesFileName));

    private final String battlefieldRow = "//tr[@class='battlefield-row']";
    private final String notifications = "//div[@class='notifications']";
    private final String notOpponentsTurnNotification = "//div[@class='notifications']//div[@class='notification notification__move-off none']";
    private final String onMainPageNotification = "//div[@class='notifications']//div[@class='notification notification__init']";

    private final String cell = ".battlefield-cell";
    private final String notification = ".notification";

    private final String none = "none";
    private final String classText = "class";


    BattleshipRivalForm(String locator, String name) {
        super(locator, name);
    }


    private Button getBattlefieldCell() {
        return new Button(getLocator() + battlefieldRow, "Battlefield cell");
    }

    private List<WebElement> getNotificationsList() {
        Label notificationsLabel = new Label(notifications, "Notifications label");
        return notificationsLabel.getElementsByCss(notification);
    }

    public boolean onMainPageNotificationCheck() {
        Label onMainPageNotificationLabel = new Label(onMainPageNotification, "On main page notification");
        return onMainPageNotificationLabel.isVisible();
    }

    public String getNotificationText() {
        String notificationText = "";
        for (WebElement el : getNotificationsList()) {
            if (!el.getAttribute(classText).contains(none)) {
                Log.notification(el.getText());
                notificationText = el.getText().substring(0, el.getText().length() - 4);
                break;
            }
        }
        return notificationText;
    }

    private void waitForOpponentToClick() {
        ElementWaits.explicitWaitForInvisibility(notOpponentsTurnNotification);
    }

    private boolean checkForPopUps() {
        boolean gameIsStillGoing = true;
        for (WebElement el : getNotificationsList()) {
            if (!el.getAttribute(classText).contains(none)) {
                if (el.getAttribute(classText).contains(BattleshipEnums.notificationType.RIVAL_LEFT.getType())) {
                    gameIsStillGoing = false;
                    break;
                } else if (el.getAttribute(classText).contains(BattleshipEnums.notificationType.YOU_WON.getType())) {
                    gameIsStillGoing = false;
                    break;
                } else if (el.getAttribute(classText).contains(BattleshipEnums.notificationType.YOU_LOST.getType())) {
                    gameIsStillGoing = false;
                    break;
                } else if (el.getAttribute(classText).contains(BattleshipEnums.notificationType.SERVER_ERROR.getType())) {
                    gameIsStillGoing = false;
                    break;
                } else if (el.getAttribute(classText).contains(BattleshipEnums.notificationType.GAME_ERROR.getType())) {
                    gameIsStillGoing = false;
                    break;
                }
            }
        }
        return gameIsStillGoing;
    }

    private String getCellClass(int i, int r) {
        String str = "";
        if (checkForPopUps())
            if (formIsVisible())
                str = getBattlefieldCell().getElements().get(i).findElements(By.cssSelector(cell)).get(r).getAttribute("class");
        return str;
    }

    private void cellClick(int i, int r) {
        Log.info("Clicked on the cell with coordinates y:" + i + " x:" + r);
        if (checkForPopUps())
            if (formIsVisible())
                getBattlefieldCell().getElements().get(i).findElements(By.cssSelector(cell)).get(r).click();
    }

    private boolean checkToSwitchDirection(int n, int m, BattleshipEnums.directionName direction) {
        boolean switchDirection = false;
        if (getCellClass(n, m).contains(BattleshipEnums.cellsType.MISSED.getType()) ||
                getCellClass(n, m).contains(BattleshipEnums.cellsType.AUTO_MISSED.getType()) ||
                getCellClass(n, m).contains(BattleshipEnums.cellsType.LAST_MISSED.getType()) ||
                (m < 1 && direction.equals(BattleshipEnums.directionName.LEFT)) ||
                (n < 1 && direction.equals(BattleshipEnums.directionName.UP)) ||
                (n > 8 && direction.equals(BattleshipEnums.directionName.DOWN) || (m > 8 && direction.equals(BattleshipEnums.directionName.RIGHT)))
        ) {
            switchDirection = true;
        }
        return switchDirection;
    }

    private void clickCellsInSelectDirection(int i, int r, BattleshipEnums.directionName direction) {
        int n = i;
        int m = r;
        while (checkForPopUps()) {
            waitForOpponentToClick();
            if (direction == null)
                break;
            if (checkToSwitchDirection(n, m, direction)) {
                clickCellsInSelectDirection(i, r, directionSwitch(direction));
                break;
            }
            switch (direction) {
                case RIGHT:
                    m++;
                    break;
                case LEFT:
                    m--;
                    break;
                case DOWN:
                    n++;
                    break;
                case UP:
                    n--;
                    break;
                default:
                    break;
            }
            cellClick(n, m);
        }
    }

    private BattleshipEnums.directionName directionSwitch(BattleshipEnums.directionName direction) {

        switch (direction) {
            case RIGHT:
                direction = BattleshipEnums.directionName.LEFT;
                break;
            case LEFT:
                direction = BattleshipEnums.directionName.DOWN;
                break;
            case DOWN:
                direction = BattleshipEnums.directionName.UP;
                break;
            default:
                direction = null;
                break;
        }
        return direction;
    }

    private void clickCellsDiagonally(int y, int x, BattleshipEnums.diagonalNumber diagonal) {
        for (int i = y, r = x; r <= y && i >= x; i--, r++) {
            if (checkForPopUps())
                waitForOpponentToClick();
            else
                break;
            if (getCellClass(i, r).contains(BattleshipEnums.cellsType.MISSED.getType()) ||
                    getCellClass(i, r).contains(BattleshipEnums.cellsType.AUTO_MISSED.getType()) ||
                    getCellClass(i, r).contains(BattleshipEnums.cellsType.DONE.getType())) {
                if (i > 0 && r < 9) {
                    i--;
                    r++;
                }
            }
            cellClick(i, r);
            if (getCellClass(i, r).contains(BattleshipEnums.cellsType.LAST_HIT.getType())) {
                clickCellsInSelectDirection(i, r, BattleshipEnums.directionName.RIGHT);
            }
        }
        switch (diagonal) {
            case FIRST:
            case SECOND:
                y += 4;
                break;
            case THIRD:
            case FORTH:
                x += 4;
                break;
            case FIFTH:
                x -= 2;
                break;
            case SIXTH:
                x -= 4;
                break;
            case SEVENTH:
                y -= 2;
                x -= 2;
                break;
            case EIGHTH:
                y -= 4;
                break;
            case LAST_LEFT_PART:
                for (int i = 0; i < 10; i += 2)
                    clickCellsDiagonally(i, x, BattleshipEnums.diagonalNumber.END);
                break;
            case LAST_RIGHT_PART:
                y += 6;
                for (int i = 1; i < 10; i += 2)
                    clickCellsDiagonally(y, i, BattleshipEnums.diagonalNumber.END);
                break;
            default:
                break;
        }
        if (diagonalSwitch(diagonal) != null)
            clickCellsDiagonally(y, x, diagonalSwitch(diagonal));
    }

    private BattleshipEnums.diagonalNumber diagonalSwitch(BattleshipEnums.diagonalNumber diagonal) {
        switch (diagonal) {
            case START:
                diagonal = BattleshipEnums.diagonalNumber.FIRST;
                break;
            case FIRST:
                diagonal = BattleshipEnums.diagonalNumber.SECOND;
                break;
            case SECOND:
                diagonal = BattleshipEnums.diagonalNumber.THIRD;
                break;
            case THIRD:
                diagonal = BattleshipEnums.diagonalNumber.FORTH;
                break;
            case FORTH:
                diagonal = BattleshipEnums.diagonalNumber.FIFTH;
                break;
            case FIFTH:
                diagonal = BattleshipEnums.diagonalNumber.SIXTH;
                break;
            case SIXTH:
                diagonal = BattleshipEnums.diagonalNumber.SEVENTH;
                break;
            case SEVENTH:
                diagonal = BattleshipEnums.diagonalNumber.EIGHTH;
                break;
            case EIGHTH:
                diagonal = BattleshipEnums.diagonalNumber.LAST_LEFT_PART;
                break;
            case LAST_LEFT_PART:
                diagonal = BattleshipEnums.diagonalNumber.LAST_RIGHT_PART;
                break;
            case LAST_RIGHT_PART:
            case END:
                diagonal = null;
                break;
        }
        return diagonal;
    }

    public void playingGameWithTheMostWinRateMethod() {
        clickCellsDiagonally(y, x, BattleshipEnums.diagonalNumber.START);
    }
}
