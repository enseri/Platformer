package Game;

import Objects.Object;
import States.GameStates;

import javax.swing.JFrame;

public class Game extends JFrame {
    private Game() {
        initUI();
    }

    private int frames;
    private long lastFPS;
    long lastFrame;
    double f = 120, u = 60;

    GameScreen gameScreen;

    private void initUI() {
        gameScreen = new GameScreen(this);
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

    public void setFPS(int newFPS) {
        f = newFPS;
        u = newFPS / 2;
    }

    public double getFPS() {
        return f;
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
            if (System.nanoTime() - game.lastFrame >= 1000000000 / game.f) {
                if (game.lastFrame == 1)
                    running = false;
                game.callFPS();
                game.renderGame();
            }
        }
    }
}

class UpdateThread extends Thread {
    private Game game;

    UpdateThread(Game game) {
        this.game = game;
    }

    public void run() {
        while(!GameStates.getGameState().equals("PLAYING")) {
            System.out.print("");
        }
        for (int i = 0; i < game.gameScreen.objects.size(); i++) {
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
        if (!object.shiftAble())
            running = false;
        while (running) {
            if (GameStates.getGameState().equals("PLAYING")) {
                if (System.nanoTime() - lastUpdate >= 1000000000.0 / game.u) {
                    if (lastUpdate == 1)
                        running = false;
                    callUPS();
                    object.update();
                    object.velocityShift(game.gameScreen);
                    lastUpdate = System.nanoTime();
                }
            } else {
                while(!GameStates.getGameState().equals("PLAYING")) {
                    System.out.print("");
                }
                new UpdateThread(game).start();
                break;
            }
        }
    }

    private void callUPS() {
        if (System.currentTimeMillis() - lastUPS >= 1000) {
            lastUPS = System.currentTimeMillis();
        }
    }
}
