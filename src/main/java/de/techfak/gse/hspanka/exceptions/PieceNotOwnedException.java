package de.techfak.gse.hspanka.exceptions;

/**
 * Exception that should be thrown if a piece is not owned by the current player.
 */
public class PieceNotOwnedException extends ApplicationMoveException {
    private static final int ERR_CODE = 103;

    public PieceNotOwnedException(String message) {
        super(message);
    }

    @Override
    public int getErrorCode() {
        return ERR_CODE;
    }
}
