package de.techfak.gse.hspanka.exceptions;

/**
 * Exception that should be thrown if the move is invalid.
 */
public class MoveToItselfException extends ApplicationMoveException {
    /**
     * The exit code the application should exit with.
     */
    private static final int ERR_CODE = 104;

    public MoveToItselfException(final String message) {
        super(message);
    }

    public MoveToItselfException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getErrorCode() {
        return ERR_CODE;
    }
}
