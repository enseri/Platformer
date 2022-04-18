package States;

public enum GameStates {
    MENU,
    MAPSELECTION,
    SETTINGS,
    PLAYING,
    CREATING,
    END;

    public static GameStates gameState = MENU;

    public static void setGameState(String newGameState) {
        switch (newGameState) {
            case "CREATING":
                gameState = CREATING;
                break;
            case "PLAYING":
                gameState = PLAYING;
                break;
            case "MENU":
                gameState = MENU;
                break;
            case "END":
                gameState = END;
                break;
            case "SETTINGS":
                gameState = SETTINGS;
                break;
            case "MAPSELECTION":
                gameState = MAPSELECTION;
                break;
            default:
                gameState = MENU;
                break;
        }
    }

    public static String getGameState() {
        switch (gameState) {
            case CREATING:
                return "CREATING";
            case END:
                return "END";
            case MAPSELECTION:
                return "MAPSELECTION";
            case MENU:
                return "MENU";
            case PLAYING:
                return "PLAYING";
            case SETTINGS:
                return "SETTINGS";
            default:
                return null;

        }
    }
}
