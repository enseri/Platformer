package Inputs;

import java.awt.event.*;

import Game.GameScreen;
import Game.Generator;
import States.GameStates;

public class MyMouseListener implements MouseListener, MouseMotionListener {
    GameScreen gameScreen;

    public MyMouseListener(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    int initialX, initialY, finalX, finalY;
    boolean dragged = false;

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!GameStates.getGameState().equals("MAPSELECTION")) {
            boolean objectFound = false;
            String buttonText = "";
            for (int i = 0; i < gameScreen.render.menu.objects.size(); i++) {
                int x = gameScreen.render.menu.objects.get(i).getData()[0];
                int y = gameScreen.render.menu.objects.get(i).getData()[1];
                int width = gameScreen.render.menu.objects.get(i).getData()[2];
                int height = gameScreen.render.menu.objects.get(i).getData()[3];
                if (e.getX() < x + width && e.getX() >= x && e.getY() < y + height && e.getY() >= y)
                    buttonText = gameScreen.render.menu.objects.get(i).getText();
            }
            switch (buttonText) {
                case "PLAY":
                    GameStates.setGameState("MAPSELECTION");
                    break;
                case "CREATE":
                    GameStates.setGameState("CREATING");
                    break;
                case "SETTINGS":
                    GameStates.setGameState("SETTINGS");
                    break;
            }
        } else {
            for (int i = 0; i < gameScreen.render.mapSelection.objects.size(); i++) {
                int x = gameScreen.render.mapSelection.objects.get(i).getData()[0];
                int y = gameScreen.render.mapSelection.objects.get(i).getData()[1];
                int width = gameScreen.render.mapSelection.objects.get(i).getData()[2];
                int height = gameScreen.render.mapSelection.objects.get(i).getData()[3];
                if (e.getX() < x + width && e.getX() >= x && e.getY() < y + height && e.getY() >= y) {
                    gameScreen.clearMapData();
                    new Generator(gameScreen).loadMap(gameScreen.render.mapSelection.objects.get(i).getText());
                    GameStates.setGameState("PLAYING");
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        initialX = e.getX();
        initialY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (dragged) {
            finalX = e.getX();
            finalY = e.getY();
            if(GameStates.getGameState().equals("MAPSELECTION"));
            gameScreen.camera.moveCamera("scroll", initialY - finalY);
            dragged = false;
        } else {
            System.out.println("Not Draggeed");
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        dragged = true;
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
