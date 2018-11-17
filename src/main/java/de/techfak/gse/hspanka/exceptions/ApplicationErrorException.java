package de.techfak.gse.hspanka.exceptions;

/**
 * Base Exception handled by the application to exit with an error code.
 */
public abstract class ApplicationErrorException extends Exception {
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final long serialVersionUID = 2505365132097187187L;

    /**
     * Construct the ApplicationErrorException.
     */
    ApplicationErrorException() {
        super();
    }

    /**
     * Construct the ApplicationErrorException.
     *
     * @param message The error message.
     */
    ApplicationErrorException(final String message) {
        super(message);
    }

    /**
     * Construct the ApplicationErrorException.
     *
     * @param message The error message.
     * @param cause   The cause.
     */
    ApplicationErrorException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @return An error code that the application exits with
     */
    public abstract int getErrorCode();
}
