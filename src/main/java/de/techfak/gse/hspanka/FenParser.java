package de.techfak.gse.hspanka;

import de.techfak.gse.hspanka.exceptions.EmptyBoardConfigurationException;
import de.techfak.gse.hspanka.exceptions.InvalidBoardConfiguration;
import de.techfak.gse.hspanka.piece.Piece;

public class FenParser {
    /**
     * The board model.
     */
    private final Board board;

    /**
     * The FEN row separator.
     */
    private static final char FEN_ROW_SEPARATOR = '/';

    public FenParser(Board board) {
        this.board = board;
    }

    /**
     * Sets the board to the given configuration.
     *
     * @param conf The board configuration to be set in simplified FEN notation
     * @throws InvalidBoardConfiguration        The configuration can not be parsed.
     * @throws EmptyBoardConfigurationException An empty string was supplied.
     * @see <a href="https://en.wikipedia.org/wiki/Chess#Movement">Chess Movement</a>
     */
    @SuppressWarnings("PMD.CyclomaticComplexity")
    public void parse(final String conf) throws
        InvalidBoardConfiguration,
        EmptyBoardConfigurationException {
        if (conf.isEmpty()) {
            throw new EmptyBoardConfigurationException("The configuration is empty.");
        }

        // We start at the top left of the board.
        int row = 0;
        int col = 0;

        // Parse the conf character by character.
        for (int i = 0; i < conf.length(); i++) {
            char character = conf.charAt(i);

            // If the character is a digit, it indicates how many fields in the current row we can skip (are empty).
            if (Character.isDigit(character)) {
                col += (character - '0');
                continue;
            }

            /*
             * A slash indicates that the current row of the chess board is complete
             * and we should start filling up the next row.
             * To be sure, we check if we have actually reached the last column,
             * so we don't leave any fields uninitialised.
             */
            if (character == FEN_ROW_SEPARATOR) {
                if (col != Board.FIELD_SIZE) {
                    throw new InvalidBoardConfiguration("Piece expected but not found.");
                }

                // Parse the next row starting at the first column.
                row += 1;
                col = 0;
                continue;
            }

            /*
             * If we encounter a whitespace it means we have parsed the whole board.
             * After the whitespace a character ('w' or 'b') is expected that tells us
             * who's next turn it is (White or Black player).
             * Just to be safe, we check that after the whitespace only one character follows.
             */
            if (Character.isWhitespace(character)) {
                /*
                 * Check that we have a full board.
                 */
                if (col != Board.FIELD_SIZE || row != Board.FIELD_SIZE - 1) {
                    throw new InvalidBoardConfiguration("Some pieces are missing.");
                }

                if (i + 2 == conf.length()) {
                    character = conf.charAt(i + 1);
                    try {
                        board.setPlayer(Player.playerFromChar(character));
                    } catch (IllegalArgumentException e) {
                        throw new InvalidBoardConfiguration(e.getMessage(), e);
                    }

                    return;
                } else {
                    throw new InvalidBoardConfiguration("Whitespace encountered but not expected.");
                }
            }

            /*
             * Check that we do not exceed the column/row boundary.
             */
            if (col > Board.FIELD_SIZE - 1 || row > Board.FIELD_SIZE - 1) {
                throw new InvalidBoardConfiguration("Too many pieces in a row or column.");
            }

            // Create an instance of the piece and place it on the board.
            final Piece piece = Piece.fromChar(character);
            board.placePiece(piece, row, col);

            // Parse the next column in the current row.
            col += 1;
        }

        // No whitespace was encountered where the player is encoded but the end of the string was reached.
        throw new InvalidBoardConfiguration("The string terminated unexpectedly.");
    }

    public String toString(Piece[][] pieces, Player player) {
        // The number of subsequent empty fields in a row.
        int emptyPieces = 0;

        // The string that will be displayed in the user's terminal.
        final StringBuilder result = new StringBuilder();

        // We start at the top left of the board.
        for (int row = 0; row < Board.FIELD_SIZE; row++) {
            // Check each piece in a row from the left to the right.
            for (final Piece piece : pieces[row]) {
                if (piece == null) {
                    // Empty pieces can be skipped. We will just print the number of pieces we skipped in a given row.
                    emptyPieces++;
                } else {
                    // Print the number of pieces we skipped before printing the next piece.
                    if (emptyPieces > 0) {
                        result.append(emptyPieces);
                        emptyPieces = 0;
                    }

                    // Print the next piece.
                    result.append(piece.toChar());
                }
            }

            // If we skipped pieces at the end of a row we need to print them now before continuing to the next row.
            if (emptyPieces > 0) {
                result.append(emptyPieces);
                emptyPieces = 0;
            }

            // If we are not in the last row, add the row separator '/'.
            if (row < Board.FIELD_SIZE - 1) {
                result.append('/');
            }
        }

        // Print a whitespace and encode the player who's turn it is as a character.
        result.append(' ').append(player.toChar());

        return result.toString();
    }
}
