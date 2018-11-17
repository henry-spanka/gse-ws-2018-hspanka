package de.techfak.gse.hspanka.exceptions;

/**
 * Base Exception handled by the application to exit with an error code
 */
public abstract class ApplicationMoveException extends ApplicationErrorException {
    private static final int ERR_CODE = 1;

    public ApplicationMoveException() {
        super();
    }

    public ApplicationMoveException(String message) {
        super(message);
    }

    /**
     * @return An error code that the application exits with
     */
    public int getErrorCode() {
        return ERR_CODE;
    };
}
