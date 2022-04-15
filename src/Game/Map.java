package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.concurrent.TimeUnit;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.util.ArrayList;

class TempVelocityThread extends Thread {
    Map map;
    int i;
    boolean running = true;

    public TempVelocityThread(Map map, int i) {
        this.map = map;
        this.i = i;
    }

    public void run() {
        while (running) {
            System.out.print("");
                map.updateVelocity(i);
        }
    }

    public void stop2() {
        running = false;
    }
}

class UPDPlayerThread extends Thread {
    Map map;

    public UPDPlayerThread(Map map) {
        this.map = map;
    }

    public void run() {
        while (true) {
            System.out.print("");
            map.updatePlayer();
        }
    }
}

class RenderThread extends Thread {
    Map map;

    public RenderThread(Map map) {
        this.map = map;
    }

    public void run() {
        while (true) {
            if (map.fpsLimitReached()) {
                map.repaint();
            }
        }
    }
}

class CameraThread extends Thread{
    Map map;
    public CameraThread(Map map) {
        this.map = map;
    }
    public void run() {
        //while (true) {
            System.out.print("");
            map.moveCamera("track_player", 0);
        //}
    }
}

public class Map extends JPanel {
    private int playerIndex;
    private int frames;

    private double timePerFrame;
    private double timePerUpdate;
    private double timePerJump;
    private long lastFrame;
    private long lastTime;
    private long lastUpdate;
    public long lastJump;

    public ArrayList<String> object = new ArrayList<String>();
    private ArrayList<ArrayList<Integer>> objectData = new ArrayList<ArrayList<Integer>>();
    public ArrayList<Integer> renderedObject = new ArrayList<Integer>();
    private ArrayList<Integer> mapData = new ArrayList<Integer>();
    public ArrayList<TempVelocityThread> tempVelocityThreads = new ArrayList<TempVelocityThread>();

    private int[] cameraData = new int[4];

    private boolean inGame = true;
    public boolean playerTouchingGround = false;

    private RenderThread renderThread;
    private UPDPlayerThread updPlayerThread;
    private CameraThread cameraThread;

    private KeyboardListener keyboardListener;

    public Map() {

        initMap();

        timePerFrame = 1000000000.0 / 60.0;
        timePerUpdate = 1000000000.0 / 120.0;
        timePerJump = 500000000;
    }

    public void initMap() {
        keyboardListener = new KeyboardListener(this);
        addKeyListener(keyboardListener);
        setBackground(Color.black);
        setFocusable(true);
        loadMap();
        cameraData[2] = mapData.get(2);
        cameraData[3] = mapData.get(3);
        setPreferredSize(new Dimension(cameraData[2], cameraData[3]));
        //setPreferredSize(new Dimension(1200, 300));
        initGame();
    }

