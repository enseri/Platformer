package Game;

import javax.swing.*;

import States.GameStates;
import States.PLAYING;
import States.MENU;

import java.awt.*;

class Render {
    private GameScreen gameScreen;
    MENU menu;
    PLAYING playing;
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
                System.out.println("mapselection");
                break;
            case SETTINGS:
                System.out.println("settings");
                break;
            default:
                System.out.println("error");
                break;
        }
    }
}
