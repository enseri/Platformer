package Inputs;

import java.awt.Dimension;
import java.awt.event.*;
import java.util.ArrayList;

import Game.GameScreen;
import Game.Generator;
import States.GameStates;
import Objects.Object;
import java.util.Random;

public class MyMouseListener implements MouseListener, MouseMotionListener {
    private GameScreen gameScreen;
    Random rand = new Random();

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
                        gameScreen.camera.reset();
                        GameStates.setGameState("MAPSELECTION");
                        break;
                    case "CREATE":
                        gameScreen.camera.reset();
                        GameStates.setGameState("CREATING");
                        break;
                    case "SETTINGS":
                        gameScreen.camera.reset();
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
                            gameScreen.camera.reset();
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
                            gameScreen.camera.reset();
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
                            gameScreen.camera.reset();
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
                        } else if (gameScreen.render.creating.objects.get(i).getText().equals("Atlas")) {
                            int[] data = gameScreen.render.creating.objects.get(i).getData();
                            if (e.getX() < data[0] + data[2] / 2) {
                                if (e.getY() < data[1] + data[3] / 2)
                                    gameScreen.render.creating.editingMode = 1;
                                if (e.getY() >= data[1] + data[3] / 2)
                                    gameScreen.render.creating.editingMode = 3;
                            }
                            if (e.getX() >= data[0] + data[2] / 2) {
                                if (e.getY() < data[1] + data[3] / 2)
                                    gameScreen.render.creating.editingMode = 2;
                                if (e.getY() >= data[1] + data[3] / 2)
                                    gameScreen.render.creating.editingMode = 4;
                            }
                        }
                    }
                }
                for (int i = 0; i < gameScreen.render.creating.dropDownObjects.size(); i++) {
                    int x = gameScreen.render.creating.dropDownObjects.get(i).getData()[0];
                    int y = gameScreen.render.creating.dropDownObjects.get(i).getData()[1];
                    int width = gameScreen.render.creating.dropDownObjects.get(i).getData()[2];
                    int height = gameScreen.render.creating.dropDownObjects.get(i).getData()[3];
                    if (e.getX() < x + width && e.getX() >= x && e.getY() < y + height && e.getY() >= y) {
                        int[] data = gameScreen.camera.getData();
                        int newX = data[0];
                        int newY = data[1];
                        boolean searching = true;
                        for(int tempX = 0; tempX * 75 < data[2] && searching; tempX++) {
                            for(int tempY = 0; tempY * 75 < data[3] && searching; tempY++) {
                                newX = data[0] + tempX * 75;
                                newY = data[1] + tempY * 75;
                                if(gameScreen.render.creating.futureConflict(gameScreen.render.creating.createdObject, null, newX, newY, 75, 75) == null)
                                    searching = false;
                            }
                        }
                        if (!searching){
                            gameScreen.render.creating.createdObject.add(new Generator(gameScreen).generateObject(false,
                                    newX, newY, 75, 75, 0, 0,
                                    gameScreen.render.creating.dropDownObjects.get(i).getImage()));
                        }
                    }
                }
                boolean notFound = true;
                for (int i = 0; i < gameScreen.render.creating.createdObject.size(); i++) {
                    int x = gameScreen.render.creating.createdObject.get(i).getData()[0];
                    int y = gameScreen.render.creating.createdObject.get(i).getData()[1];
                    int width = gameScreen.render.creating.createdObject.get(i).getData()[2];
                    int height = gameScreen.render.creating.createdObject.get(i).getData()[3];
                    if (e.getX() < x + width && e.getX() >= x && e.getY() < y + height && e.getY() >= y) {
                        notFound = false;
                        gameScreen.render.creating.selectedObject = gameScreen.render.creating.createdObject.get(i);
                    }
                }
                if (notFound)
                    gameScreen.render.creating.selectedObject = null;
                if (gameScreen.render.creating.editingMode == 4 && gameScreen.render.creating.selectedObject != null) {
                    gameScreen.render.creating.createdObject.remove(gameScreen.render.creating.selectedObject);
                    gameScreen.render.creating.selectedObject = null;
                }
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
        if (gameScreen.render.creating.mode == 1 && gameScreen.render.creating.selectedObject != null) {
            Object temp = gameScreen.render.creating.selectedObject;
            int x = temp.getData()[0], y = temp.getData()[1], width = temp.getData()[2], height = temp.getData()[3];
            if (gameScreen.render.creating.editingMode == 1) {
                if (initialX < x + width && initialX >= x && initialY < y + height && initialY >= y) {
                    int newX = x + (e.getX() - initialX);
                    int newY = y + (e.getY() - initialY);
                    if (gameScreen.render.creating.futureConflict(gameScreen.render.creating.createdObject, temp, newX, newY, width,
                            height) == null) {
                        gameScreen.render.creating.selectedObject.setX(newX);
                        gameScreen.render.creating.selectedObject.setY(newY);
                        initialX = e.getX();
                        initialY = e.getY();
                    }
                }
            } else if (gameScreen.render.creating.editingMode == 2) {
                if (initialX < x + width && initialX >= x && initialY < y + height && initialY >= y) {
                    if (width + e.getX() - initialX > 5 && height + e.getY() - initialY > 5) {
                        int newWidth = width + e.getX() - initialX;
                        int newHeight = height + e.getY() - initialY;
                        if (gameScreen.render.creating.futureConflict(gameScreen.render.creating.createdObject, temp, x, y, newWidth,
                                newHeight) == null) {
                            gameScreen.render.creating.selectedObject.setWidth(newWidth);
                            gameScreen.render.creating.selectedObject.setHeight(newHeight);
                            initialX = e.getX();
                            initialY = e.getY();
                        }
                    }
                }
            }
        }
        dragged = true;
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}