package de.techfak.gse.hspanka.piece;

import de.techfak.gse.hspanka.Player;

/**
 * A specific Piece that is used to play Chess.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Chess#Setup">Chess Setup</a>
 */
public class Knight extends Piece {
    public Knight(final Player player) {
        super(player);
    }

    @Override
    public char toChar() {
        return playerToChar('n');
    }
}
