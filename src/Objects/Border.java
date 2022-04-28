package Objects;

import java.util.ArrayList;

import Game.GameScreen;

public class Border extends Object{
    boolean collision;
    int x, y, width, height;
    String image;
    public Border(boolean collision, int x, int y, int width, int height, String image) {
        this.collision = collision;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
    }

    @Override
    public void velocityShift(GameScreen gameScreen) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int[] getData() {
        // TODO Auto-generated method stub
        return new int[]{x, y, width, height};
    }

    @Override
    public void specialFunction(String event) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean getCollision() {
        // TODO Auto-generated method stub
        return collision;
    }

    @Override
    public String getImage() {
        // TODO Auto-generated method stub
        return image;
    }

    @Override
    public void setX(int x) {
        // TODO Auto-generated method stub
        this.x = x;
    }

    @Override
    public void setY(int y) {
        // TODO Auto-generated method stub
        this.y = y;
    }

    @Override
    public void setWidth(int width) {
        // TODO Auto-generated method stub
        this.width = width;
    }

    @Override
    public void setHeight(int height) {
        // TODO Auto-generated method stub
        this.height = height;
    }

    @Override
    public void setXVelocity(int xVelocity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setYVelocity(int yVelocity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void jump() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean shiftAble() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String getText() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<Button> getDropDown() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean getDropped() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void toggleDropped() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Object copy() {
        // TODO Auto-generated method stub
        return new Border(collision, x, y, width, height, image);
    }
    
}
