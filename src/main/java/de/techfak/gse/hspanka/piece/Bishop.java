package de.techfak.gse.hspanka.piece;

import de.techfak.gse.hspanka.Player;

public class Bishop extends Piece {
    public Bishop(Player player) {
        super(player);
    }

    @Override
    public char toChar() {
        return playerToChar('b');
    }
}
