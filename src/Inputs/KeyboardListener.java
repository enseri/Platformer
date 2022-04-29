package Inputs;

import Game.GameScreen;
import States.GameStates;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

class RegisterThread extends Thread {
    private GameScreen gameScreen;
    private int key;
    private boolean running = true;
    private boolean firstPress = true;

    RegisterThread(GameScreen gameScreen, int key) {
        this.gameScreen = gameScreen;
        this.key = key;
    }

    public void run() {
        assert GameStates.getGameState() != null;
        while (running && (GameStates.getGameState().equals("PLAYING") || GameStates.getGameState().equals("END")
                || GameStates.getGameState().equals("CREATING"))) {
            System.out.print("");
            if (key == 27) {
                gameScreen.setDefault();
                GameStates.setGameState("MENU");
            }
            if (key == 37 && GameStates.getGameState().equals("PLAYING"))
                gameScreen.objects.get(gameScreen.findPlayer()).setXVelocity(-10);
            if (key == 38 && GameStates.getGameState().equals("PLAYING")) {
                gameScreen.objects.get(gameScreen.findPlayer()).jump();
            }
            if (key == 39 && GameStates.getGameState().equals("PLAYING")) {
                gameScreen.objects.get(gameScreen.findPlayer()).setXVelocity(10);
            }
            if (key == 40 && GameStates.getGameState().equals("PLAYING"))
                gameScreen.objects.get(gameScreen.findPlayer()).setYVelocity(10);
            if (key == 37 && GameStates.getGameState().equals("CREATING")
                    && gameScreen.render.creating.editingMode == 3)
                gameScreen.render.creating.copySelectedObject("left");
            if (key == 38 && GameStates.getGameState().equals("CREATING")
                    && gameScreen.render.creating.editingMode == 3) {
                gameScreen.render.creating.copySelectedObject("up");
            }
            if (key == 39 && GameStates.getGameState().equals("CREATING")
                    && gameScreen.render.creating.editingMode == 3) {
                gameScreen.render.creating.copySelectedObject("right");
            }
            if (key == 40 && GameStates.getGameState().equals("CREATING")
                    && gameScreen.render.creating.editingMode == 3)
                gameScreen.render.creating.copySelectedObject("down");
            if (key == 61)
                System.out.println(gameScreen.objects.get(gameScreen.findPlayer()).getData()[0] + " "
                        + gameScreen.objects.get(gameScreen.findPlayer()).getData()[1]);
            if (key == 91)
                gameScreen.camera.moveCamera("backward", 10);
            if (key == 93)
                gameScreen.camera.moveCamera("forward", 10);
            if (key == 10 && gameScreen.render.creating.mode == 1)
                gameScreen.render.creating.nextMode();
            if (key == 49)
                gameScreen.render.creating.editingMode = 1;
            if (key == 50)
                gameScreen.render.creating.editingMode = 2;
            if (key == 51)
                gameScreen.render.creating.editingMode = 3;
            if (key == 52)
                gameScreen.render.creating.editingMode = 4;
            if (firstPress) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException ignore) {

                }
                firstPress = false;
            }
        }
    }

    void stop2() {
        running = false;
    }
}

public class KeyboardListener implements KeyListener {
    private GameScreen gameScreen;
    private ArrayList<RegisterThread> threads = new ArrayList<>();
    private ArrayList<Integer> keys = new ArrayList<>();

    public KeyboardListener(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
        if (!keys.contains(arg0.getKeyCode())) {
            threads.add(new RegisterThread(gameScreen, arg0.getKeyCode()));
            threads.get(threads.size() - 1).start();
            keys.add(arg0.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        if((!(arg0.getKeyChar() + "").toLowerCase().equals((arg0.getKeyChar() + "").toUpperCase()) || arg0.getKeyChar() == '-' || arg0.getKeyChar() == ' ') && gameScreen.render.creating.mode == 2) {
            String pressed = arg0.getKeyChar() + "";
            if(keys.contains(16))
            pressed.toUpperCase();
            gameScreen.render.creating.mapName += pressed;
        }
        else if(arg0.getKeyCode() == 8 && gameScreen.render.creating.mode == 2 && gameScreen.render.creating.mapName.length() > 0)
            gameScreen.render.creating.mapName = gameScreen.render.creating.mapName.substring(0, gameScreen.render.creating.mapName.length() - 1);
        threads.get(keys.indexOf(arg0.getKeyCode())).stop2();
        threads.remove(keys.indexOf(arg0.getKeyCode()));
        keys.remove((Integer) arg0.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent arg0) {

    }

}
