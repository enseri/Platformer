package States;

import Game.GameScreen;
import java.awt.Graphics;

public class SETTINGS extends GameState{
    Graphics g;
    GameScreen gameScreen;
    public SETTINGS(Graphics g, GameScreen gameScreen) {
        this.g = g;
        this.gameScreen = gameScreen;
    }
    @Override
    void run() {
        
    }

    @Override
    GameState getGameState() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    void clear() {
        // TODO Auto-generated method stub
        
    }
    
}
