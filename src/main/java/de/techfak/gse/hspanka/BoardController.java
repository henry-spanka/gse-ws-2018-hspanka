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
        boardView.showCurrentConfiguration(board.getConfiguration(), board.getPlayer());
    }

    public void setDefaultBoardConfiguration() throws InvalidBoardConfiguration {
        String conf = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w";

        setBoardConfigurationFromString(conf);
    }

    /**
     * @param conf The board configuration to be set in simplified FEN notation
     * @throws InvalidBoardConfiguration Thrown if the supplied configuration cannot be parsed or is invalid
     */
    public void setBoardConfigurationFromString(String conf) throws InvalidBoardConfiguration {
        if (conf.isEmpty()) {
            throw new InvalidBoardConfiguration("The configuration is empty.");
        }

        int row = 7, col = 0;

        for (int i = 0; i < conf.length(); i++) {
            char c = conf.charAt(i);

            if (Character.isDigit(c)) {
                col += c;
                continue;
            }

            if (c == '/') {
                row -= 1;
                col = 0;
                continue;
            }

            if (Character.isWhitespace(c)) {
                if (i+2 == conf.length()) {
                    c = conf.charAt(i+1);
                    try {
                        board.setPlayer(Player.playerFromChar(c));
                    } catch (IllegalArgumentException e) {
                        throw new InvalidBoardConfiguration(e.getMessage());
                    }

                    break;
                } else {
                    throw new InvalidBoardConfiguration("Whitespace encountered but not expected.");
                }
            }

            Piece piece = Piece.fromChar(c);
            board.placePiece(piece, row, col);

            col += 1;
        }
    }

    public void makeMove(Move move) {
        board.executeMove(move);
    }
}
