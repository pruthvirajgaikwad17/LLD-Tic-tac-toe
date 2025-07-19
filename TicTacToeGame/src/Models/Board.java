package Models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
public class Board {
    public int N;

    List<List<Cell>> cells = new ArrayList<>();

    public Board(int N) {

        this.N = N;

        for(int i = 0; i < N; i++) {
            List<Cell> row = new ArrayList<>();
            for(int j = 0; j < N; j++) {
                row.add(new Cell(i, j));
            }
            cells.add(row);
        }
    }

    public void displayBoard() {
        for(int i =0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                Cell cell = cells.get(i).get(j);
                if(cell.cellState == CellState.OCCUPIED) {
                    System.out.printf("| %c", cell.player.symbol);
                } else {
                    System.out.printf("| ");
                }
            }
            System.out.println(" |");
        }
    }

    public void updateBoard(Cell cell, Player player) {
        int row = cell.row;
        int col = cell.col;

        if(row < N && col < N && row >= 0 && col >= 0 && this.cells.get(row).get(col).cellState == CellState.FREE) {
            this.cells.get(row).get(col).updateCell(player);
        } else {
            throw new IllegalStateException("move is invalid");
        }
    }

    public boolean isFull() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                Cell cell = cells.get(i).get(j);
                if (cell.cellState == CellState.FREE) return false;
            }
        }
        return true;
    }
}
