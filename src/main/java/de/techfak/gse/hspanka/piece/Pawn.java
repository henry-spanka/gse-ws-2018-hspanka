package de.techfak.gse.hspanka.piece;

import de.techfak.gse.hspanka.Player;

public class Pawn extends Piece {
    public Pawn(Player player) {
        super(player);
    }

    @Override
    public char toChar() {
        return playerToChar('p');
    }
}
