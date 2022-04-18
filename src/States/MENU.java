package States;

import Objects.Object;

import java.awt.*;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import Game.GameScreen;
import Objects.Button;

public class MENU extends GameState {
    Graphics g;
    GameScreen gameScreen;
    String map;
    ArrayList<Object> objects = new ArrayList<>();

    public MENU(Graphics g, GameScreen gameScreen) {
        this.g = g;
        this.gameScreen = gameScreen;
    }

    @Override
    public void run() {
        renderButtons();
    }

    @Override
    GameState getGameState() {
        return this;
    }

    @Override
    void clear() {

    }

    public void renderButtons() {
        if (objects.size() == 0) {
            objects.add(new Button(false,
                    gameScreen.camera.getData()[0] + (gameScreen.camera.getData()[2] / 2) - 50, 50, 100, 25,
                    "button.jpg", "PLAY"));
        }
        for (int i = 0; i < objects.size(); i++) {
            g.drawImage(new ImageIcon("src/Images/" + objects.get(i).getImage()).getImage(),
                    objects.get(i).getData()[0], objects.get(i).getData()[1], objects.get(i).getData()[2],
                    objects.get(i).getData()[3], null);
            if (objects.get(i).getImage().equals("button.jpg")) {
                Color origin = g.getColor();
                g.setColor(Color.white);
                g.drawString(objects.get(i).getText(),
                        objects.get(i).getData()[0]
                                + objects.get(i).getData()[2] / 2 - (objects.get(i).getText().length() * 4),
                        objects.get(i).getData()[1]
                                + (objects.get(i).getData()[3] / 2));
                g.setColor(origin);
            }
        }
    }

    public String getMap() {
        return map;
    }
}
