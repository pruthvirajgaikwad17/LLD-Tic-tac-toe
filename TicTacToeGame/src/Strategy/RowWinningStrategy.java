package Strategy;

import Models.Cell;
import Models.CellState;
import Models.Game;
import Models.Player;

public class RowWinningStrategy implements WinningStrategy{
    public boolean isWinning(Game game) {
        // Iterate Over all the rows and decide if the game ends
        Player currPlayer = game.playerList.get(game.currPlayerIndex);

        Cell cell = game.moves.get(game.moves.size() - 1);

        int row = cell.getRow();

        for(int i = 0 ; i < game.board.getN(); i++) {
            Cell curr = game.board.getCells().get(row).get(i);
            if (curr.getCellState().equals(CellState.FREE) || !curr.getPlayer().equals(currPlayer)) {
                return false;
            }
        }
        return true;
    }
}
