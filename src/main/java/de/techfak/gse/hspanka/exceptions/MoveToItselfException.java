package de.techfak.gse.hspanka.exceptions;

/**
 * Exception that should be thrown if the move is invalid.
 */
public class MoveToItselfException extends ApplicationMoveException {
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final long serialVersionUID = -5026509754206946696L;

    /**
     * The exit code the application should exit with.
     */
    private static final int ERR_CODE = 104;

    public MoveToItselfException(final String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public MoveToItselfException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getErrorCode() {
        return ERR_CODE;
    }
}
