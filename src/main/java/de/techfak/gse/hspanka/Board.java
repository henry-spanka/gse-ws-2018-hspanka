package de.techfak.gse.hspanka;

import de.techfak.gse.hspanka.Piece.Piece;

/**
 * The board model
 */
public class Board {
    /**
     * The default configuration if not manually set
     */
    private Piece[][] configuration = new Piece[8][8];

    /**
     * @return Current board configuration
     */
    public Piece[][] getConfiguration() {
        return configuration;
    }

    public void placePiece(Piece piece, int x, int y) {
        configuration[x][y] = piece;
    }
}
