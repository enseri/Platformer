package States;

import Game.GameScreen;
import Objects.DropDown;
import Game.Generator;
import Objects.Object;
import Objects.Button;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

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
    int mode = 0;
    public int editingMode = 1;

    public CREATING(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    public void run(Graphics g) {
        if (mode == 0) {
            if (firstRun) {
                for (int i = 0; i != 4; i++) {
                    mapData.add(0);
                }
                mapData.set(0, 600);
                mapData.set(1, 600);

                mapData.set(2, 100);
                mapData.set(3, 100);
                boolean error = false;
                do {
                    // Min Width: 600, Max Width: 2400, Min Height 600, Max Height 2400, Min Camera
                    // Width 100, Min Camera Height 100, Max Camera Width 1200, Max Camera Width
                    // 1200
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
            g.fillRect(objects.get(0).getData()[0], objects.get(0).getData()[1], ((int) (mapData.get(0) / 3.6) - 165),
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
            g.fillRect(objects.get(1).getData()[0], objects.get(1).getData()[1], ((int) (mapData.get(1) / 3.6) - 165),
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
            g.fillRect(objects.get(2).getData()[0], objects.get(2).getData()[1], ((int) (mapData.get(2) / 2.2) - 45),
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
            g.fillRect(objects.get(3).getData()[0], objects.get(3).getData()[1], ((int) (mapData.get(3) / 2.2) - 45),
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
            if(!(selectedObject == null)) {
                g.setColor(Color.green);
                g.drawRect(selectedObject.getData()[0] - 1, selectedObject.getData()[1] - 1, selectedObject.getData()[2] + 2, selectedObject.getData()[3] + 2);
            }
            for(int i = 0; i != createdObject.size(); i++) {
                g.drawImage(new ImageIcon("src/Images/" + createdObject.get(i).getImage()).getImage(), createdObject.get(i).getData()[0], createdObject.get(i).getData()[1], createdObject.get(i).getData()[2], createdObject.get(i).getData()[3], null);
            }
            if (objects.size() == 0) {
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
            for(int i = 0; i != objects.size(); i++) {
                int x = objects.get(i).getData()[0], y = objects.get(i).getData()[1], width = objects.get(i).getData()[2], height = objects.get(i).getData()[3];
                String text = objects.get(i).getText();
                g.setColor(Color.red);
                g.fillRect(x, y, width, height);
                g.setColor(Color.black);
                g.drawString(text, (int) (x + (width / 2) - (text.length() * 3.25)), y + 10);
                if(objects.get(i).getDropped()) {
                    ArrayList<Button> temp = objects.get(i).getDropDown();
                    for(int a = 0; a != temp.size(); a++) {
                        if(!dropDownObjects.contains(temp.get(a)))
                        dropDownObjects.add(temp.get(a));
                        int x1 = temp.get(a).getData()[0], y1 = temp.get(a).getData()[1], width1 = temp.get(a).getData()[2], height1 = temp.get(a).getData()[3];
                        Image image = new ImageIcon("src/Images/" + temp.get(a).getImage()).getImage();
                        g.drawImage(image, x1, y1, width1, height1, null);
                        g.setColor(Color.white);
                        g.drawRect(x1, y1 + height1 - 1, width1, 1);
                    }
                } else {
                    ArrayList<Button> temp = objects.get(i).getDropDown();
                    for(int a = 0; a != temp.size(); a++) {
                        if(dropDownObjects.contains(temp.get(a)))
                        dropDownObjects.remove(temp.get(a));
                    }
                }
            }
        }
    }

    public void updateCameraWidth(int x) {
        int difference = x - objects.get(2).getData()[0];
        difference *= 2.2;
        difference += 100;
        if (difference < 100) {
            difference = 100;
        }
        if (difference > 1200) {
            difference = 1200;
        }
        mapData.set(2, difference);
    }

    public void updateCameraHeight(int y) {
        int difference = y - objects.get(3).getData()[0];
        difference *= 2.2;
        difference += 100;
        if (difference < 100) {
            difference = 100;
        }
        if (difference > 1200) {
            difference = 1200;
        }
        mapData.set(3, difference);
    }

    public void updateWidth(int x) {
        int difference = x - objects.get(0).getData()[0];
        difference *= 3.6;
        difference += 600;
        if (difference < 600) {
            difference = 600;
        }
        if (difference > 2400) {
            difference = 2400;
        }
        mapData.set(0, difference);
    }

    public void updateHeight(int y) {
        int difference = y - objects.get(3).getData()[0];
        difference *= 3.6;
        difference += 600;
        if (difference < 600) {
            difference = 600;
        }
        if (difference > 2400) {
            difference = 2400;
        }
        mapData.set(1, difference);
    }

    public void nextMode() {
        while (objects.size() != 0)
            objects.remove(0);
        mode++;
    }

    public int getMode() {
        return mode;
    }
}
