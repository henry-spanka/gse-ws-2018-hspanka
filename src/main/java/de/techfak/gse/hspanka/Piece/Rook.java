package de.techfak.gse.hspanka.Piece;

import de.techfak.gse.hspanka.Player;

public class Rook extends Piece {
    public Rook(Player player) {
        super(player);
    }

    @Override
    public char toChar() {
        return playerToChar('r');
    }
}
