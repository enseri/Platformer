package States;

import Game.Camera;
import Game.GameScreen;
import Objects.DropDown;
import Game.Generator;
import Objects.Object;
import Objects.Border;
import Objects.Button;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.lang.model.util.ElementScanner6;
import javax.swing.ImageIcon;

import java.awt.Color;

public class CREATING {
    public ArrayList<Object> objects = new ArrayList<>();
    public ArrayList<Object> createdObject = new ArrayList<>();
    public ArrayList<Integer> mapData = new ArrayList<>();
    public ArrayList<String> backgrounds = new ArrayList<>();
    public ArrayList<Object> dropDownObjects = new ArrayList<>();
    public Object selectedObject;
    GameScreen gameScreen;
    boolean firstRun = true;
    public int mode = 0;
    public int editingMode = 1;
    public String mapName = "Default";

    public CREATING(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    public void run(Graphics g) {
        if (mode == 0) {
            if (firstRun) {
                for (int i = 0; i != 4; i++) {
                    mapData.add(0);
                }
                mapData.set(0, 1200);
                mapData.set(1, 300);

                mapData.set(2, 600);
                mapData.set(3, 300);
                boolean error = false;
                do {
                    objects.add(new Button(true, 50, 25, 500, 20, "button.jpg", "Width: "));
                    objects.add(new Button(true, 50, 75, 500, 20, "button.jpg", "Height: "));
                    objects.add(new Button(true, 50, 125, 500, 20, "button.jpg", "Camera Width: "));
                    objects.add(new Button(true, 50, 175, 500, 20, "button.jpg", "Camera Height: "));
                } while (error);
                firstRun = false;
            }
            g.setColor(Color.black);
            g.drawString("Parameters", (int) ((600 / 2) - 32.5), 10);
            g.setColor(Color.red);
            g.fillRect(objects.get(0).getData()[0], objects.get(0).getData()[1], objects.get(0).getData()[2],
                    objects.get(0).getData()[3]);
            g.setColor(Color.green);
            g.fillRect(objects.get(0).getData()[0], objects.get(0).getData()[1], ((int) (mapData.get(0) / 4.6) - 22),
                    objects.get(0).getData()[3]);
            g.setColor(Color.black);
            g.drawRect(objects.get(0).getData()[0], objects.get(0).getData()[1], objects.get(0).getData()[2],
                    objects.get(0).getData()[3]);
            String text = objects.get(0).getText() + mapData.get(0);
            g.drawString(text,
                    objects.get(0).getData()[0] + (objects.get(0).getData()[2] / 2) - (int) (text.length() * 3.25),
                    objects.get(0).getData()[1] + (int) (objects.get(0).getData()[3] / 2));

            g.setColor(Color.red);
            g.fillRect(objects.get(1).getData()[0], objects.get(1).getData()[1], objects.get(1).getData()[2],
                    objects.get(1).getData()[3]);
            g.setColor(Color.green);
            g.fillRect(objects.get(1).getData()[0], objects.get(1).getData()[1], ((int) (mapData.get(1) / 4.6) - 22),
                    objects.get(1).getData()[3]);
            g.setColor(Color.black);
            g.drawRect(objects.get(1).getData()[0], objects.get(1).getData()[1], objects.get(1).getData()[2],
                    objects.get(1).getData()[3]);
            text = objects.get(1).getText() + mapData.get(1);
            g.drawString(text,
                    objects.get(1).getData()[0] + (objects.get(1).getData()[2] / 2) - (int) (text.length() * 3.25),
                    objects.get(1).getData()[1] + (int) (objects.get(1).getData()[3] / 2));

            g.setColor(Color.red);
            g.fillRect(objects.get(2).getData()[0], objects.get(2).getData()[1], objects.get(2).getData()[2],
                    objects.get(2).getData()[3]);
            g.setColor(Color.green);
            g.fillRect(objects.get(2).getData()[0], objects.get(2).getData()[1], ((int) (mapData.get(2) / 4.6) - 22),
                    objects.get(2).getData()[3]);
            g.setColor(Color.black);
            g.drawRect(objects.get(2).getData()[0], objects.get(2).getData()[1], objects.get(2).getData()[2],
                    objects.get(2).getData()[3]);
            text = objects.get(2).getText() + mapData.get(2);
            g.drawString(text,
                    objects.get(2).getData()[0] + (objects.get(2).getData()[2] / 2) - (int) (text.length() * 3.25),
                    objects.get(2).getData()[1] + (int) (objects.get(2).getData()[3] / 2));

            g.setColor(Color.red);
            g.fillRect(objects.get(3).getData()[0], objects.get(3).getData()[1], objects.get(3).getData()[2],
                    objects.get(3).getData()[3]);
            g.setColor(Color.green);
            g.fillRect(objects.get(3).getData()[0], objects.get(3).getData()[1], ((int) (mapData.get(3) / 4.6) - 22),
                    objects.get(3).getData()[3]);
            g.setColor(Color.black);
            g.drawRect(objects.get(3).getData()[0], objects.get(3).getData()[1], objects.get(3).getData()[2],
                    objects.get(3).getData()[3]);
            text = objects.get(3).getText() + mapData.get(3);
            g.drawString(text,
                    objects.get(3).getData()[0] + (objects.get(3).getData()[2] / 2) - (int) (text.length() * 3.25),
                    objects.get(3).getData()[1] + (int) (objects.get(3).getData()[3] / 2));
            if (objects.size() == 5)
                objects.remove(objects.size() - 1);
            objects.add(new Objects.Button(false, 0, 250, 50, 50, "Button.jpg", "BACK"));
            g.setColor(Color.red);
            g.fillRect(0, 250, 50, 50);
            g.setColor(Color.black);
            g.drawString("BACK", 9, 275);
            if (objects.size() == 6)
                objects.remove(objects.size() - 1);
            objects.add(new Objects.Button(false, 550, 250, 50, 50, "Button.jpg", "NEXT"));
            g.setColor(Color.red);
            g.fillRect(550, 250, 50, 50);
            g.setColor(Color.black);
            g.drawString("NEXT", 559, 275);
        } else if (mode == 1) {
            if (!(selectedObject == null)) {
                g.setColor(Color.green);
                g.drawRect(selectedObject.getData()[0] - 1, selectedObject.getData()[1] - 1,
                        selectedObject.getData()[2] + 2, selectedObject.getData()[3] + 2);
            }
            for (int i = 0; i != createdObject.size(); i++) {
                g.drawImage(new ImageIcon("src/Images/" + createdObject.get(i).getImage()).getImage(),
                        createdObject.get(i).getData()[0], createdObject.get(i).getData()[1],
                        createdObject.get(i).getData()[2], createdObject.get(i).getData()[3], null);
            }
            g.setColor(new Color(0, 255, 0, 90));
            g.fillRect(gameScreen.camera.getData()[0], gameScreen.camera.getData()[1], gameScreen.camera.getData()[2],
                    gameScreen.camera.getData()[2]);
            if (objects.size() == 0) {
                objects.add(new Button(false, 0, mapData.get(1) - 100, 100, 100, "createAtlas.jpg", "Atlas"));
                ArrayList<Button> tempArray = new ArrayList<>();
                new Generator(gameScreen);
                File[] arr = Generator.getAllFiles("Images");
                int x = 75, y = 0;
                for (int i = 0; i != arr.length; i++) {
                    if (arr[i].getName().length() >= 10 && arr[i].getName().substring(0, 10).equals("background")) {
                        tempArray.add(new Button(false, x, y, 75, 10, arr[i].getName(), arr[i].getName()));
                        y += 10;
                    }
                }
                objects.add(new Objects.DropDown(tempArray, 0, 0, 75, 10, "Button.jpg", "Background"));
                tempArray.clear();
                x = 225;
                y = 0;
                for (int i = 0; i != arr.length; i++) {
                    if (arr[i].getName().length() >= 5 && arr[i].getName().substring(0, 5).equals("block")) {
                        tempArray.add(new Button(false, x, y, 75, 10, arr[i].getName(), arr[i].getName()));
                        y += 10;
                    }
                }
                objects.add(new Objects.DropDown(tempArray, 150, 0, 75, 10, "Button.jpg", "Block"));
                tempArray.clear();
                x = 375;
                y = 0;
                for (int i = 0; i != arr.length; i++) {
                    if (arr[i].getName().length() >= 4 && arr[i].getName().substring(0, 4).equals("flag")) {
                        tempArray.add(new Button(false, x, y, 75, 10, arr[i].getName(), arr[i].getName()));
                        y += 10;
                    }
                }
                objects.add(new Objects.DropDown(tempArray, 300, 0, 75, 10, "Button.jpg", "Flag"));
                tempArray.clear();
                x = 525;
                y = 0;
                for (int i = 0; i != arr.length; i++) {
                    if (arr[i].getName().length() >= 6 && arr[i].getName().substring(0, 6).equals("player")) {
                        tempArray.add(new Button(false, x, y, 75, 10, arr[i].getName(), arr[i].getName()));
                        y += 10;
                    }
                }
                objects.add(new Objects.DropDown(tempArray, 450, 0, 75, 10, "Button.jpg", "Player"));
            }
            for (int i = 0; i != objects.size(); i++) {
                if (objects.get(i).getImage().equals("Button.jpg")) {
                    int x = objects.get(i).getData()[0], y = objects.get(i).getData()[1],
                            width = objects.get(i).getData()[2], height = objects.get(i).getData()[3];
                    String text = objects.get(i).getText();
                    g.setColor(Color.red);
                    g.fillRect(x, y, width, height);
                    g.setColor(Color.black);
                    g.drawString(text, (int) (x + (width / 2) - (text.length() * 3.25)), y + 10);
                    if (objects.get(i).getDropped()) {
                        ArrayList<Button> temp = objects.get(i).getDropDown();
                        for (int a = 0; a != temp.size(); a++) {
                            if (!dropDownObjects.contains(temp.get(a)))
                                dropDownObjects.add(temp.get(a));
                            int x1 = temp.get(a).getData()[0], y1 = temp.get(a).getData()[1],
                                    width1 = temp.get(a).getData()[2], height1 = temp.get(a).getData()[3];
                            Image image = new ImageIcon("src/Images/" + temp.get(a).getImage()).getImage();
                            g.drawImage(image, x1, y1, width1, height1, null);
                            g.setColor(Color.white);
                            g.drawRect(x1, y1 + height1 - 1, width1, 1);
                        }

                    } else {
                        ArrayList<Button> temp = objects.get(i).getDropDown();
                        for (int a = 0; a != temp.size(); a++) {
                            if (dropDownObjects.contains(temp.get(a)))
                                dropDownObjects.remove(temp.get(a));
                        }
                    }
                } else if (objects.get(i).getImage().equals("createAtlas.jpg")) {
                    int x = objects.get(i).getData()[0], y = objects.get(i).getData()[1],
                            width = objects.get(i).getData()[2], height = objects.get(i).getData()[3];
                    g.drawImage(new ImageIcon("src/Images/createAtlas.jpg").getImage(), x, y, width, height, null);
                    switch (editingMode) {
                        case 1:
                            g.setColor(Color.red);
                            g.drawRect(x - 1, y - 1, width / 2 + 1, height / 2 + 1);
                            break;
                        case 2:
                            g.setColor(Color.red);
                            g.drawRect(x + width / 2 - 1, y - 1, width / 2 + 1, height / 2 + 1);
                            break;
                        case 3:
                            g.setColor(Color.red);
                            g.drawRect(x - 1, y + height / 2 - 1, width / 2 + 1, height / 2 + 1);
                            break;
                        case 4:
                            g.setColor(Color.red);
                            g.drawRect(x + width / 2 - 1, y + height / 2 - 1, width / 2 + 1, height / 2 + 1);
                            break;
                    }
                }
            }
        } else if (mode == 2) {
            g.setColor(Color.black);
            g.drawString("Last Step", mapData.get(0) / 2, 50);
            g.setColor(Color.red);
            g.drawString("Map Name", mapData.get(0) / 2, 100);
            g.setColor(Color.magenta);
            g.drawRect(mapData.get(0) / 2 - 50, 110, 150, 20);
            g.setColor(Color.black);
            g.drawString(mapName, (mapData.get(0) / 2) - 50 + 75 - (int) (mapName.length() * 3.25), 120);
            if (objects.size() == 1)
                objects.remove(objects.size() - 1);
            objects.add(new Objects.Button(false, 550, 250, 50, 50, "Button.jpg", "NEXT"));
            g.setColor(Color.red);
            g.fillRect(550, 250, 50, 50);
            g.setColor(Color.black);
            g.drawString("NEXT", 559, 275);
        } else if (mode == 3) {
            if(!new Generator(gameScreen).saveMap(mapName, mapData, createdObject))
                mode = 2;
            else {
                gameScreen.setDefault();
                GameStates.setGameState("MENU");
            }
        }
    }

    public void updateCameraWidth(int x) {
        int difference = x - objects.get(2).getData()[0];
        difference *= 4.6;
        difference += 100;
        if (difference < 100) {
            difference = 100;
        }
        if (difference > 2400) {
            difference = 2400;
        }
        mapData.set(2, difference);
    }

    public void copySelectedObject(String direction) {
        if (selectedObject != null) {
            int[] data = selectedObject.getData();
            Object temp = selectedObject.copy();
            switch (direction.toLowerCase()) {
                case "up":
                    int tempY = data[1] - data[3];
                    while (tempY >= 0) {
                        if (futureConflict(createdObject, temp, data[0], tempY, data[2], data[3]) == null) {
                            temp.setY(tempY);
                            createdObject.add(temp);
                            break;
                        }
                        tempY = tempY - data[3];
                    }
                    break;
                case "right":
                    int tempX = data[0] + data[2];
                    while (tempX + data[2] < mapData.get(0)) {
                        if (futureConflict(createdObject, temp, tempX, data[1], data[2], data[3]) == null) {
                            temp.setX(tempX);
                            createdObject.add(temp);
                            break;
                        }
                        tempX = tempX + data[2];
                    }
                    break;
                case "down":
                    tempY = data[1] + data[3];
                    while (tempY + data[3] < mapData.get(1)) {
                        if (futureConflict(createdObject, temp, data[0], tempY, data[2], data[3]) == null) {
                            temp.setY(tempY);
                            createdObject.add(temp);
                            break;
                        }
                        tempY = tempY + data[3];
                    }
                    break;
                case "left":
                    tempX = data[0] - data[2];
                    while (tempX >= 0) {
                        if (futureConflict(createdObject, temp, data[0] - data[2], data[1], data[2], data[3]) == null) {
                            temp.setX(data[0] - data[2]);
                            createdObject.add(temp);
                            break;
                        }
                        tempX = tempX - data[2];
                    }
                    break;
            }
        }
    }

    public void updateCameraHeight(int y) {
        int difference = y - objects.get(3).getData()[0];
        difference *= 4.6;
        difference += 100;
        if (difference < 100) {
            difference = 100;
        }
        if (difference > 2400) {
            difference = 2400;
        }
        mapData.set(3, difference);
    }

    public void updateWidth(int x) {
        int difference = x - objects.get(0).getData()[0];
        difference *= 4.6;
        difference += 100;
        if (difference < 100) {
            difference = 100;
        }
        if (difference > 2400) {
            difference = 2400;
        }
        mapData.set(0, difference);
    }

    public void updateHeight(int y) {
        int difference = y - objects.get(1).getData()[0];
        difference *= 4.6;
        difference += 100;
        if (difference < 100) {
            difference = 100;
        }
        if (difference > 2400) {
            difference = 2400;
        }
        mapData.set(1, difference);
    }

    public void nextMode() {
        if (mode == 0) {
            createdObject.add(new Border(true, 0, 0, mapData.get(0), 1, "border0.jpg"));
            createdObject.add(new Border(true, mapData.get(0), 0, 1, mapData.get(1), "border0.jpg"));
            createdObject.add(new Border(true, 0, mapData.get(1), mapData.get(0), 1, "border0.jpg"));
            createdObject.add(new Border(true, 0, 0, 1, mapData.get(1), "border0.jpg"));
            gameScreen.game.setSize(mapData.get(0), mapData.get(1));
            gameScreen.setSize(mapData.get(0), mapData.get(1));
            gameScreen.camera = new Camera(0, 0, mapData.get(2), mapData.get(3), gameScreen);
            // gameScreen.game.pack();
        }
        objects.clear();
        mode++;
    }

    public int getMode() {
        return mode;
    }

    public Object futureConflict(ArrayList<Object> objects, Object currentObject, int x, int y, int width, int height) {
        for (Object object : objects) {
            if (object.getCollision()) {
                if (object != currentObject) {
                    int[] data = object.getData();
                    int newX = data[0];
                    int newY = data[1];
                    int newWidth = data[2];
                    int newHeight = data[3];
                    boolean xIntercept = false;
                    for (int x1 = x; x1 < x + width; x1++) {
                        if (x1 < newX + newWidth && x1 >= newX) {
                            xIntercept = true;
                            break;
                        }
                    }
                    boolean yIntercept = false;
                    if (xIntercept) {
                        for (int y1 = y; y1 < y + height; y1++) {
                            if (y1 < newY + newHeight && y1 >= newY) {
                                yIntercept = true;
                                break;
                            }
                        }
                    }

                    if (xIntercept && yIntercept) {
                        for (int x1 = x; x1 < x + width; x1++) {
                            for (int y1 = y; y1 < y + height; y1++) {
                                for (int x2 = newX; x2 < newX + newWidth; x2++) {
                                    for (int y2 = newY; y2 < newY + newHeight; y2++) {
                                        if (y2 == y1) {
                                            if (x2 == x1) {
                                                return object;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
        return null;
    }
}
