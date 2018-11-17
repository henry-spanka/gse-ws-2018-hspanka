package de.techfak.gse.hspanka.exceptions;

/**
 * Base Move Exception handled by the application to exit with an error code.
 * Before the application exits it will print the current board state.
 */
public abstract class ApplicationMoveException extends ApplicationErrorException {
    /**
     * The exit code the application should exit with.
     */
    private static final int ERR_CODE = 1;

    public ApplicationMoveException() {
        super();
    }

    public ApplicationMoveException(final String message) {
        super(message);
    }

    /**
     * @return An error code that the application exits with
     */
    public int getErrorCode() {
        return ERR_CODE;
    };
}
