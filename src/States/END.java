package States;

import Game.GameScreen;
import java.awt.*;

public class END {
    private Graphics g;
    private GameScreen gameScreen;
    public END(Graphics g, GameScreen gameScreen) {
        this.g = g;
        this.gameScreen = gameScreen;
    }

    public void run() {
        g.setColor(new Color(0, 0, 0, 150));
        g.drawRect(0, 0, gameScreen.camera.getData()[2], gameScreen.camera.getData()[3]);
        g.setColor(Color.red);
        g.drawString("VICTORY!!!", gameScreen.camera.getData()[2] / 2 - 35, gameScreen. camera.getData()[3] / 2);
        g.drawString("Press ESC to return to menu", gameScreen.camera.getData()[2] / 2 - 95, gameScreen.camera.getData()[3] / 2 + 50);
    }
}
