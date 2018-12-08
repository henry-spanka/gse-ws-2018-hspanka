package de.techfak.gse.hspanka.view.console;

import de.techfak.gse.hspanka.Board;
import de.techfak.gse.hspanka.FenParser;
import de.techfak.gse.hspanka.Player;
import de.techfak.gse.hspanka.piece.Piece;

/**
 * The board view that is responsible for displaying the current board for the user who interacts
 * with the application.
 */
public class BoardView {
    /**
     * Displays the board on the user's screen.
     *
     * @param configuration The configuration that should be displayed.
     * @param player        The player who's turn it is.
     */
    public void showCurrentConfiguration(final Piece[][] configuration, final Player player) {
        FenParser fen = new FenParser(null);

        // Print the current configuration.
        System.out.println(fen.toString(configuration, player));
    }
}
