package de.techfak.gse.hspanka.exceptions;

/**
 * Base Move Exception handled by the application to exit with an error code.
 * Before the application exits it will print the current board state.
 */
public abstract class ApplicationMoveException extends ApplicationErrorException {

    /**
     * Construct the ApplicationMoveException.
     */
    ApplicationMoveException() {
        super();
    }

    /**
     * Construct the ApplicationMoveException.
     *
     * @param message The message.
     */
    ApplicationMoveException(final String message) {
        super(message);
    }

    /**
     * Construct the ApplicationMoveException.
     *
     * @param message The message.
     * @param cause   The cause.
     */
    ApplicationMoveException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @return An error code that the application exits with
     */
    @SuppressWarnings("unused")
    public abstract int getErrorCode();
}
