package Game;

import Objects.Object;

import javax.swing.JFrame;

public class Game extends JFrame {
    private Game() {
        initUI();
    }

    private int frames;
    private long lastFPS;
    long lastFrame;
    double timePerFrame = 1000000000.0 / 120.0;

    GameScreen gameScreen;

    private void initUI() {
        gameScreen = new GameScreen();
        add(gameScreen);
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

    public static void main(String[] args) {
        Game game = new Game();
        RenderThread renderThread = new RenderThread(game);
        UpdateThread updateThread = new UpdateThread(game);
        renderThread.start();
        updateThread.start();
    }

    void renderGame() {
        gameScreen.repaint();
        lastFrame = System.nanoTime();
    }
}

class RenderThread extends Thread {
    private Game game;

    RenderThread(Game game) {
        this.game = game;
    }

    public void run() {
        boolean running = true;
        while (running) {
            if (System.nanoTime() - game.lastFrame >= game.timePerFrame) {
                if(game.lastFrame == 1)
                    running = false;
                game.callFPS();
                game.renderGame();
            }
        }
    }
}

class UpdateThread extends Thread {
    private Game game;
    UpdateThread(Game game) {this.game = game;}

    public void run() {
        for(int i = 0; i < game.gameScreen.objects.size(); i++) {
            new TempUpdateThread(game.gameScreen.objects.get(i), game).start();
        }
    }
}

class TempUpdateThread extends Thread {
    private Object object;
    private Game game;
    private long lastUPS;
    private long lastUpdate;

    TempUpdateThread(Object object, Game game) {
        this.object = object;
        this.game = game;
    }

    public void run() {
        boolean running = true;
        if(!object.shiftAble())
            running = false;
        while(running) {
            if (System.nanoTime() - lastUpdate >= 1000000000.0 / 60) {
                if(lastUpdate == 1)
                    running = false;
                callUPS();
                object.update();
                object.velocityShift(game.gameScreen);
                lastUpdate = System.nanoTime();
            }
        }
    }

    private void callUPS() {
        if (System.currentTimeMillis() - lastUPS >= 1000) {
            lastUPS = System.currentTimeMillis();
        }
    }
}
