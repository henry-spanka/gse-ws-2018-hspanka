package de.techfak.gse.hspanka.piece;

import de.techfak.gse.hspanka.Player;

public class King extends Piece {
    public King(Player player) {
        super(player);
    }

    @Override
    public char toChar() {
        return playerToChar('k');
    }
}
