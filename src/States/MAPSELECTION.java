package States;
import java.awt.*;

import Objects.Object;
import Game.GameScreen;
import Game.Generator;
import Objects.Blank;
import Objects.Button;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
public class MAPSELECTION extends GameState{
    Graphics g;
    GameScreen gameScreen;
    ArrayList<Object> objects = new ArrayList<>();


    public MAPSELECTION(Graphics g, GameScreen gameScreen) {
        this.g = g;
        this.gameScreen = gameScreen;
    }

    @Override
    public void run() {
        if(objects.size() == 0) {
            File[] arr = new Generator(gameScreen).getAllFiles();
            int x = 0, y = 0;
            for(int i = 0; i != arr.length; i++) {
                objects.add(new Button(false, x, y, 200, 100, "spaceOne.jpg", arr[i].getName().substring(0, arr[i].getName().length() - 4)));
                x += 200;
                if (x % 600 == 0) {
                    x = 0;
                    y += 100;
                }
            }
        }
        for(int i = 0; i != objects.size(); i++) {
            int x = objects.get(i).getData()[0];
            int y = objects.get(i).getData()[1];
            Color origin = g.getColor();
            g.setColor(new Color(255, 255, 255, 200));
            g.fillRect(x, y, 200, 100);
            g.setColor(Color.black);
            g.drawRect(x, y, 200, 100);
            g.setColor(origin);
            g.drawImage(new ImageIcon("src/Images/" + objects.get(i).getImage()).getImage(), x, y, 200, 100, null);
            String text = objects.get(i).getText();
            g.setColor(Color.BLUE);
            g.drawString(text, x + 100 - (text.length() * 4), y + 60);
            g.setColor(origin);
        }
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
