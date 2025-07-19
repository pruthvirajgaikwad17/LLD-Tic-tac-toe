package Strategy;

import Models.Game;

public interface WinningStrategy {
    boolean isWinning(Game game);
}
