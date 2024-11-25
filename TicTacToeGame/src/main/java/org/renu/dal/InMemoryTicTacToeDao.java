package org.renu.dal;

import org.renu.model.Board;

public class InMemoryTicTacToeDao implements TicTacToeDao{
    private Board board;

    @Override
    public void saveBoard(Board board) {
        this.board = board;
    }

    @Override
    public Board getBoard() {
        return this.board;
    }

}

