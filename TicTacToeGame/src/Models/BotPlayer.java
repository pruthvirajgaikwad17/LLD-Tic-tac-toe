package Models;

import Strategy.BotPlayingStrategy;
import Strategy.BotPlayingStrategyFactory;

public class BotPlayer extends Player {

    BotDifficultyLevel difficultyLevel;
    BotPlayingStrategy botPlayingStrategy;

    public BotPlayer(String name, Character symbol, int id, BotDifficultyLevel difficultyLevel) {
        super(name, symbol, id);
        this.difficultyLevel = difficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy(this.difficultyLevel);
    }

    @Override
    public Cell makeMove(Board board, Player player) {
        // Logic for bot to make a move
        // This could be a random move or a more sophisticated AI logic
        return botPlayingStrategy.suggestMove(player, board);
    }
}
