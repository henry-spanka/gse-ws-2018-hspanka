package de.techfak.gse.hspanka.exceptions;

/**
 * Exception that should be thrown if a piece is not owned by the current player.
 */
public class PieceNotOwnedException extends ApplicationMoveException {
    /**
     * The exit code the application should exit with.
     */
    private static final int ERR_CODE = 103;

    public PieceNotOwnedException(final String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public PieceNotOwnedException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getErrorCode() {
        return ERR_CODE;
    }
}
