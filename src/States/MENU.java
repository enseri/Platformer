package States;

import Objects.Object;

import java.awt.*;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import Game.GameScreen;
import Objects.Button;

public class MENU{
    private Graphics g;
    private GameScreen gameScreen;
    public ArrayList<Object> objects = new ArrayList<>();

    public MENU(Graphics g, GameScreen gameScreen) {
        this.g = g;
        this.gameScreen = gameScreen;
    }

    public void run() {
        renderButtons();
    }

    private void renderButtons() {
        if (objects.size() == 0) {
            objects.add(new Button(false,
                    (gameScreen.camera.getData()[2] / 2) - 50, 50, 100, 25,
                    "button.jpg", "PLAY"));
            objects.add(new Button(false,
                    (gameScreen.camera.getData()[2] / 2) - 50, 100, 100, 25,
                    "button.jpg", "CREATE"));
            objects.add(new Button(false,
                    (gameScreen.camera.getData()[2] / 2) - 50, 150, 100, 25,
                    "button.jpg", "SETTINGS"));
        }
        for (Object object : objects) {
            g.drawImage(new ImageIcon("src/Images/" + object.getImage()).getImage(),
                    object.getData()[0], object.getData()[1], object.getData()[2],
                    object.getData()[3], null);
            if (object.getImage().equals("button.jpg")) {
                Color origin = g.getColor();
                g.setColor(Color.white);
                g.drawString(object.getText(),
                        object.getData()[0]
                                + object.getData()[2] / 2 - (int) (object.getText().length() * 3.5),
                        object.getData()[1]
                                + (object.getData()[3] / 2));
                g.setColor(origin);
            }
        }
    }
}
