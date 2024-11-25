package org.renu.dal;

import org.renu.model.Board;

public interface TicTacToeDao {
    void saveBoard(Board board);
    Board getBoard();
}
