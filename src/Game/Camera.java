package Game;

public class Camera {
    private int x, y, width, height;
    private GameScreen gameScreen;
    public Camera(int x, int y, int width, int height, GameScreen gameScreen) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.gameScreen = gameScreen;
    }

    public void moveCamera(String direction, int distance) {
        switch (direction.toLowerCase()) {
            case "forward":
                if (x + distance + width < gameScreen.mapData.get(0))
                    x += distance;
                break;
            case "backward":
                if (x - distance > -1)
                    x -= distance;
                break;
            case "scroll":
                y += distance;
            case "track_player":
                if (x + (gameScreen.objects.get(gameScreen.findPlayer()).getData()[0] + (gameScreen.objects.get(gameScreen.findPlayer()).getData()[2] / 2))
                        - (x + (width / 2)) + width < gameScreen.mapData.get(0)
                        && x
                        + (gameScreen.objects.get(gameScreen.findPlayer()).getData()[0] + (gameScreen.objects.get(gameScreen.findPlayer()).getData()[2] / 2))
                        - (x + (width / 2)) > -1)
                    x += (gameScreen.objects.get(gameScreen.findPlayer()).getData()[0] + (gameScreen.objects.get(gameScreen.findPlayer()).getData()[2] / 2))
                            - (x + (width / 2));
                if (y + (gameScreen.objects.get(gameScreen.findPlayer()).getData()[1] + (gameScreen.objects.get(gameScreen.findPlayer()).getData()[3] / 2))
                        - (y + (height / 2)) + height < gameScreen.mapData.get(1)
                        && y
                        + (gameScreen.objects.get(gameScreen.findPlayer()).getData()[1] + (gameScreen.objects.get(gameScreen.findPlayer()).getData()[3] / 2))
                        - (y + (height / 2)) > -1)
                    y += (gameScreen.objects.get(gameScreen.findPlayer()).getData()[1] + (gameScreen.objects.get(gameScreen.findPlayer()).getData()[3] / 2))
                            - (y + (height / 2));
                break;
            default:
                if (x >= gameScreen.mapData.get(0))
                    x = gameScreen.mapData.get(0) - width;
                if (x < 0)
                    x = 0;
                break;
        }
    }

    public void reset() {
        x = 0;
        y = 0;
    }

    public int[] getData() {
        return new int[]{x, y, width, height};
    }
}
