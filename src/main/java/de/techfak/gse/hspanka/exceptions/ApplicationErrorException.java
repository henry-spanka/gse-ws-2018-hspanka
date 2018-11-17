package de.techfak.gse.hspanka.exceptions;

/**
 * Base Exception handled by the application to exit with an error code.
 */
public abstract class ApplicationErrorException extends Exception {
    /**
     * The exit code the application should exit with.
     */
    private static final int ERR_CODE = 1;

    public ApplicationErrorException() {
        super();
    }

    public ApplicationErrorException(String message) {
        super(message);
    }

    /**
     * @return An error code that the application exits with
     */
    public int getErrorCode() {
        return ERR_CODE;
    };
}
