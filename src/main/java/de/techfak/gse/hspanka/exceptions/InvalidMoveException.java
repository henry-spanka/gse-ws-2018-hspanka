package de.techfak.gse.hspanka.exceptions;

/**
 * Exception that should be thrown if the move is invalid.
 */
public class InvalidMoveException extends ApplicationMoveException {
    /**
     * The exit code the application should exit with.
     */
    private static final int ERR_CODE = 101;

    public InvalidMoveException(final String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public InvalidMoveException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getErrorCode() {
        return ERR_CODE;
    }
}
