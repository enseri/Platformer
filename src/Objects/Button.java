package Objects;

import Game.GameScreen;

public class Button extends Object {
    private boolean collision;
    private int x;
    private int y;
    private int width;
    private int height;
    private String image;
    private String text;

    public Button(boolean collision, int x, int y, int width, int height, String image, String text) {
        this.collision = collision;
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
    public boolean getCollision() {
        return collision;
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
}

