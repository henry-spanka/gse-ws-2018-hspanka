package de.techfak.gse.hspanka.exceptions;

/**
 * Base Exception handled by the application to exit with an error code.
 */
public abstract class ApplicationErrorException extends Exception {

    ApplicationErrorException() {
        super();
    }

    ApplicationErrorException(final String message) {
        super(message);
    }

    ApplicationErrorException(final String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @return An error code that the application exits with
     */
    public abstract int getErrorCode();
}
