package de.techfak.gse.hspanka.exceptions;

/**
 * Exception that should be thrown if the board configuration is empty.
 */
public class EmptyCommandException extends ApplicationErrorException {
    /**
     * The exit code the application should exit with.
     */
    private static final int ERR_CODE = 0;

    public EmptyCommandException(final String message) {
        super(message);
    }

    public EmptyCommandException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getErrorCode() {
        return ERR_CODE;
    }
}
