package Game;

import Objects.Background;
import Objects.Player;
import Objects.Blank;
import Objects.Block;
import Objects.Border;
import Objects.Object;
import Objects.Flag;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Generator {
    private GameScreen gameScreen;

    public Generator(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    private String readMapData(String map) {
        String str = "blank";
        boolean error;
        do {
            try {
                Scanner read = new Scanner(new File("src/Saves/" + map + ".txt"));
                str = read.nextLine();
                error = false;
            } catch (IOException E) {
                map = "default";
                System.out.println("Map Not Found");
                error = true;
            }
        } while (error);
        return str;
    }

    public void loadMap(String map) {
        boolean passedMapData = false;
        String data = new Generator(gameScreen).readMapData(map);
        String currentData = "";
        int collision = -1, x = -1, y = -1, width = -1, height = -1, xV = -1, yV = -1, repeatX = 1, repeatY = 1;
        String image;
        for (int i = 0; i < data.length(); i++) {
            if (data.substring(i, i + 1).equals(",")) {
                if (strToNum(currentData) == -1) {
                    if (passedMapData) {
                        if (currentData.substring(0, 1).equals("x"))
                            repeatX = strToNum(currentData.substring(1));
                        if (currentData.substring(0, 1).equals("y"))
                            repeatY = strToNum(currentData.substring(1));
                    }
                    currentData = "";
                } else {
                    boolean found = false;
                    if (passedMapData) {
                        if (collision == -1) {
                            collision = strToNum(currentData);
                            found = true;
                        }
                        if (x == -1 && !found) {
                            x = strToNum(currentData);
                            found = true;
                        }
                        if (y == -1 && !found) {
                            y = strToNum(currentData);
                            found = true;
                        }
                        if (width == -1 && !found) {
                            width = strToNum(currentData);
                            found = true;
                        }
                        if (height == -1 && !found) {
                            height = strToNum(currentData);
                            found = true;
                        }
                        if (xV == -1 && !found) {
                            xV = strToNum(currentData);
                            found = true;
                        }
                        if (yV == -1 && !found)
                            yV = strToNum(currentData);
                    } else {
                        gameScreen.mapData.add(strToNum(currentData));
                    }
                    currentData = "";
                }
                i += 2;
            }
            if (data.substring(i, i + 1).equals("|")) {
                if (i + 2 <= data.length() && data.substring(i, i + 2).equals("||")) {
                    gameScreen.mapData.add(strToNum(currentData));
                    passedMapData = true;
                    i++;
                } else {
                    image = currentData;
                    int tempRepeatX = 0, tempRepeatY = 0;
                    while (tempRepeatX < repeatX || tempRepeatY < repeatY) {
                        gameScreen.objects.add(generateObject(numToBool(collision), x + (width * tempRepeatX),
                                y + (height * tempRepeatY), width, height, xV, yV, image));
                        if (tempRepeatX == repeatX - 1 && tempRepeatY == repeatY - 1)
                            break;
                        if (tempRepeatX < repeatX - 1)
                            tempRepeatX++;
                        if (tempRepeatY < repeatY - 1)
                            tempRepeatY++;
                    }
                    collision = -1;
                    x = -1;
                    y = -1;
                    width = -1;
                    height = -1;
                    xV = -1;
                    yV = -1;
                    repeatX = 1;
                    repeatY = 1;
                }
                currentData = "";
                i++;
            }
            if (i < data.length())
                currentData += data.substring(i, i + 1);
        }
        if (gameScreen.camera != null) {
            gameScreen.camera.setSize(gameScreen.mapData.get(2), gameScreen.mapData.get(3));
            gameScreen.setSize(new Dimension(gameScreen.mapData.get(2), gameScreen.mapData.get(3)));
            gameScreen.game.setSize(new Dimension(gameScreen.mapData.get(2), gameScreen.mapData.get(3)));
        }
    }

    private int strToNum(String str) {
        int x = -1;
        while (!(x + "").equals(str) && x < 10000) {
            x++;
        }
        if (x == 10000)
            x = -1;
        return x;
    }

    private Boolean numToBool(int num) {
        return num == 1;
    }

    public Object generateObject(Boolean collision, int x, int y, int width, int height, int xV, int yV, String image) {
        int stopper = 0;
        for (int i = 0; i != image.length() && stopper == 0; i++) {
            if (image.substring(i, i + 1).toUpperCase().equals(image.substring(i, i + 1).toLowerCase()))
                stopper = i;
        }
        switch (image.substring(0, stopper)) {
            case "player":
                return new Player(true, x, y, width, height, xV, yV, image, gameScreen);
            case "block":
                return new Block(true, x, y, width, height, image);
            case "background":
                return new Background(false, x, y, width, height, image);
            case "flag":
                return new Flag(true, x, y, width, height, image);
            case "border":
                return new Border(true, x, y, width, height, image);
            default:
                return new Blank(false, x, y, width, height, image);
        }
    }

    public static File[] getAllFiles(String Folder) {
        File dir = new File("src/" + Folder);
        File[] arr = new File[Objects.requireNonNull(dir.listFiles()).length];
        for (int i = 0; i != arr.length; i++) {
            arr[i] = Objects.requireNonNull(dir.listFiles())[i];
        }
        return arr;
    }

    public boolean saveMap(String mapName, ArrayList<Integer> mapData, ArrayList<Object> objects) {
        File dir = new File("src/Saves");
        File[] tempArr = new File[Objects.requireNonNull(dir.listFiles()).length];
        ArrayList<String> arr = new ArrayList<>();
        for (int i = 0; i != tempArr.length; i++) {
            arr.add(Objects.requireNonNull(dir.listFiles())[i].getName());
        }
        if (arr.contains(mapName + ".txt"))
            return false;
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(new File("src/Saves/" + mapName + ".txt"), true);
        } catch (IOException e) {

        }
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(mapName + ", " + mapData.get(0) + ", " + mapData.get(1) + ", " + mapData.get(2) + ", "
                + mapData.get(3) + "||");
        for (int i = 0; i != objects.size(); i++) {
            Object temp = objects.get(i);
            printWriter.print(boolToNum(temp.getCollision()) + ", " + temp.getData()[0] + ", " + temp.getData()[1]
                    + ", " + temp.getData()[2] + ", " + temp.getData()[3] + ", " + 0 + ", " + 0 + ", " + temp.getImage()
                    + "|");
        }
        printWriter.close();
        return true;
    }

    public int boolToNum(boolean bool) {
        if (bool)
            return 1;
        return 0;
    }
}
