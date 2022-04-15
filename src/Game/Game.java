package Game;

import javax.print.DocFlavor.READER;
//import java.awt.EventQueue;
import javax.swing.JFrame;

public class Game extends JFrame {
    public Game() {
        initUI();
    }

    private int updates;
    private int frames;
    long lastCall;
    long lastTime;
    long lastFrame;
    long lastUpd;
    double timePerFrame = 1000000000.0 / 60.0;
    double timePerUpd = 1000000000.0 / 30.0;
    private Thread gameThread;

    Map map;

    private void initUI() {
        map = new Map();
        add(map);
        setResizable(false);
        pack();
        setTitle("Platformer");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    void callFPS() {
        frames++;
        if (System.currentTimeMillis() - lastTime >= 1000) {
            System.out.println("FPS: " + frames);
            frames = 0;
            lastTime = System.currentTimeMillis();
        }
    }

    void callUPS() {
        updates++;
        if (System.currentTimeMillis() - lastCall >= 1000) {
            System.out.println("UPS: " + updates);
            updates = 0;
            lastCall = System.currentTimeMillis();
        }
    }

    public static void main(String[] args) {
        // EventQueue.invokeLater(() -> {
        // JFrame ex = new Game();
        // ex.setVisible(true);
        // });
        Game game = new Game();
        RenderThread renderThread = new RenderThread(game);
        UpdateThread updateThread = new UpdateThread(game);
        renderThread.start();
        updateThread.start();
    }

    void updateGame() {
        map.updatePlayer();
        map.updateVelocity();
        lastUpd = System.nanoTime();
    }

    void renderGame() {
        map.repaint();
        lastFrame = System.nanoTime();
    }
}

class RenderThread extends Thread {
    Game game;

    public RenderThread(Game game) {
        this.game = game;
    }

    public void run() {
        while (true) {
            if (System.nanoTime() - game.lastFrame >= game.timePerFrame) {
                game.callFPS();
                game.renderGame();
            }
        }
    }
}

class UpdateThread extends Thread {
    Game game;

    public UpdateThread(Game game) {
        this.game = game;
    }

    public void run() {
        while (true) {
            //if (System.nanoTime() - game.lastUpd >= game.timePerUpd) {
                //game.callUPS();
                game.updateGame();
           // }
        }
    }
}
