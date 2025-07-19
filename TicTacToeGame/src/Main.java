import Controllers.GameController;
import Models.Board;
import Models.Game;
import Models.GameState;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Game game = GameController.initializeGame();
        GameController gc = new GameController(game);

        while (game.getGameState().equals(GameState.IN_PROGRESS)) {

            // Prints the next Player's move
            // Ask the user for input
            gc.makeNextMove();
            game.getBoard().displayBoard();
            System.out.println("do you want to undo the last move ?");
            Scanner sc = new Scanner(System.in);
            String ans = sc.next();
            if(ans.equals("yes")) {
                gc.undoLatMove();
            }
        }

        game.getBoard().displayBoard();

        if (game.getGameState().equals(GameState.WINNING)) {
            System.out.printf("The Winner of the game is %s\n ", game.getWinner().getName());
        } else {
            System.out.println("Draw");
        }
    }
}