package de.techfak.gse.hspanka.Piece;

import de.techfak.gse.hspanka.Player;

public class Queen extends Piece {
    public Queen(Player player) {
        super(player);
    }

    @Override
    public char toChar() {
        return playerToChar('q');
    }
}
