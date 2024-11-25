package org.renu.model;

public class Board {
        private final char[][] grid;
        private final int size;

        public Board(int size) {
            this.size = size;
            this.grid = new char[size][size];

            // Initialize the board with empty cells, e.g., '-'
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    grid[i][j] = '-';
                }
            }
        }

        // Getter for board size
        public int getSize() {
            return size;
        }

        // Getter for a specific cell value
        public char getCell(int row, int col) {
            return grid[row][col];
        }

        // Method to place a move on the board
        public void placeMove(int row, int col, char symbol) {
            grid[row][col] = symbol;
        }

        // Method to check if a move is valid
        public boolean isMoveValid(int row, int col) {
            return row >= 0 && row < size && col >= 0 && col < size && grid[row][col] == '-';
        }

        // Method to display the board
        public void displayBoard() {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    System.out.print(grid[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }

        // Method to check if the board is full
        public boolean isFull() {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (grid[i][j] == '-') {
                        return false;
                    }
                }
            }
            return true;
        }
    }


