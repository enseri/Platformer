package Game;

import States.*;

import java.awt.*;

public class Render {
    private GameScreen gameScreen;
    public MENU menu;
    public MAPSELECTION mapSelection;
    public SETTINGS settings;
    public CREATING creating;

    Render(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        creating = new CREATING(gameScreen);
    }
    
    void render(Graphics g) {
        switch(GameStates.gameState) {
            case MENU:
                menu = new MENU(g, gameScreen);
                menu.run();
                break;
            case PLAYING:
                PLAYING playing = new PLAYING(g, gameScreen);
                playing.run();
                break;
            case CREATING:
                creating.run(g);
                break;
            case END:
                END end = new END(g, gameScreen);
                end.run();
                break;
            case MAPSELECTION:
                mapSelection = new MAPSELECTION(g, gameScreen);
                mapSelection.run();
                break;
            case SETTINGS:
                settings = new SETTINGS(g, gameScreen);
                settings.run();
                break;
            default:
                System.out.println("error");
                break;
        }
    }
}
