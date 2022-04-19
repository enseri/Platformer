package Game;

import Objects.Background;
import Objects.Player;
import Objects.Blank;
import Objects.Grass;
import Objects.Object;

import java.io.File;
import java.io.IOException;
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
                map = "";
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
        for(int i = 0; i < data.length(); i++) {
            if(data.substring(i, i + 1).equals(",")) {
                if(strToNum(currentData) == -1) {
                    if(passedMapData) {
                        if(currentData.substring(0, 1).equals("x"))
                            repeatX = strToNum(currentData.substring(1));
                        if(currentData.substring(0, 1).equals("y"))
                            repeatY = strToNum(currentData.substring(1));
                    }
                    currentData = "";
                } else {
                    boolean found = false;
                    if(passedMapData) {
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
            if(data.substring(i, i + 1).equals("|")) {
                if(i + 2 <= data.length() && data.substring(i, i + 2).equals("||")) {
                    gameScreen.mapData.add(strToNum(currentData));
                    passedMapData = true;
                    i++;
                } else {
                    image = currentData;
                    int tempRepeatX = 0, tempRepeatY = 0;
                    while(tempRepeatX < repeatX || tempRepeatY < repeatY) {
                        gameScreen.objects.add(generateObject(numToBool(collision), x + (width * tempRepeatX), y + (height * tempRepeatY), width, height, xV, yV, image));
                        if(tempRepeatX == repeatX - 1 && tempRepeatY == repeatY - 1)
                            break;
                        if(tempRepeatX < repeatX - 1)
                            tempRepeatX++;
                        if(tempRepeatY < repeatY - 1)
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
            if(i < data.length())
            currentData += data.substring(i, i+1);
        }
    }

    private int strToNum(String str) {
        int x = -1;
        while (!(x + "").equals(str) && x < 10000) {
            x++;
        }
        if(x == 10000)
            x = -1;
        return x;
    }

    private Boolean numToBool(int num) {
        return num == 1;
    }

    private Object generateObject(Boolean collision, int x, int y, int width, int height, int xV, int yV, String image) {
        switch(image.substring(0, image.length() - 4)) {
            case "player":
                return new Player(collision, x, y, width, height, xV, yV, image, gameScreen);
            case "grass":
                return new Grass(collision, x, y, width, height, image);
            case "background":
                return new Background(collision, x, y, width, height, image);
            default:
                return new Blank(collision, x, y, width, height, image);
        }
    }

    public static File[] getAllFiles() {
        File dir = new File("src/saves");
        File[] arr = new File[Objects.requireNonNull(dir.listFiles()).length];
        for(int i = 0; i != arr.length; i++) {
            arr[i] = Objects.requireNonNull(dir.listFiles())[i];
        }
        return arr;
    }
}
