package de.techfak.gse.hspanka.exceptions;

/**
 * Exception that should be thrown if the game has already ended.
 */
public class GameEndedException extends ApplicationMoveException {
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final long serialVersionUID = -5982416068585671493L;

    /**
     * The exit code the application should exit with.
     */
    private static final int ERR_CODE = 0;

    public GameEndedException(final String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public GameEndedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getErrorCode() {
        return ERR_CODE;
    }
}
