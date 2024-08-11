package battleship.pageobject.Enums;

public class BattleshipEnums {
    public  enum notificationType {
        RIVAL_LEFT("notification__rival-leave"),
        YOU_WON("notification__game-over-win"),
        YOU_LOST("notification__game-over-lose"),
        SERVER_ERROR("notification__server-error"),
        GAME_ERROR("notification__game-error");
        private String type;

        notificationType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public enum cellsType {
        LAST_MISSED("battlefield-cell__miss battlefield-cell__last"),
        MISSED("battlefield-cell__miss"),
        AUTO_MISSED("battlefield-cell__miss__auto"),
        DONE("battlefield-cell__done"),
        LAST_HIT("battlefield-cell__hit battlefield-cell__last");
        private String type;

        cellsType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public enum directionName {
        RIGHT,
        LEFT,
        DOWN,
        UP
    }

    public enum diagonalNumber {
        START,
        FIRST,
        SECOND,
        THIRD,
        FORTH,
        FIFTH,
        SIXTH,
        SEVENTH,
        EIGHTH,
        LAST_LEFT_PART,
        LAST_RIGHT_PART,
        END
    }
}
