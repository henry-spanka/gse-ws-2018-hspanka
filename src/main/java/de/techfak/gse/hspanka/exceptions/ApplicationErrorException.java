package de.techfak.gse.hspanka.exceptions;

/**
 * Base Exception handled by the application to exit with an error code
 */
public abstract class ApplicationErrorException extends Exception {
    public ApplicationErrorException() {
        super();
    }

    public ApplicationErrorException(String message) {
        super(message);
    }

    /**
     * @return An error code that the application exits with
     */
    public abstract int getErrorCode();
}
