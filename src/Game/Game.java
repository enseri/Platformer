package Game;

import javax.print.DocFlavor.READER;
//import java.awt.EventQueue;
import javax.swing.JFrame;

public class Game extends JFrame {
    public Game() {
        initUI();
    }

    private int velUpdates;
    private int frames;
    private int plyUpdates;
    long lastFPS;
    long lastUVPS;
    long lastUPPS;
    long lastFrame;
    long lastVelUpd;
    long lastPlyUpd;
    double timePerFrame = 1000000000.0 / 60.0;
    double timePerVelUpd = 1000000000.0 / 30.0;
    double timePerPlyUpd = 1000000000.0 / 30.0;
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
        if (System.currentTimeMillis() - lastFPS >= 1000) {
            System.out.println("FPS: " + frames);
            frames = 0;
            lastFPS = System.currentTimeMillis();
        }
    }

    void callVelUPS() {
        velUpdates++;
        if (System.currentTimeMillis() - lastUVPS >= 1000) {
            System.out.println("UVPS: " + velUpdates);
            velUpdates = 0;
            lastUVPS = System.currentTimeMillis();
        }
    }

    void callPlyUPS() {
        plyUpdates++;
        if(System.currentTimeMillis() - lastUPPS >= 1000) {
            System.out.println("UPPS: " + plyUpdates);
            plyUpdates = 0;
            lastUPPS = System.currentTimeMillis();
        }
    }

    public static void main(String[] args) {
        // EventQueue.invokeLater(() -> {
        // JFrame ex = new Game();
        // ex.setVisible(true);
        // });
        Game game = new Game();
        RenderThread renderThread = new RenderThread(game);
        UpdateVelThread updateVelThread = new UpdateVelThread(game);
        UpdatePlyThread updatePlyThread = new UpdatePlyThread(game);
        renderThread.start();
        updateVelThread.start();
        updatePlyThread.start();
    }

    void updateVelGame() {
        map.updateVelocity();
        lastUVPS = System.nanoTime();
    }

    void updatePlyGame() {
        map.updatePlayer();
        lastUPPS = System.nanoTime();
    }

    void renderGame() {
        map.repaint();
        lastFrame = System.nanoTime();
    }

    public Map getMap() {
        return map;
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

class UpdatePlyThread extends Thread {
    Game game;

    public UpdatePlyThread(Game game) {
        this.game = game;
    }

    public void run() {
        while (true) {
            if (System.nanoTime() - game.lastPlyUpd >= game.timePerPlyUpd) {
                game.callPlyUPS();
                if(game.getMap().renderFinished)
                game.updatePlyGame();
            }
        }
    }
}

class UpdateVelThread extends Thread {
    Game game;
    public UpdateVelThread(Game game) {this.game = game;}

    public void run() {
        while(true) {
            if(System.nanoTime() - game.lastVelUpd >= game.timePerVelUpd) {
                game.callVelUPS();
                if(game.getMap().renderFinished)
                    game.updateVelGame();
            }
        }
    }
}
