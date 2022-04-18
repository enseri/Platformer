package Inputs;

import Game.GameScreen;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
class RegisterThread extends Thread{
    private GameScreen gameScreen;
    private int key;
    private boolean running = true;
    private boolean firstPress = true;
    RegisterThread(GameScreen gameScreen, int key){
        this.gameScreen = gameScreen;
        this.key = key;
    }

    public void run() {
        while(running) {
            System.out.print("");
            if (key == 37)
                gameScreen.objects.get(gameScreen.findPlayer()).setXVelocity(-10);
            if (key == 38) {
                gameScreen.objects.get(gameScreen.findPlayer()).jump();
            }
            if (key == 39) {
                gameScreen.objects.get(gameScreen.findPlayer()).setXVelocity(10);
            }
            if (key == 40)
                gameScreen.objects.get(gameScreen.findPlayer()).setYVelocity(10);
            if (key == 61)
                gameScreen.camera.moveCamera("track_player", 0);
            if (key == 91)
                gameScreen.camera.moveCamera("backward", 10);
            if (key == 93)
                gameScreen.camera.moveCamera("forward", 10);
            if(firstPress) {
                try{
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
        if(!keys.contains(arg0.getKeyCode())) {
            threads.add(new RegisterThread(gameScreen, arg0.getKeyCode()));
            threads.get(threads.size() - 1).start();
            keys.add(arg0.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        threads.get(keys.indexOf(arg0.getKeyCode())).stop2();
        threads.remove(keys.indexOf(arg0.getKeyCode()));
        keys.remove((Integer) arg0.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent arg0) {

    }

}
