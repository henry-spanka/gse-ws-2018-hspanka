package de.techfak.gse.hspanka.Exceptions;

/**
 * Base Exception handled by the application to exit with an error code
 */
public abstract class ApplicationMoveException extends ApplicationErrorException {
    public ApplicationMoveException() {
        super();
    }

    public ApplicationMoveException(String message) {
        super(message);
    }

    /**
     * @return An error code that the application exits with
     */
    public abstract int getErrorCode();
}
