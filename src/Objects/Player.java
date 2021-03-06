package Objects;

import Game.GameScreen;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Player extends Object {
    private boolean collision;
    private int x, y, width, height, xVelocity, yVelocity;
    private String image;
    private long lastJump;
    private GameScreen gameScreen;
    private boolean touchingGround;

    public Player(boolean collision, int x, int y, int width, int height, int xVelocity, int yVelocity, String image, GameScreen gameScreen) {
        this.collision = collision;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.image = image;
        this.gameScreen = gameScreen;
    }

    public void velocityShift(GameScreen gameScreen) {
        if (xVelocity != 0 || yVelocity != 0) {
            double ROD = .50;
            double PPV = ((height + width) / 6) / 10;
            if(PPV < .5)
                PPV = .5;
            double PTMPM = .20;
            int newX = x;
            int newY = y;
            int PTMX = (int) (PPV * (double) xVelocity);
            int PTMY = (int) (PPV * (double) yVelocity);
            int moveX = (int) (PTMX * PTMPM);
            int moveY = (int) (PTMY * PTMPM);
            int PTMMX = 0;
            int PTMMY = 0;
            xVelocity *= 1 - ROD;
            yVelocity *= 1 - ROD;
            while (true) {
                if (!(Math.abs(PTMMX) > Math.abs(PTMX)))
                    newX += moveX;
                if (!(Math.abs(PTMMY) > Math.abs(PTMY)))
                    newY += moveY;
                Object temp = gameScreen.futureConflict(this, newX, newY, width, height);
                if (temp == null) {
                    x = newX;
                    y = newY;
                    gameScreen.camera.moveCamera("track_player", 0);
                } else {
                    gameScreen.connectObjects(this, temp);
                    temp.specialFunction("collision");
                    break;
                }
                PTMMX += (int) (PTMX * PTMPM);
                PTMMY += (int) (PTMY * PTMPM);
                if ((Math.abs(PTMMX) > Math.abs(PTMX) || moveX == 0) && (Math.abs(PTMMY) > Math.abs(PTMY) || moveY == 0))
                    break;
                try {
                    TimeUnit.MILLISECONDS.sleep(25);
                } catch (InterruptedException ignored) {

                }
            }
        }
    }

    public void update() {
        boolean updated = false;
        for (int i = 0; i != gameScreen.objects.size(); i++) {
            if (gameScreen.objects.get(i).getCollision() && this != gameScreen.objects.get(i)) {
                if (y + height == gameScreen.objects.get(i).getData()[1]) {
                    for (int x1 = x; x1 != x + width; x1++) {
                        if (x1 < gameScreen.objects.get(i).getData()[0] + gameScreen.objects.get(i).getData()[2] && x1 >= gameScreen.objects.get(i).getData()[0]) {
                            updated = true;
                            touchingGround = true;
                            break;
                        }
                    }
                }
                if (updated)
                    break;
            }
        }
        if(!updated)
            touchingGround = false;
        if (!touchingGround && yVelocity < 10 &&
                gpLimitReached()) {
            yVelocity = 10;
        } else if (touchingGround && yVelocity >= 0) {
            yVelocity = 0;
        }
    }

    @Override
    public boolean shiftAble() {
        return true;
    }

    private boolean gpLimitReached() {
        long timePerJump = 500000000;
        return System.nanoTime() - lastJump >= timePerJump;
    }

    public int[] getData(){
        return new int[]{x, y, width, height};
    }

    @Override
    public void specialFunction(String event) {

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
        this.xVelocity = xVelocity;
    }

    @Override
    public void setYVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    @Override
    public void jump() {
        if(touchingGround) {
            lastJump = System.nanoTime();
            yVelocity = -20;
        }
    }

    @Override
    public String getText() {
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
        return new Player(collision, x, y, width, height, xVelocity, yVelocity, image, gameScreen);
    }

}

