package States;
import java.util.ArrayList;

import Objects.Object;
public abstract class GameState {
    abstract void run();
    abstract GameState getGameState();
    abstract void clear();
}
