package org.renu.service;


import org.renu.dal.TicTacToeDao;
import org.renu.model.Board;
import org.renu.model.Player;

public class TicTacToeService {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private final TicTacToeDao ticTacToeDao;

    public TicTacToeService(Player player1, Player player2, int boardSize, TicTacToeDao ticTacToeDao) {
        this.board = new Board(boardSize);
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.ticTacToeDao = ticTacToeDao;

        // Save initial game state
        ticTacToeDao.saveBoard(board);
    }

    public void startGame() {
        System.out.println("Starting Tic-Tac-Toe!");
        System.out.println("Player " + player1.getName() + " goes first.");
        board.displayBoard();
    }

    public boolean playTurn(int row, int col) {
        if (board.isMoveValid(row, col)) {
            board.placeMove(row, col, currentPlayer.getSymbol());
            board.displayBoard();

            // Update and save board state
            ticTacToeDao.saveBoard(board);

            if (checkWinner(row, col)) {
                System.out.println("Player " + currentPlayer.getName() + " wins!");
                return true;
            } else if (checkDraw()) {
                System.out.println("It's a draw!");
                return true;
            }
            switchPlayer();
            return false;
        } else {
            System.out.println("Invalid move. Try again.");
            return false;
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
        System.out.println("Next turn: " + currentPlayer.getName());
    }

    private boolean checkWinner(int row, int col) {
        char symbol = currentPlayer.getSymbol();
        int boardSize = board.getSize();

        // Check the row
        boolean winRow = true;
        for (int i = 0; i < boardSize; i++) {
            if (board.getCell(row, i) != symbol) {
                winRow = false;
                break;
            }
        }

        // Check the column
        boolean winCol = true;
        for (int i = 0; i < boardSize; i++) {
            if (board.getCell(i, col) != symbol) {
                winCol = false;
                break;
            }
        }

        // Check the main diagonal
        boolean winMainDiag = true;
        if (row == col) {  // Only check if move is on main diagonal
            for (int i = 0; i < boardSize; i++) {
                if (board.getCell(i, i) != symbol) {
                    winMainDiag = false;
                    break;
                }
            }
        } else {
            winMainDiag = false;
        }

        // Check the anti-diagonal
        boolean winAntiDiag = true;
        if (row + col == boardSize - 1) {  // Only check if move is on anti-diagonal
            for (int i = 0; i < boardSize; i++) {
                if (board.getCell(i, boardSize - 1 - i) != symbol) {
                    winAntiDiag = false;
                    break;
                }
            }
        } else {
            winAntiDiag = false;
        }

        return winRow || winCol || winMainDiag || winAntiDiag;
    }

    private boolean checkDraw() {
        return board.isFull();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
