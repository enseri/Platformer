package States;
import java.awt.*;

import Objects.Object;
import Game.GameScreen;
import Game.Generator;
import Objects.Button;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
public class MAPSELECTION{
    private Graphics g;
    private GameScreen gameScreen;
    public ArrayList<Object> objects = new ArrayList<>();


    public MAPSELECTION(Graphics g, GameScreen gameScreen) {
        this.g = g;
        this.gameScreen = gameScreen;
    }

    public void run() {
        if(objects.size() == 0) {
            new Generator(gameScreen);
            File[] arr = Generator.getAllFiles();
            int x = 0, y = 0;
            for(int i = 0; i != arr.length; i++) {
                objects.add(new Button(false, x, y, 200, 100, "background0.jpg", arr[i].getName().substring(0, arr[i].getName().length() - 4)));
                x += 200;
                if (x % 600 == 0) {
                    x = 0;
                    y += 100;
                }
            }
            objects.add(new Objects.Button(false, 0, 250, 50, 50, "Button.jpg", "BACK"));
        }
        for(int i = 0; i != objects.size() - 1; i++) {
            int x = objects.get(i).getData()[0];
            int y = objects.get(i).getData()[1] - gameScreen.camera.getData()[1];
            Color origin = g.getColor();
            g.drawImage(new ImageIcon("src/Images/" + objects.get(i).getImage()).getImage(), x, y, 200, 100, null);
            g.setColor(new Color(255, 255, 255, 100));
            g.fillRect(x, y, 200, 100);
            g.setColor(Color.red);
            g.drawRect(x, y, 200, 100);
            g.setColor(origin);
            String text = objects.get(i).getText();
            g.setColor(Color.BLUE);
            g.drawString(text, x + 100 - (int) (text.length() * 3.5), y + 60);
            g.setColor(origin);
        }
        g.setColor(Color.red);
        g.fillRect(0, 250, 50, 50);
        g.setColor(Color.black);
        g.drawString("BACK", 9, 275);
    }

}
