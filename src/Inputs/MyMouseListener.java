package Inputs;

import java.awt.event.*;

import Game.GameScreen;
import Game.Generator;
import States.GameStates;
import Objects.Object;

public class MyMouseListener implements MouseListener, MouseMotionListener {
    private GameScreen gameScreen;

    public MyMouseListener(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    private int initialY;
    private int initialX;
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
            case "CREATING":
                for (int i = 0; i < gameScreen.render.creating.objects.size(); i++) {
                    int x = gameScreen.render.creating.objects.get(i).getData()[0];
                    int y = gameScreen.render.creating.objects.get(i).getData()[1];
                    int width = gameScreen.render.creating.objects.get(i).getData()[2];
                    int height = gameScreen.render.creating.objects.get(i).getData()[3];
                    if (e.getX() < x + width && e.getX() >= x && e.getY() < y + height && e.getY() >= y) {
                        if (gameScreen.render.creating.objects.get(i).getText().equals("Width: ")) {
                            gameScreen.render.creating.updateWidth(e.getX());
                        } else if (gameScreen.render.creating.objects.get(i).getText().equals("BACK")) {
                            GameStates.setGameState("MENU");
                        } else if (gameScreen.render.creating.objects.get(i).getText().equals("NEXT")) {
                            gameScreen.render.creating.nextMode();
                        } else if (gameScreen.render.creating.objects.get(i).getText().equals("Camera Width: ")) {
                            gameScreen.render.creating.updateCameraWidth(e.getX());
                        } else if (gameScreen.render.creating.objects.get(i).getText().equals("Camera Height: ")) {
                            gameScreen.render.creating.updateCameraHeight(e.getX());
                        } else if (gameScreen.render.creating.objects.get(i).getText().equals("Height: ")) {
                            gameScreen.render.creating.updateHeight(e.getX());
                        } else if (gameScreen.render.creating.objects.get(i).getText().equals("Background")) {
                            gameScreen.render.creating.objects.get(i).toggleDropped();
                        } else if (gameScreen.render.creating.objects.get(i).getText().equals("Block")) {
                            gameScreen.render.creating.objects.get(i).toggleDropped();
                        } else if (gameScreen.render.creating.objects.get(i).getText().equals("Flag")) {
                            gameScreen.render.creating.objects.get(i).toggleDropped();
                        } else if (gameScreen.render.creating.objects.get(i).getText().equals("Player")) {
                            gameScreen.render.creating.objects.get(i).toggleDropped();
                        }
                    }
                }
                for (int i = 0; i < gameScreen.render.creating.dropDownObjects.size(); i++) {
                    int x = gameScreen.render.creating.dropDownObjects.get(i).getData()[0];
                    int y = gameScreen.render.creating.dropDownObjects.get(i).getData()[1];
                    int width = gameScreen.render.creating.dropDownObjects.get(i).getData()[2];
                    int height = gameScreen.render.creating.dropDownObjects.get(i).getData()[3];
                    if (e.getX() < x + width && e.getX() >= x && e.getY() < y + height && e.getY() >= y) {
                        gameScreen.render.creating.createdObject.add(new Generator(gameScreen).generateObject(false, 100, 100, 75, 75, 0, 0, gameScreen.render.creating.dropDownObjects.get(i).getImage()));
                    }
                }
                boolean notFound = true;
                for(int i = 0; i < gameScreen.render.creating.createdObject.size(); i++) {
                    int x = gameScreen.render.creating.createdObject.get(i).getData()[0];
                    int y = gameScreen.render.creating.createdObject.get(i).getData()[1];
                    int width = gameScreen.render.creating.createdObject.get(i).getData()[2];
                    int height = gameScreen.render.creating.createdObject.get(i).getData()[3];
                    if (e.getX() < x + width && e.getX() >= x && e.getY() < y + height && e.getY() >= y) {
                        notFound = false;
                        gameScreen.render.creating.selectedObject = gameScreen.render.creating.createdObject.get(i);
                    }
                }
                if(notFound)
                    gameScreen.render.creating.selectedObject = null;
                break;
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
        if (GameStates.getGameState().equals("MAPSELECTION")
                || (GameStates.getGameState().equals("CREATING") && gameScreen.render.creating.getMode() == 1)) {
            gameScreen.camera.moveCamera("scroll", initialY - e.getY());
            initialY = e.getY();
        }
        if (GameStates.getGameState().equals("SETTINGS")) {
            for (int i = 0; i < gameScreen.render.settings.objects.size(); i++) {
                int x = gameScreen.render.settings.objects.get(i).getData()[0];
                int y = gameScreen.render.settings.objects.get(i).getData()[1];
                int width = gameScreen.render.settings.objects.get(i).getData()[2];
                int height = gameScreen.render.settings.objects.get(i).getData()[3];
                if (initialX < x + width && initialX >= x && initialY < y + height && initialY >= y) {
                    if (gameScreen.render.settings.objects.get(i).getText().equals("FPS: "))
                        gameScreen.render.settings.updateFPS(e.getX());
                }
            }
        }
        if (GameStates.getGameState().equals("CREATING") && gameScreen.render.creating.getMode() == 0) {
            for (int i = 0; i < gameScreen.render.creating.objects.size(); i++) {
                int x = gameScreen.render.creating.objects.get(i).getData()[0];
                int y = gameScreen.render.creating.objects.get(i).getData()[1];
                int width = gameScreen.render.creating.objects.get(i).getData()[2];
                int height = gameScreen.render.creating.objects.get(i).getData()[3];
                if (initialX < x + width && initialX >= x && initialY < y + height && initialY >= y) {
                    if (gameScreen.render.creating.objects.get(i).getText().equals("Width: ")) {
                        gameScreen.render.creating.updateWidth(e.getX());
                    }
                    if (gameScreen.render.creating.objects.get(i).getText().equals("Height: ")) {
                        gameScreen.render.creating.updateHeight(e.getX());
                    }
                    if (gameScreen.render.creating.objects.get(i).getText().equals("Camera Width: ")) {
                        gameScreen.render.creating.updateCameraWidth(e.getX());
                    }
                    if (gameScreen.render.creating.objects.get(i).getText().equals("Camera Height: ")) {
                        gameScreen.render.creating.updateCameraHeight(e.getX());
                    }
                }
            }
        }
        if(gameScreen.render.creating.editingMode == 1 && gameScreen.render.creating.selectedObject != null) {
            Object temp = gameScreen.render.creating.selectedObject;
            int x = temp.getData()[0], y = temp.getData()[1], width = temp.getData()[2], height = temp.getData()[3]; 
            if(initialX < x + width && initialX >= x && initialY < y + height && initialY >= y) {
                gameScreen.render.creating.selectedObject.setX(temp.getData()[0] + (e.getX() - initialX));
                gameScreen.render.creating.selectedObject.setY(temp.getData()[1] + (e.getY() - initialY));
                initialX = e.getX();
                initialY = e.getY();
            }
        }
        dragged = true;
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
