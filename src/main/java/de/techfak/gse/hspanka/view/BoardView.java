package de.techfak.gse.hspanka.view;

import de.techfak.gse.hspanka.piece.Piece;
import de.techfak.gse.hspanka.Player;

/**
 * The board view that is responsible for displaying the current board for the user who interacts
 * with the application.
 */
public class BoardView {
    /**
     * Displays the board on the user's screen.
     * @param configuration The configuration that should be displayed.
     * @param player The player who's turn it is.
     */
    public void showCurrentConfiguration(Piece[][] configuration, Player player) {
        // The number of subsequent empty fields in a row.
        int emptyPieces = 0;

        // The string that will be displayed in the user's terminal.
        StringBuilder output = new StringBuilder();

        // Row is set to 7 (max value) because it's at the top left of chess board where we start.
        for (int row = 7; row >= 0; row--) {
            // Check each piece in a row from the left to the right.
            for (Piece piece: configuration[row]) {
                if (piece == null) {
                    // Empty pieces can be skipped. We will just print the number of pieces we skipped in a given row.
                    emptyPieces++;
                } else {
                    // Print the number of pieces we skipped before printing the next piece.
                    if (emptyPieces > 0) {
                        output.append(emptyPieces);
                        emptyPieces = 0;
                    }

                    // Print the next piece.
                    output.append(piece.toChar());
                }
            }

            // If we skipped pieces at the end of a row we need to print them now before continuing to the next row.
            if (emptyPieces > 0) {
                output.append(emptyPieces);
                emptyPieces = 0;
            }

            // If we are not in the last row, add the row separator '/'.
            if (row > 0) {
                output.append('/');
            }
        }

        // Print a whitespace and encode the player who's turn it is as a character.
        output.append(' ');
        output.append(player.toChar());

        // Print the current configuration.
        System.out.println(output);
    }
}
