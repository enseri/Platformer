package Game;

import Inputs.KeyboardListener;
import Inputs.MyMouseListener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JPanel;
import java.util.ArrayList;
import Objects.Object;
import java.util.Scanner;

public class GameScreen extends JPanel {
    public Camera camera;
    ArrayList<Object> renderedObject = new ArrayList<>();
    ArrayList<Integer> mapData = new ArrayList<>();
    public ArrayList<Object> objects = new ArrayList<>();

    private Render render;

    GameScreen() {

        init();


    }

    private void init() {
        KeyboardListener keyboardListener = new KeyboardListener(this);
        MyMouseListener myMouseListener = new MyMouseListener();
        addKeyListener(keyboardListener);
        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
        setBackground(Color.black);
        setFocusable(true);
        String map = new Scanner(System.in).nextLine();
        new Generator(this).loadMap(map);
        camera = new Camera(0, 0, mapData.get(2), mapData.get(3), this);
        setPreferredSize(new Dimension(camera.getData()[2], camera.getData()[3]));
        initGame();
    }

    private void initGame() {
        render = new Render(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        render.render(g);
        Toolkit.getDefaultToolkit().sync();
    }

    public int findPlayer() {
        for(int i = 0; i < objects.size(); i++) {
            if(objects.get(i).getImage().equals("player.jpg"))
                return i;
        }
        return -1;
    }


    public void connectObjects(Object object1, Object object2) {
        int[] data1 = object1.getData();
        int[] data2 = object2.getData();
        boolean xIntercept = false;
        int x1 = data1[0], y1 = data1[1], width1 = data1[2], height1 = data1[3];
        int x2 = data2[0], y2 = data2[1], width2 = data2[2], height2 = data2[3];
        for (int x = x1; x < x1 + width1; x++) {
            if (x1 < x2 + width2 && x1 >= x2) {
                xIntercept = true;
                break;
            }
        }
        boolean yIntercept = false;
        for (int y = y1; y < y1 + height1; y++) {
            if (y1 < y2 + height2 && y1 >= y2) {
                yIntercept = true;
                break;
            }
        }
        if (!xIntercept) {
            if (x1 < x2)
                x1 += x2 - (x1 + width1);
            else
                x1 -= x1 - (x2 + width2);
            object1.setXVelocity(0);
        }
        if (!yIntercept) {
            if (y1 < y2)
                y1 += y2 - (y1 + height1);
            else
                y1 -= y1 - (y2 + height2);
            object1.setYVelocity(0);
        }
        object1.setX(x1);
        object1.setY(y1);
    }

    public Object futureConflict(Object currentObject, int x, int y, int width, int height) {
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
