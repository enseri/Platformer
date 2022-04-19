package Game;

import javax.swing.*;

import States.GameStates;
import States.PLAYING;
import States.MENU;
import States.MAPSELECTION;
import States.SETTINGS;

import java.awt.*;

public class Render {
    private GameScreen gameScreen;
    public MENU menu;
    public MAPSELECTION mapSelection;
    PLAYING playing;
    SETTINGS settings
    Render(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }
    
    void render(Graphics g) {
        switch(GameStates.gameState) {
            case MENU:
                menu = new MENU(g, gameScreen);
                menu.run();
                break;
            case PLAYING:
                playing = new PLAYING(g, gameScreen);
                playing.run();
                break;
            case CREATING:
                System.out.println("creating");
                break;
            case END:
                System.out.println("end");
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
