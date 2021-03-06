package Objects;

import java.util.ArrayList;

import Game.GameScreen;

public class Button extends Object {
    private int x;
    private int y;
    private int width;
    private int height;
    private String image;
    private String text;

    public Button(boolean bar, int x, int y, int width, int height, String image, String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
        this.text = text;
    }

    public void velocityShift(GameScreen gameScreen) {

    }

    public void update() {

    }

    @Override
    public boolean shiftAble() {
        return false;
    }

    public int[] getData(){
        return new int[]{x, y, width, height};
    }

    @Override
    public void specialFunction(String event) {

    }

    @Override
    public boolean getCollision() {
        return false;
    }

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void setXVelocity(int xVelocity) {
    }

    @Override
    public void setYVelocity(int yVelocity) {
    }

    @Override
    public void jump() {

    }

    public String getText() {
        return text;
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
    public ArrayList<Button> getDropDown() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
        
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
        
    }
    public Object copy() {
        return new Button(false, x, y, width, height, image, text);
    }

}

