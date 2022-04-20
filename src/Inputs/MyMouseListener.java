package Inputs;

import java.awt.event.*;

import Game.GameScreen;
import Game.Generator;
import States.GameStates;

public class MyMouseListener implements MouseListener, MouseMotionListener {
    private GameScreen gameScreen;

    public MyMouseListener(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    private int initialY;
    private boolean dragged = false;

    @Override
    public void mouseClicked(MouseEvent e) {
        assert GameStates.getGameState() != null;
        switch (GameStates.getGameState()) {
            case "MENU":
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
                break;
            case "MAPSELECTION":
                for (int i = 0; i < gameScreen.render.mapSelection.objects.size(); i++) {
                    int x = gameScreen.render.mapSelection.objects.get(i).getData()[0];
                    int y = gameScreen.render.mapSelection.objects.get(i).getData()[1];
                    int width = gameScreen.render.mapSelection.objects.get(i).getData()[2];
                    int height = gameScreen.render.mapSelection.objects.get(i).getData()[3];
                    if (e.getX() < x + width && e.getX() >= x && e.getY() < y + height && e.getY() >= y) {
                        if (!gameScreen.render.mapSelection.objects.get(i).getText().equals("BACK")) {
                            gameScreen.camera.reset();
                            gameScreen.clearMapData();
                            new Generator(gameScreen).loadMap(gameScreen.render.mapSelection.objects.get(i).getText());
                            GameStates.setGameState("PLAYING");
                        } else {
                            GameStates.setGameState("MENU");
                        }
                    }
                }
                break;
            case "SETTINGS":
                for (int i = 0; i < gameScreen.render.settings.objects.size(); i++) {
                    int x = gameScreen.render.settings.objects.get(i).getData()[0];
                    int y = gameScreen.render.settings.objects.get(i).getData()[1];
                    int width = gameScreen.render.settings.objects.get(i).getData()[2];
                    int height = gameScreen.render.settings.objects.get(i).getData()[3];
                    if (e.getX() < x + width && e.getX() >= x && e.getY() < y + height && e.getY() >= y) {
                        if (gameScreen.render.settings.objects.get(i).getText().equals("FPS: ")) {
                            gameScreen.render.settings.updateFPS(e.getX());
                        } else if (gameScreen.render.settings.objects.get(i).getText().equals("BACK")) {
                            GameStates.setGameState("MENU");
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        initialY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (dragged) {
            dragged = false;
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
        assert GameStates.getGameState() != null;
        if (GameStates.getGameState().equals("MAPSELECTION")) {
            gameScreen.camera.moveCamera("scroll", initialY - e.getY());
            initialY = e.getY();
        }
        if (GameStates.getGameState().equals("SETTINGS")) {
            for (int i = 0; i < gameScreen.render.settings.objects.size(); i++) {
                int x = gameScreen.render.settings.objects.get(i).getData()[0];
                int y = gameScreen.render.settings.objects.get(i).getData()[1];
                int width = gameScreen.render.settings.objects.get(i).getData()[2];
                int height = gameScreen.render.settings.objects.get(i).getData()[3];
                if (e.getX() < x + width && e.getX() >= x && e.getY() < y + height && e.getY() >= y) {
                    gameScreen.render.settings.updateFPS(e.getX());
                }
            }
        }
        dragged = true;
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
