package Strategy;

import Models.Cell;
import Models.CellState;
import Models.Game;
import Models.Player;

public class DiagonalWinningStrategy implements WinningStrategy {
    public boolean isWinning(Game game) {
        // Iterate Over all the rows and decide if the game ends
        return leftTopToRightBottomCheck(game) || rightTopToLeftBottomCheck(game);
    }

    public boolean leftTopToRightBottomCheck(Game game) {
        Player currPlayer = game.playerList.get(game.currPlayerIndex);

        Cell cell = game.moves.get(game.moves.size() - 1);

        for(int i = 0 ; i < game.board.getN(); i++) {
            Cell curr = game.board.getCells().get(i).get(i);
            if (curr.getCellState().equals(CellState.FREE) || !curr.getPlayer().equals(currPlayer)) {
                return false;
            }
        }
        return true;
    }

    public boolean rightTopToLeftBottomCheck(Game game) {
        Player currPlayer = game.playerList.get(game.currPlayerIndex);

        Cell cell = game.moves.get(game.moves.size() - 1);

        int N = game.board.getN();

        for(int i = 0 ; i < N; i++) {
            Cell curr = game.board.getCells().get(i).get(N - i - 1);
            if (curr.getCellState().equals(CellState.FREE) || !curr.getPlayer().equals(currPlayer)) {
                return false;
            }
        }
        return true;
    }
}
