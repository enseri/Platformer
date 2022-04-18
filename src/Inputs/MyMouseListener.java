package Inputs;

import java.awt.event.*;

import States.GameStates;
public class MyMouseListener implements MouseListener, MouseMotionListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        GameStates.setGameState("PLAYING");
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
