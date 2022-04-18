package States;

public abstract class GameState {
    abstract void run();
    abstract GameState getGameState();
    abstract void clear();
}
