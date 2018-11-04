package de.techfak.gse.hspanka.Piece;

import de.techfak.gse.hspanka.Player;

public class Knight extends Piece {
    public Knight(Player player) {
        super(player);
    }

    @Override
    public char toChar() {
        return playerToChar('n');
    }
}
