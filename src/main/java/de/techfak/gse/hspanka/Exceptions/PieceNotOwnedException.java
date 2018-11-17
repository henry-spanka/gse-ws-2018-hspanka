package de.techfak.gse.hspanka.Exceptions;

/**
 * Exception that should be thrown if a piece is not owned by the current player.
 */
public class PieceNotOwnedException extends ApplicationMoveException {
    public PieceNotOwnedException(String message) {
        super(message);
    }

    @Override
    public int getErrorCode() {
        return 103;
    }
}
