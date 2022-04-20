package States;
import Game.GameScreen;

import java.awt.*;

import javax.swing.ImageIcon;

public class PLAYING{
    private Graphics g;
    private GameScreen gameScreen;

    public PLAYING(Graphics g, GameScreen gameScreen) {
        this.g = g;
        this.gameScreen = gameScreen;
    }

    public
    void run() {
        renderObjectWithinWindow(g);
        renderObject(g);
    }

    private void renderObject(Graphics g) {
        for (int i = 0; i != gameScreen.renderedObject.size(); i++) {
            int tempX, tempY, tempWidth, tempHeight;
            tempX = gameScreen.objects.get(gameScreen.objects.indexOf(gameScreen.renderedObject.get(i))).getData()[0];
            tempY = gameScreen.objects.get(gameScreen.objects.indexOf(gameScreen.renderedObject.get(i))).getData()[1];
            tempWidth = gameScreen.objects.get(gameScreen.objects.indexOf(gameScreen.renderedObject.get(i)))
                    .getData()[2];
            tempHeight = gameScreen.objects.get(gameScreen.objects.indexOf(gameScreen.renderedObject.get(i)))
                    .getData()[3];
            g.drawImage(
                    new ImageIcon("src/Images/" + gameScreen.objects
                            .get(gameScreen.objects.indexOf(gameScreen.renderedObject.get(i))).getImage()).getImage(),
                    tempX - gameScreen.camera.getData()[0],
                    tempY - gameScreen.camera.getData()[1],
                    tempWidth, tempHeight, null);
        }
    }

    private void renderObjectWithinWindow(Graphics g) {
        int renderDeficit = 10;
        for (int i = 0; i != gameScreen.objects.size(); i++) {
            boolean render = false;
            int tempX, tempY, tempWidth, tempHeight;
            tempX = gameScreen.objects.get(i).getData()[0];
            tempY = gameScreen.objects.get(i).getData()[1];
            tempWidth = gameScreen.objects.get(i).getData()[2];
            tempHeight = gameScreen.objects.get(i).getData()[3];
            for (int a = tempX; a < tempX + tempWidth; a++) {
                for (int b = tempY; b < tempY + tempHeight; b++) {
                    if (a < gameScreen.camera.getData()[0] + gameScreen.camera.getData()[2] + renderDeficit
                            && a >= gameScreen.camera.getData()[0] - renderDeficit
                            && b < gameScreen.camera.getData()[1] + renderDeficit + gameScreen.camera.getData()[3]
                            && b >= gameScreen.camera.getData()[1] - renderDeficit) {
                        render = true;
                        if (!gameScreen.renderedObject.contains(gameScreen.objects.get(i))) {
                            gameScreen.renderedObject.add(gameScreen.objects.get(i));
                            g.drawImage(new ImageIcon("src/Images/" + gameScreen.objects.get(i)).getImage(),
                                    tempX - gameScreen.camera.getData()[0],
                                    tempY - gameScreen.camera.getData()[1],
                                    tempWidth, tempHeight, null);
                        }
                    }
                }
            }
            if (gameScreen.renderedObject.contains(gameScreen.objects.get(i)) && !render) {
                gameScreen.renderedObject.remove(gameScreen.objects.get(i));
            }
        }
    }

}
