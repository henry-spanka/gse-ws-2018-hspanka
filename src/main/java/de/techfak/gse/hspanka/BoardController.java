package de.techfak.gse.hspanka;

import de.techfak.gse.hspanka.exceptions.ApplicationMoveException;
import de.techfak.gse.hspanka.exceptions.EmptyBoardConfigurationException;
import de.techfak.gse.hspanka.exceptions.InvalidBoardConfiguration;
import de.techfak.gse.hspanka.piece.Piece;
import de.techfak.gse.hspanka.view.BoardView;

/**
 * Controller for interacting with the Chess Board.
 */
public class BoardController {
    /**
     * The board model.
     */
    private Board board;
    /**
     * The board view.
     */
    private BoardView boardView;

    public BoardController() {
        board = new Board();
        boardView = new BoardView();
    }

    /**
     * Instructs the view to display the current board configuration.
     */
    public void showCurrentBoard() {
        boardView.showCurrentConfiguration(board.getConfiguration(), board.getPlayer());
    }

    /**
     * Sets the board to the default configuration.
     * @throws InvalidBoardConfiguration The configuration can not be parsed.
     * @throws EmptyBoardConfigurationException An empty string was supplied.
     * @see <a href="https://en.wikipedia.org/wiki/Chess#Movement">Chess Movement</a>
     */
    public void setDefaultBoardConfiguration() throws InvalidBoardConfiguration, EmptyBoardConfigurationException {
        String conf = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w";

        setBoardConfigurationFromString(conf);
    }

    /**
     * Sets the board to the given configuration.
     * @param conf The board configuration to be set in simplified FEN notation
     * @throws InvalidBoardConfiguration The configuration can not be parsed.
     * @throws EmptyBoardConfigurationException An empty string was supplied.
     * @see <a href="https://en.wikipedia.org/wiki/Chess#Movement">Chess Movement</a>
     */
    public void setBoardConfigurationFromString(String conf) throws
        InvalidBoardConfiguration,
        EmptyBoardConfigurationException {
        if (conf.isEmpty()) {
            throw new EmptyBoardConfigurationException("The configuration is empty.");
        }

        // Row is set to field size - 1 (max value) because it's at the top left of chess board where we start.
        int row = Board.FIELD_SIZE - 1;
        int col = 0;

        // Parse the conf character by character.
        for (int i = 0; i < conf.length(); i++) {
            char c = conf.charAt(i);

            // If the character is a digit, it indicates how many fields in the current row we can skip (are empty).
            if (Character.isDigit(c)) {
                col += (c - '0');
                continue;
            }

            /*
             * A slash indicates that the current row of the chess board is complete
             * and we should start filling up the next row.
             * To be sure, we check if we have actually reached the last column,
             * so we don't leave any fields uninitialised.
             */
            if (c == '/') {
                if (col != Board.FIELD_SIZE) {
                    throw new InvalidBoardConfiguration("Piece expected but not found.");
                }

                // Parse the next row starting at the first column.
                row -= 1;
                col = 0;
                continue;
            }

            /*
             * If we encounter a whitespace it means we have parsed the whole board.
             * After the whitespace a character ('w' or 'b') is expected that tells us
             * who's next turn it is (White or Black player).
             * Just to be safe, we check that after the whitespace only one character follows.
             */
            if (Character.isWhitespace(c)) {
                if (i + 2 == conf.length()) {
                    c = conf.charAt(i + 1);
                    try {
                        board.setPlayer(Player.playerFromChar(c));
                    } catch (IllegalArgumentException e) {
                        throw new InvalidBoardConfiguration(e.getMessage());
                    }

                    return;
                } else {
                    throw new InvalidBoardConfiguration("Whitespace encountered but not expected.");
                }
            }

            // Create an instance of the piece and place it on the board.
            Piece piece = Piece.fromChar(c);
            board.placePiece(piece, row, col);

            // Parse the next column in the current row.
            col += 1;
        }

        // No whitespace was encountered where the player is encoded but the end of the string was reached.
        throw new InvalidBoardConfiguration("The string terminated unexpectedly.");
    }

    /**
     * Tries to make a move.
     * @param move The move that should be made.
     * @throws ApplicationMoveException If the move is invalid or can not be made.
     */
    public void makeMove(Move move) throws ApplicationMoveException {
        move.validateUniqueness();
        board.validateMove(move);
        board.executeMove(move);
    }
}
