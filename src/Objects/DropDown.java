package Objects;

import java.util.ArrayList;

import Game.GameScreen;

public class DropDown extends Object {
    private int x;
    private int y;
    private ArrayList<Button> objects = new ArrayList<>();
    private int width;
    private int height;
    private String image;
    private String text;
    private boolean dropped = false;

    public DropDown(ArrayList<Button> objects, int x, int y, int width, int height, String image, String text) {
        for(int i = 0; i != objects.size(); i++)
            this.objects.add(objects.get(i));
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

    public ArrayList<Button> getDropDown() {
        return objects;
    }

    public void toggleDropped() {
        if(dropped)
            dropped = false;
        else
            dropped = true;
    }

    public boolean getDropped() {
        return dropped;
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
        return new DropDown(objects, x, y, width, height, image, text);
    }
}

