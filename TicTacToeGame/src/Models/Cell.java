package Models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Cell {
    int row;
    int col;
    Player player;

    CellState cellState;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.cellState = CellState.FREE;
    }

    public void updateCell(Player player) {
        cellState = CellState.OCCUPIED;
        this.player = player;
    }
}
