package de.techfak.gse.hspanka;

import de.techfak.gse.hspanka.exceptions.ApplicationMoveException;
import de.techfak.gse.hspanka.exceptions.BoardPositionEmptyException;
import de.techfak.gse.hspanka.exceptions.CannotMoveToOwnedPieceException;
import de.techfak.gse.hspanka.exceptions.PieceNotOwnedException;
import de.techfak.gse.hspanka.piece.Piece;

import java.util.Observable;

/**
 * The board model that is responsible for managing the board and the pieces on it.
 */
public class Board extends Observable {
    /**
     * The size of the chess field.
     */
    public static final int FIELD_SIZE = 8;

    /**
     * The board array in which the pieces are stored.
     * Null values indicate that the field is empty.
     */
    private final Piece[][] configuration = new Piece[FIELD_SIZE][FIELD_SIZE];

    /**
     * The player who currently makes a move.
     */
    private Player player;

    /**
     * A pending move.
     */
    private Move move;

    /**
     * Get the board configuration.
     *
     * @return Current board configuration.
     */
    public Piece[][] getConfiguration() {
        return configuration.clone();
    }

    /**
     * Get the current player.
     *
     * @return The current player.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Set the current player who turn it is.
     *
     * @param player The player model.
     */
    public void setPlayer(final Player player) {
        this.player = player;

        setChanged();
        notifyObservers();
    }

    /**
     * Places a piece on the board.
     *
     * @param piece The piece that should be placed on the board.
     * @param row   The row where the piece should be placed.
     * @param col   The column where the piece should be placed. (a=0, b=1, ..., h=7)
     * @see <a href="https://en.wikipedia.org/wiki/Chess#Movement">Chess Movement</a>
     */
    public void placePiece(final Piece piece, final int row, final int col) {
        configuration[row][col] = piece;

        setChanged();
        notifyObservers();
    }

    public Move getMove() {
        return move;
    }

    public void setMove(final Move move) {
        this.move = move;

        setChanged();
        notifyObservers();
    }

    /**
     * Executes the move by changing the board configuration.
     */
    public void executeMove() {
        configuration[move.getrTo()][move.getcTo()] = configuration[move.getrFrom()][move.getcFrom()];
        configuration[move.getrFrom()][move.getcFrom()] = null;

        move = null;

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

        setChanged();
        notifyObservers();
    }

    /**
     * Get a Piece from the board by position.
     *
     * @param row The row where the piece is located.
     * @param col The column where the piece is located.
     * @return The piece at the specified position.
     * @throws BoardPositionEmptyException Thrown if the Position is empty.
     */
    private Piece getPiece(final int row, final int col) throws BoardPositionEmptyException {
        final Piece piece = configuration[row][col];

        if (piece == null) {
            throw new BoardPositionEmptyException("No piece found at the position on the board");
        }

        return piece;
    }

    /**
     * Validates the move by checking whether the move is legal and the player
     * can actually move this piece.
     *
     * @throws ApplicationMoveException A subclass is thrown that indicates the constraint that failed.
     */
    public void validateMove() throws ApplicationMoveException {
        validateMove(this.move);
    }

    /**
     * Validates the move by checking whether the move is legal and the player
     * can actually move this piece. Does not check destination
     *
     * @param move The move that should be checked
     * @throws ApplicationMoveException A subclass is thrown that indicates the constraint that failed.
     */
    public void validateMove(final Move move) throws ApplicationMoveException {
        validateMove(move, false);
    }

    /**
     * Validates the move by checking whether the move is legal and the player
     * can actually move this piece.
     *
     * @param move      The move that should be checked.
     * @param checkDest Whether we should also check that the destination is correct.
     * @throws ApplicationMoveException A subclass is thrown that indicates the constraint that failed.
     */
    @SuppressWarnings("PMD.EmptyCatchBlock")
    public void validateMove(final Move move, final boolean checkDest) throws ApplicationMoveException {
        if (move.sourceComplete()) {
            final Piece fromPiece = getPiece(move.getrFrom(), move.getcFrom());

            if (fromPiece.getPlayer() != player) {
                throw new PieceNotOwnedException("Not allowed to move that piece.");
            }
        }

        if (move.destinationComplete() && checkDest) {
            try {
                final Piece toPiece = getPiece(move.getrTo(), move.getcTo());

                if (toPiece.getPlayer() == player) {
                    throw new CannotMoveToOwnedPieceException(
                        "Cannot move to a piece that is owned by the executing player"
                    );
                }
            } catch (BoardPositionEmptyException e) {
                //
            }
        }
    }
}
