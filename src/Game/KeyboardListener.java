package Game;

import java.awt.event.*;
class RegisterThread extends Thread{
    Map map;
    KeyEvent arg0;
    public RegisterThread(Map map, KeyEvent arg0){
        this.map = map;
        this.arg0 = arg0;
    }

    public void run() {
        int key = arg0.getKeyCode();
        if (key == 37)
            map.setXVelocity(-10);
        if (key == 38 && map.playerTouchingGround) {
            map.setYVelocity(-10);
            map.lastJump = System.nanoTime();
        }
        
        else if(key == 38 && !map.playerTouchingGround) {
            System.out.println("not touching ground");
        }
        
        if (key == 39)
            map.setXVelocity(10);
        if (key == 40)
            map.setYVelocity(10);
        if (key == 61)
            map.moveCamera("track_player", 0);
        if (key == 91)
            map.moveCamera("backward", 10);
        if (key == 93)
            map.moveCamera("forward", 10);
    }
}

public class KeyboardListener implements KeyListener {
    Map map;

    public KeyboardListener(Map map) {
        this.map = map;
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
        new RegisterThread(map, arg0).start();
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }

}
