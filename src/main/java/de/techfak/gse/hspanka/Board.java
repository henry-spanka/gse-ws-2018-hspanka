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
     * The player who currently makes a turn
     */
    private Player player;

    /**
     * @return Current board configuration
     */
    public Piece[][] getConfiguration() {
        return configuration;
    }

    public Player getPlayer() {
        return player;
    }

    public void placePiece(Piece piece, int col, int row) {
        configuration[row][col] = piece;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
