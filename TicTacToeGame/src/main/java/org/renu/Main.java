package org.renu;

import org.renu.dal.InMemoryTicTacToeDao;
import org.renu.dal.TicTacToeDao;
import org.renu.model.Board;
import org.renu.model.Player;
import org.renu.service.TicTacToeService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Set up board size and players
        System.out.print("Enter board size (e.g., 3 for a 3x3 grid): ");
        int boardSize = scanner.nextInt();

        System.out.print("Enter Player 1 name: ");
        String player1Name = scanner.next();
        Player player1 = new Player(player1Name, 'X');

        System.out.print("Enter Player 2 name: ");
        String player2Name = scanner.next();
        Player player2 = new Player(player2Name, 'O');

        // Set up repository and game service
        TicTacToeDao ticTacToeDao= new InMemoryTicTacToeDao();
        TicTacToeService ticTacToeService= new TicTacToeService(player1, player2, boardSize, ticTacToeDao);

        // Start the game
        ticTacToeService.startGame();

        // Main game loop
        boolean gameEnded = false;
        while (!gameEnded) {
            System.out.print("Enter row and column for " + ticTacToeService.getCurrentPlayer().getName() + "'s move, separated by a space: ");
            int row = scanner.nextInt() - 1;  // Adjusting for 0-based index
            int col = scanner.nextInt() - 1;  // Adjusting for 0-based index

            gameEnded = ticTacToeService.playTurn(row, col);
        }

        scanner.close();
    }

}
