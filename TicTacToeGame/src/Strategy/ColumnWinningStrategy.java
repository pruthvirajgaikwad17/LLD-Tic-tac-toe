package Strategy;

import Models.Cell;
import Models.CellState;
import Models.Game;
import Models.Player;

public class ColumnWinningStrategy implements WinningStrategy{
    public boolean isWinning(Game game) {
        // Iterate Over all the rows and decide if the game ends
        Player currPlayer = game.playerList.get(game.currPlayerIndex);

        Cell cell = game.moves.get(game.moves.size() - 1);

        int col = cell.getCol();

        for(int i = 0 ; i < game.board.getN(); i++) {
            Cell curr = game.board.getCells().get(i).get(col);
            if (curr.getCellState().equals(CellState.FREE) || !curr.getPlayer().equals(currPlayer)) {
                return false;
            }
        }
        return true;
    }
}
