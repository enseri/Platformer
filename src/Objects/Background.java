package Objects;

import Game.GameScreen;

public class Background extends Object {
    private boolean collision;
    private int x;
    private int y;
    private int width;
    private int height;
    private String image;

    public Background(boolean collision, int x, int y, int width, int height, String image) {
        this.collision = collision;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
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

    @Override
    public String getText() {
        // TODO Auto-generated method stub
        return null;
    }
}

