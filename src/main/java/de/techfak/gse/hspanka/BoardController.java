package de.techfak.gse.hspanka;

import de.techfak.gse.hspanka.View.BoardView;

public class BoardController {
    private Board board;
    private BoardView boardView;

    public BoardController() {
        board = new Board();
        boardView = new BoardView();
    }

    public void showCurrentBoard() {
        boardView.showCurrentConfiguration(board.getConfiguration());
    }
}
