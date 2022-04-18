package States;
import java.awt.Graphics;
import Game.GameScreen;
public class MAPSELECTION extends GameState{
    Graphics g;
    GameScreen gameScreen;
    public MAPSELECTION(Graphics g, GameScreen gameScreen) {
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
