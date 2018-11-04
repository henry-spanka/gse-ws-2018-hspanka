package de.techfak.gse.hspanka;

import de.techfak.gse.hspanka.Exceptions.InvalidBoardConfiguration;
import de.techfak.gse.hspanka.Piece.Piece;
import de.techfak.gse.hspanka.View.BoardView;

/**
 * Controller for interacting with the Chess Board
 */
public class BoardController {
    /**
     * The board model
     */
    private Board board;
    /**
     * The board view
     */
    private BoardView boardView;

    public BoardController() {
        board = new Board();
        boardView = new BoardView();
    }

    /**
     * Instructs the view to display the current board configuration
     */
    public void showCurrentBoard() {
        boardView.showCurrentConfiguration(board.getConfiguration());
    }

    /**
     * @param conf The board configuration to be set in simplified FEN notation
     * @throws InvalidBoardConfiguration Thrown if the supplied configuration cannot be parsed or is invalid
     */
    public void setBoardConfigurationFromString(String conf) throws InvalidBoardConfiguration {
        if (conf.isEmpty()) {
            throw new InvalidBoardConfiguration("The configuration is empty.");
        }

        int x = 0, y = 0;

        for (int i = 0; i < conf.length(); i++) {
            char c = conf.charAt(i);

            if (Character.isDigit(c)) {
                x += c;
                continue;
            }

            if (c == '/') {
                x = 0;
                y += 1;
                continue;
            }

            Piece piece = Piece.fromChar(c);

            x++;
        }
    }
}