    private void initGame() {
        renderThread = new RenderThread(this);
        //updPlayerThread = new UPDPlayerThread(this);
        //cameraThread = new CameraThread(this);
        renderThread.start();
        //updPlayerThread.start();
        //cameraThread.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        if (inGame) {
            // Color origin = g.getColor();
            // g.setColor(new Color(255, 255, 255, 127));
            // g.fillRect(cameraData[0], cameraData[1], cameraData[2], cameraData[3]);
            // g.setColor(origin);
            //renderObjectWithinWindow(g);
            //renderObject(g);
            callFPS();
            //Toolkit.getDefaultToolkit().sync();
        } else {

        }
        //repaint();
    }

    public void updatePlayer() {
        // boolean updated = false;
        // for (int i = 0; i != renderedObject.size(); i++) {
        //     if (playerIndex != renderedObject.get(i)) {
        //         if (objectData.get(playerIndex).get(1) + objectData.get(playerIndex).get(3) == objectData
        //                 .get(renderedObject.get(i)).get(1)) {
        //             for (int x = 0; x != objectData.get(playerIndex).get(0) + objectData.get(playerIndex).get(2); x++) {
        //                 if (x < objectData.get(renderedObject.get(i)).get(0)
        //                         + objectData.get(renderedObject.get(i)).get(2)
        //                         && x >= objectData.get(renderedObject.get(i)).get(0)) {
        //                     updated = true;
        //                     playerTouchingGround = true;
        //                     break;
        //                 }
        //             }
        //         }
        //         if (playerTouchingGround && updated)
        //             break;
        //     }
        //     if (!updated)
        //         playerTouchingGround = false;
        // }
        // if (!playerTouchingGround && objectData.get(playerIndex).get(5) <= 0 && gpLimitReached()) {
        //     objectData.get(playerIndex).set(5, 10);
        // } else if (playerTouchingGround && objectData.get(playerIndex).get(5) >= 0) {
        //     objectData.get(playerIndex).set(5, 0);
        // }
        playerTouchingGround = true;
    }

    public void moveCamera(String direction, int distance) {
        switch (direction.toLowerCase()) {
            case "forward":
                if (cameraData[0] + 10 + cameraData[2] < mapData.get(0))
                    cameraData[0] += distance;
                break;
            case "backward":
                if (cameraData[0] - 10 > -1)
                    cameraData[0] -= distance;
                break;
            case "track_player":
                if (cameraData[0] + (objectData.get(playerIndex).get(0) + (objectData.get(playerIndex).get(2) / 2))
                        - (cameraData[0] + (cameraData[2] / 2)) + cameraData[2] < mapData.get(0)
                        && cameraData[0]
                                + (objectData.get(playerIndex).get(0) + (objectData.get(playerIndex).get(2) / 2))
                                - (cameraData[0] + (cameraData[2] / 2)) > -1)
                    cameraData[0] += (objectData.get(playerIndex).get(0) + (objectData.get(playerIndex).get(2) / 2))
                            - (cameraData[0] + (cameraData[2] / 2));
                if (cameraData[1] + (objectData.get(playerIndex).get(1) + (objectData.get(playerIndex).get(3) / 2))
                        - (cameraData[1] + (cameraData[3] / 2)) + cameraData[3] < mapData.get(1)
                        && cameraData[1]
                                + (objectData.get(playerIndex).get(1) + (objectData.get(playerIndex).get(3) / 2))
                                - (cameraData[1] + (cameraData[3] / 2)) > -1)
                    cameraData[1] += (objectData.get(playerIndex).get(1) + (objectData.get(playerIndex).get(3) / 2))
                            - (cameraData[1] + (cameraData[3] / 2));
                break;
            default:
                if (cameraData[0] >= mapData.get(0))
                    cameraData[0] = mapData.get(0) - cameraData[2];
                if (cameraData[0] < 0)
                    cameraData[0] = 0;
                break;
        }
    }

    public void renderObject(Graphics g) {
        for (int i = 0; i != renderedObject.size(); i++) {
            int tempX, tempY, tempWidth, tempHeight;
            tempX = objectData.get(renderedObject.get(i)).get(0);
            tempY = objectData.get(renderedObject.get(i)).get(1);
            tempWidth = objectData.get(renderedObject.get(i)).get(2);
            tempHeight = objectData.get(renderedObject.get(i)).get(3);
            if (object.get(renderedObject.get(i)).equals("player.jpg"))
                 playerIndex = renderedObject.get(i);
            g.drawImage(new ImageIcon("src/Images/" + object.get(renderedObject.get(i))).getImage(), tempX - cameraData[0],
                    tempY - cameraData[1],
                    tempWidth, tempHeight, null);
        }
    }

    public void renderObjectWithinWindow(Graphics g) {
        int renderDeficit = 10;
        for (int i = 0; i != objectData.size(); i++) {
            boolean render = false;
            int tempX, tempY, tempWidth, tempHeight;
            tempX = objectData.get(i).get(0);
            tempY = objectData.get(i).get(1);
            tempWidth = objectData.get(i).get(2);
            tempHeight = objectData.get(i).get(3);
            for (int a = tempX; a < tempX + tempWidth; a++) {
                for (int b = tempY; b < tempY + tempHeight; b++) {
                    if (a < cameraData[0] + cameraData[2] + renderDeficit && a >= cameraData[0] - renderDeficit
                            && b < cameraData[1] + renderDeficit + cameraData[3]
                            && b >= cameraData[1] - renderDeficit) {
                        render = true;
                        if (!renderedObject.contains(i)) {
                            renderedObject.add(i);
                            tempVelocityThreads.add(new TempVelocityThread(this, i));
                            tempVelocityThreads.get(tempVelocityThreads.size() - 1).start();
                            g.drawImage(new ImageIcon("src/Images/" + object.get(i)).getImage(), tempX - cameraData[0], tempY - cameraData[1],
                                    tempWidth, tempHeight, null);
                        }
                    }
                }
            }
            if (renderedObject.contains(i) && !render) {
                tempVelocityThreads.get(renderedObject.indexOf(i)).stop2();
                tempVelocityThreads.remove(renderedObject.indexOf(i));
                renderedObject.remove(renderedObject.indexOf(i));
            }
        }
    }

    private void loadMap() {
        String data = new Generator().readMapData(0);
        String currentData = "";
        boolean pastMapDataParam = false;
        int TTRX = 1;
        int TTRY = 1;
        for (int i = 0; i < data.length(); i++) {
            if (data.substring(i, i + 1).equals(",")) {
                if (pastMapDataParam) {
                    i += 2;
                    if (currentData.toUpperCase().equals(currentData)) {
                        if (objectData.size() == 0)
                            objectData.add(new ArrayList<Integer>());
                        objectData.get(objectData.size() - 1).add(strToNum(currentData));
                        currentData = "";
                    } else {
                        if (currentData.substring(0, 1).equals("x"))
                            TTRX = strToNum(currentData.substring(1, currentData.length()));
                        if (currentData.substring(0, 1).equals("y"))
                            TTRY = strToNum(currentData.substring(1, currentData.length()));
                        currentData = "";
                    }
                } else {
                    i += 2;
                    if (currentData.toLowerCase().compareTo(currentData) != 0) {

                    } else {
                        mapData.add(strToNum(currentData));
                    }
                    currentData = "";
                }
            }
            if (i + 2 <= data.length() && data.substring(i, i + 2).equals("||")) {
                i += 2;
                pastMapDataParam = true;
                mapData.add(strToNum(currentData));
                currentData = "";
            } else if (data.substring(i, i + 1).equals("|")) {
                object.add(currentData);
                currentData = "";
                if (TTRX > 1 && TTRY == 1) {
                    int origin = objectData.size() - 1;
                    for (int a = 1; a != TTRX + 1; a++) {
                        objectData.add(new ArrayList<Integer>());
                        object.add(object.get(origin));
                        objectData.get(objectData.size() - 1).add(objectData.get(origin).get(2) * a);
                        objectData.get(objectData.size() - 1).add(objectData.get(origin).get(1));
                        objectData.get(objectData.size() - 1).add(objectData.get(origin).get(2));
                        objectData.get(objectData.size() - 1).add(objectData.get(origin).get(3));
                        objectData.get(objectData.size() - 1).add(objectData.get(origin).get(4));
                        objectData.get(objectData.size() - 1).add(objectData.get(origin).get(5));
                    }
                }
                if (TTRY > 1 && TTRX == 1) {
                    int origin = objectData.size() - 1;
                    for (int a = 1; a != TTRY + 1; a++) {
                        objectData.add(new ArrayList<Integer>());
                        object.add(object.get(origin));
                        objectData.get(objectData.size() - 1).add(objectData.get(origin).get(0));
                        objectData.get(objectData.size() - 1).add(objectData.get(origin).get(3) * a);
                        objectData.get(objectData.size() - 1).add(objectData.get(origin).get(2));
                        objectData.get(objectData.size() - 1).add(objectData.get(origin).get(3));
                        objectData.get(objectData.size() - 1).add(objectData.get(origin).get(4));
                        objectData.get(objectData.size() - 1).add(objectData.get(origin).get(5));
                    }
                }
                if (TTRX > 1 && TTRY > 1) {
                    int origin = objectData.size() - 1;
                    int x = 1;
                    int y = 1;
                    while (true) {
                        objectData.add(new ArrayList<Integer>());
                        object.add(object.get(origin));
                        objectData.get(objectData.size() - 1).add(objectData.get(origin).get(2) * x);
                        objectData.get(objectData.size() - 1).add(objectData.get(origin).get(3) * y);
                        objectData.get(objectData.size() - 1).add(objectData.get(origin).get(2));
                        objectData.get(objectData.size() - 1).add(objectData.get(origin).get(3));
                        objectData.get(objectData.size() - 1).add(objectData.get(origin).get(4));
                        objectData.get(objectData.size() - 1).add(objectData.get(origin).get(5));
                        if (x < TTRX + 1)
                            x++;
                        if (y < TTRY + 1)
                            y++;
                        if (x == TTRX + 1 && y == TTRY + 1)
                            break;
                    }
                }
                TTRY = 1;
                TTRX = 1;
                if (i + 1 < data.length()) {
                    objectData.add(new ArrayList<Integer>());
                    i++;
                }
            }
            if (i + 1 >= data.length())
                break;
            currentData += data.substring(i, i + 1);
        }
    }

    private int strToNum(String str) {
        int x = -1;
        while (!(x + "").equals(str) && x < 10000) {
            x++;
        }
        return x;
    }

    public void callFPS() {
        frames++;
        if (System.currentTimeMillis() - lastTime >= 1000) {
            System.out.println("FPS: " + frames);
            frames = 0;
            lastTime = System.currentTimeMillis();
        }
    }

    public boolean fpsLimitReached() {
        if (System.nanoTime() - lastFrame >= timePerFrame)
            lastFrame = System.nanoTime();
        return System.nanoTime() - lastFrame >= timePerFrame;
    }

    public boolean upsLimitReached() {
        if (System.nanoTime() - lastUpdate >= timePerUpdate)
            lastUpdate = System.nanoTime();
        return System.nanoTime() - lastUpdate >= timePerUpdate;

    }

    public boolean gpLimitReached() {
        return System.nanoTime() - lastJump >= timePerJump;
    }

    public void setXVelocity(int xVelocity) {
        objectData.get(playerIndex).set(4, xVelocity);
    }

    public void setYVelocity(int yVelocity) {
        objectData.get(playerIndex).set(5, yVelocity);
    }

    public String Conflict(int x, int y, int width, int height) {
        for (int a = 0; a != renderedObject.size(); a++) {

        }
        return "-1";
    }

    public void gravityCheck() {

    }

    public void updateVelocity(int i) {
        int xVelocity = objectData.get(i).get(4);
        int yVelocity = objectData.get(i).get(5);
        if (xVelocity != 0 || yVelocity != 0) {
            System.out.println("updating velocity");
            double ROD = .50;
            double PPV = 2.5;
            double PTMP50M = .20;
            int newX = objectData.get(i).get(0);
            int newY = objectData.get(i).get(1);
            int width = objectData.get(i).get(2);
            int height = objectData.get(i).get(3);
            int PTMX = (int) (PPV * (double) xVelocity);
            int PTMY = (int) (PPV * (double) yVelocity);
            int x = (int) (PTMX * PTMP50M);
            int y = (int) (PTMY * PTMP50M);
            int PTMMX = 0;
            int PTMMY = 0;
            xVelocity *= 1 - ROD;
            yVelocity *= 1 - ROD;
            objectData.get(i).set(4, xVelocity);
            objectData.get(i).set(5, yVelocity);
            while (true) {
                if (!(Math.abs(PTMMX) > Math.abs(PTMX)))
                    newX += x;
                if (!(Math.abs(PTMMY) > Math.abs(PTMY)))
                    newY += y;
                if (futureConflict(i, newX, newY, width, height) == -1) {
                    System.out.println("moving|(" + objectData.get(i).get(0) + ", " + objectData.get(i).get(1) + ") ("
                            + newX + ", " + newY + ") Velocity x: " + xVelocity + " Velocity y: " + yVelocity);
                    objectData.get(i).set(0, newX);
                    objectData.get(i).set(1, newY);
                    moveCamera("track_player", 0);
                    System.out.println("done moving");
                } else if (xVelocity * (1 - ROD) == 0) {
                    System.out.println("connecting");
                    connectObjects(i, futureConflict(i, newX, newY, width, height));
                    System.out.println("done connecting");
                    break;
                } else if (futureConflict(i, newX, newY, width, height) != -1 && xVelocity * (1 - ROD) > 0) {
                    System.out.println("waiting to connect");
                    break;
                }
                objectData.get(i).set(2, width);
                objectData.get(i).set(3, height);
                PTMMX += (int) (PTMX * PTMP50M);
                PTMMY += (int) (PTMY * PTMP50M);
                if ((Math.abs(PTMMX) > Math.abs(PTMX) || x == 0) && (Math.abs(PTMMY) > Math.abs(PTMY) || y == 0))
                    break;
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException E) {

                }
            }
            System.out.println("done updating velocity");
        }
    }

    public void connectObjects(int object1, int object2) {
        boolean xIntercept = false;
        int x1 = objectData.get(object1).get(0), y1 = objectData.get(object1).get(1),
                width1 = objectData.get(object1).get(2), height1 = objectData.get(object1).get(3);
        int x2 = objectData.get(object2).get(0), y2 = objectData.get(object2).get(1),
                width2 = objectData.get(object2).get(2), height2 = objectData.get(object2).get(3);
        for (int x = x1; x < x1 + width1; x++) {
            if (x1 < x2 + width2 && x1 >= x2)
                xIntercept = true;
        }
        boolean yIntercept = false;
        for (int y = y1; y < y1 + height1; y++) {
            if (y1 < y2 + height2 && y1 >= y2)
                yIntercept = true;
        }
        if (!xIntercept) {
            if (x1 < x2)
                x1 += x2 - (x1 + width1);
            else
                x1 -= x1 - (x2 + width2);
        }
        if (!yIntercept) {
            if (y1 < y2)
                y1 += y2 - (y1 + height1);
            else
                y1 -= y1 - (y2 + height2);
        }
        objectData.get(object1).set(0, x1);
        objectData.get(object1).set(1, y1);
    }

    public int futureConflict(int currentObject, int x, int y, int width, int height) {
        System.out.println("checking conflict");
        for (int i = 0; i < objectData.size(); i++) {
            if (i != currentObject) {
                int newX = objectData.get(i).get(0);
                int newY = objectData.get(i).get(1);
                int newWidth = objectData.get(i).get(2);
                int newHeight = objectData.get(i).get(3);
                boolean xIntercept = false;
                for (int x1 = x; x1 < x + width; x1++) {
                    if (x1 < newX + newWidth && x1 >= newX)
                        xIntercept = true;
                }
                boolean yIntercept = false;
                if (xIntercept) {
                    for (int y1 = y; y1 < y + height; y1++) {
                        if (y1 < newY + newHeight && y1 >= newY)
                            yIntercept = true;
                    }
                }
                if (xIntercept && yIntercept) {
                    for (int x1 = x; x1 < x + width; x1++) {
                        for (int y1 = y; y1 < y + height; y1++) {
                            for (int x2 = newX; x2 < newX + newWidth; x2++) {
                                for (int y2 = newY; y2 < newY + newHeight; y2++) {
                                    if (y2 == y1) {
                                        if (x2 == x1 && y2 == y1) {
                                            System.out.println("conflict");
                                            return i;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
        System.out.println("No conflict");
        return -1;
    }
}
