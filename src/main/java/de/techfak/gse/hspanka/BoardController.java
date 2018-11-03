package de.techfak.gse.hspanka;

import de.techfak.gse.hspanka.Exceptions.InvalidBoardConfiguration;
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

    public void setBoardConfigurationFromString(String conf) throws InvalidBoardConfiguration {
        if (conf.isEmpty()) {
            throw new InvalidBoardConfiguration("The configuration is empty.");
        }
    }
}
