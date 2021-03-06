package States;

import Game.GameScreen;
import Objects.Button;
import Objects.Object;
import java.awt.Color;

import java.awt.Graphics;
import java.util.ArrayList;

public class SETTINGS{
    private Graphics g;
    private GameScreen gameScreen;
    public ArrayList<Object> objects = new ArrayList<>();
    public SETTINGS(Graphics g, GameScreen gameScreen) {
        this.g = g;
        this.gameScreen = gameScreen;
    }
    public
    void run() {
        if(objects.size() == 0) {
            objects.add(new Button(true, (gameScreen.camera.getData()[2] / 2) - (480 /  2), 100, 480, 20, "button.jpg", "FPS: "));
        }
        g.setColor(Color.red);
        g.fillRect(objects.get(0).getData()[0], objects.get(0).getData()[1], objects.get(0).getData()[2], objects.get(0).getData()[3]);
        g.setColor(Color.green);
        g.fillRect(objects.get(0).getData()[0], objects.get(0).getData()[1], (int) gameScreen.game.getFPS() * 2, objects.get(0).getData()[3]);
        g.setColor(Color.black);
        g.drawRect(objects.get(0).getData()[0], objects.get(0).getData()[1], objects.get(0).getData()[2], objects.get(0).getData()[3]);
        String text = objects.get(0).getText() + gameScreen.game.getFPS();
        g.drawString(text, objects.get(0).getData()[0] + (objects.get(0).getData()[2] / 2) - (int)(text.length() * 3.25), objects.get(0).getData()[1] + (int)(objects.get(0).getData()[3] / 2));
        if(objects.size() == 2)
        objects.remove(objects.size() - 1);
        objects.add(new Objects.Button(false, 0, 250, 50, 50, "Button.jpg", "BACK"));
        g.setColor(Color.red);
        g.fillRect(0, 250, 50, 50);
        g.setColor(Color.black);
        g.drawString("BACK", 9, 275);
    }
    
    public void updateFPS(int x) {
        int difference = x - objects.get(0).getData()[0];
        difference /= 2;
        if(difference <= 0) {
            difference = 1;
        } else if(difference > 240) {
            difference = 240;
        }
        gameScreen.game.setFPS(difference);
    }

}
