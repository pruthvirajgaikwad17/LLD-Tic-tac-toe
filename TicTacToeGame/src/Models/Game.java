package Models;

import Strategy.ColumnWinningStrategy;
import Strategy.DiagonalWinningStrategy;
import Strategy.RowWinningStrategy;
import Strategy.WinningStrategy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Game {
    public Board board;
    public List<Player> playerList;
    public int currPlayerIndex;
    public List<Cell> moves;
    GameState gameState;
    List<WinningStrategy> winningStrategies;
    Player winner;

    public Game(int n, List<Player> playerList) {
        this.board = new Board(n);
        this.playerList = playerList;
        this.currPlayerIndex = 0;
        this.moves = new ArrayList<>();
        this.gameState = GameState.IN_PROGRESS;
        this.winningStrategies = new ArrayList<>(List.of(
                new RowWinningStrategy(),
                new ColumnWinningStrategy(),
                new DiagonalWinningStrategy()));
    }

    public void setWinner() {
         gameState = GameState.WINNING;
         winner = playerList.get(currPlayerIndex);
    }

    public void setDraw() {
        gameState = GameState.DRAW;
    }

    /*
    * This method makes the next player decide the move and updates the board
    *
    * */
    public void makeMoveForCurrPlayer() {
        Player currPlayer = this.playerList.get(currPlayerIndex);
        Cell cell = currPlayer.makeMove(board, currPlayer);

        // increment the game index
        // check for winner

        // step 3 - update the board , if it fails again try again to make the next move
        try {
            this.getBoard().updateBoard(cell, currPlayer);
            this.moves.add(cell);
        } catch(IllegalArgumentException e) {
            System.out.println("please choose the valid cell");
            makeMoveForCurrPlayer();
            return;
        }
    }

    public void postMoveWinnerCheck() {
        Player currPlayer = this.playerList.get(currPlayerIndex);
        boolean isWin = this.getWinningStrategies()
                .stream()
                .anyMatch(winningStrategy -> winningStrategy.isWinning(this));
        if(isWin) {
            // store the winning player
            this.setWinner();
        } else {
            // update the next player index
            this.currPlayerIndex += 1;
            this.currPlayerIndex %= this.playerList.size();
        }
    }
}
