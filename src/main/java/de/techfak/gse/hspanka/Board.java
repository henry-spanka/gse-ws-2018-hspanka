package de.techfak.gse.hspanka;

import de.techfak.gse.hspanka.exceptions.ApplicationMoveException;
import de.techfak.gse.hspanka.exceptions.BoardPositionEmptyException;
import de.techfak.gse.hspanka.exceptions.PieceNotOwnedException;
import de.techfak.gse.hspanka.piece.Piece;

/**
 * The board model that is responsible for managing the board and the pieces on it.
 */
public class Board {
    /**
     * The size of the chess field.
     */
    public static final int FIELD_SIZE = 8;

    /**
     * The board array in which the pieces are stored.
     * Null values indicate that the field is empty.
     */
    private Piece[][] configuration = new Piece[8][8];

    /**
     * The player who currently makes a move.
     */
    private Player player;

    /**
     * Get the board configuration.
     * @return Current board configuration.
     */
    public Piece[][] getConfiguration() {
        return configuration;
    }

    /**
     * Get the current player.
     * @return The current player.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Places a piece on the board.
     * @param piece The piece that should be placed on the board.
     * @param row The row where the piece should be placed.
     * @param col The column where the piece should be placed. (a=0, b=1, ..., h=7)
     * @see <a href="https://en.wikipedia.org/wiki/Chess#Movement">Chess Movement</a>
     */
    public void placePiece(Piece piece, int row, int col) {
        configuration[row][col] = piece;
    }

    /**
     * Set the current player who turn it is.
     * @param player The player model.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Executes a move by changing the board configuration.
     * @param move The move object that describes the move.
     */
    public void executeMove(Move move) {
        configuration[move.getrTo()][move.getcTo()] = configuration[move.getrFrom()][move.getcFrom()];
        configuration[move.getrFrom()][move.getcFrom()] = null;

        switchTurn();
    }

    /**
     * Switch the player who's should make a move next.
     */
    private void switchTurn() {
        if (player == Player.BLACK) {
            player = Player.WHITE;
        } else {
            player = Player.BLACK;
        }
    }

    /**
     * Get a Piece from the board by position.
     * @param row The row where the piece is located.
     * @param col The column where the piece is located.
     * @return The piece at the specified position.
     * @throws BoardPositionEmptyException Thrown if the Position is empty.
     */
    private Piece getPiece(int row, int col) throws BoardPositionEmptyException {
        Piece piece = configuration[row][col];

        if (piece == null) {
            throw new BoardPositionEmptyException("No piece found at the position on the board");
        }

        return piece;
    }

    /**
     * Validates the move by checking whether the move is legal and the player
     * can actually move this piece.
     * @param move The move that should be checked.
     * @throws ApplicationMoveException A subclass is thrown that indicates the constraint that failed.
     */
    public void validateMove(Move move) throws ApplicationMoveException {
        Piece fromPiece = getPiece(move.getrFrom(), move.getcFrom());

        if (fromPiece.getPlayer() != player) {
            throw new PieceNotOwnedException("Not allowed to move that piece.");
        }

        try {
            Piece toPiece = getPiece(move.getrTo(), move.getcTo());
        } catch (BoardPositionEmptyException e) {
            return;
        }
    }
}
