package de.techfak.gse.hspanka.exceptions;

/**
 * Exception that should be thrown if the move is invalid.
 */
public class InvalidMoveException extends ApplicationMoveException {
    /**
     * The exit code the application should exit with.
     */
    private static final int ERR_CODE = 101;

    public InvalidMoveException(String message) {
        super(message);
    }

    @Override
    public int getErrorCode() {
        return ERR_CODE;
    }
}
