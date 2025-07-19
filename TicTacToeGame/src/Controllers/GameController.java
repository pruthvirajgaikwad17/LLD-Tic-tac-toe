package Controllers;

import Models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameController {

    Game game;

    public GameController(Game game) {
        this.game = game;
    }


    public static Game initializeGame() {
        System.out.println("Enter the board size: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        List<Player> playerList = new ArrayList<>();
        for(int i = 0 ; i < 2; i++) {
            System.out.println("Enter the player name and symbol");
            String name = sc.next();
            String symbol = sc.next();
            System.out.println("Is it a bot player?");
            String ans = sc.next();
            if(ans.equals("yes")) {
                System.out.println("Enter difficulty level (1/2/3)");
                int val = sc.nextInt();
                BotDifficultyLevel botDifficultyLevel = switch (val) {
                    case 1 -> BotDifficultyLevel.EASY;
                    case 2 -> BotDifficultyLevel.MEDIUM;
                    default -> BotDifficultyLevel.MEDIUM;
                };
                playerList.add(new BotPlayer(name, symbol.charAt(0), i+1, botDifficultyLevel));
            }
            //TODO : Handle bot player as Input
            playerList.add(new HumanPlayer(name, symbol.charAt(0), i+1));

        }
        return new Game(n, playerList);
//        return Game.builder()
//                .board()
//                .playerList()
//                .build();
    }

    /**
     * Helps the user make next move
     *
     * 1. Finds the player with the next move
     * 2. Calls the makeMove method for the corresponding player
     * 3. Updates the board with that move and corresponding validation
     * 4. Check all the winning strategies
     * 5. Display the board
     *
     */

    public void makeNextMove() {

        if(game.getBoard().isFull()){
            game.setDraw();
            return;
        }

        // step 1
        int currentPlayerIndex = game.getCurrPlayerIndex();
        Player currPlayer = game.playerList.get(currentPlayerIndex);

        // step 2
        System.out.printf("It's %s player move \n", currPlayer.getName());

        game.makeMoveForCurrPlayer();

        // step 4 check for the winning strategies
        game.postMoveWinnerCheck();
    }

    public void undoLatMove() {
        // remove from moves list
        Cell moveCell = game.moves.get(game.moves.size() - 1);
        game.moves.remove(moveCell);

        // updating the board without that cell
        Cell cell = game.getBoard().getCells().get(moveCell.getRow()).get(moveCell.getCol());
        cell.setCellState(null);
        cell.setCellState(CellState.FREE);

        // update the curr player
        game.currPlayerIndex = (game.currPlayerIndex - 1 * game.playerList.size()) % game.playerList.size();

    }
}
