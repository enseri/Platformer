package Objects;

import java.util.ArrayList;

import Game.GameScreen;

public abstract class Object {
    public abstract void velocityShift(GameScreen gameScreen);
    public abstract int[] getData();
    public abstract void specialFunction(String event);
    public abstract boolean getCollision();
    public abstract String getImage();
    public abstract void setX(int x);
    public abstract void setY(int y);
    public abstract void setXVelocity(int xVelocity);
    public abstract void setYVelocity(int yVelocity);
    public abstract void jump();
    public abstract void update();
    public abstract boolean shiftAble();
    public abstract String getText();
    public abstract ArrayList<Button> getDropDown();
    public abstract boolean getDropped();
    public abstract void toggleDropped();
}
