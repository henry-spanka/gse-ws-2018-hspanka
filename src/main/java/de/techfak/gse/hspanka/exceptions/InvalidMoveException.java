package de.techfak.gse.hspanka.exceptions;

/**
 * Exception that should be thrown if the move is invalid.
 */
public class InvalidMoveException extends ApplicationMoveException {
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final long serialVersionUID = -665221287725573889L;

    /**
     * The exit code the application should exit with.
     */
    private static final int ERR_CODE = 101;

    public InvalidMoveException(final String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public InvalidMoveException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getErrorCode() {
        return ERR_CODE;
    }
}
